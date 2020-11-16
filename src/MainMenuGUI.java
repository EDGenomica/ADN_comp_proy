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

public class MainMenuGUI extends JFrame {

	private JPanel contentPane;
	public Object rdbtnMenuPrincOpc1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenuGUI frame = new MainMenuGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainMenuGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 593, 409);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblMenuPrincBienvenidos = new JLabel("\u00A1BIENVENIDOS A ADA SOLUTIONS!");
		lblMenuPrincBienvenidos.setBounds(203, 11, 169, 14);
		contentPane.add(lblMenuPrincBienvenidos);

		JLabel lblMenuPrincTitulo = new JLabel("SISTEMA DE GESTION Y ANALISIS DE ADN");
		lblMenuPrincTitulo.setBounds(183, 36, 204, 14);
		contentPane.add(lblMenuPrincTitulo);

		JLabel lblMenuPrincSeleccione = new JLabel(
				"Seleccione una opci\u00F3n entre 1 y 4 o digite la letra Q para salir.");
		lblMenuPrincSeleccione.setBounds(55, 73, 297, 14);
		contentPane.add(lblMenuPrincSeleccione);

		ButtonGroup menuPrincGrupo = new ButtonGroup(); // Clase ButtonGroup obliga a que sólo una opción sea
														// seleccionada.

		JRadioButton rdbtnMenuPrincOpc1 = new JRadioButton("1 Compara secuencias de ADN - porcentaje de similitud.");
		rdbtnMenuPrincOpc1.setBounds(41, 105, 395, 23);
		contentPane.add(rdbtnMenuPrincOpc1);
		rdbtnMenuPrincOpc1.addActionListener(new ActionListener() { // Pone al rdbtnMenuPrincOpc1 en modo escucha.
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Valor de rdbtnMenuPrincOpc1 pulsado:  " + arg0);
				System.out.println("Pulsé opc1");
			}
		});

		JRadioButton rdbtnMenuPrincOpc2 = new JRadioButton("2 Subcadenas m\u00E1s frecuentes en una secuencia.");
		rdbtnMenuPrincOpc2.setBounds(41, 131, 395, 23);
		contentPane.add(rdbtnMenuPrincOpc2);
		rdbtnMenuPrincOpc2.addActionListener(new ActionListener() { // Pone al rdbtnMenuPrincOpc2 en modo escucha.
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Pulsé opc2");
			}
		});

		JRadioButton rdbtnMenuPrincOpc3 = new JRadioButton("3 Ocurrencia de una subcacena en una secuencia.");
		rdbtnMenuPrincOpc3.setBounds(41, 157, 395, 23);
		contentPane.add(rdbtnMenuPrincOpc3);
		rdbtnMenuPrincOpc3.addActionListener(new ActionListener() { // Pone al rdbtnMenuPrincOpc3 en modo escucha.
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Pulsé opc3");
			}
		});

		JRadioButton rdbtnMenuPrincOpc4 = new JRadioButton("4 Complemento reverso de una secuencia.");
		rdbtnMenuPrincOpc4.setBounds(41, 183, 395, 23);
		contentPane.add(rdbtnMenuPrincOpc4);
		rdbtnMenuPrincOpc4.addActionListener(new ActionListener() { // Pone al rdbtnMenuPrincOpc4 en modo escucha.
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Pulsé opc4");
			}
		});

		menuPrincGrupo.add(rdbtnMenuPrincOpc1);
		menuPrincGrupo.add(rdbtnMenuPrincOpc2);
		menuPrincGrupo.add(rdbtnMenuPrincOpc3);
		menuPrincGrupo.add(rdbtnMenuPrincOpc4);

		JButton btnMenuPrincAcpetar = new JButton("Aceptar");
		btnMenuPrincAcpetar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { // Según la opción escogida, acá se programa el evento del
															// botón aceptar.
				boolean q = true;
				while (q) {
					System.out.println("Entré al while (q)");
					if (arg0.getSource() == rdbtnMenuPrincOpc1) {
						System.out.println("Enté al if (arg0.get...");
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
						System.out.println("Ingrese una opciÃ³n vÃ¡lida");
						q = false;
						Main.mainMenuPrint();	
					}
				}
			}
		});
		btnMenuPrincAcpetar.setBounds(83, 269, 89, 23);
		contentPane.add(btnMenuPrincAcpetar);

		JButton btnMenuPrincSalir = new JButton("Salir");
		btnMenuPrincSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnMenuPrincSalir.setBounds(401, 269, 89, 23);
		contentPane.add(btnMenuPrincSalir);
	}

	private class EventRadio implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == rdbtnMenuPrincOpc1) {
				System.out.println("He pulsado opc1");
			}
		}

	}
}
