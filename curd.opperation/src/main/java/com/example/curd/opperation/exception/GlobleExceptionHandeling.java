package com.example.curd.opperation.exception;

import com.example.curd.opperation.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobleExceptionHandeling {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> haldleResourceNotFoundException (ResourceNotFoundException ex){

        String message = ex.getMessage();

        ApiResponse apiResponse = new ApiResponse(message, false , HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(apiResponse , HttpStatus.NOT_FOUND);
    }
    /*
    here we use Map<String , Object > bcoz ...it contain many values

    getBindingResult().getAllErrors() ---- gives all binding error associated with exception



     */

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String , Object>> handleMethodArgumentNotValidException (MethodArgumentNotValidException ex){

        List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();

        Map<String , Object> response = new HashMap();

        allErrors.stream().forEach((ObjectError)->{
            String message = ObjectError.getDefaultMessage();
            String field = ((FieldError) ObjectError).getField();
            response.put(message , field);
        });
        return  new ResponseEntity<>(response , HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(BadApiRequest.class)
    public ResponseEntity<ApiResponse> handleBadApiRequest (BadApiRequest ex){

        String message = ex.getMessage();

        ApiResponse apiResponse = new ApiResponse(message, false , HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(apiResponse , HttpStatus.BAD_REQUEST);
    }


}
