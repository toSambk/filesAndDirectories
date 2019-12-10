package filesdirectories.builder;

import java.io.IOException;

public interface Converter<T, V> {

    T convert(V file) throws IOException;

}
