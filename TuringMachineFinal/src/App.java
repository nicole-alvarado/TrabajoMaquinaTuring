import java.io.File;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class App {

    public static void main(String[] args) throws Exception {

        Data data = new Data();
        File file;
        Screen screen = new Screen();
        file = data.loadFile(screen.requestFilePath());
        TuringMachine machine = new TuringMachine(data.getNumberColumns(), data.getNumberRows(), data.getTIME());
        machine.drawMatrix(file);
        machine.loadStates(file);
        machine.validate(screen.requestString());

        int x = JOptionPane.showConfirmDialog(null, "¿Desea volver al inicio?", "Confirmación",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (x == 0) {
            App.main(args);
        } else {
            System.exit(0);
        }

    }

}
