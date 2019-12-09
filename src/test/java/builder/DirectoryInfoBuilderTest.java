package builder;

import config.TestConfig;
import filesdirectories.builder.Converter;
import filesdirectories.builder.DirectoryBuilder;
import filesdirectories.builder.DirectoryInfoBuilder;
import filesdirectories.entities.Directory;
import filesdirectories.exceptions.CannotReachFileAttributes;
import filesdirectories.viewRepresentation.DirectoryInfo;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;

import static org.junit.Assert.*;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class DirectoryInfoBuilderTest {

    @Autowired
    private DirectoryBuilder directoryBuilder;

    @Autowired
    private DirectoryInfoBuilder directoryInfoBuilder;

    @Autowired
    private Converter converter;

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

        try {
            DirectoryInfo result = directoryInfoBuilder.build(built);
            assertNotNull(result);
            assertEquals(1, result.getNumberOfDirectories());
            assertEquals(1, result.getNumberOfFiles());
        } catch (CannotReachFileAttributes e) {
            fail();
        }

    }

    @Test
    public void byteConversionTest() {

        String result = converter.byteConversion(1000000);
        System.out.println(result);

    }


}
