package com.psjw.photogram.handler.ex;


public class CustomApiException extends RuntimeException {

    //객체 구분할때 사용
    private static final long serialVersionUID = 1L;


    public CustomApiException(String message) {
        super(message);
    }


}
