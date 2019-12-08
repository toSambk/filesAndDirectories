package filesdirectories.builder;

import filesdirectories.entities.Directory;
import filesdirectories.entities._File;
import filesdirectories.exceptions.FileNotExistsException;
import filesdirectories.exceptions.NotDirectoryException;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class DirectoryBuilder implements Builder<Directory> {

    @Override
    public Directory build(File root) {

        if (!root.exists()) throw new FileNotExistsException("Добавляемой директории не существует");
        if (!root.isDirectory()) throw new NotDirectoryException("Добавляемый файл не является директорией");

        Directory rootDir = new Directory(root.getAbsolutePath(), new Date());
        List<Directory> childDirs = new ArrayList<>();
        List<_File> childFiles = new ArrayList<>();

        for (File file : root.listFiles()) {
            if (file.isDirectory()) {
                Directory curDir = new Directory(file.getAbsolutePath(), new Date());
                curDir.setParentDirectory(rootDir);
                childDirs.add(curDir);
            } else {
                _File curFile = new _File(file.length());
                curFile.setDirectory(rootDir);
                childFiles.add(curFile);
            }
        }

        rootDir.setDirectories(childDirs);
        rootDir.setFiles(childFiles);

        return rootDir;
    }


}
