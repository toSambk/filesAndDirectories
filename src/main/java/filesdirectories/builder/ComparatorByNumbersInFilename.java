package filesdirectories.builder;

import filesdirectories.viewRepresentation.FileInfo;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ComparatorByNumbersInFilename implements Comparator<FileInfo> {

    @Override
    public int compare(FileInfo o1, FileInfo o2) {
        int result = 0;
        String first;
        String second;
        if (!o1.isDirectory() || !o2.isDirectory()) {
            first = o1.getFilename().substring(0, o1.getFilename().lastIndexOf('.'));
            second = o2.getFilename().substring(0, o2.getFilename().lastIndexOf('.'));
        } else {
            first = o1.getFilename();
            second = o2.getFilename();
        }
        Pattern pat = Pattern.compile("[0-9]*");
        Matcher matcherFirst = pat.matcher(first);
        Matcher matcherSecond = pat.matcher(second);
        List<Long> matches1 = new ArrayList<>();
        List<Long> matches2 = new ArrayList<>();
        while (matcherFirst.find()) {
            if (!matcherFirst.group().isEmpty()) matches1.add(Long.parseLong(matcherFirst.group()));
        }
        while (matcherSecond.find()) {
            if (!matcherSecond.group().isEmpty()) matches2.add(Long.parseLong(matcherSecond.group()));
        }
        if (!matches1.isEmpty() && !matches2.isEmpty()) {
            for (int i = 0; i < Math.min(matches1.size(), matches2.size()); i++) {
                if (matches1.get(i) == matches2.get(i)) {
                    result = 0;
                    if(i == matches1.size() - 1) result = -1;
                    if(i == matches2.size() - 1) result = 1;
                    continue;
                }
                if (matches1.get(i) > matches2.get(i)) {
                    result = 1;
                    break;
                }
                if (matches1.get(i) < matches2.get(i)) {
                    result = -1;
                    break;
                }
            }
        }
        return result;
    }

}
