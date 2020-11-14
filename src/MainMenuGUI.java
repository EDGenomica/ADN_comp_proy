import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainMenuGUI extends JFrame {

	private JPanel contentPane;

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
		
		JLabel lblMenuPrincSeleccione = new JLabel("Seleccione una opci\u00F3n entre 1 y 4 o digite la letra Q para salir.");
		lblMenuPrincSeleccione.setBounds(55, 73, 297, 14);
		contentPane.add(lblMenuPrincSeleccione);
		
		JRadioButton rdbtnMenuPrincOpc1 = new JRadioButton("1 Compara secuencias de ADN - porcentaje de similitud.");
		rdbtnMenuPrincOpc1.setBounds(41, 105, 395, 23);
		contentPane.add(rdbtnMenuPrincOpc1);
		
		JRadioButton rdbtnMenuPrincOpc2 = new JRadioButton("2 Subcadenas m\u00E1s frecuentes en una secuencia.");
		rdbtnMenuPrincOpc2.setBounds(41, 131, 395, 23);
		contentPane.add(rdbtnMenuPrincOpc2);
		
		JRadioButton rbtnMenuPrincOpc3 = new JRadioButton("3 Ocurrencia de una subcacena en una secuencia.");
		rbtnMenuPrincOpc3.setBounds(41, 157, 395, 23);
		contentPane.add(rbtnMenuPrincOpc3);
		
		JRadioButton rbtnMenuPrincOpc4 = new JRadioButton("4 Complemento reverso de una secuencia.");
		rbtnMenuPrincOpc4.setBounds(41, 183, 395, 23);
		contentPane.add(rbtnMenuPrincOpc4);
		
		JRadioButton rbtnMenuPrincOpcSalir = new JRadioButton("Salir.");
		rbtnMenuPrincOpcSalir.setBounds(41, 209, 395, 23);
		contentPane.add(rbtnMenuPrincOpcSalir);
		
		JButton btnMenuPrincAcpetar = new JButton("Aceptar");
		btnMenuPrincAcpetar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnMenuPrincAcpetar.setBounds(83, 269, 89, 23);
		contentPane.add(btnMenuPrincAcpetar);
		
		JButton btnMenuPrincSalir = new JButton("Salir");
		btnMenuPrincSalir.setBounds(401, 269, 89, 23);
		contentPane.add(btnMenuPrincSalir);
	}
}
