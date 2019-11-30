package com.sportbettings.model;

import java.util.List;

public class TableData {

	private List<RowData> tableData;

	private int rowToDelete;

	public List<RowData> getTableData() {
		return tableData;
	}

	public void setTableData(List<RowData> tableData) {
		this.tableData = tableData;
	}

	public int getRowToDelete() {
		return rowToDelete;
	}

	public void setRowToDelete(int rowToDelete) {
		this.rowToDelete = rowToDelete;
	}

}
