package in.kanha.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppExceptionHandler {

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<ErrorInfo> handleException(Exception e)
	{
		String message = e.getMessage();
		ErrorInfo info = new ErrorInfo();
		info.setCode("SID85256");
		info.setMsg(message);
		info.setWhen(LocalDateTime.now());
		
		return new ResponseEntity<ErrorInfo>(info, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value = TicketNumberNotFoundException.class)
	public ResponseEntity<ErrorInfo> userException(Exception e)
	{
		String message = e.getMessage();
		ErrorInfo info = new ErrorInfo();
		info.setCode("SID85256");
		info.setMsg(message);
		info.setWhen(LocalDateTime.now());
		
		return new ResponseEntity<ErrorInfo>(info, HttpStatus.BAD_REQUEST);
	}
}
