package filesdirectories.forms;

import filesdirectories.entities.Directory;

import java.util.Date;

public class DirectoryViews {

    private Date date;

    private String path;

    private int numberOfDirectories;

    private int numberOfFiles;

    private double sizeOfFiles;

    private int directoryId;



    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getNumberOfDirectories() {
        return numberOfDirectories;
    }

    public void setNumberOfDirectories(int numberOfDirectories) {
        this.numberOfDirectories = numberOfDirectories;
    }

    public int getNumberOfFiles() {
        return numberOfFiles;
    }

    public void setNumberOfFiles(int numberOfFiles) {
        this.numberOfFiles = numberOfFiles;
    }

    public double getSizeOfFiles() {
        return sizeOfFiles;
    }

    public void setSizeOfFiles(double sizeOfFiles) {
        this.sizeOfFiles = sizeOfFiles;
    }

    public int getDirectoryId() {
        return directoryId;
    }

    public void setDirectoryId(int directoryId) {
        this.directoryId = directoryId;
    }
}
