package com.yart.user.advice;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;

import com.yart.user.bean.response.ServiceResponse;
import com.yart.user.util.YartUtils;

/**
 * . This method will handle all the mentioned exceptions that are thrown from
 * the Controllers
 */
@ControllerAdvice
public class YartControllerAdvice {

	private final static Logger logger = Logger
			.getLogger(YartControllerAdvice.class);

	@ControllerAdvice(basePackages={"com.yart.user"})
	static class JsonpAdvice extends AbstractJsonpResponseBodyAdvice {
		public JsonpAdvice() {
			super("callback");
		}
	}

	/**
	 * . Exception Handler method, that handles the IllegalArgumentException
	 * Exception, and displaying, specific or generic exception message.
	 */
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ServiceResponse> handleBadRequests(
			HttpServletResponse response, IllegalArgumentException exception)
			throws IOException {

		ServiceResponse serviceResponseError = new ServiceResponse();
		serviceResponseError.setStatusCode(HttpStatus.BAD_REQUEST.value());

		if (exception.getMessage() != null) {
			logger.error("IllegalArgumentException Occured: "
					+ exception.getMessage());
			serviceResponseError.setStatusMsg(exception.getMessage());
		} else {
			logger.error("IllegalArgumentException Occured:"
					+ YartUtils.IllegalArgumentExceptionStr);
			serviceResponseError
					.setStatusMsg(YartUtils.IllegalArgumentExceptionStr);
		}
		return new ResponseEntity<ServiceResponse>(serviceResponseError,
				HttpStatus.BAD_REQUEST);
	}

	/**
	 * . Exception Handler method, that handles the NullPointerException
	 * Exception, and displaying, specific or generic exception message.
	 */
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<ServiceResponse> handleNullPointer(
			HttpServletResponse response, NullPointerException exception)
			throws IOException {

		ServiceResponse serviceResponseError = new ServiceResponse();
		serviceResponseError.setStatusCode(HttpStatus.NOT_FOUND.value());

		if (exception.getMessage() != null) {
			logger.error("NullPointerException Occured: "
					+ exception.getMessage());
			serviceResponseError.setStatusMsg(exception.getMessage());
		} else {
			logger.error("NullPointerException Occured:"
					+ YartUtils.NullPointerExceptionStr);
			serviceResponseError
					.setStatusMsg(YartUtils.NullPointerExceptionStr);
		}
		return new ResponseEntity<ServiceResponse>(serviceResponseError,
				HttpStatus.NOT_FOUND);
	}

	/**
	 * . Exception Handler method, that handles the RuntimeException Exception,
	 * and displaying, specific or generic exception message.
	 */
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ServiceResponse> handleRunTimeException(
			HttpServletResponse response, RuntimeException exception)
			throws IOException {

		ServiceResponse serviceResponseError = new ServiceResponse();
		serviceResponseError.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR
				.value());

		if (exception.getMessage() != null) {
			logger.error("RuntimeException Occured: " + exception.getMessage());
			serviceResponseError.setStatusMsg(exception.getMessage());
		} else {
			logger.error("RuntimeException Occured: "
					+ YartUtils.RuntimeExceptionStr);
			serviceResponseError
					.setStatusMsg(YartUtils.RuntimeExceptionStr);
		}
		return new ResponseEntity<ServiceResponse>(serviceResponseError,
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * . Exception Handler method, that handles the Exception exception, and
	 * displaying, specific or generic exception message.
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ServiceResponse> handleException(
			HttpServletResponse response, Exception exception)
			throws IOException {

		ServiceResponse serviceResponseError = new ServiceResponse();
		serviceResponseError.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR
				.value());

		if (exception.getMessage() != null) {
			logger.error("Exception Occured: " + exception.getMessage());
			serviceResponseError.setStatusMsg(exception.getMessage());
		} else {
			logger.error("Exception Occured: " + YartUtils.ExceptionStr);
			serviceResponseError.setStatusMsg(YartUtils.ExceptionStr);
		}
		return new ResponseEntity<ServiceResponse>(serviceResponseError,
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
