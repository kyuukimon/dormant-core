package cn.com.dormant.service.core.common;

import java.util.HashMap;
import java.util.Map;

/**
 * <code>Pager<code>
 *
 * @description: This class represent one page object which refer to some page related attributes
 * such as total rows,page size, total pages and some page related relations such as go to next page , previous page,
 * first page and last page
 * @author: Wang Jianguang(aguang_1@126.com)
 * @creation: 2014/08/19
 * @version: 1.0
 */
public class Pager {
	private int totalRows;
	
	private int pageSize = 10; 
	
	private int totalPages = 1;
	
	private int curPage = 1;
	
	private int pageStartRow = 0;
	
	private boolean hasNextPage = false; 
	
	private boolean hasPreviousPage = false; 
	
	private String orderBy = "";

	private String order = "asc";

    private Object params = new Object();

    public Object getParams() {
        return params;
    }

    public void setParams(Object params) {
        this.params = params;
    }

    public Pager()
	{
		
	}

	public Pager(int totalRows)
	{
		this.totalRows = totalRows;
		this.totalPages = (totalRows+pageSize-1)/pageSize;
		this.curPage = 1;
		this.pageStartRow = 0;
		
		if(this.curPage == this.totalPages)
			this.hasNextPage = false;
		else
			this.hasNextPage = true;
		
		if(this.curPage == 1)
			this.hasPreviousPage = false;
		else
			this.hasPreviousPage = true;
	}
	
	public Pager(int totalRows,int pageSize)
	{
		this.totalRows = totalRows;
		this.pageSize=pageSize;
		this.totalPages = (totalRows+pageSize-1)/pageSize;
		this.curPage = 1;
		this.pageStartRow = 0;
		
		if(this.curPage == this.totalPages)
			this.hasNextPage = false;
		else
			this.hasNextPage = true;
		
		if(this.curPage == 1)
			this.hasPreviousPage = false;
		else
			this.hasPreviousPage = true;
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;

		try{
			pageStartRow = pageSize*( curPage - 1 );
		}catch(Exception ex ){
			pageStartRow = 0;
		}
	}

	public boolean isHasNextPage() {
		return hasNextPage;
	}

	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}

	public boolean isHasPreviousPage() {
		return hasPreviousPage;
	}

	public void setHasPreviousPage(boolean hasPreviousPage) {
		this.hasPreviousPage = hasPreviousPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageStartRow() {
		return pageStartRow;
	}

	public void setPageStartRow(int pageStartRow) {
//		this.pageStartRow = this.pageSize*(this.curPage - 1); 
		this.pageStartRow = pageStartRow;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}
	
	public void resetPreviousAndNext()
	{
		if(this.curPage >= this.totalPages)
			this.hasNextPage = false;
		else
			this.hasNextPage = true;
		
		if(this.curPage <= 1)
			this.hasPreviousPage = false;
		else
			this.hasPreviousPage = true;
	}
	
	public void previous()
	{
		if(this.curPage == 1)
			return;
		
		this.curPage--;
		this.pageStartRow = this.pageSize*this.curPage - this.pageSize; 
		
		resetPreviousAndNext();
	}
	
	public void next()
	{
		if(this.curPage == this.totalPages)
			return;
		
		this.curPage++;
		this.pageStartRow = this.pageSize*this.curPage - this.pageSize;
		
		resetPreviousAndNext();
	}
	
	public void first()
	{
		this.curPage = 1;
		this.pageStartRow = 0;
	}
	
	public void last()
	{
		this.curPage = this.totalPages;
		this.pageStartRow = this.pageSize*this.curPage - this.pageSize;
	}
	
	public void refresh(int _curPage)
	{
		if(this.totalPages<_curPage)
			last();	
	}

	/**
	 * @return the orderBy
	 */
	public String getOrderBy() {
		return orderBy;
	}

	/**
	 * @param orderBy the orderBy to set
	 */
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	/**
	 * @return the order
	 */
	public String getOrder() {
		return order;
	}

	/**
	 * @param order the order to set
	 */
	public void setOrder(String order) {
		this.order = order;
	}
	
	
}
