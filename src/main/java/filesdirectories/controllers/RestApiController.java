package filesdirectories.controllers;

import filesdirectories.service.DirectoryService;
import filesdirectories.viewRepresentation.FileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestApiController {

    @Autowired
    private DirectoryService directoryService;

    @GetMapping("api/files/find")
    public List<FileInfo> getDetailedDirectoryInfo (@RequestParam("rootDirId") long rootDirId) {
        return directoryService.getFilesInfo(rootDirId);
    }

}
