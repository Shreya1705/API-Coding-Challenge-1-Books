package com.hexaware.Exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalException {

	 @ExceptionHandler(BookNotFoundException.class)
	    public ResponseEntity<Object> handleBookNotFoundException(BookNotFoundException ex, WebRequest request) {
	        Map<String, Object> response = new HashMap<>();
	        response.put("message", ex.getMessage());
	        response.put("status", HttpStatus.NOT_FOUND.value());
	        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	    }

}
