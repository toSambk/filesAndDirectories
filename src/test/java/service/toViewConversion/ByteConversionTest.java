package service.toViewConversion;

import config.TestConfig;
import filesdirectories.service.toViewConversion.ByteConverter;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ByteConversionTest {

    @Autowired
    private ByteConverter converter;

    @Test
    public void testConversion() {
        assertEquals("1Kb", converter.convert(1_024L));
        assertEquals("1Mb", converter.convert(1_048_576L));
        assertEquals("1Gb", converter.convert(1_073_741_824L));
        assertEquals("1Tb", converter.convert(1_099_511_627_776L));
    }


}
