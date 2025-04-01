package dev.springfirst.productservicedec24.advices;

import dev.springfirst.productservicedec24.Exceptions.ProductNotFoundException;
import dev.springfirst.productservicedec24.dtos.ErrorDto;
import dev.springfirst.productservicedec24.models.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorDto> handleProductNotFoundException(ProductNotFoundException productNotFoundException){
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage(productNotFoundException.getMessage());

        ResponseEntity<ErrorDto> responseEntity = new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);

        return responseEntity;
    }
}
/*
Instead of sending a clumsy error for the client this errordto(data transfer object) is created and is used
in sending back the response to the client by handing the productnotfoundexception in the whole project

 */