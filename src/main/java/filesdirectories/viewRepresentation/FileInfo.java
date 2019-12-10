package filesdirectories.viewRepresentation;

public class FileInfo {

    private String filename;

    private String size;

    private long id;

    private String rootPath;

    private String rootDate;

    private boolean directory;

    public FileInfo(){}

    public FileInfo(String fileName, String size, long id, boolean directory) {
        this.filename = fileName;
        this.size = size;
        this.id = id;
        this.directory = directory;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
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

    public boolean isDirectory() {
        return directory;
    }

    public void setDirectory(boolean directory) {
        this.directory = directory;
    }
}
