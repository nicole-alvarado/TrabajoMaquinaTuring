import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Data {
    
    private final int TIME = 1;
    private Scanner sc = null;
    private int numberColumns = 0;
    private int numberRows = 0;

    public Data() {
    }

    /**
     * Busca y carga el archivo que contiene la matriz de datos para la simulación.
     * @param filePath
     * @return
     */
    public File loadFile(String filePath){
        File file = null;
        try {
            sc = new Scanner(new File(filePath));
            file = new File(filePath);
        } catch (FileNotFoundException e) {
            //TODO: handle exception
            System.out.println("Archivo no encontrado.");
			System.exit(0);
        }
    return file;
    }

    /**
     * Lee el tipo de operación a realizar con la cadena ingresada
     * @return
     */
    /*public int TypeOperation(){
        System.out.println("¿Qué desea hacer con cadena?\n1. Validar\n2. Operar");
        Scanner sc = new Scanner(System.in);
        String op = sc.next();
        System.out.println(op);
        if (op.compareTo("1") == 0 || op.compareTo("2") == 0)
            return Integer.parseInt(op);
        else
            System.out.println("Opción inválida");        
            return -1;
    }*/

    /**
     * 
     * @return la cantidad de columnas de la matriz
     */
    public int getNumberColumns() {
        while(sc.hasNext()){
            numberColumns = sc.next().split("/").length;
            numberRows++;
        }
        //System.out.println(numberColumns);
        return numberColumns;
    }

    public void setNumberColumns(int numberColumns) {
        this.numberColumns = numberColumns;
    }

    /**
     * 
     * @return cantidad de filas de la matriz-
     */
    public int getNumberRows() {
        //System.out.println(numberRows);
        return numberRows;
    }

    public void setNumberRows(int numberRows) {
        this.numberRows = numberRows;
    }

    public int getTIME() {
        return TIME;
    }

    public Scanner getSc() {
        return sc;
    }

    public void setSc(Scanner sc) {
        this.sc = sc;
    }

    
    /**
     * Inicializa la máquina de Turing con los datos previamente cargados.
     */
}
