package fr.astro.util.buffers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import fr.astro.util.Instantiable;

/**
 * FileWriterManagement, helps to manage file writers
 * 
 * @see Instantiable
 */
public class FileWriterManagement implements Instantiable {

    // Instance
    private static FileWriterManagement instance;

    // Optimisation : keep buffer in memory - TODO
    private BufferedWriter lastBufferedWriter;
    private File lastFile;
    private boolean isLastBufferedWriterAppend;

    /**
     * Constructor
     */
    private FileWriterManagement() {
    }

    /**
     * Return a FileWriter
     * Create a new FileWriter if instance is null
     * 
     * @return a FileWriter
     */
    public static FileWriterManagement getInstance() {
        if (instance == null) {
            instance = new FileWriterManagement();
        }
        return instance;
    }

    /**
     * Get a buffered writer
     * 
     * @param file
     * @throws Exception
     */
    public void getBufferedWriter(File file, boolean append) throws Exception {

        // // If the buffered writer is null or if the file is different or if the append
        // if (lastBufferedWriter == null || !file.equals(lastFile) || isLastBufferedWriterAppend != append) {
        //     lastBufferedWriter = new BufferedWriter(new FileWriter(file, append));

        //     // Save the file
        //     lastFile = file;
        //     isLastBufferedWriterAppend = append;
        // }

        // // Set buffer reader to the beginning of the file
        // lastBufferedWriter.flush();

        lastBufferedWriter = new BufferedWriter(new FileWriter(file, append));
    }

    /**
     * Append a text to a file
     * 
     * @param file the file
     * @param text the text to append
     * @return true if the text has been appended, false otherwise
     */
    public boolean appendTextToFile(File file, String text) throws Exception {

        // Create only if it doesn't exist
        FileManagement.getInstance().createFile(file);

        // Get the buffered writer
        getBufferedWriter(file, true);

        PrintWriter printWriter = new PrintWriter(lastBufferedWriter);

        printWriter.println(text);

        printWriter.close();

        return true;

    }

    /**
     * Append a text to a file
     * 
     * @param fileName the name of the file
     * @param text     the text to append
     * @return true if the text has been appended, false otherwise
     */
    public boolean appendTextToFile(String fileName, String text) throws Exception {
        return appendTextToFile(FileManagement.getInstance().getFile(fileName), text);
    }

    /**
     * Write a text to a file (overwrite)
     * 
     * @param file the file
     * @param text the text to write
     * @return true if the text has been written, false otherwise
     */
    public boolean writeTextToFile(File file, String text) throws Exception {

        // Create only if it doesn't exist
        FileManagement.getInstance().createFile(file);

        // Get the buffered writer
        getBufferedWriter(file, false);

        PrintWriter printWriter = new PrintWriter(lastBufferedWriter);

        printWriter.write(text);

        printWriter.close();

        return true;

    }

}
