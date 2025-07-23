package ra.hackathon.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ra.hackathon.model.dto.response.ApiResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {
 @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>>handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String,String>  map = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> map.put(error.getField(),error.getDefaultMessage()));
        return new ResponseEntity<>(new ApiResponse<>(false,ex.getMessage(),map, HttpStatus.BAD_REQUEST),HttpStatus.BAD_REQUEST);



 }
  @ExceptionHandler(customException.class)
  public ResponseEntity<ApiResponse<Map<String, String>>>handleCustomException(customException ex) {
      return new ResponseEntity<>(new ApiResponse<>(false,ex.getMessage(),null, HttpStatus.BAD_REQUEST),HttpStatus.BAD_REQUEST);

  }

  @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ApiResponse<String>> handleNoSuchElementException(NoSuchElementException ex) {
        return new ResponseEntity<>(new ApiResponse<>(false, ex.getMessage(), null, HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
    }

}
