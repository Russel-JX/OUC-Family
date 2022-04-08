package patten.decorator.decorators;

import patten.decorator.FileOperator;

public class FileOperatorDecorator implements FileOperator {
    FileOperator fileOperator;

    public FileOperatorDecorator(FileOperator fileOperator) {
        this.fileOperator = fileOperator;
    }

    @Override
    public void write(String data) {
        fileOperator.write(data);
    }
}
