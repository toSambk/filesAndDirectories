package service;

import config.TestConfig;
import filesdirectories.entities.Directory;
import filesdirectories.repo.DirectoryRepo;
import filesdirectories.service.DirectoryService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystem;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class DirectoryServiceTest {

    @Autowired
    private DirectoryService directoryService;

    @Autowired
    private DirectoryRepo directoryRepo;

    private String path;

    private File file;

    private String sep = File.separator;

    @Before
    public void setup() throws IOException {
        file = new File("src" + sep + "test" + sep + "resources" + sep + "testFolder");

        path = file.getPath();
        System.out.println(path);
    }

    @Test
    public void addDirectoryTest() {
        directoryService.addNewDirectory(path);
        Directory foundRoot = directoryRepo.findByPath(path);
        assertNotNull(foundRoot);
        assertEquals(path, foundRoot.getPath());
        assertEquals(1, foundRoot.getFiles().size());
        assertEquals(1 , foundRoot.getDirectories().size());

    }

}
