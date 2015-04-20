package net.guns_project.gui;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class NewUpdateFonClass extends JPanel {

	private static final long serialVersionUID = 1L;

	private BufferedImage Fonimage;

	public NewUpdateFonClass() {
		try {
			Fonimage = ImageIO.read(new File("img/newupdatefon.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(Fonimage, 0, 0, getWidth(), getHeight(), null);
	}
}