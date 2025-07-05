package course.service.course_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException ex, WebRequest request) {
        
     
        HttpStatus status;
        String message = ex.getMessage();

       
        if (message.contains("User service is currently unavailable")) {
           
            status = HttpStatus.SERVICE_UNAVAILABLE; // 503
        } else if (message.contains("Invalid instructor ID") || message.contains("not an instructor")) {
         
            status = HttpStatus.BAD_REQUEST; 
        } else {
           
            status = HttpStatus.INTERNAL_SERVER_ERROR; // 500
        }

       
        ErrorResponse errorResponse = new ErrorResponse(status.value(), message);

        System.err.println("==== GLOBAL EXCEPTION HANDLER: " + message);

       
        return new ResponseEntity<>(errorResponse, status);
    }

  
}

