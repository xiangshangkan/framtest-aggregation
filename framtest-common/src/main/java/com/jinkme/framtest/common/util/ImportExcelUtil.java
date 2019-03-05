package com.jinkme.framtest.common.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author : Administrator
 * @date : 2019/2/22 14:37
 */
public class ImportExcelUtil {

    public final static String EXCEL_XLS = ".xls";
    public final static String EXCEL_XLSX = ".xlsx";

    public final static String STRING_1 =  "1";
    public final static String STRING_2 = "2";


    /**
     * @description //获取导入模板 excel
     * @param  title 标题头
     * @param  excelType cxcel表格 类型
     * @return
     * @author      zhouhui
     * @date        2019/3/5 16:46
    */
    public static void getImportTemplate(HttpServletResponse response, Map<String, Object> title, String[] fields, String excelType, String fileName){

        if(title == null || fields == null) {
            System.out.println("排除抛常见异常的可能！");
            throw new RuntimeException();
        }

        Workbook workbook = null;
        if(excelType.endsWith(STRING_1)){
            workbook = new HSSFWorkbook();
        } else {
            workbook = new XSSFWorkbook();
        }

        Sheet sheet = workbook.createSheet();
        Row fieldRow = sheet.createRow(0);
        Row titleRow = sheet.createRow(1);

        for(int i = 0; i < fields.length; i++) {
            Cell cell = fieldRow.createCell(i);
            cell.setCellValue(fields[i]);
            cell = titleRow.createCell(i);
            cell.setCellValue(title.get(fields[i]).toString());
        }

        String sFileName = fileName + (excelType.endsWith(STRING_1) ? EXCEL_XLS : EXCEL_XLSX );

        try {
            response.setHeader("Content-Disposition", "attachment;filename="
                    .concat(String.valueOf(URLEncoder.encode(sFileName, "UTF-8"))));
            response.setHeader("Connection", "close");
            response.setHeader("Content-Type", "application/vnd.ms-excel");
            workbook.write(response.getOutputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @description //导入excel（适合对象映射导入）
     * @param
     * @return
     * @author      zhouhui
     * @date        2019/3/5 16:47
    */
    public static void importExcel(String fileAddress, Class clazz){

        try {
            InputStream inputStream = new FileInputStream(new File(fileAddress));
            Workbook workbook = null;
            //判断excel文件类型
            if(fileAddress.endsWith(EXCEL_XLS)){
                workbook = new HSSFWorkbook(inputStream);
            } else if (fileAddress.endsWith(EXCEL_XLSX)){
                workbook = new XSSFWorkbook(inputStream);
            }

            Sheet sheet = workbook.getSheetAt(0);
            Integer totalRow = sheet.getLastRowNum() + 1;

            //获取对象属性
            Row  fieldRow = sheet.getRow(0);
            Iterator it = fieldRow.cellIterator();
            Map<Integer,String> fieldMap = new HashMap<>();
            int i = 0;
            while (it.hasNext()){
                fieldMap.put(i,it.next().toString());
                i++;
            }

            for(int j = 2; j < totalRow; j++){
                Row row = sheet.getRow(j);
                Iterator iterator =  row.cellIterator();
                while(iterator.hasNext()){
                  System.out.println(iterator.next());
                }
            }

            OutputStream outputStream = new FileOutputStream(new File("result"+fileAddress.substring(fileAddress.lastIndexOf('.'))));
            workbook.write(outputStream);
        } catch (FileNotFoundException e) {
            System.out.println("未找到对应文件！");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("表格读写错误！");
            e.printStackTrace();
        }

    }


    /**
     * @description //
     * @param
     * @return
     * @author      zhouhui
     * @date        2019/3/5 16:47
    */
    public static void exportExcel(String title, String[] headers, String[] fields, List<T> datas){
        //创建新的工作bu
        HSSFWorkbook wk = new HSSFWorkbook();
        //创建新的sheet页
        HSSFSheet sheet = wk.createSheet(title);
        //标题行
        HSSFRow titleRow = sheet.createRow(0);
        HSSFCell titleCell = titleRow.createCell(0);
        titleCell.setCellValue(title);
        //合并单元格: CellRangeAddress 的参数分别为起始行，截止行，起始列，截止列
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,fields.length-1));

        //列头
        HSSFRow titleTowRow = sheet.createRow(1);
        for(int i = 0;i < headers.length; i++) {
            HSSFCell cell = titleTowRow.createCell(i);
            cell.setCellValue(headers[i]);
        }

        //数据
        for(int i = 0; i < datas.size(); i++ ){
            HSSFRow dataRow = sheet.createRow(i+2);
            JSONObject jsonObject = (JSONObject) JSONObject.toJSON(datas.get(i));
            for (int j = 0; j < fields.length; j++ ){
                HSSFCell dataCell = dataRow.createCell(j);
                String value = jsonObject.getString(fields[j]);
                dataCell.setCellValue(value);
            }
        }
        //导出
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String sFileName = title + "_"+ sdf.format(new Date())+".xls";
        try {
            OutputStream outputStream = new FileOutputStream(sFileName);
            wk.write(outputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
       /* try {
            String sFileName = title + "_"+ sdf.format(new Date())+".xls";
            response.setHeader("Content-Disposition", "attachment;filename="
                    .concat(String.valueOf(URLEncoder.encode(sFileName, "UTF-8"))));
            response.setHeader("Connection", "close");
            response.setHeader("Content-Type", "application/vnd.ms-excel");
            wb.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                wb.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/

       /* try {
            OutputStream outputStream = new FileOutputStream();
            wk.write(outputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

    }
}
