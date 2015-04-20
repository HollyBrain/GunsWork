package net.guns_project.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import net.guns_project.manager.IDescriptionManager;

import net.guns_project.domain.Description;
import net.guns_project.manager.RemoteDescriptionManagerProxy;

public class NewDescription extends JDialog{
	private Description desc;
	private static final long serialVersionUID = -7265530307974489903L;
	
	private JTextField weaponName;
	private JTextField name;
	private JTextField value;
	private JTextField other;
	private JLabel JLabel_1 = new JLabel();
	private JLabel JLabel_2 = new JLabel();
	private JLabel JLabel_3 = new JLabel();
	private JLabel JLabel_4 = new JLabel();
	
	private IDescriptionManager descManager;
	
	public NewDescription()
	{
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);
		setTitle("Опис");
		setSize(400, 450);
		setModal(true);
		setResizable(false);
		descManager = new RemoteDescriptionManagerProxy();
		
		final JButton cmdSave = new JButton("Зберегти");
		final JButton cmdCancel = new JButton("Відмінити");
		
		weaponName= new JTextField(15);
		name=new JTextField(15);
		value=new JTextField(15);
		other=new JTextField(15);
		
		NewUpdateFonClass NewGroupMainPanel = new NewUpdateFonClass();
		final JPanel fieldsPanel = new JPanel(new GridLayout(10, 2, 10, 7));
		final JPanel fieldsPanelBorder = new JPanel(new FlowLayout(
				FlowLayout.CENTER, 10, 10));
		fieldsPanel.setOpaque(false);
		fieldsPanelBorder.setOpaque(false);
		fieldsPanelBorder.add(fieldsPanel);
		
		JLabel_1.setText("Назва зброї");
		JLabel_2.setText("Характеристика опису");
		JLabel_3.setText("Значення");
		JLabel_4.setText("Інформація");
		
		fieldsPanel.add(JLabel_1);
		fieldsPanel.add(weaponName);
		fieldsPanel.add(JLabel_2);
		fieldsPanel.add(name);
		fieldsPanel.add(JLabel_3);
		fieldsPanel.add(value);
		fieldsPanel.add(JLabel_4);
		fieldsPanel.add(other);
		
		final JPanel commandsPanel = new JPanel(new FlowLayout());
		final JPanel commandsPanelBorder = new JPanel(new FlowLayout(
				FlowLayout.CENTER, 0, 0));
		commandsPanelBorder.add(commandsPanel);
		commandsPanel.setOpaque(false);
		commandsPanel.add(cmdSave);
		commandsPanel.add(cmdCancel);
		commandsPanelBorder.setOpaque(false);
		NewGroupMainPanel.add(fieldsPanelBorder, BorderLayout.NORTH);
		NewGroupMainPanel.add(commandsPanelBorder, BorderLayout.SOUTH);
		Container c = getContentPane();
		c.add(NewGroupMainPanel);
		cmdSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveDesc();
			}
		});

		cmdCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelSave();
			}
		});
	}
	
	public Description getDescription()
	{
		return desc;
	}
	public void setDescription(Description desc)
	{
		this.desc=desc;
		weaponName.setText(Integer.toString(desc.getWeaponName()));
		name.setText(desc.getDescriptionName());
		value.setText(desc.getValueDescription());
		other.setText(desc.getOtherDescription());	
	}
	
	private void saveDesc() {
		// TODO Auto-generated method stub
		try
		{
			desc=new Description();
			desc.setWeaponName(Integer.parseInt(weaponName.getText()));
			desc.setDescriptionName(name.getText());
			desc.setValueDescription(value.getText());
			desc.setOtherDescription(other.getText());
			if (desc.getId() == null) {
				int newId = descManager.createDescription(desc);
				desc.setId(newId);
			} else {
				descManager.updateDescription(desc);
			}
			this.setVisible(false);
			}catch(Exception e){
				e.printStackTrace();
				JOptionPane.showMessageDialog(this, "Помилка при збереженні опису: "+e.getMessage());
			}
		}
	
	private void cancelSave()
	{
		this.setVisible(false);
	}
}
