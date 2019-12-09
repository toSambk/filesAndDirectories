package filesdirectories.service;

import filesdirectories.builder.Converter;
import filesdirectories.builder.DirectoryBuilder;
import filesdirectories.builder.DirectoryInfoBuilder;
import filesdirectories.entities.Directory;
import filesdirectories.repo.DirectoryRepo;
import filesdirectories.viewRepresentation.DirectoryInfo;
import filesdirectories.viewRepresentation.FileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
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

    @Autowired
    private Converter converter;

    public void addDirectory(String path) {
        Directory buildDir = directoryBuilder.build(new File(path));
        directoryRepo.save(buildDir);
    }

    public List<DirectoryInfo> getDirectoryInfo() {
        return directoryRepo.findAllByRootTrue().stream()
                .map(x -> directoryInfoBuilder.build(x))
                .collect(Collectors.toList());
    }

    public List<FileInfo> getFilesInfo(long rootDirId) {

        List<FileInfo> result = new ArrayList<>();
        Directory found = directoryRepo.findById(rootDirId);
        found.getDirectories()
                .forEach(x-> result.add(new FileInfo(x.getPath(), "<DIR>", x.getId())));
        found.getFiles()
                .forEach(x-> result.add(new FileInfo(x.getPath(), converter.byteConversion(x.getSize()), x.getId())));

        return result;
    }

}
