package com.example.demo.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Analysis {

    public Analysis() {
        throw new Error("工具类不允许实例化！");
    }

    /**
     * 获取并解析excel文件，返回一个二维集合
     * @param file 上传的文件
     * @return 二维集合（第一重集合为行，第二重集合为列，每一行包含该行的列集合，列集合包含该行的全部单元格的值）
     */
    public static ArrayList<ArrayList<String>> analysis(MultipartFile file) {
        ArrayList<ArrayList<String>> row = new ArrayList<>();
        //获取文件名称
        String fileName = file.getOriginalFilename();
        try {
            InputStream inputStream = file.getInputStream();
            //判断excel版本
            Workbook workbook = null;
            //判断文件类型
            if (fileName.endsWith("xls")) {
                // 2003版本
                workbook = new HSSFWorkbook(inputStream);

            } else if (fileName.endsWith("xlsx")) {
                // 2007版本
                workbook = new XSSFWorkbook(inputStream);
            }

            //获取第一张工作表
            Sheet sheet = workbook.getSheetAt(0);
            //从第一行开始获取
            for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
                //循环获取工作表的每一行
                Row sheetRow = sheet.getRow(i);
                //循环获取每一列
                ArrayList<String> cell = new ArrayList<>();
                for (int j = 0; j < sheetRow.getPhysicalNumberOfCells(); j++) {
                    //将每一个单元格的值装入列集合
                    cell.add(sheetRow.getCell(j).getStringCellValue());
                }
                //将装有每一列的集合装入大集合
                row.add(cell);

                //关闭资源
                workbook.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("===================未找到文件======================");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("===================上传失败======================");
        }

        return row;
    }

}