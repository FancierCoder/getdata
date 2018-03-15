package com.zetta.fancier.getdata.method;


import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;

public class GetExcelInfo2 {

    public static void main(String[] args) {
        try {
            XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream("D:\\liduo_profile\\证券市场\\证券公司统计\\Y证券营业部数量：爱建证券.xlsx"));
            Sheet sheet = wb.getSheetAt(0);

            //下面是处理合并单元格的代码。

            //获取当前sheet页中，所有的合并单元格的数量。
            int regionsCount = sheet.getNumMergedRegions();

            if (regionsCount > 0) {
                for (int i = 0; i < regionsCount; i++) {
                    CellRangeAddress region = sheet.getMergedRegion(i);
                    //输出单元格的起止。
                    System.out.println(region.formatAsString());
                    //获取单元格的起止行和列
                    int rowFrom = region.getFirstRow();
                    int rowTo = region.getLastRow();
                    int cellFrom = region.getFirstColumn();
                    int cellTo = region.getLastColumn();

                    //sheet.getRow().getCell   可以获取某行某个单元格。
                    String hbzhi = sheet.getRow(rowFrom).getCell(cellFrom).toString();
                    System.out.println(hbzhi);
                }
            }

            //处理合并单元格 end

            //如果没有合并单元格，可以设置行和列的下标，0开始，循环，使用sheet.getRow(rowFrom).getCell(cellFrom).toString();，可以遍历整个excel。
            //如果有合并单元格，请按照上面代码块查询数据。

        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
