package filesdirectories.service.toViewConversion;

import filesdirectories.service.toViewConversion.comparators.ComparatorByName;
import filesdirectories.service.toViewConversion.comparators.ComparatorByNumbersInFilename;
import filesdirectories.entities.Directory;
import filesdirectories.viewRepresentation.FileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Component
public class ViewFileInfoConverter implements Converter<List<FileInfo>, Directory> {

    @Autowired
    private ByteConverter byteConverter;

    @Autowired
    private ComparatorByNumbersInFilename comparatorByNumbersInFilename;

    @Autowired
    private ComparatorByName comparatorByName;

    @Override
    public List<FileInfo> convert(Directory root) {

        List<FileInfo> resultList = new ArrayList<>();
        List<FileInfo> fileDirs = new ArrayList<>();

        root.getDirectories().forEach(x-> resultList.add(new FileInfo(parsePath(x.getPath()), "<DIR>", x.getId(), true)));
        root.getFiles().forEach(x-> fileDirs.add(new FileInfo(parsePath(x.getPath()), byteConverter.convert(x.getSize()), x.getId(), false)));

        resultList.sort(comparatorByName.thenComparing(comparatorByNumbersInFilename));
        fileDirs.sort(comparatorByName.thenComparing(comparatorByNumbersInFilename));

        resultList.addAll(fileDirs);

        return resultList;
    }

    private String parsePath(String path) {
        return path.substring(path.lastIndexOf(File.separator) + 1);
    }




}
