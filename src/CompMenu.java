import java.awt.event.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;

public class CompMenu extends JDialog {
    // combobox
    static JComboBox c1;
    static JDialog d;
    static JDialog dialog;
    static JTextField t;
    static int testNum;

    String s1[] = { "1. Short sequences (mil bases)", "2. Beta Globin Locus (10 mil bases)",
            "3. Mnd2 Locus (240 mil bases)", "4. Vibrio Cholerae & E-Coli (4 millones de bases)"};

    public CompMenu(){
        d = new JDialog(MainMenuGUI.frame, "Comparación de secuencias de ADN");
        // create a label
        JLabel l = new JLabel("Seleccione el DataSet:");
        l.setBounds(80, 15, 200, 30);
        l.setFont(new Font("Segoe UI SemiLight", Font.PLAIN, 17));
        l.setForeground(Color.decode("#D6EAF8"));

        JLabel l1 = new JLabel("Longitud de emparejamiento:");
        l1.setBounds(25, 100, 200, 20);
        l1.setFont(new Font("Segoe UI SemiLight", Font.PLAIN, 14));
        l1.setForeground(Color.decode("#D6EAF8"));

        t = new JTextField(3);
        t.setBounds(250, 100, 60, 20);
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
                File file = new File("results/comp/data" + testNum + ".csv");
                Desktop desktop = Desktop.getDesktop();
                desktop.open(file);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            // create a dialog Box
        }
    }

    public void results() {
        dialog = new JDialog(MainMenuGUI.frame, "Comparación de secuencias de ADN");
        testNum = c1.getSelectedIndex() + 1;

        String s1 = Main.readSeq("data/test_data_comp/" + "case" + testNum + "-s1.txt");
        String s2 = Main.readSeq("data/test_data_comp/" + "case" + testNum + "-s2.txt");

        String numcar_s1 = "";
        String numcar_s2 = "";

        // Se imprime el número de bases(caracteres) que tiene cada secuencia.
        if (s1 != null && s2 != null) {
            numcar_s1 = "Secuencia 1 : " + s1.length() + " bases";
            numcar_s2 = "Secuencia 2 : " + s2.length() + " bases";
        }
        int matchLength = Integer.parseInt(t.getText());
        long startTime, estimatedTime;

        ArrayList<String> commonSubs;
        // Verificación no nulidad de cadenas.
        if (s1 == null || s2 == null) {
            throw new AssertionError();
        } else {
            // Inicia la medición de tiempo.
            startTime = System.nanoTime();
            // Llamado a función principal de comparación
            commonSubs = Main.SubStringMatch(s1, s2, matchLength);
            // Finaliza medición de tiempo.
            estimatedTime = System.nanoTime() - startTime;
        }
        System.out.println("Elapsed Time:" + estimatedTime + "\n\n");
        // Contador de substrings comunes.
        int countCommon = commonSubs.size();
        try {
            Main.convertToCSV(commonSubs, testNum, "comp");
        } catch (IOException e) {
            e.printStackTrace();
        }
        String  res = "Se encontraron " + countCommon + " subsecuencias comunes.";

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
        l1.setBounds(30, 40, 310, 15);
        l1.setFont(new Font("Segoe UI SemiLight", Font.PLAIN, 12));
        l1.setForeground(Color.decode("#D6EAF8"));

        JLabel l2 = new JLabel(numcar_s2);
        l2.setBounds(30, 60, 310, 15);
        l2.setFont(new Font("Segoe UI SemiLight", Font.PLAIN, 12));
        l2.setForeground(Color.decode("#D6EAF8"));

        JLabel l3 = new JLabel(res);
        l3.setBounds(30, 80, 310, 15);
        l3.setFont(new Font("Segoe UI SemiLight", Font.PLAIN, 14));
        l3.setForeground(Color.decode("#D6EAF8"));

        JLabel l4 = new JLabel("La lista de subsecuencias comunes se encuentra en:");
        l4.setBounds(30, 106, 310, 10);
        l4.setFont(new Font("Segoe UI SemiLight", Font.PLAIN, 12));
        l4.setForeground(Color.white);

        JLabel l5 = new JLabel("results/comp/data"+testNum+".csv");
        l5.setBounds(30, 107, 310, 50);
        l5.setFont(new Font("Segoe UI SemiLight", Font.BOLD, 12));
        l5.setForeground(Color.white);

        background.add(l);
        background.add(l1);
        background.add(l2);
        background.add(l3);
        background.add(l4);
        background.add(l5);

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