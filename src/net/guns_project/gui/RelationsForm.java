package net.guns_project.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PrinterException;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.Border;
import net.guns_project.domain.Relations;
import net.guns_project.manager.IRelationManager;
import net.guns_project.manager.RemoteRelationManagerProxy;

public class RelationsForm extends JFrame{
	private static final long serialVersionUID = -1286577455252661221L;
	private RelationsTableModel rlsTableModel;
	private IRelationManager rlManager = new RemoteRelationManagerProxy();
	
	private JTable rlsTable;
	
	public RelationsForm()
	{
		super();
		rlsTableModel=getTableModel();
		rlsTable=new JTable(rlsTableModel);
		rlsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		rlsTable.setPreferredScrollableViewportSize(new Dimension(880,180));
		rlsTable.getColumnModel().getColumn(0).setMinWidth(40);
		rlsTable.getColumnModel().getColumn(1).setMinWidth(100);
		rlsTable.getColumnModel().getColumn(2).setMinWidth(20);
		rlsTable.getColumnModel().getColumn(3).setMinWidth(120);
		rlsTable.setGridColor(Color.WHITE);
		rlsTable.setRowHeight(20);
		Font FontGrid = new Font(Font.MONOSPACED, Font.PLAIN, 14);
		rlsTable.setFont(FontGrid);
		
		JScrollPane scrollPane = new JScrollPane(rlsTable);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);

		MainFonClass mainPanel = new MainFonClass();
		mainPanel.add(scrollPane);
		
		setSize(600, 380);
		setResizable(false);
		getContentPane().add(mainPanel, BorderLayout.CENTER);

}

	private RelationsTableModel getTableModel() {
		try
		{
			final List<Relations> rl = rlManager.getAllRelations();
			return new RelationsTableModel(rl);
		}catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this,
					"Помилка при заповненні таблиці звязку: " + e.getMessage());
		}
		return new RelationsTableModel(new ArrayList<Relations>(0));
	}

}
