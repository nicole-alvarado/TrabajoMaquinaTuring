import javax.swing.JOptionPane;

public class Screen {

    private Data data;
    private TuringMachine machine;

    public Screen() {
        /*
         * setSize(500, 500); setTitle("Simulación"); setLocationRelativeTo(null);
         * setMinimumSize(new Dimension(200, 200));
         */
        // this.data = new Data();
        // this.machine = new TuringMachine(data.getNumberColumns(),
        // data.getNumberRows(), data.getTIME());
        // this.getPanel().add(panel);
    }

    public String requestFilePath() {
        String filePath = JOptionPane.showInputDialog("Ingrese la ruta del archivo a analizar:");
        if (filePath == null) {
            System.exit(0);
        }
        return filePath;
    }

    public String requestString() {
        String str = JOptionPane.showInputDialog("Ingrese la cadena a analizar:");
        if (str == null) {
            System.exit(0);
        }
        return str;
    }

    /*
     * public void showSimulation() throws InterruptedException{ //String str =
     * JOptionPane.showInputDialog("Ingrese la cadena a analizar:");
     * //System.out.println("tape es de tipo " +
     * ((Object)tape).getClass().getSimpleName());
     * machine.validate(JOptionPane.showInputDialog("Ingrese la cadena a analizar:")
     * ); //System.out.println(tape); setVisible(true); JPanel panel = new JPanel();
     * panel.setLayout(null); getContentPane().add(panel); //JLabel lbl = new
     * JLabel(); //lbl.setText(machine.validate(tape)); //lbl.setBounds(200, 200,
     * 300, 400); JTextArea jt = new JTextArea();
     * jt.setText(machine.validate("#aba#")); panel.add(jt); }
     */

}
