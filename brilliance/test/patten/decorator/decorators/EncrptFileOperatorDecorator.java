package patten.decorator.decorators;

import patten.decorator.FileOperator;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EncrptFileOperatorDecorator extends FileOperatorDecorator {
    public EncrptFileOperatorDecorator(FileOperator fileOperator) {
        super(fileOperator);
    }

    @Override
    public void write(String data) {
        //加密:取偶数
        String encrptData = Stream.of(data.split(",")).map(e->Integer.valueOf(e)).
                filter(e->e%2==0).map(Object::toString).
                collect(Collectors.joining(","));
        super.write(encrptData);
    }
}
