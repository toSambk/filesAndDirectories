package filesdirectories.viewRepresentation;

public class FileInfo {

    private String fileName;

    private String size;

    private long id;

    private String rootPath;

    private String rootDate;

    public FileInfo(){}

    public FileInfo(String fileName, String size, long id) {
        this.fileName = fileName;
        this.size = size;
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRootPath() {
        return rootPath;
    }

    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    public String getRootDate() {
        return rootDate;
    }

    public void setRootDate(String rootDate) {
        this.rootDate = rootDate;
    }
}
