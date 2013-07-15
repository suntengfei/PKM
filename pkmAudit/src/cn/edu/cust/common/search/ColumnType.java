package cn.edu.cust.common.search;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ColumnType {
	public static final ColumnType STRING = new ColumnType();
	
	public static final ColumnType SHORT = new ColumnType(){
		@Override
		public Object getValue(String value) {
			return Short.valueOf(value);
		}
	};
	
	
	public static final ColumnType INT = new ColumnType(){

		@Override
		public Object getValue(String value) {
			return Integer.valueOf(value);
		}
		
	};
	
	
	public static final ColumnType LONG = new ColumnType(){
		@Override
		public Object getValue(String value) {
			return Long.valueOf(value);
		}
	};
	
	public static final ColumnType FLOAT = new ColumnType(){
		@Override
		public Object getValue(String value) {
			return Float.valueOf(value);
		}
	};
	
	public static final ColumnType DOUBLE = new ColumnType(){
		@Override
		public Object getValue(String value) {
			return Double.valueOf(value);
		}
	};
	
	public static final ColumnType DATE = new ColumnType(){
		@Override
		public Object getValue(String value) {
		
			try {
				return new SimpleDateFormat("yyyy-MM-dd").parse(value);
			} catch (ParseException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	};

	public Object getValue(String value){
		return value;
	}
}
