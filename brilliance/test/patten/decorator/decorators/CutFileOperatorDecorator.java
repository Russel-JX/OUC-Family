package patten.decorator.decorators;

import patten.decorator.FileOperator;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CutFileOperatorDecorator extends FileOperatorDecorator {
    public CutFileOperatorDecorator(FileOperator fileOperator) {
        super(fileOperator);
    }

    @Override
    public void write(String data) {
        //截断:去掉最后两位字符串
        String[] elements = data.split(",");
        String cutData = Stream.of(elements).limit(elements.length-2).collect(Collectors.joining(","));
        super.write(cutData);
    }
}
