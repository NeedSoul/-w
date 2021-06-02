package com.fh.exception;

import com.fh.result.ResultObject;
import org.springframework.web.bind.annotation.*;


import java.io.Serializable;

@RestControllerAdvice
public class ErrorExceptionHandler implements Serializable {

    @ExceptionHandler(Exception.class)
    public @ResponseBody
    ResultObject exception(Exception e){
        e.printStackTrace();
        return ResultObject.error();
    }
}
