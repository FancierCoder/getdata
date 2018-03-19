package com.zetta.fancier.getdata.method;


import com.zetta.fancier.getdata.entity.DataSet;
import com.zetta.fancier.getdata.entity.ShuLie;
import com.zetta.fancier.getdata.service.DataSetService;
import com.zetta.fancier.getdata.service.MongoDbService;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
@Transactional
public class GetExcelInfo {

    @Resource
    private DataSetService dataSetService;

    @Resource
    private MongoDbService mongoDbService;

    public void getDataFromExcelFilePath(String filePathFolder) {
        // 此处路径指定到目录而不是单个文件
        File file = new File(filePathFolder);
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File f : files)
                    // 如果还存在子目录则继续读取子目录下的Excel文件
                    if (f.isDirectory()) {
                        File[] subFiles = f.listFiles();
                        if (subFiles != null) {
                            for (File fi : subFiles) {
                                // 对文件进行过滤，只读取Excel文件，非Excel文件不读取，否则会出错
                                if (fi.getName().indexOf(".xls") > 0) {
                                    getDataFromExcelFile(fi.getAbsolutePath());
                                }
                                //System.out.println(fi.getName());
                            }
                        }
                    } else {
                        // 对文件进行过滤，只读取Excel文件，非Excel文件不读取，否则会出错
                        if (f.getName().indexOf(".xls") > 0) {
                            getDataFromExcelFile(f.getAbsolutePath());
                            //System.out.println(f.getName());
                        }
                    }
            }
        }
    }

    /**
     * @param cell 一个单元格的对象
     * @return 返回该单元格相应的类型的值
     */
    @SuppressWarnings("deprecation")
    private Object getRightTypeCell(Cell cell) {
        Object object = null;
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING: {
                object = cell.getStringCellValue();
                break;
            }
            case Cell.CELL_TYPE_NUMERIC: {
                if (HSSFDateUtil.isCellDateFormatted(cell)) {// 处理日期格式、时间格式
                    SimpleDateFormat sdf;
                    if (cell.getCellStyle().getDataFormat() == HSSFDataFormat
                            .getBuiltinFormat("h:mm")) {
                        sdf = new SimpleDateFormat("HH:mm");
                    } else {// 日期
                        sdf = new SimpleDateFormat("yyyy-MM-dd");
                    }
                    Date date = cell.getDateCellValue();
                    object = sdf.format(date);
                } else {
                    object = cell.getNumericCellValue();
                }
                break;
            }
            case Cell.CELL_TYPE_FORMULA: {
                cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                object = cell.getNumericCellValue();
                break;
            }
            case Cell.CELL_TYPE_BLANK: {
                object = cell.getStringCellValue();
                break;
            }
        }
        return object;
    }

    /**
     * 读取出filePath中的所有数据信息
     *
     * @param filePath excel文件的绝对路径
     */
    @SuppressWarnings("deprecation")
    public void getDataFromExcelFile(String filePath) {
        // 判断是否为excel类型文件
        if (!filePath.endsWith(".xls") && !filePath.endsWith(".xlsx")) {
            System.out.println("文件不是excel类型");
            //throw new RuntimeException("文件不是excel类型");
            return;
        }
        FileInputStream fis = null;
        Workbook workBook = null;
        try {
            // 获取一个绝对地址的流
            fis = new FileInputStream(filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            // 2003版本的excel，用.xls结尾
            if (fis != null) {
                workBook = new HSSFWorkbook(fis);// 得到工作簿
            }
        } catch (Exception ex) {
            try {
                // 2007版本的excel，用.xlsx结尾
                workBook = new XSSFWorkbook(filePath);// 得到工作簿
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 得到一个工作表
        Sheet sheet = null;
        if (workBook != null) {
            sheet = workBook.getSheetAt(0);
        }
        if (sheet != null) {
            getData(sheet);
        }
    }

    private void getData(Sheet sheet) {
        // 获得表头
        Row rowHead = sheet.getRow(1);//从第二行开始
        if (rowHead == null)
            return;
        int flag = 1;//从第二列开始
        // ----------------这里根据你的表格有多少列
        while (flag < rowHead.getPhysicalNumberOfCells()) {
            DataSet dataSet = new DataSet();
            //Cell cell = rowHead.getCell(flag);
            int totalRowNum = sheet.getLastRowNum();
            for (int i = 1; i < 10; i++) {//提取前9行的信息（表头信息）
                Row row = sheet.getRow(i);
                Cell cell = row.getCell(flag);
                if (i == 1) {
                    continue;
                }
                if (i == 2) {
                    dataSet.setRemark(getRightTypeCell(cell).toString());
                    continue;
                }
                if (i == 3) {
                    dataSet.setSetName(getRightTypeCell(cell).toString());
                    continue;
                }
                if (i == 4) {
                    dataSet.setPeriod(getRightTypeCell(cell).toString());
                    continue;
                }
                if (i == 5) {
                    dataSet.setValueUnit(getRightTypeCell(cell).toString());
                    continue;
                }
                if (i == 6) {
                    continue;
                }
                if (i == 7) {
                    continue;
                }
                if (i == 8) {
                    dataSet.setLastUpdateTime(cell.getDateCellValue());
                    continue;
                }
                dataSet.setDataSource(getRightTypeCell(cell).toString());
            }
            String token = UUID.randomUUID().toString().replaceAll("-", "");
            ArrayList<ShuLie> shuLies = new ArrayList<>();
            for (int j = 10; j < totalRowNum - 1; j++) {//提取后面全部行的信息
                Row row = sheet.getRow(j);
                Cell cell;
                try {
                    cell = row.getCell(flag);
                } catch (Exception e) {
                    continue;
                }
                ShuLie shuLie = new ShuLie();
                shuLie.setToken(token);
                if (!(getRightTypeCell(cell) == ""))//如果有值
                    shuLie.setValue((Double) getRightTypeCell(cell));
                else
                    continue;
                SimpleDateFormat format =
                        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置时区为格林尼治时间，处理mongodb时间减8个小时的错误
                format.setCalendar(new GregorianCalendar(new SimpleTimeZone(0, "GMT")));
                try {
                    shuLie.setDate(format.parse(getRightTypeCell(row.getCell(0)) + " 00:00:00"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                shuLies.add(shuLie);
                //System.out.println(shuLie);
            }
            flag++;//下一列
            System.out.println(shuLies.size());
            if (shuLies.size() == 0) {//如果本列没有数据
                continue;
            }
            dataSet.setPointNumber(shuLies.size());
            dataSet.setCreateTime(new Date());
            dataSet.setLastInsertTime(new Date());
            dataSet.setToken(token);
            dataSet.setStatus(1);
            dataSet.setSetType(1);
            //持久化操作
            mongoDbService.insertAll(shuLies);
            boolean insert = dataSetService.insert(dataSet);
            if (!insert) {//如果插入不成功
                throw new RuntimeException("插入时出现错误");
            }
            //int i = 1 / 0;//制造错误，测试是否回滚
            System.out.println(dataSet);

        }
    }
}
