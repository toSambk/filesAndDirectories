package filesdirectories.service;

import filesdirectories.builder.DirectoryBuilder;
import filesdirectories.builder.DirectoryInfoBuilder;
import filesdirectories.entities.Directory;
import filesdirectories.repo.DirectoryRepo;
import filesdirectories.viewRepresentation.DirectoryInfo;
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
    private DirectoryInfoBuilder directoryInfoBuilder;

    public void addDirectory(String path) {

        Directory buildDir = directoryBuilder.build(new File(path));
        directoryRepo.save(buildDir);

    }

    public List<DirectoryInfo> getDirectoryInfo() {

        return directoryRepo.findAllByRootTrue().stream()
                .map(x -> directoryInfoBuilder.build(x))
                .collect(Collectors.toList());

    }

}
