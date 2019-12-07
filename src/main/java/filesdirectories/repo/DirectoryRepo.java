package filesdirectories.repo;

import filesdirectories.entities.Directory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectoryRepo extends PagingAndSortingRepository<Directory, Long> {

    Directory save(Directory directory);

    Directory findById(long id);

    void deleteById(long id);

    void deleteAll();

}
