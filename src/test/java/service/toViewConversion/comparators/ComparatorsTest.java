package service.toViewConversion.comparators;

import config.TestConfig;
import filesdirectories.service.toViewConversion.comparators.ComparatorByName;
import filesdirectories.service.toViewConversion.comparators.ComparatorByNumbersInFilename;
import filesdirectories.viewRepresentation.FileInfo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ComparatorsTest {


    @Autowired
    private ComparatorByNumbersInFilename comparatorByNumbersInFilename;

    @Autowired
    private ComparatorByName comparatorByName;

    private List<FileInfo> testList1;

    private List<FileInfo> testList2;

    @Before
    public void setup() {
        testList1 = Arrays.asList(
                new FileInfo("abc", "125", 1, true),        //0
                new FileInfo("a123bc", "125", 1, true),     //1
                new FileInfo("a10bc", "125", 1, true),      //2
                new FileInfo("123456789", "125", 1, true),  //3
                new FileInfo("a123bc523", "125", 1, true),  //4
                new FileInfo("a123bc123", "125", 1, true),  //5
                new FileInfo("bcbga10bc456", "125", 1, true),//6
                new FileInfo("bcbga10bc", "125", 1, true)   //7
        );

        testList2 = Arrays.asList(
                new FileInfo("abc", "125", 1, true),        //0
                new FileInfo("bc", "125", 1, true),     //1
                new FileInfo("cd", "125", 1, true),      //2
                new FileInfo("de", "125", 1, true),  //3
                new FileInfo("ef", "125", 1, true),  //4
                new FileInfo("fg", "125", 1, true),  //5
                new FileInfo("gh", "125", 1, true),//6
                new FileInfo("hi", "125", 1, true)   //7
        );
    }

    @Test
    public void sortingTestInNumbers() {
        testList1.sort(comparatorByNumbersInFilename);
        assertEquals("abc", testList1.get(0).getFilename());
        assertEquals("a123bc123", testList1.get(5).getFilename());
        assertEquals("a123bc523", testList1.get(6).getFilename());
        assertEquals("123456789", testList1.get(7).getFilename());

    }

    @Test
    public void sortingTestAlphabeticallyAndNumbers() {
        testList1.sort(comparatorByName.thenComparing(comparatorByNumbersInFilename));
        testList1.forEach(x -> System.out.println(x.getFilename()));
    }


    @Test
    public void sortingTestAlphabetically() {
        testList2.sort(comparatorByName);
        assertEquals("abc", testList2.get(0).getFilename());
        assertEquals("fg", testList2.get(5).getFilename());
        assertEquals("gh", testList2.get(6).getFilename());
        assertEquals("hi", testList2.get(7).getFilename());
    }


    @Test
    public void sortingTestNumbersAndAlphabetically() {
        testList1.sort(comparatorByName.thenComparing(comparatorByNumbersInFilename));
        testList1.forEach(x-> System.out.println(x.getFilename()));
        assertEquals("123456789", testList1.get(0).getFilename());
        assertEquals("abc", testList1.get(1).getFilename());
        assertEquals("a10bc", testList1.get(2).getFilename());
        assertEquals("a123bc", testList1.get(3).getFilename());
        assertEquals("a123bc123", testList1.get(4).getFilename());
        assertEquals("a123bc523", testList1.get(5).getFilename());
        assertEquals("bcbga10bc", testList1.get(6).getFilename());
        assertEquals("bcbga10bc456", testList1.get(7).getFilename());
    }


}
