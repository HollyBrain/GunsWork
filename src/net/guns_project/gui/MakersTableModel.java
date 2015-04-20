package net.guns_project.gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import net.guns_project.domain.Maker;

public class MakersTableModel extends AbstractTableModel{
	private static final long serialVersionUID = -2677658636846257452L;
	
	private String[] columns = new String[] {"ID","Назва Компанії", "Рік заснування", "Імя директора",
			"Реєстраційний номер", "Адреса", "Телефно", "Факс","E-mail","Web-site" };
	
	private List<Maker> makers;

	public MakersTableModel(List<Maker> makers) {
		this.makers = makers;
	}

	public void addMaker(Maker maker) {
		makers.add(maker);
		fireTableRowsInserted(0, makers.size());
	}

	public Maker getRowGroup(int rowIndex) {
		return makers.get(rowIndex);
	}

	public void removeRow(int rowIndex) {
		makers.remove(rowIndex);
		fireTableRowsDeleted(rowIndex, rowIndex);
	}

	public void refreshUpdatedTable() {
		fireTableRowsUpdated(0, makers.size());
	}
	public Object getValueAt(int rowIndex, int columnIndex) {
		Maker m = makers.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return Integer.toString(m.getId());
		case 1:
			return m.getCompanyName();
		case 2:
			return Integer.toString(m.getYearOfFoundation());
		case 3:
			return m.getDirectorName();
		case 4:
			return m.getRegistrationNumber();
		case 5:
			return m.getAdress();
		case 6:
			return m.getPhoneNumber();
		case 7:
			return m.getFaxNumber();
		case 8:
			return m.getMail();
		case 9:
			return m.getSite();

		}
		return "";
	}
	public int getRowCount() {
			return (Integer) makers.size();
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
