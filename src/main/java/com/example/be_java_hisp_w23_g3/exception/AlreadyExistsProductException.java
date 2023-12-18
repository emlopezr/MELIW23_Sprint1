package com.example.be_java_hisp_w23_g3.exception;

public class AlreadyExistsProductException extends RuntimeException {

    public AlreadyExistsProductException(String message) {
        super(message);
    }

}
