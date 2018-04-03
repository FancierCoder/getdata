package com.zettayun.method;

import com.csvreader.CsvReader;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class CsvUtil {

    public static List<String> readCsv(String filePath){
        List<String> allCell = new ArrayList<>();
        try {
            //File file = new File(filePath);
            //FileReader fReader = new FileReader(file);
            // 用来保存数据
            ArrayList<String[]> csvFileList = new ArrayList<>();

            // 创建CSV读对象 例如:CsvReader(文件路径，分隔符，编码格式);
            CsvReader csvReader = new CsvReader(filePath, ',', Charset.forName("GBK"));
            // 跳过表头 如果需要表头的话，这句可以忽略
            //csvReader.readHeaders();

            // 逐行读入数据
            while (csvReader.readRecord()) {
                //System.out.println(csvReader.getRawRecord());
                csvFileList.add(csvReader.getValues());
            }

            // 遍历读取的CSV文件
            for (String[] aCsvFileList : csvFileList) {
                // 取得每行第0列的数据
                for (String cell : aCsvFileList) {
                    allCell.add(cell);
                    System.out.println(cell);
//                    String[] split = cell.split(" ");
//                    System.out.println();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allCell;
    }

    public static void main(String[] args) {
        List<String> list = readCsv("D:\\LDprofile\\abcd.csv");

    }
}
