package filesdirectories.service.toViewConversion.visitors;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

@Component
public class FileVisitor extends SimpleFileVisitor<Path> {

    private long directoriesCount = 0;
    private long filesCount = 0;
    private long sizeInBytes = 0;

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        if(attrs.isDirectory()) {
            directoriesCount++;
        }

        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

        if(attrs.isRegularFile()) {
            filesCount++;
            sizeInBytes += attrs.size();
        }
        return FileVisitResult.CONTINUE;

    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        return FileVisitResult.SKIP_SUBTREE;
    }

    public long getDirectoriesCount() {
        return directoriesCount;
    }

    public long getFilesCount() {
        return filesCount;
    }

    public long getSizeInBytes() {
        return sizeInBytes;
    }
}
