package fr.astro.util.buffers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.util.ArrayList;

import fr.astro.util.Instantiable;

/**
 * FileReaderManagement, helps to manage file readers
 * 
 * @see Instantiable
 */
public class FileReaderManagement implements Instantiable {

    // Instance
    private static FileReaderManagement instance;

    // Optimisation : keep buffer in memory - TODO
    private BufferedReader lastFileReader;
    private File lastFile;


    /**
     * Constructor
     */
    private FileReaderManagement() {
    }

    /**
     * Return a FileReaderManagement
     * Create a new FileReaderManagement if instance is null
     * 
     * @return a FileReaderManagement
     */
    public static FileReaderManagement getInstance() {
        if (instance == null) {
            instance = new FileReaderManagement();
        }
        return instance;
    }

    /**
     * Get a buffer reader
     * @param file
     * @throws Exception
     */
    public void getBufferedWriter(File file) throws Exception {

        // if (lastFileReader == null || !file.equals(lastFile)) {
        //     lastFileReader = new BufferedReader(new FileReader(FileManagement.getInstance().getFilePath(file)));

        //     // Save the file
        //     lastFile = file;
        // }

        // // Set buffer reader to the beginning of the file
        // lastFileReader.mark(0);

        lastFileReader = new BufferedReader(new FileReader(FileManagement.getInstance().getFilePath(file)));

    }

    /**
     * Return the content of a file
     * 
     * @param file the file
     * @return the content of the file
     */
    public List<String> getFileContent(File file) throws Exception {

        // Get a buffer reader
        getBufferedWriter(file);

        // Read the file
        List<String> fileContent = new ArrayList<>();
        String line;

        while ((line = lastFileReader.readLine()) != null) {
            // Remove the last \n or \n\r
            if (line.endsWith("\n\r")) {
                line = line.substring(0, line.length() - 2);
            } else if (line.endsWith("\n") || line.endsWith("\r")) {
                line = line.substring(0, line.length() - 1);
            }

            fileContent.add(line);
        }

        // Return the content of the file
        return fileContent;

    }
    
    /**
     * Return a specific line of a file
     * 
     * @param file the file
     * @param line the line
     * @return the line of the file
     */
    public String getLineOfFile(File file, int line) throws Exception {

        // Get a buffer reader
        getBufferedWriter(file);

        // Read the file
        int i = 0;
        String lineOfFile = null;
        String lineOfFileTemp;

        while ((lineOfFileTemp = lastFileReader.readLine()) != null) {
            if (i == line) {
                lineOfFile = lineOfFileTemp;
                break;
            }
            i++;
        }

        // Return the line of the file
        return lineOfFile;
    }

    /**
     * Return the number of lines of a file
     * 
     * @param file the file
     * @return the number of lines of the file
     */
    public int getNumberOfLinesOfFile(File file) throws Exception {

        // Get a buffer reader
        getBufferedWriter(file);

        // Read the file
        int numberOfLines = 0;
        while (lastFileReader.readLine() != null) {
            numberOfLines++;
        }

        // Return the number of lines of the file
        return numberOfLines;
    }
}
