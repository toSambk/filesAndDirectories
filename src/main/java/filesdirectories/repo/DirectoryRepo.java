package filesdirectories.repo;

import filesdirectories.entities.Directory;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DirectoryRepo extends PagingAndSortingRepository<Directory, Long> {

    Directory save(Directory directory);

    Directory findById(long id);

    List<Directory> findByParentDirectoryId(long parentId);

    Directory findByPath(String path);

    List<Directory> findAll();

    void deleteById(long id);

    void deleteAll();

    List<Directory> findAllByRootTrue();

}
