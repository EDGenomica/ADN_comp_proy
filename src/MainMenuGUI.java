import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.plaf.metal.*;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.imageio.ImageIO;

public class MainMenuGUI extends JFrame implements ActionListener{

	private JPanel contentPane;
	public Object rdbtnMenuPrincOpc1;
	static MainMenuGUI frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					String lookAndFeel = "javax.swing.plaf.metal.MetalLookAndFeel";
					UIManager.setLookAndFeel(lookAndFeel);
					frame = new MainMenuGUI();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void actionPerformed(ActionEvent e)
	{
		String s = e.getActionCommand();
		if (s.equals("Salir"))
			System.exit(0);
		if (s.equals("Comparación de secuencias")) {
			new CompMenu();
			// create a dialog Box
		}
	}

	/**
	 * Create the frame.
	 */
	public MainMenuGUI() {
		setTitle("ADA Solutions");
		setSize(600,400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

		//*********Backgroung Image e Icono**************************

		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("images/genomics_back.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Image dimg = img.getScaledInstance(600, 400, Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(dimg);

		ImageIcon icon = new ImageIcon("images/ADNIcon.png");
		setIconImage(icon.getImage());

		JLabel background=new JLabel(imageIcon);
		//contentPane = new JPanel();
		setContentPane(background);
		//background.add(contentPane);


		//********************************Labels de Titulos*****************************
		JLabel lblAda = new JLabel("ADA Solutions");
		lblAda.setBounds(175, 30, 250, 40);
		lblAda.setFont(new Font("Segoe UI SemiLight", Font.BOLD, 38));
		lblAda.setForeground(Color.white);
		background.add(lblAda);

		JLabel lblSub = new JLabel("Sistema de gestión y análisis de ADN");
		lblSub.setBounds(140, 70, 350, 30);
		lblSub.setFont(new Font("Segoe UI SemiLight", Font.PLAIN, 20));
		lblSub.setForeground(Color.decode("#85C1E9"));
		background.add(lblSub);

		//******************************Botones Menu Principal*************************

		//Salir Button
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(this::actionPerformed);
		btnSalir.setBounds(30, 320, 89, 23);
		//btnSalir.setBackground(Color.decode("#3498DB"));
		btnSalir.setFont(new Font("Segoe UI SemiLight", Font.PLAIN, 12));
		background.add(btnSalir);

		//Funcionalidades
		JButton btnComp = new JButton("Comparación de secuencias");
		btnComp.setBounds(360, 140, 185, 30);
		btnComp.setFont(new Font("Segoe UI SemiLight", Font.PLAIN, 12));
		background.add(btnComp);
		btnComp.addActionListener(this::actionPerformed);

		JButton btnSubFreq = new JButton("Substring más frecuente");
		btnSubFreq.setBounds(360, 180, 185, 30);
		btnSubFreq.setFont(new Font("Segoe UI SemiLight", Font.PLAIN, 12));
		background.add(btnSubFreq);

		JButton btnOcc = new JButton("Occurrencia substring");
		btnOcc.setBounds(360, 220, 185, 30);
		btnOcc.setFont(new Font("Segoe UI SemiLight", Font.PLAIN, 12));
		background.add(btnOcc);

		JButton btnCompRev = new JButton("Complemento Reverso");
		btnCompRev.setBounds(360, 260, 185, 30);
		btnCompRev.setFont(new Font("Segoe UI SemiLight", Font.PLAIN, 12));
		background.add(btnCompRev);

		//Dialogos




		/*
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);

		JLabel lblMenuPrincSeleccione = new JLabel(
				"Seleccione una opci\u00F3n entre 1 y 4 o digite la letra Q para salir.");
		lblMenuPrincSeleccione.setBounds(55, 73, 297, 14);
		contentPane.add(lblMenuPrincSeleccione);

		ButtonGroup menuPrincGrupo = new ButtonGroup(); // Clase ButtonGroup obliga a que s�lo una opci�n sea
														// seleccionada.

		JRadioButton rdbtnMenuPrincOpc1 = new JRadioButton("1 Compara secuencias de ADN - porcentaje de similitud.");
		rdbtnMenuPrincOpc1.setBounds(41, 105, 395, 23);
		contentPane.add(rdbtnMenuPrincOpc1);
		rdbtnMenuPrincOpc1.addActionListener(new ActionListener() { // Pone al rdbtnMenuPrincOpc1 en modo escucha.
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Valor de rdbtnMenuPrincOpc1 pulsado:  " + arg0);
				System.out.println("Puls� opc1");
			}
		});

		JRadioButton rdbtnMenuPrincOpc2 = new JRadioButton("2 Subcadenas m\u00E1s frecuentes en una secuencia.");
		rdbtnMenuPrincOpc2.setBounds(41, 131, 395, 23);
		contentPane.add(rdbtnMenuPrincOpc2);
		rdbtnMenuPrincOpc2.addActionListener(new ActionListener() { // Pone al rdbtnMenuPrincOpc2 en modo escucha.
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Puls� opc2");
			}
		});

		JRadioButton rdbtnMenuPrincOpc3 = new JRadioButton("3 Ocurrencia de una subcacena en una secuencia.");
		rdbtnMenuPrincOpc3.setBounds(41, 157, 395, 23);
		contentPane.add(rdbtnMenuPrincOpc3);
		rdbtnMenuPrincOpc3.addActionListener(new ActionListener() { // Pone al rdbtnMenuPrincOpc3 en modo escucha.
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Puls� opc3");
			}
		});

		JRadioButton rdbtnMenuPrincOpc4 = new JRadioButton("4 Complemento reverso de una secuencia.");
		rdbtnMenuPrincOpc4.setBounds(41, 183, 395, 23);
		contentPane.add(rdbtnMenuPrincOpc4);
		rdbtnMenuPrincOpc4.addActionListener(new ActionListener() { // Pone al rdbtnMenuPrincOpc4 en modo escucha.
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Puls� opc4");
			}
		});

		menuPrincGrupo.add(rdbtnMenuPrincOpc1);
		menuPrincGrupo.add(rdbtnMenuPrincOpc2);
		menuPrincGrupo.add(rdbtnMenuPrincOpc3);
		menuPrincGrupo.add(rdbtnMenuPrincOpc4);

		JButton btnMenuPrincAcpetar = new JButton("Aceptar");
		btnMenuPrincAcpetar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { // Seg�n la opci�n escogida, ac� se programa el evento del
															// bot�n aceptar.
				boolean q = true;
				while (q) {
					System.out.println("Entr� al while (q)");
					if (arg0.getSource() == rdbtnMenuPrincOpc1) {
						System.out.println("Ent� al if (arg0.get...");
						Main.compMenu();
						Main.mainMenu();
					} else if (arg0.getSource() == rdbtnMenuPrincOpc2) {
						Main.subFreqMenu();
						Main.mainMenu();
					} else if (arg0.getSource() == rdbtnMenuPrincOpc3) {
						Main.occSubMenu();
						Main.mainMenu();
					} else if (arg0.getSource() == rdbtnMenuPrincOpc4) {
						Main.revSeqMenu();
						Main.mainMenu();
					} else {
						System.out.println("Ingrese una opción válida");
						q = false;
						Main.mainMenuPrint();	
					}
				}
			}
		});

		 */
	}
}
