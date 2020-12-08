import java.awt.event.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;

public class OccMenu extends JDialog {
    // combobox
    static JComboBox c1;
    static JDialog d;
    static JDialog dialog;
    static JTextField t;
    static int testNum;

    String s1[] = { "1. Short sequences (mil bases)", "2. Beta Globin Locus (10 mil bases)",
            "3. Mnd2 Locus (240 mil bases)", "4. Vibrio Cholerae (1 millón de bases)","5. E-Coli (4 millones de bases)",
            "6. Lynx Canadiensis (6 millones de bases)"};

    public OccMenu(){
        d = new JDialog(MainMenuGUI.frame, "Occurrencia de Substring en cadena");
        // create a label
        JLabel l = new JLabel("Seleccione el DataSet:");
        l.setBounds(80, 15, 200, 30);
        l.setFont(new Font("Segoe UI SemiLight", Font.PLAIN, 17));
        l.setForeground(Color.decode("#D6EAF8"));

        JLabel l1 = new JLabel("Subsecuencia a buscar:");
        l1.setBounds(25, 100, 200, 20);
        l1.setFont(new Font("Segoe UI SemiLight", Font.PLAIN, 14));
        l1.setForeground(Color.decode("#D6EAF8"));

        t = new JTextField(20);
        t.setBounds(190, 100, 110, 20);
        // setsize of dialog
        d.setBounds(790, 450, 350, 230);
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("images/DNAdata.jpg"));
        } catch (IOException exc) {
            exc.printStackTrace();
        }
        Image dimg = img.getScaledInstance(350, 230, Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(dimg);
        JLabel background = new JLabel(imageIcon);
        d.add(background);
        background.add(l);
        background.add(l1);
        background.add(t);

        // array of string contating cities

        // create checkbox
        c1 = new JComboBox(s1);
        c1.setBounds(50, 55, 240, 25);
        background.add(c1);

        // set visibility of dialog
        d.setVisible(true);

        //Salir Button
        JButton btnRegresar = new JButton("Regresar");
        btnRegresar.addActionListener(this::actionPerformed);
        btnRegresar.setBounds(40, 150, 90, 25);
        //btnSalir.setBackground(Color.decode("#3498DB"));
        btnRegresar.setFont(new Font("Segoe UI SemiLight", Font.PLAIN, 12));
        background.add(btnRegresar);

        JButton btnContinuar = new JButton("Continuar");
        btnContinuar.setBounds(200, 150, 90, 25);
        btnContinuar.setFont(new Font("Segoe UI SemiLight", Font.PLAIN, 12));
        background.add(btnContinuar);
        btnContinuar.addActionListener(this::actionPerformed);
    }

    public void actionPerformed(ActionEvent e)
    {
        String s = e.getActionCommand();
        if (s.equals("Regresar"))
            d.dispose();
        else if (s.equals("Continuar")) {
            results();
        }
        else if (s.equals("Atrás")) {
            dialog.dispose();
        }
        else if (s.equals("Abrir")) {
            try {
                File file = new File("results/occ/data" + testNum + ".csv");
                Desktop desktop = Desktop.getDesktop();
                desktop.open(file);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            // create a dialog Box
        }
    }

    public void results() {
        dialog = new JDialog(MainMenuGUI.frame, "Ocurrencia de Substring");
        testNum = c1.getSelectedIndex() + 1;

        String s1 = Main.readSeq("data/test_data_sec/case" + testNum + ".txt");

        String numcar_s1 = "";

        // Se imprime el número de bases(caracteres) que tiene cada secuencia.
        if (s1 != null) {
            numcar_s1 = "Secuencia 1: " + s1.length() + " bases";
        }
        String sequence = t.getText();
        int COUNT;
        long startTime, estimatedTime;

        ArrayList<String> occSubs = new ArrayList<>();
        // Verificación no nulidad de cadenas.
        if (s1 == null) {
            throw new AssertionError();
        } else {
            // Inicia la medición de tiempo.
            startTime = System.nanoTime();
            // Llamado a función principal
            occSubs = Main.PatternMatching(s1, sequence.toUpperCase());
            // Finaliza medición de tiempo.
            estimatedTime = System.nanoTime() - startTime;
            COUNT = occSubs.size();
        }
        try {
            Main.convertToCSV(occSubs, testNum, "occ");
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Elapsed Time:" + estimatedTime + "\n\n");
        String  res1 = "La subsecuencia "+ sequence.toUpperCase() + " aparece en la cadena";
        String  res2 = COUNT + " veces.";

        dialog.setBounds(790, 450, 350, 230);

        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("images/DNAresults.jpg"));
        } catch (IOException exc) {
            exc.printStackTrace();
        }
        Image dimg = img.getScaledInstance(350, 230, Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(dimg);
        JLabel background = new JLabel(imageIcon);
        dialog.add(background);

        JLabel l = new JLabel("Resultados");
        l.setBounds(100, 10, 200, 25);
        l.setFont(new Font("Segoe UI SemiLight", Font.BOLD, 17));
        l.setForeground(Color.WHITE);

        JLabel l1 = new JLabel(numcar_s1);
        l1.setBounds(30, 40, 310, 10);
        l1.setFont(new Font("Segoe UI SemiLight", Font.PLAIN, 12));
        l1.setForeground(Color.decode("#D6EAF8"));

        JLabel l3 = new JLabel(res1);
        l3.setBounds(30, 58, 310, 15);
        l3.setFont(new Font("Segoe UI SemiLight", Font.PLAIN, 13));
        l3.setForeground(Color.decode("#D6EAF8"));

        JLabel l6 = new JLabel(res2);
        l6.setBounds(30, 73, 310, 15);
        l6.setFont(new Font("Segoe UI SemiLight", Font.PLAIN, 13));
        l6.setForeground(Color.decode("#D6EAF8"));

        JLabel l4 = new JLabel("Los resultados se han almacenado en:");
        l4.setBounds(30, 98, 310, 10);
        l4.setFont(new Font("Segoe UI SemiLight", Font.PLAIN, 12));
        l4.setForeground(Color.white);

        JLabel l5 = new JLabel("results/occ/data"+testNum+".csv");
        l5.setBounds(30, 100, 310, 50);
        l5.setFont(new Font("Segoe UI SemiLight", Font.BOLD, 12));
        l5.setForeground(Color.white);

        background.add(l);
        background.add(l1);
        background.add(l3);
        background.add(l4);
        background.add(l5);
        background.add(l6);

        dialog.setVisible(true);

        JButton btnAbrir = new JButton("Abrir");
        btnAbrir.setBounds(200, 148, 90, 25);
        btnAbrir.setFont(new Font("Segoe UI SemiLight", Font.PLAIN, 12));
        background.add(btnAbrir);
        btnAbrir.addActionListener(this::actionPerformed);

        JButton btnBack = new JButton("Atrás");
        btnBack.setBounds(30, 148, 90, 25);
        btnBack.setFont(new Font("Segoe UI SemiLight", Font.PLAIN, 12));
        background.add(btnBack);
        btnBack.addActionListener(this::actionPerformed);

        background.add(btnAbrir);
        background.add(btnBack);

    }
}
