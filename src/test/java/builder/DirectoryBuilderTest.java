package builder;

import config.TestConfig;
import filesdirectories.builder.DirectoryBuilder;
import filesdirectories.entities.Directory;
import filesdirectories.exceptions.FileNotExistsException;
import filesdirectories.exceptions.NotDirectoryException;
import org.junit.Assert;
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
    private DirectoryBuilder directoryBuilder;

    @Test
    public void successfulBuild() {

        String path = "src\\test\\resources\\testFolder";
        File file = new File(path);
        path = file.getAbsolutePath();

        Directory built = directoryBuilder.build(file);

        assertNotNull(built);
        assertEquals(path, built.getPath());
        assertEquals(built.getDirectories().size(), 1);
        assertEquals(built.getFiles().size(), 1);

    }

    @Test
    public void fileNotFoundTest() {
        String path = "src\\test\\resources\\testFolderNew";
        File file = new File(path);
        try {
            Directory built = directoryBuilder.build(file);
            fail();
        } catch (FileNotExistsException e) {
        }
    }

    @Test
    public void fileNotDirectoryTest() {
        String path = "src\\test\\resources\\testFolder\\testFile.txt";
        File file = new File(path);
        try {
            Directory built = directoryBuilder.build(file);
            fail();
        } catch (NotDirectoryException e) {
        }
    }


}
