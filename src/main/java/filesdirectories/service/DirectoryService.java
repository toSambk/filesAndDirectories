package filesdirectories.service;

import filesdirectories.builder.DirectoryBuilder;
import filesdirectories.builder.ViewDirectoryInfoConverter;
import filesdirectories.builder.ViewFileInfoConverter;
import filesdirectories.entities.Directory;
import filesdirectories.repo.DirectoryRepo;
import filesdirectories.viewRepresentation.DirectoryInfo;
import filesdirectories.viewRepresentation.FileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
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
        Directory buildDir = directoryBuilder.build(new File(path));
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
