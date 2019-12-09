package filesdirectories.builder;

import filesdirectories.entities.Directory;
import filesdirectories.exceptions.CannotReachFileAttributes;
import filesdirectories.viewRepresentation.DirectoryInfo;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;

@Component
public class DirectoryInfoBuilder implements Builder<DirectoryInfo, Directory> {

    @Override
    public DirectoryInfo build(Directory directory) {

        FileVisitor visitor = new FileVisitor();
        try {
            Files.walkFileTree(Paths.get(directory.getPath()), visitor);
        } catch (IOException e) {
            throw new CannotReachFileAttributes();
        }

        return new DirectoryInfo(directory, visitor.getDirectoriesCount() - 1,
                visitor.getFilesCount(), byteConversion(visitor.getSizeInBytes()));

    }

    public String byteConversion(long sizeInBytes) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        double result = sizeInBytes;
        if(result < 1024) return decimalFormat.format(result) + "b";
        result /= 1024;
        if(result < 1024) return decimalFormat.format(result) + "Kb";
        result /= 1024;
        if(result < 1024) return decimalFormat.format(result) + "Mb";
        result /= 1024;
        if(result < 1024) return decimalFormat.format(result) + "Gb";
        result /= 1024;
        return decimalFormat.format(result) + "Tb";

    }

}



