package service.toViewConversion;

import config.TestConfig;
import filesdirectories.service.toViewConversion.ByteConverter;
import filesdirectories.service.builder.DirectoryBuilder;
import filesdirectories.service.toViewConversion.ViewDirectoryInfoConverter;
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
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ViewDirectoryInfoConverterTest {

    @Autowired
    private DirectoryBuilder directoryBuilder;

    @Autowired
    private ViewDirectoryInfoConverter viewDirectoryInfoConverter;

    @Autowired
    private ByteConverter converter;

    private String sep = File.separator;

    @Test
    public void successfulBuild() {
        String path = "src" + sep + "test" + sep + "resources" + sep + "testFolder";
        File file = new File(path);
        path = file.getAbsolutePath();
        Directory built = directoryBuilder.build(file);

        assertNotNull(built);
        assertEquals(path, built.getPath());
        assertEquals(built.getDirectories().size(), 1);
        assertEquals(built.getFiles().size(), 1);

        try {
            DirectoryInfo result = viewDirectoryInfoConverter.convert(built);
            assertNotNull(result);
            assertEquals(1, result.getNumberOfDirectories());
            assertEquals(1, result.getNumberOfFiles());
        } catch (CannotReachFileAttributes e) {
            fail();
        }

    }


}
