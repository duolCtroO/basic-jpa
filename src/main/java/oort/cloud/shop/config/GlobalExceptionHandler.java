package oort.cloud.shop.config;

import lombok.extern.slf4j.Slf4j;
import oort.cloud.shop.data.ErrorType;
import oort.cloud.shop.data.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    //미리 만든 클래스 디테일하게 작업이 필요함
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleTemp(Exception e){
        log.error("테스트 Exception");
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionResponse(e.getMessage(), ErrorType.UNKNOWN));
    }

}
