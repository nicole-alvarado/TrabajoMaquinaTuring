import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Dimension;

public class TuringMachine {

    private int numberColumns;
    private int numberRows;
    private String[][] machine;
    private String state = null;
    private String transition = null;
    private int mov = 0;
    private boolean firstState = true;
    private Data data = new Data();
    private String[] tape;
    private Icon acceptIcon;

    public TuringMachine(int numberColumns, int numberRows, int time) {
        acceptIcon = new ImageIcon("acceptIcon.jpg");
        this.numberColumns = numberColumns;
        this.numberRows = numberRows;
        this.machine = new String[numberRows][numberColumns];
    }

    /**
     * 
     * @param file
     * @throws FileNotFoundException
     */
    public void loadStates(File file) throws FileNotFoundException {
        String[] row;
        Scanner sc = null;
        sc = new Scanner(file);

        int i = 0;
        while (sc.hasNext()) {
            row = sc.next().split("/");
            for (int j = 0; j < numberColumns; j++) {
                machine[i][j] = row[j];
            }
            i++;
        }
    }

    public void drawMatrix(File file) throws FileNotFoundException {
        String[] row;
        Scanner sc = null;
        sc = new Scanner(file);
        int i = 0;
        System.out.println("Matriz cargada:\n\n ");
        while (sc.hasNext()) {
            row = sc.next().split("/");
            for (int j = 0; j < numberColumns; j++) {
                machine[i][j] = row[j];
                int max = 11;
                int n = 0;
                if (machine[i][j].split("").length < max) {
                    n = max - machine[i][j].split("").length;
                }
                for (int k = 0; k < n; k++) {
                    machine[i][j] += " ";
                }
                System.out.print(machine[i][j] + "\t");
            }
            System.out.println("\n");
            System.out
                    .println(("__________________________________________________________________________________\n"));
            i++;
        }
    }

    /**
     * 
     * @param string
     * @throws InterruptedException
     */
    public void validate(String str) throws InterruptedException {
        tape = str.split("");
        int head = 0;
        transition = machine[1][getColumn(tape[1])];
        System.out.println("Simulación:\n");
        drawTape(tape, head);

        head = 1;
        while (!transition.equals("#")) {
            // while(transition.compareTo("#") != 0){
            Thread.sleep(data.getTIME() * 1000);
            getTransition(head);
            drawTape(tape, head);

            if (!transition.equals("#")) {
                // if(transition.compareTo("#") != 0){
                mov = moveHead(transition.split(",")[2]);
                head += mov;
            }

        }
        finalState();

    }

    public void finalState() {
        if (machine[getRow(state)][getColumn("F")].equals("1")) {
            // if(machine[getRow(state)][getColumn("F")].compareTo("1") == 0){
            // System.out.println("¡Cadena aceptada!");
            JOptionPane.showMessageDialog(null, "¡Cadena aceptada!", "", JOptionPane.INFORMATION_MESSAGE, acceptIcon);
        } else {
            JOptionPane.showMessageDialog(null, "¡Cadena no aceptada!", "", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Obtiene cada transición
     * 
     * @param head
     */
    public void getTransition(int head) {
        if (firstState) {
            state = machine[1][0];
            transition = machine[1][getColumn(tape[1])];
            firstState = false;
        } else {
            state = machine[getRow(transition.split(",")[0])][0];
            // System.out.println("estado en linea 104" + state);
            transition = machine[getRow(transition.split(",")[0])][getColumn(tape[head])];
        }
        // if(transition.compareTo("#") != 0){
        if (!transition.equals("#")) {
            // Lo que escribe en la cinta
            String aux = transition.split(",")[1];
            // if(aux.compareTo("lambda") != 0){
            if (!aux.equals("lambda")) {
                tape[head] = aux;
            }
        }
    }

    public int moveHead(String move) {
        switch (move) {
        case "L":
            return -1;
        case "R":
            return 1;
        case "N":
            return 0;
        /*
         * default: System.out.println("¡Movimiento inválido!");
         */
        }
        throw new RuntimeException("Movimiento inválido");
    }

    /**
     * Dibuja el recorrido de la cinta.
     * 
     * @param tape cinta a analizar.
     * @param head cabezal de la máquina.
     */
    public void drawTape(String[] tape, int head) {
        System.out.println("\n\n\n\n\n\n\nTransición: " + state + " ➜ ➜ ➜  " + transition.split(",")[0] + "\n\n");
        for (int i = 0; i < tape.length; i++) {
            /*
             * if(head != i){ System.out.print("   ▍  " + tape[i] + "   ▍"); } else{
             * System.out.print("   ▍▶ " + tape[i] + "   ▍"); }
             */
            if (head != i) {
                System.out.print("     " + tape[i] + "   ");
            } else {
                System.out.print("   ▶ " + tape[i] + "   ");
            }
        }
        System.out.println("\n");
    }

    /**
     * Obtiene la columna de un determinado elemento del alfabeto.
     * 
     * @param e elemento leído de la cinta.
     * @return número de la columna donde se encuentra el elemento.
     */
    public int getColumn(String e) {
        for (int j = 1; j < numberColumns; j++) {
            if (machine[0][j].compareTo(e) == 0) {
                return j;
            }
        }
        throw new RuntimeException("No existe el símbolo en el alfabeto");
    }

    public int getRow(String state) {
        // System.out.println("estado en getRow" + state);
        for (int i = 1; i <= numberRows; i++) {
            if (machine[i][0].compareTo(state) == 0) {
                return i;
            }
        }
        throw new RuntimeException("Estado inexistente.");
    }

    public String Matrix() {
        String matrix = "Matriz del ejercicio:\n\n\n";
        for (int i = 0; i < numberRows; i++) {
            for (int j = 0; j < numberColumns; j++) {
                matrix += " " + machine[i][j] + "\t";
            }

        }

        return matrix;
    }

    /**
     * Muestra una ventana con el listado de las estaciones y sus lineas,
     * identificados con los códigos.
     */
    public void showMatrix() {
        JTextArea lista = new JTextArea();
        JScrollPane jsp;
        lista.setText(Matrix());
        lista.setEditable(false);
        jsp = new JScrollPane(lista) {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(480, 320);
            }
        };
        JOptionPane.showMessageDialog(null, jsp, "Matriz: ", JOptionPane.PLAIN_MESSAGE);

    }
}
