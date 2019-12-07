package filesdirectories.repo;

import filesdirectories.entities._File;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepo extends PagingAndSortingRepository<_File, Long> {

    _File save(_File directory);

    _File findById(long id);

    void deleteById(long id);

    void deleteAll();

}
