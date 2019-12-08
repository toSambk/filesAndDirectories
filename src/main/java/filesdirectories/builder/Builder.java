package filesdirectories.builder;

import java.io.File;

public interface Builder<T> {

        T build(File file);

}
