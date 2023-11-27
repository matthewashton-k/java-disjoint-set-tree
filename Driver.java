package comprehensive;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *  driver class, takes in an input file for a graph and depending on the queries in the file, it will output connected
 *  or not connected
 */
public class Driver {

    /**
     *  the main method
     * @param args command line arguments
     * @throws FileNotFoundException if file not found
     */
    public static void main(String[] args) throws FileNotFoundException {
        // create scanner to read from file
        Scanner inFile = new Scanner(new File(args[0]));
        DisjointSetForest<String> djs = new DisjointSetForest<>();

        String line = inFile.nextLine();
        // loop until blank line or end of file
        while (inFile.hasNext() && !line.isBlank()) {
            // make set for each line
            djs.makeSet(line);
            // read next line
            line = inFile.nextLine();
        }
        // read next line after blank line
        line = inFile.nextLine();

        // loop until blank line or end of file
        while (inFile.hasNext() && !line.isBlank()) {
            String[] s = line.split("\\s+");
            // union the two sets
            djs.union(s[0],s[1]);
            // read next line
            line = inFile.nextLine();
        }
        // read next line after blank line
        line = inFile.nextLine();

        // loop until blank line or end of file
        while (!line.isBlank()) {
            // split line into two strings
            String[] s = line.split("\\s+");

            // check if the two sets are connected
            if (djs.getRepresentative(s[0]).equals(djs.getRepresentative(s[1]))) {
                System.out.println("connected");
            } else {
                System.out.println("not connected");
            }
            if (inFile.hasNext()) {
                // read next line if there is one
                line = inFile.nextLine();
            } else {
                break;
            }
        }
    }
}