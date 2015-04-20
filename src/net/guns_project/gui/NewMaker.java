package net.guns_project.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import net.guns_project.manager.IMakerManager;

import net.guns_project.domain.Maker;
import net.guns_project.manager.RemoteMakerManagerProxy;

public class NewMaker extends JDialog{
	private static final long serialVersionUID = -7265530307974489903L;
	
	private Maker maker;
	
	private JTextField companyName;
	private JTextField year;
	private JTextField director;
	private JTextField registr;
	private JTextField adress;
	private JTextField phone;
	private JTextField fax;
	private JTextField mail;
	private JTextField site;
	private JLabel JLabel_1 = new JLabel();
	private JLabel JLabel_2 = new JLabel();
	private JLabel JLabel_3 = new JLabel();
	private JLabel JLabel_4 = new JLabel();
	private JLabel JLabel_5 = new JLabel();
	private JLabel JLabel_6 = new JLabel();
	private JLabel JLabel_7 = new JLabel();
	private JLabel JLabel_8 = new JLabel();
	private JLabel JLabel_9 = new JLabel();
	
	private IMakerManager makeManager;
	
	public NewMaker()
	{
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);
		setTitle("Виробник");
		setSize(400, 450);
		setModal(true);
		setResizable(false);
		makeManager = new RemoteMakerManagerProxy();
		
		final JButton cmdSave = new JButton("Зберегти");
		final JButton cmdCancel = new JButton("Відмінити");
		
		companyName = new JTextField(15);
		year=new JTextField(15);
		director=new JTextField(15);
		registr=new JTextField(15);
		adress=new JTextField(15);
		phone=new JTextField(15);
		fax=new JTextField(15);
		mail=new JTextField(15);
		site=new JTextField(15);
		
		NewUpdateFonClass NewGroupMainPanel = new NewUpdateFonClass();
		final JPanel fieldsPanel = new JPanel(new GridLayout(10, 2, 10, 7));
		final JPanel fieldsPanelBorder = new JPanel(new FlowLayout(
				FlowLayout.CENTER, 10, 10));
		fieldsPanel.setOpaque(false);
		fieldsPanelBorder.setOpaque(false);
		fieldsPanelBorder.add(fieldsPanel);
		
		JLabel_1.setText("Назва компанії");
		JLabel_2.setText("Рік заснування");
		JLabel_3.setText("Директор");
		JLabel_4.setText("Реєстраційний номер");
		JLabel_5.setText("Адреса");
		JLabel_6.setText("Телефон");
		JLabel_7.setText("Факс");
		JLabel_8.setText("E-mail");
		JLabel_9.setText("Web-site");
		
		fieldsPanel.add(JLabel_1);
		fieldsPanel.add(companyName);
		fieldsPanel.add(JLabel_2);
		fieldsPanel.add(year);
		fieldsPanel.add(JLabel_3);
		fieldsPanel.add(director);
		fieldsPanel.add(JLabel_4);
		fieldsPanel.add(registr);
		fieldsPanel.add(JLabel_5);
		fieldsPanel.add(adress);
		fieldsPanel.add(JLabel_6);
		fieldsPanel.add(phone);
		fieldsPanel.add(JLabel_7);
		fieldsPanel.add(fax);
		fieldsPanel.add(JLabel_8);
		fieldsPanel.add(mail);
		fieldsPanel.add(JLabel_9);
		fieldsPanel.add(site);
		
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
				saveMaker();
			}
		});

		cmdCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelSave();
			}
		});
	}

	public Maker getMaker()
	{
		return maker;
	}
	public void setMaker(Maker maker)
	{
		this.maker=maker;
		companyName.setText(maker.getCompanyName());
		year.setText(Integer.toString(maker.getYearOfFoundation()));
		director.setText(maker.getDirectorName());
		registr.setText(maker.getRegistrationNumber());
		adress.setText(maker.getAdress());
		phone.setText(maker.getPhoneNumber());
		fax.setText(maker.getFaxNumber());
		mail.setText(maker.getMail());
		site.setText(maker.getSite());
			
	}
	
	
	private void saveMaker() {
		// TODO Auto-generated method stub
		try
		{
		maker.setCompanyName(companyName.getText());
		maker.setYearOfFoundation(Integer.parseInt(year.getText()));
		maker.setDirectorName(director.getText());
		maker.setRegistrationNumber(registr.getText());
		maker.setAdress(adress.getText());
		maker.setPhoneNumber(phone.getText());
		maker.setFaxNumber(fax.getText());
		maker.setMail(mail.getText());
		maker.setSite(site.getText());
		if (maker.getId() == null) {
			int newId = makeManager.createMaker(maker);
			maker.setId(newId);
		} else {
			makeManager.updateMaker(maker);
		}
		this.setVisible(false);
		}catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Помилка при збереженні виробника: "+e.getMessage());
		}
		
		
	}

	private void cancelSave() {
		// TODO Auto-generated method stub
		this.setVisible(false);
		
	}

}
