package filesdirectories.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "directories")
public class Directory {

    public Directory(){};

    public Directory(String path, Date date) {
        this.path = path;
        this.date = date;
    }

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Column(nullable = false)
    private String path;

    @OneToMany(mappedBy = "directory", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<_File> files;

    @OneToMany(mappedBy = "parentDirectory", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Directory> directories;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "parent_directory_id")
    private Directory parentDirectory;



    //Getters / Setters
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
}
