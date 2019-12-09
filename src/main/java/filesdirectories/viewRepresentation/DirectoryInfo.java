package filesdirectories.viewRepresentation;

import filesdirectories.entities.Directory;

public class DirectoryInfo {

    private String date;

    private String path;

    private long numberOfDirectories;

    private long numberOfFiles;

    private String sizeOfFiles;

    private long directoryId;


    public DirectoryInfo(Directory root, long numberOfDirectories, long numberOfFiles, String sizeOfFiles) {
        this.date = root.getDate();
        this.directoryId = root.getId();
        this.path = root.getPath();
        this.numberOfDirectories = numberOfDirectories;
        this.numberOfFiles = numberOfFiles;
        this.sizeOfFiles = sizeOfFiles;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public long getNumberOfDirectories() {
        return numberOfDirectories;
    }

    public void setNumberOfDirectories(int numberOfDirectories) {
        this.numberOfDirectories = numberOfDirectories;
    }

    public long getNumberOfFiles() {
        return numberOfFiles;
    }

    public void setNumberOfFiles(int numberOfFiles) {
        this.numberOfFiles = numberOfFiles;
    }

    public String getSizeOfFiles() {
        return sizeOfFiles;
    }

    public void setSizeOfFiles(String sizeOfFiles) {
        this.sizeOfFiles = sizeOfFiles;
    }

    public long getDirectoryId() {
        return directoryId;
    }

    public void setDirectoryId(long directoryId) {
        this.directoryId = directoryId;
    }
}
