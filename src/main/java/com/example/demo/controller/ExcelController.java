package com.example.demo.controller;

import com.example.demo.bean.ApiResponse;
import com.example.demo.dict.MaterialEnum;
import com.example.demo.util.Analysis;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: demo
 * @description:
 * @author: CheGuangQuan
 * @create: 2020-02-27 10:46
 **/
@RestController
@RequestMapping("/import")
public class ExcelController {

    @PostMapping(value = "/excelImport",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse excelImport(@RequestPart(value = "file") MultipartFile file) {
        //调用工具类解析excel文件
        List<ArrayList<String>> row = Analysis.analysis(file);

        ArrayList<String> title = row.get(0);
        List<String> titles = CNtoEN(title);
        if (titles.contains("failRow")){
            return ApiResponse.buildErrorMessage("有命名不合法的列");
        }
        List<Map> materialList = new ArrayList<>();
        Map map = null;

        for (int i = 1; i < row.size(); i++) {
            ArrayList<String> materials = row.get(i);
            map = new HashMap();
            for (int j = 0; j < materials.size(); j++) {
                map.put(titles.get(j),materials.get(j));
            }
            materialList.add(map);
        }

        return ApiResponse.buildSuccessResponse(materialList);
    }

    /**
     * 中文转英文
     * @auther CheGuangQuan
     * @date 2020/2/27 15:11
     * @param cn
     * @return java.util.List<java.lang.String>
    */
    public static List<String> CNtoEN(List<String> cn){
        List<String> enStr = new ArrayList<>();
        cn.stream().forEach(c->{
            String str = "";
            if (MaterialEnum.SUPPLIER_PHONE.getDesc().equals(c)){
                str = MaterialEnum.SUPPLIER_PHONE.getCode();
            }else if (MaterialEnum.MATERIAL_NAME.getDesc().equals(c)){
                str = MaterialEnum.MATERIAL_NAME.getCode();
            }else if (MaterialEnum.MODELS.getDesc().equals(c)){
                str = MaterialEnum.MODELS.getCode();
            }else if (MaterialEnum.OPERATE_TIME.getDesc().equals(c)){
                str = MaterialEnum.OPERATE_TIME.getCode();
            }else if (MaterialEnum.OPERATE_NAME.getDesc().equals(c)){
                str = MaterialEnum.OPERATE_NAME.getCode();
            }else if (MaterialEnum.PRICE.getDesc().equals(c)){
                str = MaterialEnum.PRICE.getCode();
            }else if (MaterialEnum.MATERIAL_CODE.getDesc().equals(c)){
                str = MaterialEnum.MATERIAL_CODE.getCode();
            }else if (MaterialEnum.STOCK_NUM.getDesc().equals(c)){
                str = MaterialEnum.STOCK_NUM.getCode();
            }else if (MaterialEnum.SUPPLIER_NAME.getDesc().equals(c)){
                str = MaterialEnum.SUPPLIER_NAME.getCode();
            }else if (MaterialEnum.UNIT.getDesc().equals(c)){
                str = MaterialEnum.UNIT.getCode();
            }else {
                str = "failRow";
            }
            enStr.add(str);
        });
        return enStr;
    }
}