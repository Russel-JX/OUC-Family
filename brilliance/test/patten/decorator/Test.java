package patten.decorator;

import patten.decorator.decorators.CutFileOperatorDecorator;
import patten.decorator.decorators.EncrptFileOperatorDecorator;

import java.io.File;

public class Test {
    public static void main(String[] args){
        String num = "1,2,3,4,5,6";
        String filePath = new File("brilliance/test/patten/decorator/file1.txt").getAbsolutePath();
        String dbFilePath = new File("brilliance/test/patten/decorator/file2.txt").getAbsolutePath();;

        //先加密，再写。
        FileOperator fo1 = new EncrptFileOperatorDecorator(new BasicFileOperator(filePath));
        fo1.write(num);
        //先截断，再写。
        FileOperator fo2 = new CutFileOperatorDecorator(new BasicFileOperator(filePath));
        fo2.write(num);
        //先加密，再截断，再写。
        FileOperator fo3 = new EncrptFileOperatorDecorator(new CutFileOperatorDecorator(new BasicFileOperator(filePath)));
        fo3.write(num);
        /**
         * 输出
         * Fri Apr 08 19:50:10 CST 2022:2,4,6
         Fri Apr 08 19:50:10 CST 2022:1,2,3,4
         Fri Apr 08 19:50:10 CST 2022:2
         */


        //先加密，再写到DB中。
        FileOperator fo4 = new EncrptFileOperatorDecorator(new OverWriteFileOperator(dbFilePath));
        fo4.write(num);//输出2,4,6
    }
}
