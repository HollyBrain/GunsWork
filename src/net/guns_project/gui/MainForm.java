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

import net.guns_project.domain.Maker;
import net.guns_project.manager.IMakerManager;
import net.guns_project.manager.RemoteMakerManagerProxy;

public class MainForm extends JFrame implements ActionListener{
	private static final long serialVersionUID = -1286577455252661221L;
	private JButton cmdClose;
	private JButton cmdAddMaker;
	private JButton cmdUpdateMaker;
	private JButton cmdDeleteMaker;
	private JButton cmdOpenWeapon;
	private JButton cmdOpenDesc;
	private JButton cmdPrinMaker;
	private JLabel jLab;
	private JTable makersTable;
	
	private NewMaker newMaker = new NewMaker();
	JPopupMenu popupMenu = new JPopupMenu();

	JButton bnew, bupdate, bremove, bprint, bclose, bstudent, bteacher,
			cmdNew, cmdEdit, cmdDelete, cmdPrint, cmdWeapon,
			cmdDescription, cmdMaker, cmdKmClose;

	private MakersTableModel makersTableModel;
	private IMakerManager makerManager = new RemoteMakerManagerProxy();
	
	JMenuItem addMaker,updateMaker, removeMaker, printMaker, openWeapon, openDescription,
		      openMaker, onClose;
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addMaker) {
			addMaker();
		} else if (e.getSource() == updateMaker) {
			updateMaker();
		} else if (e.getSource() == removeMaker) {
			removeMaker();
		} else if (e.getSource() == printMaker) {
			printMaker();
		} else if (e.getSource() == openWeapon) {
			try {
				openWeapon();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else if (e.getSource() == openDescription) {
			try {
				openDescription();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}else if (e.getSource() == openMaker) {
			try {
				openMaker();
			} catch (Exception e1) {
				e1.printStackTrace();
			} 
		}else if (e.getSource() == onClose) {
			onClose();
		}
		
		
	}
	void createMenu()
	{
		Color colorMenu=(Color.WHITE);
		Font fontMenu=new Font(Font.MONOSPACED, Font.PLAIN,14);
		JMenuBar menuBar=new JMenuBar();
		
		JMenu mFile=new JMenu("Файл");
		JMenu mInform=new JMenu("Редагувати");
		JMenu mAbout=new JMenu("Про програму");
		
		mFile.setFont(fontMenu);
		mInform.setFont(fontMenu);
		mAbout.setFont(fontMenu);
		menuBar.setBackground(colorMenu);
		
		
		addMaker=new JMenuItem("Добавити виробника");
		addMaker.setToolTipText("Добавити новий записа в базу");
		addMaker.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		addMaker.setFont(fontMenu);
		addMaker.addActionListener(this);
		mFile.add(addMaker);
		
		updateMaker=new JMenuItem("Редагувати дані");
		updateMaker.setToolTipText("Внести зміни про виробника");
		updateMaker.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
		updateMaker.setFont(fontMenu);
		updateMaker.addActionListener(this);
		mFile.add(updateMaker);
		
		removeMaker=new JMenuItem("Видалити дані");
		removeMaker.setToolTipText("Видалити дані про виробника");
		removeMaker.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
		removeMaker.setFont(fontMenu);
		removeMaker.addActionListener(this);
		mFile.add(removeMaker);
		
		mFile.addSeparator();
		
		onClose=new JMenuItem("Вийти");
		onClose.setToolTipText("Здійснити акриття програми");
		onClose.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
		onClose.setFont(fontMenu);
		onClose.addActionListener(this);
		mFile.add(onClose);
		
		openMaker=new JMenuItem("Виробників");
		openMaker.setToolTipText("Відкрити інформацію про виробників");
		openMaker.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK));
		openMaker.setFont(fontMenu);
		openMaker.addActionListener(this);
		mInform.add(openMaker);
		
		openWeapon=new JMenuItem("Зброю");
		openWeapon.setToolTipText("Відкрити інформацію про зброю");
		openWeapon.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, ActionEvent.CTRL_MASK));
		openWeapon.setFont(fontMenu);
		openWeapon.addActionListener(this);
		mInform.add(openWeapon);
		
		openDescription=new JMenuItem("Опис зброї");
		openDescription.setToolTipText("Відкрити інформацію про опис зброї");
		openDescription.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		openDescription.setFont(fontMenu);
		openDescription.addActionListener(this);
		mInform.add(openDescription);
		
		menuBar.add(mFile);
		menuBar.add(mInform);
		menuBar.add(mAbout);
		setJMenuBar(menuBar);
	}
	
	public MainForm()
	{
		super();
		setTitle("Виробники вогнепальної зброї");
		
		createMenu();
		
		JMenuItem cmdNew=new JMenuItem("Новий запис");
		cmdNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.CTRL_MASK));
		cmdNew.addActionListener(this);
		popupMenu.add(cmdNew);
		cmdNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addMaker();
			}
		});

		JMenuItem cmdEdit=new JMenuItem("Редагувати запис");
		cmdNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,ActionEvent.CTRL_MASK));
		cmdNew.addActionListener(this);
		popupMenu.add(cmdEdit);
		cmdEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateMaker();
			}
		});
		
		JMenuItem cmdDelete=new JMenuItem("Видалити запис");
		cmdNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,ActionEvent.CTRL_MASK));
		cmdNew.addActionListener(this);
		popupMenu.add(cmdDelete);
		cmdDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeMaker();
			}
		});
		
		JMenuItem cmdMaker=new JMenuItem("Докладніше про виробників");
		cmdMaker.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,ActionEvent.CTRL_MASK));
		cmdMaker.addActionListener(this);
		popupMenu.add(cmdMaker);
		cmdMaker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openMaker();
			}
		});
		
		JMenuItem cmdWeapon=new JMenuItem("Докладніше про зброю");
		cmdWeapon.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,ActionEvent.CTRL_MASK));
		cmdWeapon.addActionListener(this);
		popupMenu.add(cmdWeapon);
		cmdMaker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					openWeapon();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JMenuItem cmdDescription=new JMenuItem("Докладніше про опис зброї");
		cmdDescription.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,ActionEvent.CTRL_MASK));
		cmdDescription.addActionListener(this);
		popupMenu.add(cmdDescription);
		cmdMaker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openDescription();
			}
		});
		
		cmdClose=new JButton("Закрити");
		cmdAddMaker=new JButton("Добавити");
		cmdUpdateMaker=new JButton("Редагувати");
		cmdDeleteMaker=new JButton("Видалити");
		cmdOpenWeapon=new JButton("Зброя");
		cmdOpenDesc=new JButton("Опис");
		cmdPrinMaker=new JButton("Вивести на друк");
		jLab=new JLabel();
		
		makersTableModel=getTableModel();
		makersTable=new JTable(makersTableModel);
		makersTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		makersTable.setPreferredScrollableViewportSize(new Dimension(950,180));
		makersTable.getColumnModel().getColumn(0).setMinWidth(10);
		makersTable.getColumnModel().getColumn(1).setMinWidth(100);
		makersTable.getColumnModel().getColumn(2).setMinWidth(20);
		makersTable.getColumnModel().getColumn(3).setMinWidth(120);
		makersTable.getColumnModel().getColumn(4).setMinWidth(100);
		makersTable.getColumnModel().getColumn(5).setMinWidth(40);
		makersTable.getColumnModel().getColumn(6).setMinWidth(40);
		makersTable.getColumnModel().getColumn(7).setMinWidth(40);
		makersTable.getColumnModel().getColumn(8).setMinWidth(40);
		makersTable.getColumnModel().getColumn(9).setMinWidth(40);
		makersTable.setGridColor(Color.WHITE);
		makersTable.setRowHeight(20);
		Font FontGrid = new Font(Font.MONOSPACED, Font.PLAIN, 14);
		makersTable.setFont(FontGrid);
		
		JScrollPane scrollPane = new JScrollPane(makersTable);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);

		MainFonClass mainPanel = new MainFonClass();
		mainPanel.add(scrollPane);
		
		
		
		JPanel commandsPanel = new JPanel(new FlowLayout());
		commandsPanel.add(jLab);
		commandsPanel.add(cmdAddMaker);
		commandsPanel.add(cmdUpdateMaker);
		commandsPanel.add(cmdDeleteMaker);

		commandsPanel.add(cmdOpenWeapon);
		commandsPanel.add(cmdOpenDesc);
		commandsPanel.add(cmdClose);
		Border northBorder = BorderFactory
				.createTitledBorder("Командна панель");
		commandsPanel.setBorder(northBorder);
		commandsPanel.setOpaque(false);
		mainPanel.add(commandsPanel);

		// setModal(false);
		getRootPane().setDefaultButton(cmdClose);
		setSize(1000, 380);
		setResizable(false);
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		
		cmdAddMaker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addMaker();
			}
		});
		
		cmdUpdateMaker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateMaker();
			}
		});
		
		cmdDeleteMaker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeMaker();
			}
		});
		
		cmdClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onClose();
			}
		});
		
		cmdOpenWeapon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					openWeapon();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}

	private MakersTableModel getTableModel() {
		// TODO Auto-generated method stub
		try
		{
			final List<Maker> makers = makerManager.getAllMakers();
			return new MakersTableModel(makers);
		}catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this,
					"Помилка при заповненні таблиці груп: " + e.getMessage());
		}
		return new MakersTableModel(new ArrayList<Maker>(0));
	}
	
	private void onClose() {
		dispose();
		
	}

	private void openMaker() {
		// TODO Auto-generated method stub
		
	}

	private void openDescription() {
		// TODO Auto-generated method stub
		
	}

	private void openWeapon() throws Exception {
		int index = makersTable.getSelectedRow();
		if (index == -1)
			return;

		Integer id = Integer.parseInt((String) (makersTable
				.getValueAt(index, 0)));
		Maker maker = makerManager.getMakerById(id);
		MakerWeaponForm makerWeaponForm = new MakerWeaponForm(maker);
		makerWeaponForm.setVisible(true);
		
	}

	private void printMaker() {
		// TODO Auto-generated method stub
		
	}

	private void removeMaker() {
		if (JOptionPane.showConfirmDialog(MainForm.this,
				"Ви хочете видалити інформацію про навчальну групу?",
				"Видалення даних про групу", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			int index = makersTable.getSelectedRow();
			if (index == -1)
				return;

			try {
				Maker m = makersTableModel.getRowGroup(index);
				if (m != null) {
					makerManager.removeMaker(m.getId());
					makersTableModel.removeRow(index);
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(MainForm.this, e.getMessage());
			}
		}

	}
		

	private void updateMaker() {
		int index = makersTable.getSelectedRow();
		if (index == -1)
			return;

		Maker maker = makersTableModel.getRowGroup(index);
		if (maker != null) {
			newMaker.setMaker(maker);
			newMaker.setVisible(true);
			makersTableModel.refreshUpdatedTable();
		}
		
	}

	private void addMaker() {
		newMaker.setMaker(new Maker());
		newMaker.setVisible(true);
		if (newMaker.getMaker().getId() != null) {
			makersTableModel.addMaker(newMaker.getMaker());
		}
		
	}


}
