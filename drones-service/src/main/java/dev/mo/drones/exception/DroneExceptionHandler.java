package dev.mo.drones.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class DroneExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ DroneException.class })
	protected ResponseEntity<Object> handleDroneException(Exception e, WebRequest request) {
		DroneException ex = (DroneException) e;

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		ErrorClass errorClass = new ErrorClass(ex.getErrorCode(), ex.getErrorMessage());

		return handleExceptionInternal(e, errorClass, headers, HttpStatus.INTERNAL_SERVER_ERROR, request);
	}

}
