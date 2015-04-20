package net.guns_project.gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import net.guns_project.domain.Description;

public class DescriptionsTableModel extends AbstractTableModel{
private static final long serialVersionUID = -2677658636846257452L;
	
	private String[] columns = new String[] { "Назва зброї", "Назва характеристики",
			"Значення характеристики", "Інформація"};
	
	private List<Description> descs;
	
	public DescriptionsTableModel(List<Description> descs) {
		this.descs = descs;
	}

	public void addDescription(Description desc)
	{
		descs.add(desc);
		fireTableRowsInserted(0,descs.size());
	}
	public Description getDescriptionRow(int rowIndex)
	{
		return descs.get(rowIndex);
	}
	public void removeRow(int rowIndex) {
		descs.remove(rowIndex);
		fireTableRowsDeleted(rowIndex, rowIndex);
	}

	public void refreshUpdatedTable() {
		fireTableRowsUpdated(0, descs.size());
	}
	public Object getValueAt(int rowIndex, int columnIndex) {
		Description d = descs.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return Integer.toString(d.getWeaponName());
		case 1:
			return d.getDescriptionName();
		case 2:
			return d.getValueDescription();
		case 3:
			return d.getOtherDescription();

		}
		return "";
	}
	public int getRowCount() {
		return descs.size();
	}

	public String getColumnName(int columnIndex) {
		return columns[columnIndex];
	}

	public int getColumnCount() {
		return columns.length;
	}

	public Class<?> getColumnClass(int columnIndex) {
		return String.class;
	}

}
