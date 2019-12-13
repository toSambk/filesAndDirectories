package filesdirectories.service;

import filesdirectories.service.builder.DirectoryBuilder;
import filesdirectories.entities.Directory;
import filesdirectories.repo.DirectoryRepo;
import filesdirectories.service.toViewConversion.ViewDirectoryInfoConverter;
import filesdirectories.service.toViewConversion.ViewFileInfoConverter;
import filesdirectories.viewRepresentation.DirectoryInfo;
import filesdirectories.viewRepresentation.FileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

@Service
public class DirectoryService {

    @Autowired
    private DirectoryRepo directoryRepo;

    @Autowired
    private DirectoryBuilder directoryBuilder;

    @Autowired
    private ViewDirectoryInfoConverter viewDirectoryInfoConverter;

    @Autowired
    private ViewFileInfoConverter viewFileInfoConverter;

    public void addNewDirectory(String path) {

        UnaryOperator<String> manageSeparators = x-> {
            if(path.contains(File.separator)) {
                return x;
            } else if(path.contains("\\")) {
                return x.replaceAll("\\\\", "/");
            } else return x.replaceAll("/", "\\\\");
        };

        Directory buildDir = directoryBuilder.build(new File(manageSeparators.apply(path)));
        directoryRepo.save(buildDir);
    }

    public void makeRootAddedDirectory(Directory found) {

        Directory parentDirectory = directoryBuilder.build(found);
        directoryRepo.save(parentDirectory);

    }

    public List<DirectoryInfo> getDirectoryInfo() {
        return directoryRepo.findAllByRootTrue().stream()
                .map(x -> viewDirectoryInfoConverter.convert(x))
                .collect(Collectors.toList());
    }

    public List<FileInfo> getFilesInfo(long rootDirId) {
        Directory found = directoryRepo.findById(rootDirId);
        return viewFileInfoConverter.convert(found);
    }

}
