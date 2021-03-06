package filesdirectories.service.toViewConversion;

import filesdirectories.entities.Directory;
import filesdirectories.exceptions.CannotReachFileAttributes;
import filesdirectories.service.toViewConversion.visitors.FileVisitor;
import filesdirectories.viewRepresentation.DirectoryInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class ViewDirectoryInfoConverter implements Converter<DirectoryInfo, Directory> {

    @Autowired
    private ByteConverter converter;

    @Autowired
    private FileVisitor visitor;

    @Override
    public DirectoryInfo convert(Directory directory) {

        try {
            Files.walkFileTree(Paths.get(directory.getPath()), visitor);
        } catch (IOException e) {
            throw new CannotReachFileAttributes();
        }

        return new DirectoryInfo(directory, visitor.getDirectoriesCount() - 1,
                visitor.getFilesCount(), converter.convert(visitor.getSizeInBytes()));

    }



}



