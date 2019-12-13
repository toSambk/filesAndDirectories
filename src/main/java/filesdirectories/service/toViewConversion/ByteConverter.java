package filesdirectories.service.toViewConversion;

import org.springframework.stereotype.Component;

import java.text.DecimalFormat;

@Component
public class ByteConverter implements Converter<String, Long>{

    @Override
    public String convert(Long sizeInBytes) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        double result = sizeInBytes;
        if (result < 1024) return decimalFormat.format(result) + "b";
        result /= 1024;
        if (result < 1024) return decimalFormat.format(result) + "Kb";
        result /= 1024;
        if (result < 1024) return decimalFormat.format(result) + "Mb";
        result /= 1024;
        if (result < 1024) return decimalFormat.format(result) + "Gb";
        result /= 1024;
        return decimalFormat.format(result) + "Tb";
    }



}
