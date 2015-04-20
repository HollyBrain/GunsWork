package net.guns_project.gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import net.guns_project.domain.Weapon;

public class WeaponsTableModel extends AbstractTableModel{
private static final long serialVersionUID = -2677658636846257452L;
	
	private String[] columns = new String[] { "ID","Назва зброї", "Назва виробника",
			"Дата виготовлення", "Інформація"};
	
	private List<Weapon> weapons;
	
	public WeaponsTableModel(List<Weapon> weapons) {
		this.weapons = weapons;
	}

	public void addWeapon(Weapon weapon)
	{
		weapons.add(weapon);
		fireTableRowsInserted(0,weapons.size());
	}
	public Weapon getWeaponRow(int rowIndex)
	{
		return weapons.get(rowIndex);
	}
	public void removeRow(int rowIndex) {
		weapons.remove(rowIndex);
		fireTableRowsDeleted(rowIndex, rowIndex);
	}

	public void refreshUpdatedTable() {
		fireTableRowsUpdated(0, weapons.size());
	}
	public Object getValueAt(int rowIndex, int columnIndex) {
		Weapon w = weapons.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return Integer.toString(w.getId());
		case 1:
			return w.getWeaponName();
		case 2:
			return Integer.toString(w.getMakerId());
		case 3:
			return w.getDate();
		case 4:
			return w.getOther();

		}
		return "";
	}
	public int getRowCount() {
		return weapons.size();
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
