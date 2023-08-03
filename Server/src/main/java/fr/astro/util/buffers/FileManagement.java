package fr.astro.util.buffers;

import java.io.File;
import java.util.List;

import fr.astro.util.Instantiable;

/**
 * FileManagement, helps to manage files
 * 
 * @see Instantiable
 */
public class FileManagement implements Instantiable {

    // Instance
    private static FileManagement instance;

    /**
     * Constructor
     */
    private FileManagement() {
    }

    /**
     * Return a FileManagement
     * Create a new FileManagement if instance is null
     * 
     * @return a FileManagement
     */
    public static FileManagement getInstance() {
        if (instance == null) {
            instance = new FileManagement();
        }
        return instance;
    }

    /**
     * Return the path of the file
     * 
     * @param fileName the name of the file
     * @return the path of the file
     */
    public String getFilePath(String fileName) {
        return String.format("src/main/resources/files/%s", fileName);
    }

    /**
     * Return the path of the file
     * 
     * @param file
     * @return the path of the file
     */
    public String getFilePath(File file) {
        return file.getPath();
    }

    /**
     * Return the file
     * 
     * @param fileName
     * @return the file
     */
    public File getFile(String fileName) {
        return new File(getFilePath(fileName));
    }

    /**
     * Check if the file exists
     * 
     * @param fileName the name of the file
     * @return true if the file exists, false otherwise
     */
    public boolean fileExists(String fileName) {
        return fileExists(getFile(fileName));
    }

    /**
     * Check if the file exists
     * 
     * @param file
     * @return
     * @throws Exception
     */
    public boolean fileExists(File file) {
        return file.exists();
    }

    /**
     * Create a file
     * 
     * @param fileName the name of the file
     * @return true if the file has been created, false otherwise
     */
    public boolean createFile(String fileName) throws Exception {
        return createFile(getFile(fileName));
    }

    /**
     * Create a file
     * 
     * @param file
     * @return true if the file has been created, false otherwise
     */
    public boolean createFile(File file) throws Exception {
        if (!fileExists(file)) {
            return file.createNewFile();
        }
        return false;
    }

    /**
     * Delete a file
     * 
     * @param fileName the name of the file
     * @return true if the file has been deleted, false otherwise
     */
    public boolean deleteFile(String fileName) {
        return deleteFile(getFile(fileName));
    }

    /**
     * Delete a file
     * 
     * @param file
     * @return true if the file has been deleted, false otherwise
     */
    public boolean deleteFile(File file) {
        if (fileExists(file)) {
            return file.delete();
        }
        return false;
    }

    /**
     * Create a folder
     * 
     * @param folderName the name of the folder
     * @return true if the folder has been created, false otherwise
     */
    public boolean createFolder(String folderName) {
        return createFolder(getFile(folderName));
    }

    /**
     * Create a folder
     * 
     * @param folder
     * @return true if the folder has been created, false otherwise
     */
    public boolean createFolder(File folder) {
        if (!folderExists(folder)) {
            return folder.mkdir();
        }
        return false;
    }

    /**
     * Delete a folder
     * 
     * @param folderName the name of the folder
     * @return true if the folder has been deleted, false otherwise
     */
    public boolean deleteFolder(String folderName) {
        return deleteFolder(getFile(folderName));
    }

    /**
     * Delete a folder
     * 
     * @param folder
     * @return true if the folder has been deleted, false otherwise
     */
    public boolean deleteFolder(File folder) {
        if (folderExists(folder)) {
            return folder.delete();
        }
        return false;
    }

    /**
     * Check if the folder exists
     * 
     * @param folderName the name of the folder
     * @return true if the folder exists, false otherwise
     */
    public boolean folderExists(String folderName) {
        return folderExists(getFile(folderName));
    }

    /**
     * Check if the folder exists
     * 
     * @param folder
     * @return true if the folder exists, false otherwise
     */
    public boolean folderExists(File folder) {
        return folder.exists();
    }

    /**
     * Return the list of files in a folder
     * 
     * @param folderName the name of the folder
     * @return the list of files in a folder
     */
    public String[] getFilesInFolder(String folderName) {
        return getFilesInFolder(getFile(folderName));
    }

    /**
     * Return the list of files in a folder
     * 
     * @param folder
     * @return the list of files in a folder
     */
    public String[] getFilesInFolder(File folder) {
        return getFilesInFolder(folder, "");
    }

    /**
     * Return the list of folders in a folder
     * 
     * @param folder
     * @return the list of folders in a folder
     */
    public String[] getFoldersInFolder(File folder) {
        return folder.list((dir, name) -> new File(dir, name).isDirectory());
    }

    /**
     * Return the list of files in a folder
     * 
     * @param folderName the name of the folder
     * @param extension  the extension of the files
     * @return the list of files in a folder
     */
    public String[] getFilesInFolder(String folderName, String extension) {
        return getFilesInFolder(getFile(folderName), extension);
    }

    /**
     * Return the list of files in a folder
     * 
     * @param folder
     * @param extension the extension of the files
     * @return the list of files in a folder
     */
    public String[] getFilesInFolder(File folder, String extension) {
        return folder.list((dir, name) -> name.endsWith(extension) && new File(dir, name).isFile());
    }

    /**
     * Return the list of folders in a folder
     * 
     * @param folderName the name of the folder
     * @return the list of folders in a folder
     */
    public String[] getFoldersInFolder(String folderName) {
        return getFoldersInFolder(getFile(folderName));
    }

    /**
     * Add a line to a file
     * 
     * @param fileName the name of the file
     * @param line     the line to add
     * @return true if the line has been added, false otherwise
     */
    public boolean addLineToFile(String fileName, String line) throws Exception {
        if (fileExists(fileName)) {
            return FileWriterManagement.getInstance().writeTextToFile(getFile(fileName), line);
        }
        return false;
    }

    /**
     * Write a line to a file (overwrite)
     * 
     * @param fileName the name of the file
     * @param line     the line to write
     * @return true if the line has been written, false otherwise
     */
    public boolean writeLineToFile(String fileName, String line) throws Exception {
        File file = getFile(fileName);

        if (fileExists(file)) {
            return FileWriterManagement.getInstance().writeTextToFile(file, line);
        }
        return false;
    }

    /**
     * Read a line from a file
     * 
     * @param fileName   the name of the file
     * @param lineNumber the number of the line
     * @return the line
     */
    public String readLineFromFile(String fileName, int lineNumber) throws Exception {
        File file = getFile(fileName);

        if (fileExists(file)) {
            return FileReaderManagement.getInstance().getLineOfFile(file, lineNumber);
        }

        return null;
    }

    /**
     * Read all lines from a file
     * 
     * @param fileName the name of the file
     * @return the lines
     */
    public List<String> readLinesFromFile(String fileName) throws Exception {
        File file = getFile(fileName);

        if (fileExists(file)) {
            return FileReaderManagement.getInstance().getFileContent(file);
        }
        return null;
    }

}
