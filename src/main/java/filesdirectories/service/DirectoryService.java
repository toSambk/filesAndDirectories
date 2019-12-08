package filesdirectories.service;

import filesdirectories.builder.DirectoryBuilder;
import filesdirectories.entities.Directory;
import filesdirectories.repo.DirectoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class DirectoryService {

    @Autowired
    private DirectoryRepo directoryRepo;

    @Autowired
    private DirectoryBuilder builder;

    public void addDirectory(String path) {
        Directory buildDir = builder.build(new File(path));
        directoryRepo.save(buildDir);
    }



}
