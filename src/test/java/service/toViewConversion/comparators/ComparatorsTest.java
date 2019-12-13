package service.toViewConversion.comparators;

import config.TestConfig;
import filesdirectories.service.toViewConversion.comparators.ComparatorByName;
import filesdirectories.service.toViewConversion.comparators.ComparatorByNumbersInFilename;
import filesdirectories.viewRepresentation.FileInfo;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ComparatorsTest {


    @Autowired
    private ComparatorByNumbersInFilename comparatorByNumbersInFilename;

    @Autowired
    private ComparatorByName comparatorByName;

    List<FileInfo> testList;

    @Before
    public void setup() {

        testList = Arrays.asList(
                new FileInfo("abc", "125", 1, true),
                new FileInfo("a123bc", "125", 1, true),
                new FileInfo("a10bc", "125", 1, true),
                new FileInfo("123456789", "125", 1, true),
                new FileInfo("a123bc523", "125", 1, true),
                new FileInfo("a123bc123", "125", 1, true),
                new FileInfo("bcbga10bc456", "125", 1, true),
                new FileInfo("bcbga10bc", "125", 1, true)

                                                                            );
    }

    @Test
    public void sortingTestInNumbers() {

        testList.sort(comparatorByNumbersInFilename);
        testList.forEach(x-> System.out.println(x.getFilename()));

    }

    @Test
    public void sortingTestAlphabeticallyAndNumbers() {
        testList.sort(comparatorByName.thenComparing(comparatorByNumbersInFilename));
        testList.forEach(x-> System.out.println(x.getFilename()));
    }


    @Test
    public void sortingTestAlphabetically() {
        testList.sort(comparatorByName);
        testList.forEach(x-> System.out.println(x.getFilename()));
    }


    @Test
    @Ignore
    public void sortingTestNumbersAndAlphabetically() {
        testList.sort(comparatorByNumbersInFilename.thenComparing(comparatorByName));
        testList.forEach(x-> System.out.println(x.getFilename()));
    }


}
