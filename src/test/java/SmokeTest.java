import config.TestConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.util.function.UnaryOperator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class SmokeTest {

    private String path ="C:\\Users\\bksim\\Desktop";

    @Test
    public void checkLineParse() {

                UnaryOperator<String> parsedPath = x-> {
                    if(path.contains(File.separator)) {
                        return x;
                    } else if(path.contains("\\")) {
                        return x.replaceAll("\\\\", "/");
                    } else return x.replaceAll("/", "\\\\");
                };

            System.out.println(parsedPath.apply(path));



    }


}
