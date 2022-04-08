package patten.decorator;

import patten.decorator.FileOperator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Date;

public class BasicFileOperator implements FileOperator {
    private String filePath;

    public BasicFileOperator(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void write(String data) {
        try {
            data = new Date()+":"+data+System.lineSeparator();
            //追加写
            Files.write(Paths.get(filePath), data.getBytes(), StandardOpenOption.APPEND);
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
