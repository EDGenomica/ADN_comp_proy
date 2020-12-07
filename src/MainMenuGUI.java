import javax.swing.*;
import java.awt.*;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class MainMenuGUI extends JFrame implements ActionListener{

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
		else if (s.equals("Comparación de secuencias")) {
			new CompMenu();
		}
		else if (s.equals("Substring más frecuente")) {
			new FreqMenu();
		}
		else if (s.equals("Occurrencia substring")) {
			new OccMenu();
		}
		else if (s.equals("Complemento Reverso")) {
			new RevMenu();
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
		setContentPane(background);

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
		btnSubFreq.addActionListener(this::actionPerformed);

		JButton btnOcc = new JButton("Occurrencia substring");
		btnOcc.setBounds(360, 220, 185, 30);
		btnOcc.setFont(new Font("Segoe UI SemiLight", Font.PLAIN, 12));
		background.add(btnOcc);
		btnOcc.addActionListener(this::actionPerformed);

		JButton btnCompRev = new JButton("Complemento Reverso");
		btnCompRev.setBounds(360, 260, 185, 30);
		btnCompRev.setFont(new Font("Segoe UI SemiLight", Font.PLAIN, 12));
		background.add(btnCompRev);
		btnCompRev.addActionListener(this::actionPerformed);

	}
}
