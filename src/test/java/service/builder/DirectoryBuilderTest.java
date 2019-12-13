package service.builder;

import config.TestConfig;
import filesdirectories.entities.Directory;
import filesdirectories.service.builder.DirectoryBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.io.File;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class DirectoryBuilderTest {

    @Autowired
    private DirectoryBuilder builder;

    private String sep = File.separator;

    private File file;

    private String path;

    @Before
    public void setup() {
        path = "src" + sep + "test" + sep + "resources" + sep + "testFolder";
        file = new File(path);
    }

    @Test
    public void createNewDirectoryEntity() {
        Directory build = builder.build(file);
        assertNotNull(build);
        assertEquals(1, build.getDirectories().size());
        assertEquals(1, build.getFiles().size());
        assertEquals(path + sep + "testFolderChild", build.getDirectories().get(0).getPath());
        assertEquals(path + sep + "testFile.txt", build.getFiles().get(0).getPath());
        assertNull(build.getDirectories().get(0).getFiles());
    }

    @Test
    public void addGrandchildDirectoryFilesToExistingDirectory() {
        Directory parentDirectory = builder.build(file);
        Directory childDirectory = parentDirectory.getDirectories().get(0);
        Directory parentDirectoryExtended = builder.build(childDirectory);
        assertNotNull(parentDirectoryExtended);
        assertEquals(1, parentDirectoryExtended.getDirectories().size());
        assertEquals(1, parentDirectoryExtended.getFiles().size());
        assertEquals(path + sep + "testFolderChild", parentDirectoryExtended.getDirectories().get(0).getPath());
        assertEquals(path + sep + "testFile.txt", parentDirectoryExtended.getFiles().get(0).getPath());
        assertNotNull(parentDirectoryExtended.getDirectories().get(0).getFiles());
        assertEquals(path + sep + "testFolderChild" + sep + "testFile1.txt",
                parentDirectoryExtended.getDirectories().get(0).getFiles().get(0).getPath());

    }


}
