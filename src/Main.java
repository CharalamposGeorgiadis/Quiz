import java.awt.*;
import java.io.*;

/**
 * Main class of the application.
 * @author Anastasios Kachrimanis
 * @author Charalampos Georgiadis
 * @version 24/11/2020
 */

public class Main {

    /**
     * Main function of the application.
     * @param args An array of command-line arguments for the application
     * @throws FileNotFoundException if a file is not found.
     */

    public static void main(String[] args) throws IOException, FontFormatException {
        File path = new File("Buzz Questions Directory");
        File[] questions = path.listFiles();
        GUI gui = new GUI(questions);
        gui.startGUI();
    }
}