package net.guns_project.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import net.guns_project.manager.IWeaponManager;
import net.guns_project.domain.Weapon;
import net.guns_project.manager.RemoteWeaponManagerProxy;

public class NewWeapon extends JDialog{
	private static final long serialVersionUID = -7265530307974489903L;
	
	private Weapon weapon;
	
	private JTextField weaponName;
	private JTextField date;
	private JTextField other;
	private JLabel JLabel_1 = new JLabel();
	private JLabel JLabel_2 = new JLabel();
	private JLabel JLabel_3 = new JLabel();
	private JLabel JLabel_4 = new JLabel();
	
	private IWeaponManager weaponManager;
	
	public NewWeapon()
	{
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);
		setTitle("Зброя");
		setSize(400, 450);
		setModal(true);
		setResizable(false);
		weaponManager = new RemoteWeaponManagerProxy();
		
		final JButton cmdSave = new JButton("Зберегти");
		final JButton cmdCancel = new JButton("Відмінити");
		
		weaponName= new JTextField(15);
		date=new JTextField(15);
		other=new JTextField(15);
		
		NewUpdateFonClass NewGroupMainPanel = new NewUpdateFonClass();
		final JPanel fieldsPanel = new JPanel(new GridLayout(10, 2, 10, 7));
		final JPanel fieldsPanelBorder = new JPanel(new FlowLayout(
				FlowLayout.CENTER, 10, 10));
		fieldsPanel.setOpaque(false);
		fieldsPanelBorder.setOpaque(false);
		fieldsPanelBorder.add(fieldsPanel);
		
		JLabel_1.setText("Назва зброї");
		JLabel_2.setText("Виробник");
		JLabel_3.setText("Дата випуску");
		JLabel_4.setText("Інформація");
		
		fieldsPanel.add(JLabel_1);
		fieldsPanel.add(weaponName);
		fieldsPanel.add(JLabel_3);
		fieldsPanel.add(date);
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
				saveWeapon();
			}
		});

		cmdCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelSave();
			}
		});
	}
	
	public Weapon getWeapon()
	{
		return weapon;
	}
	public void setWeapon(Weapon weapon)
	{
		this.weapon=weapon;
		weaponName.setText(weapon.getWeaponName());
		date.setText(weapon.getDate());
		other.setText(weapon.getOther());	
	}
	
	private void saveWeapon() {
		weapon=new Weapon();
		try
		{	
			weapon.setWeaponName(weaponName.getText());
			weapon.setDate(date.getText());
			weapon.setOther(other.getText());
			if (weapon.getId() == null) {
				int newId = weaponManager.createWeapon(weapon);
				weapon.setId(newId);
			} else {
				weaponManager.updateWeapon(weapon);
			}
			this.setVisible(false);
			}catch(Exception e){
				e.printStackTrace();
				JOptionPane.showMessageDialog(this, "Помилка при збереженні зброї: "+e.getMessage());
			}
		}
	
	private void cancelSave()
	{
		this.setVisible(false);
	}
}
