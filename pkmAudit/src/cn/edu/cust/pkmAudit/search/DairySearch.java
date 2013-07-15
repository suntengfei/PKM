package cn.edu.cust.pkmAudit.search;

import java.util.HashMap;
import java.util.Map;

import cn.edu.cust.common.search.ColumnType;
import cn.edu.cust.common.search.Search;

public class DairySearch extends Search {
	
	private static Map<String, ColumnType> columnsSet;
	
	static {
		columnsSet = new HashMap<String, ColumnType>();
		/**
		 * 根据每个列的类型添加 hashmap的key是列名，value 是列类型，列类型用ColumnType的常量
		 */
		columnsSet.put("-", ColumnType.STRING);
		columnsSet.put("name", ColumnType.STRING);
		columnsSet.put("check", ColumnType.INT);
	}

	@Override
	protected Map<String, ColumnType> getColumnsSet() {
		return columnsSet;
	}

	@Override
	protected String getType() {
		return "Dairy";
	}

	@Override
	protected String getProjections() {
		return null;
	}
	
}
