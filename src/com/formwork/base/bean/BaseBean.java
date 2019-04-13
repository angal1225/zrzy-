package com.formwork.base.bean;

public class BaseBean {
	protected String page;
	protected String rows;
	protected String sort;
	protected String order;
	protected String ck_sign;
	protected String method;

	protected int startRow;

	public int getStartRow() {
		if (startRow == 0) {
			int page = 1;
			int size = 10;
			try {
				page = Integer.parseInt(this.page);
			} catch (NumberFormatException e) {
			}
			try {
				size = Integer.parseInt(this.rows);
			} catch (NumberFormatException e) {
			}

			startRow = page > 0 ? (page - 1) * size : 0;
		}

		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getCk_sign() {
		return ck_sign;
	}

	public void setCk_sign(String ck_sign) {
		this.ck_sign = ck_sign;
	}

	public  String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}
}
