package com.adam.auditservice.controller;


import com.adam.auditservice.exceptions.InvalidInterestRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.adam.auditservice.model.ErrorDetials;
import com.adam.auditservice.model.JSONAPIErrorDetails;

@RestControllerAdvice
@Slf4j
public class ErrorHandler {

    @ExceptionHandler(value = {InvalidInterestRequestException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public com.adam.auditservice.model.ErrorDetials handleBadRequest(final  Exception e){

        return getErrorDetails(HttpStatus.BAD_REQUEST,e.getMessage());
    }

    @ExceptionHandler(value = {RuntimeException.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDetials handleInternalServerError(final  Exception e){

        return getErrorDetails(HttpStatus.BAD_REQUEST,e.getMessage());
    }

    private ErrorDetials getErrorDetails(final HttpStatus status, final String details){
        ErrorDetials errorDetials = new ErrorDetials();
        JSONAPIErrorDetails jsonapiErrorDetails = new JSONAPIErrorDetails()
        .code(status.value())
        .details(details)
        .message(details);
        errorDetials.addErrorsItem(jsonapiErrorDetails);
        return errorDetials;
    }
}
