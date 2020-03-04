package com.example.demo.controller;

import com.example.demo.bean.ApiResponse;
import com.example.demo.dict.BusinessMsgEnum;
import com.example.demo.exception.BusinessErrorException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: demo
 * @description:
 * @author: CheGuangQuan
 * @create: 2020-03-03 13:37
 **/
@RestController
@RequestMapping("/exception")
public class ExceptionController {

    @PostMapping("/test")
    public ApiResponse test(@RequestParam("name") String name,
                            @RequestParam("pass") String pass){
        Map map = new HashMap();
        map.put("name",name);
        map.put("pass",pass);
        return ApiResponse.buildSuccessResponse(map);
    }

    @GetMapping("/business")
    public ApiResponse testException(){
        int i = 0;
        if (i==0){
            throw new BusinessErrorException(BusinessMsgEnum.UNEXPECTED_EXCEPTION);
        }
        return new ApiResponse();
    }
}