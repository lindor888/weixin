package com.ctvit.util;

import java.util.List;

/**
 * @ClassName: PageDomain
 * @author biying
 * @date 2013-6-4 下午12:01:17
 * @Description: 封装分页参数
 * @version 1.0
 * @param <T>
 */
public class PageDomain<T> {

	private int totalPageNum=1; // 总页数
	private int currentPageNum=1; // 当前页码
	private int perPageNum; // 每页的条数
	private int previewPageNum=1; // 上一页
	private int nextPageNum=1; // 下一页
	private int lookPageNum; // 查看的页数
	private List<T> resultList; // 分页结果集合
	private  Object findObj;  //查询参数于



	/**
	 * @return resultList
	 */
	public List<T> getResultList() {
		return resultList;
	}

	/**
	 * @param <T>
	 * @param list
	 *            the resultList to set
	 */
	@SuppressWarnings("unchecked")
	public void setResultList(List<Object> list) {
		this.resultList = (List<T>) list;
	}

	/** 
	* @return findObj 
	*/
	public Object getFindObj() {
		return findObj;
	}

	/**
	 * @param findObj the findObj to set
	 */
	public void setFindObj(Object findObj) {
		this.findObj = findObj;
	}

	/** 
	* @return totalPageNum 
	*/
	public int getTotalPageNum() {
		return totalPageNum;
	}

	/**
	 * @param totalPageNum the totalPageNum to set
	 */
	public void setTotalPageNum(int totalPageNum) {
		this.totalPageNum = totalPageNum;
	}

	/** 
	* @return currentPageNum 
	*/
	public int getCurrentPageNum() {
		return currentPageNum;
	}

	/**
	 * @param currentPageNum the currentPageNum to set
	 */
	public void setCurrentPageNum(int currentPageNum) {
		this.currentPageNum = currentPageNum;
		
		if(currentPageNum+1>this.totalPageNum)
		{
			this.setNextPageNum(this.totalPageNum);
		}else
		{
			this.setNextPageNum(currentPageNum+1);
		}
		if(currentPageNum>1)
		{
			this.setPreviewPageNum(currentPageNum-1);
		}else
		{
			this.setPreviewPageNum(1);
		}
	}

	/** 
	* @return perPageNum 
	*/
	public int getPerPageNum() {
		return perPageNum;
	}

	/**
	 * @param perPageNum the perPageNum to set
	 */
	public void setPerPageNum(int perPageNum) {
		this.perPageNum = perPageNum;
	}

	/** 
	* @return previewPageNum 
	*/
	public int getPreviewPageNum() {
		return previewPageNum;
	}

	/**
	 * @param previewPageNum the previewPageNum to set
	 */
	public void setPreviewPageNum(int previewPageNum) {
		this.previewPageNum = previewPageNum;
	}

	/** 
	* @return nextPageNum 
	*/
	public int getNextPageNum() {
		return nextPageNum;
	}

	/**
	 * @param nextPageNum the nextPageNum to set
	 */
	public void setNextPageNum(int nextPageNum) {
		this.nextPageNum = nextPageNum;
	}

	/** 
	* @return lookPageNum 
	*/
	public int getLookPageNum() {
		return lookPageNum;
	}

	/**
	 * @param lookPageNum the lookPageNum to set
	 */
	public void setLookPageNum(int lookPageNum) {
		this.lookPageNum = lookPageNum;
	}

}
