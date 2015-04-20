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

import net.guns_project.domain.Maker;
import net.guns_project.domain.Weapon;
import net.guns_project.manager.IWeaponManager;
import net.guns_project.manager.RemoteWeaponManagerProxy;

public class MakerWeaponForm extends JDialog implements ActionListener{
	private static final long serialVersionUID = -1286577455252661221L;

	private JButton cmdClose;
	private JButton cmdAddWeapon;
	private JButton cmdUpdateWeapon;
	private JButton cmdDeleteWeapon;
	private JButton cmdPrintWeapon;
	private JTable weaponsTable;
	
	private NewWeapon newWeapon = new NewWeapon();
	JPopupMenu popupMenu = new JPopupMenu();
	
	JButton bnew, bupdate, bremove, bprint, bclose, cmdKmNew, cmdKmUpdate,
	cmdKmRemove, cmdKmPrint, cmdKmClose;
	
	private WeaponsTableModel weaponsTableModel;
	private IWeaponManager weaponManager = new RemoteWeaponManagerProxy();

	private Maker makers;
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addWeapon) {
			addWeapon();
		} else if (e.getSource() == updateWeapon) {
			updateWeapon();
		} else if (e.getSource() == removeWeapon) {
			removeWeapon();
		} else if (e.getSource() == printWeapon) {
			printWeapon();
		} else if (e.getSource() == onClose) {
			onClose();
		}
	}
	
	JMenuItem addWeapon, updateWeapon, removeWeapon, printWeapon, onClose;
	
	void createMenu() {
		Color colorMenu = (Color.ORANGE);
		Font fontMenu = new Font(Font.MONOSPACED, Font.PLAIN, 14);
		JMenuBar MenuBar = new JMenuBar();

		JMenu mFile = new JMenu("Файл");
		mFile.setFont(fontMenu);
		MenuBar.setBackground(colorMenu);

		ImageIcon icon = new ImageIcon("img/new.gif");
		addWeapon = new JMenuItem("Додати зброю", icon);
		addWeapon.setToolTipText("Добавити новий запис до бази");
		addWeapon.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
				ActionEvent.CTRL_MASK));
		addWeapon.setFont(fontMenu);
		addWeapon.addActionListener(this);
		mFile.add(addWeapon);

		ImageIcon icon3 = new ImageIcon("img/update.gif");
		updateWeapon = new JMenuItem("Внести зміни", icon3);
		updateWeapon.setToolTipText("Внести зміни у вже створений запис");
		updateWeapon.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U,
				ActionEvent.CTRL_MASK));
		updateWeapon.setFont(fontMenu);
		updateWeapon.addActionListener(this);
		mFile.add(updateWeapon);

		ImageIcon icon2 = new ImageIcon("img/remote.gif");
		removeWeapon = new JMenuItem("Видалити запис про зброю", icon2);
		removeWeapon.setToolTipText("Видалити запис у базі");
		removeWeapon.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,
				ActionEvent.SHIFT_MASK));
		removeWeapon.setFont(fontMenu);
		removeWeapon.addActionListener(this);
		mFile.add(removeWeapon);

		mFile.addSeparator();

		ImageIcon icon7 = new ImageIcon("img/print.gif");
		printWeapon = new JMenuItem("Вивести на друк", icon7);
		printWeapon.setToolTipText("Роздрукувати інформацію з таблиці");
		printWeapon.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,
				ActionEvent.CTRL_MASK));
		printWeapon.setFont(fontMenu);
		printWeapon.addActionListener(this);
		mFile.add(printWeapon);

		mFile.addSeparator();

		ImageIcon icon4 = new ImageIcon("img/onclose.gif");
		onClose = new JMenuItem("Закрити вікно", icon4);
		onClose.setToolTipText("Перехід до головної форми");
		onClose.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4,
				ActionEvent.ALT_MASK));
		onClose.setFont(fontMenu);
		onClose.addActionListener(this);
		mFile.add(onClose);

		MenuBar.add(mFile);
		setJMenuBar(MenuBar);

	}
	
	public MakerWeaponForm(Maker m) throws IOException {
		this.makers = m;
		setTitle("Зброя компанії  " + m.getCompanyName());

		createMenu();

		ImageIcon kmNewicon = new ImageIcon("img/new.gif");
		JMenuItem cmdKmNew = new JMenuItem("Новий запис", kmNewicon);
		cmdKmNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
				ActionEvent.CTRL_MASK));
		cmdKmNew.addActionListener(this);
		popupMenu.add(cmdKmNew);

		cmdKmNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addWeapon();
			}
		});
		ImageIcon kmUpdateicon = new ImageIcon("img/update.gif");
		JMenuItem cmdKmUpdate = new JMenuItem("Редагувати запис", kmUpdateicon);
		cmdKmUpdate.addActionListener(this);
		cmdKmUpdate.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U,
				ActionEvent.CTRL_MASK));
		popupMenu.add(cmdKmUpdate);

		cmdKmUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateWeapon();
			}
		});
		popupMenu.addSeparator();
		ImageIcon kmRemoveicon = new ImageIcon("img/remote.gif");
		JMenuItem cmdKmRemove = new JMenuItem("Видалити запис", kmRemoveicon);
		cmdKmRemove.addActionListener(this);
		cmdKmRemove.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,
				ActionEvent.SHIFT_MASK));
		popupMenu.add(cmdKmRemove);

		cmdKmRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeWeapon();
			}
		});

		JToolBar tools = new JToolBar();
		Color ColorBar = Color.GRAY;
		tools.setBackground(ColorBar);

		bnew = new JButton(new ImageIcon("img/new.gif"));
		bnew.setToolTipText("Новий запис");
		tools.add(bnew);

		bnew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addWeapon();
			}
		});

		bupdate = new JButton(new ImageIcon("img/update.gif"));
		bupdate.setToolTipText("Редагувати запис");
		tools.add(bupdate);

		bupdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateWeapon();
			}
		});

		bremove = new JButton(new ImageIcon("img/remote.gif"));
		bremove.setToolTipText("Видалити запис");
		tools.add(bremove);

		bremove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeWeapon();
			}
		});
		bprint = new JButton(new ImageIcon("img/print.gif"));
		bprint.setToolTipText("Роздрукувати інформацію");
		tools.add(bprint);

		bprint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				printWeapon();
			}
		});

		bclose = new JButton(new ImageIcon("img/onclose.gif"));
		bclose.setToolTipText("Закрити програму");
		tools.add(bclose);

		bclose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onClose();
			}
		});
		cmdClose = new JButton("Закрити");
		cmdAddWeapon = new JButton("Додати");
		cmdUpdateWeapon = new JButton("Редагувати");
		cmdDeleteWeapon = new JButton("Видалити");
		cmdPrintWeapon = new JButton("Вивести на друк");
		weaponsTableModel = getTableModel(m);
		weaponsTable = new JTable(weaponsTableModel);
		weaponsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		weaponsTable
				.setPreferredScrollableViewportSize(new Dimension(920, 180));
		weaponsTable.getColumnModel().getColumn(0).setMinWidth(10);
		weaponsTable.getColumnModel().getColumn(1).setMinWidth(80);
		weaponsTable.getColumnModel().getColumn(2).setMinWidth(60);
		weaponsTable.getColumnModel().getColumn(3).setMinWidth(60);
		weaponsTable.getColumnModel().getColumn(4).setMinWidth(240);
		weaponsTable.setGridColor(Color.ORANGE);
		weaponsTable.setRowHeight(20);
		Font FontGrid = new Font(Font.MONOSPACED, Font.PLAIN, 14);
		weaponsTable.setFont(FontGrid);
		JScrollPane scrollPane = new JScrollPane(weaponsTable);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		MainFonClass mainPanel = new MainFonClass();
		mainPanel.add(scrollPane);
		JPanel commandsPanel = new JPanel(new FlowLayout());
		commandsPanel.add(cmdAddWeapon);
		commandsPanel.add(cmdUpdateWeapon);
		commandsPanel.add(cmdDeleteWeapon);
		commandsPanel.add(cmdPrintWeapon);
		commandsPanel.add(cmdClose);
		commandsPanel.setOpaque(false);
		mainPanel.add(commandsPanel);

		getRootPane().setDefaultButton(cmdClose);
		setSize(950, 350);
		setResizable(false);
		getContentPane().add(tools, BorderLayout.NORTH);
		getContentPane().add(mainPanel, BorderLayout.CENTER);

		weaponsTable.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				if (me.isPopupTrigger())
					popupMenu.show(me.getComponent(), me.getX(), me.getY());
			}

			public void mouseReleased(MouseEvent me) {
				if (me.isPopupTrigger())
					popupMenu.show(me.getComponent(), me.getX(), me.getY());
			}
		});

		cmdAddWeapon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addWeapon();
			}
		});

		cmdUpdateWeapon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateWeapon();
			}
		});

		cmdDeleteWeapon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeWeapon();
			}
		});
		cmdPrintWeapon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				printWeapon();
			}
		});
		cmdClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onClose();
			}
		});

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				onClose();
			}
		});

		mainPanel.registerKeyboardAction(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onClose();
			}
		}, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
				JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
	}

	
	
	
	
	
	private WeaponsTableModel getTableModel(Maker m) {
		try {
			final List<Weapon> weapons = weaponManager.getMakerWeapons(m.getId());

			return new WeaponsTableModel(weapons);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(
					this,
					"Помилка при заповненні таблиці студентів: "
							+ e.getMessage());
		}
		return new WeaponsTableModel(new ArrayList<Weapon>(0));
	}

	private void onClose() {
		dispose();
	}

	private void addWeapon() {
		Weapon weapon = new Weapon();
		weapon.setMakerId(1);
		newWeapon.setWeapon(weapon);
		newWeapon.setVisible(true);
		if (newWeapon.getWeapon().getId() != null) {
			weaponsTableModel.addWeapon(newWeapon.getWeapon());
		}

	}

	private void updateWeapon() {

	}

	private void removeWeapon() {

	}

	private void printWeapon() {

	}

}
