package filesdirectories.builder;

import filesdirectories.entities.Directory;
import filesdirectories.entities._File;
import filesdirectories.exceptions.NotDirectoryException;
import org.springframework.stereotype.Component;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class DirectoryBuilder {


    public Directory build(File root) {

        if (!root.isDirectory()) throw new NotDirectoryException("Добавляемый файл не является директорией");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        Directory rootDir = new Directory(root.getAbsolutePath(), dateFormat.format(new Date()));
        rootDir.setRoot(true);
        List<Directory> childDirs = new ArrayList<>();
        List<_File> childFiles = new ArrayList<>();

        for (File file : root.listFiles()) {
            if (file.isDirectory()) {
                Directory curDir = new Directory(file.getAbsolutePath(), dateFormat.format(new Date()));
                curDir.setParentDirectory(rootDir);
                childDirs.add(curDir);
            } else {
                _File curFile = new _File(file.length(), file.getAbsolutePath());
                curFile.setDirectory(rootDir);
                childFiles.add(curFile);
            }
        }

        rootDir.setDirectories(childDirs);
        rootDir.setFiles(childFiles);
        return rootDir;

    }


    public Directory build(Directory existingChildDirectory) {

        Directory parentDirectory = existingChildDirectory.getParentDirectory();

        File childRootFile = new File(existingChildDirectory.getPath());

        List<Directory> childDirs = new ArrayList<>();
        List<_File> childFiles = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");

        for (File file : childRootFile.listFiles()) {
            if (file.isDirectory()) {
                Directory curDir = new Directory(file.getAbsolutePath(), dateFormat.format(new Date()));
                curDir.setParentDirectory(existingChildDirectory);
                childDirs.add(curDir);
            } else {
                _File curFile = new _File(file.length(), file.getAbsolutePath());
                curFile.setDirectory(existingChildDirectory);
                childFiles.add(curFile);
            }
        }

        existingChildDirectory.setDirectories(childDirs);
        existingChildDirectory.setFiles(childFiles);
        existingChildDirectory.setRoot(true);
        List<Directory> directories = parentDirectory.getDirectories();
        Directory directory = directories.stream().filter(x -> x.getId() == existingChildDirectory.getId()).findFirst().get();
        int i = directories.indexOf(directory);
        directories.set(i, existingChildDirectory);
        parentDirectory.setDirectories(directories);

        return parentDirectory;
    }


}
