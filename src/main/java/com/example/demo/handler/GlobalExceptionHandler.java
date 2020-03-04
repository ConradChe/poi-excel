package com.example.demo.handler;

import com.example.demo.bean.ApiResponse;
import com.example.demo.exception.BusinessErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

/**
 * @program: demo
 * @description:
 * @author: CheGuangQuan
 * @create: 2020-03-03 13:32
 **/
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessErrorException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse handleBusinessError(BusinessErrorException ex){
        long code = ex.getCode();
        String message = ex.getMessage();
        return new ApiResponse(code,message);
    }


    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ApiResponse handleHttpMessageNotReadableException(MissingServletRequestParameterException ex){
        return ApiResponse.buildErrorMessage("缺少必要的请求参数");
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse handleUnexpectedServer(Exception ex) {
        return ApiResponse.buildErrorMessage("系统发生异常，请联系管理员");
    }
}