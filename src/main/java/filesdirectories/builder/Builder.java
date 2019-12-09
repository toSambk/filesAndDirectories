package filesdirectories.builder;

import java.io.IOException;

public interface Builder<T, V> {

    T build(V file) throws IOException;

}
