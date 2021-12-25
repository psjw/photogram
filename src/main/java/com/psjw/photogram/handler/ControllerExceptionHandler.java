package com.psjw.photogram.handler;

import com.psjw.photogram.handler.ex.CustomValidationException;
import com.psjw.photogram.util.Script;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(CustomValidationException.class)
//    public CMRespDto<Map<String, String>> validationException(CustomValidationException e) {
    /*
    public CMRespDto<?> validationException(CustomValidationException e) { //<?> 타입 추론이 가능하다.
        return new CMRespDto(-1, e.getMessage(), e.getErrorMap());
    }
     */
    public String validationException(CustomValidationException e) { //<?> 타입 추론이 가능하다.
        //CMRespDto, Script 비교
        // 1. 클라이언트에게 응답할 때는 Script 좋음.
        // 2. Ajax 통신 - CMRespDto
        // 3. Android 통신 - CMRespDto
        return Script.back(e.getErrorMap().toString());
    }
}
