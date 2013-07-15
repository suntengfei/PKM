package cn.edu.cust.common.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class Search {
	protected String[] logicalopts;//逻辑运算符
	protected String[] columns;//列名,如果某列不选择，则此列取值为"-"
	protected String[] operators;//算术运算符
	protected String[] values;//值
	protected String[] orders;//排序
	protected static Map<String, Operator> operatorsMap;
	protected static Set<String> ordersSet;
	protected static Set<String> logicaloptsSet;

	protected abstract Map<String, ColumnType> getColumnsSet();
	protected abstract String getType();
	protected abstract String getProjections();
	
	static {
		//算术运算符取值
		operatorsMap = new HashMap<String, Operator>();
		operatorsMap.put("eq", Operator.EQ);
		operatorsMap.put("ne", Operator.NE);
		operatorsMap.put("gt", Operator.GT);
		operatorsMap.put("ge", Operator.GE);
		operatorsMap.put("lt", Operator.LT);
		operatorsMap.put("le", Operator.LE);
		operatorsMap.put("like", Operator.LIKE);
		
		//排序取值
		ordersSet = new HashSet<String>();
		ordersSet.add("none");//不排序
		ordersSet.add("desc");
		ordersSet.add("asc");
		
		//逻辑运算符取值
		logicaloptsSet = new HashSet<String>();
		logicaloptsSet.add("and");
		logicaloptsSet.add("or");
	}
	
	private ColumnType[] cts;
	private Operator[] opts;
	
	public String buildSQL(){
		return buildSQL(null, null);
	}
	
	public String buildSQL(String whereClause, String orderClause){
		StringBuffer sql = new StringBuffer();
		StringBuffer orderBuf = new StringBuffer();
		sql.append(" from ");
		sql.append(getType());
		Map<String, ColumnType> cs = getColumnsSet();
		boolean addWhere = false;
		boolean addOrder = false;
		if(columns != null && columns.length > 0){
			
			int columnslen = columns.length;
			cts = new ColumnType[columnslen];
			opts = new Operator[columnslen];
			for (int i = 0; i < columnslen; i++) {
				String column = columns[i];
				if("-".equals(column)){// 如果不选择搜索列
					continue;
				}
				if(addWhere){
					sql.append(" ");
					String logicalopt = logicalopts[i];
					if(!logicaloptsSet.contains(logicalopt)){
						throw new RuntimeException("logicalopt is valid: " + logicalopt);
					}
					sql.append(logicalopt);
					sql.append(" ");
				}else{
					addWhere = true;
					sql.append(" where ( ");
				}
				ColumnType ct = cs.get(column);
				
				cts[i] = ct;
				if(ct == null){
					throw new RuntimeException("column is valid: " + column);
				}
				
				//处理like 模糊查询 如果是字符串 需要包裹上%
				if(ct == ColumnType.STRING) {
					if("like".equals(operators[i])) {
						values[i] = "%" + values[i] + "%";
					}
				}
				sql.append(column);
				
				String operator = operators[i];
				Operator opt = operatorsMap.get(operator);
				opts[i] = opt;
				if(opt == null){
					throw new RuntimeException("operator is valid: " + operator);
				}
				opt.appendSQL(sql);
				
			}
			
			for (int i = 0; i < columnslen; i++) {
				if(cts[i] == null){// 如果不选择搜索列
					continue;
				}
				String order = orders[i];
				if(!ordersSet.contains(order)){
					throw new RuntimeException("order is valid: " + order);
				}
				if("none".equals(order)){
					continue;
				}
				if(addOrder){
					orderBuf.append(", ");
				}else{
					addOrder = true;
					orderBuf.append(" order by ");
				}
				orderBuf.append(columns[i]);
				orderBuf.append(" ");
				orderBuf.append(order);
			}
		}
		
		if(addWhere) {
			sql.append(" ) ");
		}
		
		//添加原有的where子句
		if(whereClause != null) {
			if(addWhere) {
				sql.append(" and " + whereClause);
			}else {
				sql.append(" where " + whereClause);
			}
		}
		
		sql.append(orderBuf.toString());
		
		//添加原有的order子句
		if(orderClause != null) {
			if(addOrder) {
				sql.append(" , " + orderClause);
			}else {
				sql.append(" order by " + whereClause);
			}
		}
		
		return sql.toString();
	}
	
	
	public ArrayList<Object> getParams(){
		if(cts == null){
			return new ArrayList<Object>();
		}
		int ctslen = cts.length;
		int valueIndex = 0;
		ArrayList<Object> params = new ArrayList<Object>();
		for (int i = 0; i < ctslen; i++) {
			if(cts[i] != null){
				params.add(cts[i].getValue(values[valueIndex]));
				int numOfParams = opts[i].getNumOfParams();
				valueIndex += numOfParams;
			}else{
				valueIndex++;
			}
		}
		return params;
	}
	
	public String[] getLogicalopts() {
		return logicalopts;
	}
	public void setLogicalopts(String[] logicalopts) {
		this.logicalopts = logicalopts;
	}
	public String[] getColumns() {
		return columns;
	}
	public void setColumns(String[] columns) {
		this.columns = columns;
	}
	public String[] getOperators() {
		return operators;
	}
	public void setOperators(String[] operators) {
		this.operators = operators;
	}
	public String[] getValues() {
		return values;
	}
	public void setValues(String[] values) {
		this.values = values;
	}
	public String[] getOrders() {
		return orders;
	}
	public void setOrders(String[] orders) {
		this.orders = orders;
	}
}
