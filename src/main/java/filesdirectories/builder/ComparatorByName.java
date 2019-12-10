package filesdirectories.builder;

import filesdirectories.viewRepresentation.FileInfo;
import org.springframework.stereotype.Component;

import java.util.Comparator;

@Component
public class ComparatorByName implements Comparator<FileInfo> {
    @Override
    public int compare(FileInfo o1, FileInfo o2) {

        String first = o1.getFilename().replaceAll("[0-9]", "").toLowerCase();
        String second = o2.getFilename().replaceAll("[0-9]", "").toLowerCase();


        return first.compareTo(second);

    }
}
