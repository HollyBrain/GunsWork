package net.guns_project.gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import net.guns_project.domain.Relations;

public class RelationsTableModel extends AbstractTableModel{
private static final long serialVersionUID = -2677658636846257452L;
	
	private String[] columns = new String[] { "Назва виробника","Назва зброї","Характеристика","Значення" };
	
	private List<Relations> rl;
	
	public RelationsTableModel(List<Relations> rl) {
		this.rl = rl;
	}

	public void addRelations(Relations rls) {
		rl.add(rls);
		fireTableRowsInserted(0, rl.size());
	}

	public Relations getRowRelations(int rowIndex) {
		return rl.get(rowIndex);
	}

	public void removeRow(int rowIndex) {
		rl.remove(rowIndex);
		fireTableRowsDeleted(rowIndex, rowIndex);
	}

	public void refreshUpdatedTable() {
		fireTableRowsUpdated(0, rl.size());
	}
	public Object getValueAt(int rowIndex, int columnIndex) {
		Relations m =rl.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return m.getCompanyName();
		case 1:
			return m.getWeaponName();
		case 2:
			return m.getDescName();
		case 3:
			return m.getValueDesc();

		}
		return "";
	}
	public int getRowCount() {
		return rl.size();
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
