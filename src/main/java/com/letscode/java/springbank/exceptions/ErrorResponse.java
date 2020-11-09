package com.letscode.java.springbank.exceptions;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
@Setter
public class ErrorResponse { //POJO
    
    private Class exceptionClass;
    private HttpStatus httpStatus;
    private String message;
    private LocalDateTime thrownAt;
    
    public ErrorResponse(Class exceptionClass, HttpStatus httpStatus, String message) {
        this.exceptionClass = exceptionClass;
        this.httpStatus = httpStatus;
        this.message = message;
        this.thrownAt = LocalDateTime.now();
    }
}
