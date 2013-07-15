package cn.edu.cust.common.util;

import java.util.ArrayList;

public class Page {
	
	/**
	 * 页码，必须初始化
	 */
	private int pageNum = 1;
	
	/**
	 * 每页记录数
	 */
	private int pageSize = 5;
	
	/**
	 * 总记录数，每次查询必须手动设置
	 */
	private int rowCount;
	
	/**
	 * 最后一页，每次查询必须手动设置
	 */
	private int lastPage;
	
	/**
	 * range最好使用基数
	 */
	private int range = 5;
	
	public int getPageNum() {
		return pageNum;
	}
	
	public void setPageNum(int pageNum) {
		if(pageNum > 0){
			this.pageNum = pageNum;
		}
	}
	
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		if(pageSize > 0){
			this.pageSize = pageSize;
		}
	}
	
	public int getRowCount() {
		return rowCount;
	}
	
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
		setLastPage();
	}
	
	public int getLastPage() {
		return lastPage;
	}

	private void setLastPage(){
		if(rowCount == 0){
			lastPage = 1;
			return;
		}
		lastPage = rowCount % pageSize == 0 ? rowCount / pageSize : rowCount / pageSize + 1;
	}
	
	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public int getOffset(){
		return (pageNum - 1) * pageSize;
	}
	
	public static int getPageStart(int pageNumber, int pageSize) {
		return (pageNumber - 1) * pageSize;
	}
	
	public int getPrePage(){
		return pageNum <= 1 ? 1 : pageNum - 1;
	}
	
	public int getNextPage(){
		return pageNum >= lastPage ? lastPage : pageNum + 1;
	}
	
	public int getFirstPage(){
		return 1;
	}
	
	public ArrayList<?> getPages(){
		int end = pageNum + range / 2;
		if(end > lastPage){
			end = lastPage;
		}
		int start = end - range + 1;
		if(start < 1){
			start = 1;
			end = start + range - 1;
			if(end > lastPage){
				end = lastPage;
			}
		}
		ArrayList<Integer> pages = new ArrayList<Integer>();
		for (int i = start; i <= end; i++) {
			pages.add(i);
		}
		return pages;
	}
}
