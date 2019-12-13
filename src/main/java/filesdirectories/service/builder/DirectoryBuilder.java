package filesdirectories.service.builder;

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

    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");

    public Directory build(File root) {

        if (!root.isDirectory()) throw new NotDirectoryException("Добавляемый файл не является директорией");

        Directory rootDir = new Directory(root.getPath(), dateFormat.format(new Date()));
        rootDir.setRoot(true);

        ScanResult result = new ScanResult(root, rootDir);

        rootDir.setDirectories(result.getChildDirs());
        rootDir.setFiles(result.getChildFiles());
        return rootDir;

    }

    public Directory build(Directory existingChildDirectory) {

        Directory parentDirectory = existingChildDirectory.getParentDirectory();

        File childRootFile = new File(existingChildDirectory.getPath());

        ScanResult result = new ScanResult(childRootFile, existingChildDirectory);

        existingChildDirectory.setDirectories(result.getChildDirs());
        existingChildDirectory.setFiles(result.getChildFiles());
        existingChildDirectory.setRoot(true);
        List<Directory> directories = parentDirectory.getDirectories();
        Directory directory = directories.stream().filter(x -> x.getId() == existingChildDirectory.getId()).findFirst().get();
        int i = directories.indexOf(directory);
        directories.set(i, existingChildDirectory);
        parentDirectory.setDirectories(directories);

        return parentDirectory;
    }

    class ScanResult {

        private List<Directory> childDirs;

        private List<_File> childFiles;

        private File rootFile;

        private Directory rootDir;

        ScanResult(File rootFile, Directory rootDir) {
            this.childDirs = new ArrayList<>();
            this.childFiles = new ArrayList<>();
            this.rootFile = rootFile;
            this.rootDir = rootDir;
            calculate();
        }

        private void calculate() {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
            for (File file : rootFile.listFiles()) {
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
        }

        List<Directory> getChildDirs() {
            return childDirs;
        }

        List<_File> getChildFiles() {
            return childFiles;
        }

    }


}
