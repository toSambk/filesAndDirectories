package filesdirectories.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "directories")
public class Directory {

    public Directory(){};

    public Directory(String path, String date) {
        this.path = path;
        this.date = date;
    }

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String date;

    @Column(nullable = false)
    private String path;

    @OneToMany(mappedBy = "directory", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<_File> files;

    @OneToMany(mappedBy = "parentDirectory", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Directory> directories;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "parent_directory_id")
    private Directory parentDirectory;

    @Column
    private boolean root;




    //Getters / Setters
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

    public List<_File> getFiles() {
        return files;
    }

    public void setFiles(List<_File> files) {
        this.files = files;
    }

    public List<Directory> getDirectories() {
        return directories;
    }

    public void setDirectories(List<Directory> directories) {
        this.directories = directories;
    }

    public Directory getParentDirectory() {
        return parentDirectory;
    }

    public void setParentDirectory(Directory parentDirectory) {
        this.parentDirectory = parentDirectory;
    }

    public long getId() {
        return id;
    }

    public boolean isRoot() {
        return root;
    }

    public void setRoot(boolean root) {
        this.root = root;
    }
}
