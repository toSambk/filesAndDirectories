package service;

import config.TestConfig;
import filesdirectories.entities.Directory;
import filesdirectories.repo.DirectoryRepo;
import filesdirectories.service.DirectoryService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;

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

    @Before
    public void setup() {
        path = new File("src\\test\\resources\\testFolder").getAbsolutePath();
    }

    @Test
    public void addDirectoryTest() {
        directoryService.addNewDirectory(path);
        Directory foundRoot = directoryRepo.findByPath(path);
        assertNotNull(foundRoot);
        assertEquals(path, foundRoot.getPath());
        assertEquals(1 , foundRoot.getDirectories().size());
        assertEquals(1, foundRoot.getFiles().size());

        path = "C:\\Users\\bksim\\Desktop\\Смородина";

        directoryService.addNewDirectory(path);


    }

}
