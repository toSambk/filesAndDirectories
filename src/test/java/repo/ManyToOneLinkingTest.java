package repo;

import config.TestConfig;
import filesdirectories.entities.Directory;
import filesdirectories.entities._File;
import filesdirectories.repo.DirectoryRepo;
import filesdirectories.repo.FileRepo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ManyToOneLinkingTest {

    @Autowired
    private DirectoryRepo directoryRepo;

    @Autowired
    private FileRepo fileRepo;

    private Directory rootDir, childDir;

    private _File file;

    @Before
    public void setup() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        rootDir = new Directory("parent", dateFormat.format(new Date()));
        childDir = new Directory("child", dateFormat.format(new Date()));
        file = new _File(5);
        rootDir.setDirectories(Collections.singletonList(childDir));
        childDir.setParentDirectory(rootDir);
        childDir.setFiles(Collections.singletonList(file));
        file.setDirectory(childDir);
    }

    @Test
    public void saveHierarchy() {
        directoryRepo.save(rootDir);
//        directoryRepo.save(childDir);
//        fileRepo.save(file);

        Directory foundParent = directoryRepo.findById(rootDir.getId());

        assertNotNull(foundParent);
        assertEquals(1, foundParent.getDirectories().size());
        assertEquals(childDir.getId(), foundParent.getDirectories().get(0).getId());
        assertEquals(file.getId(), foundParent.getDirectories().get(0).getFiles().get(0).getId());

        _File foundFile = fileRepo.findById(file.getId());
        assertNotNull(foundFile);
        assertEquals(childDir.getId(), foundFile.getDirectory().getId());

    }



}
