package patten.decorator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Date;

public class OverWriteFileOperator implements FileOperator {
    private String filePath;

    public OverWriteFileOperator(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void write(String data) {
        File file = new File(filePath);
        try (OutputStream fos = new FileOutputStream(file)) {
            fos.write(data.getBytes(), 0, data.length());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
