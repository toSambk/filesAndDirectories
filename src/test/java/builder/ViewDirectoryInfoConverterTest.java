package builder;

import config.TestConfig;
import filesdirectories.service.builder.ByteConverter;
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

@Ignore
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
            DirectoryInfo result = viewDirectoryInfoConverter.convert(built);
            assertNotNull(result);
            assertEquals(1, result.getNumberOfDirectories());
            assertEquals(1, result.getNumberOfFiles());
        } catch (CannotReachFileAttributes e) {
            fail();
        }

    }

    @Test
    public void byteConversionTest() {

        String result = converter.convert(1000000L);
        System.out.println(result);

        List<String> allMatches = new ArrayList<String>();
        Matcher m = Pattern.compile("[0-9]*")
                .matcher("gbrt8t1n2y8n5ty2ny8ny2n8t28yn28282811n4m1,,14/1712,828/");
        while (m.find()) {
            if (!m.group().isEmpty()) allMatches.add(m.group());
        }

        int i = 0;

    }



}
