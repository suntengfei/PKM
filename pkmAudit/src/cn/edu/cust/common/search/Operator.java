package cn.edu.cust.common.search;


public class Operator {
	public static final Operator EQ = new Operator();
	public static final Operator NE = new Operator(){

		@Override
		public void appendSQL(StringBuffer sql) {
			sql.append("<>?");
		}
		
	};
	
	public static final Operator GT = new Operator(){
		@Override
		public void appendSQL(StringBuffer sql) {
			sql.append(">?");
		}
	};
	
	public static final Operator GE = new Operator(){
		@Override
		public void appendSQL(StringBuffer sql) {
			sql.append(">=?");
		}
	};
	
	public static final Operator LT = new Operator(){
		@Override
		public void appendSQL(StringBuffer sql) {
			sql.append("<?");
		}
	};
	
	public static final Operator LE = new Operator(){
		@Override
		public void appendSQL(StringBuffer sql) {
			sql.append("<=?");
		}
	};
	
	public static final Operator LIKE = new Operator(){
		@Override
		public void appendSQL(StringBuffer sql) {
			sql.append(" like ? ");
		}
	};
	
	protected int numOfParams = 1;
	
	public int getNumOfParams(){
		return numOfParams;
	}
	
	public void appendSQL(StringBuffer sql){
		sql.append("=?");
	}
	
}
