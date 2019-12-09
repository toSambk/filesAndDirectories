package filesdirectories.entities;

import javax.persistence.*;

@Entity
@Table(name = "files")
public class _File {

    public _File(){}

    public _File(long size, String path) {
        this.size = size;
        this.path = path;
    }

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private long size;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "directory_id")
    private Directory directory;

    @Column
    private String path;



    // Getters / Setters
    public long getId() {
        return id;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public Directory getDirectory() {
        return directory;
    }

    public void setDirectory(Directory directory) {
        this.directory = directory;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
