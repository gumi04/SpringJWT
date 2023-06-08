package com.example.controller;

import com.example.exception.BadRequestException;
import com.example.exception.ForbiddenExceptionException;
import com.example.models.dto.ErrorMessageDefault;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ControllerExceptionHandler {
	/**
	 * Not found request error message default.
	 *
	 * @param request   the request
	 * @param exception the exception
	 * @return the error message default
	 */
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler({HttpClientErrorException.class, NoHandlerFoundException.class})
	@ResponseBody
	public ErrorMessageDefault notFoundRequest(HttpServletRequest request, Exception exception) {
		return new ErrorMessageDefault(exception, request.getRequestURI());
	}

	/**
	 * Bad request error message default.
	 *
	 * @param request   the request
	 * @param exception the exception
	 * @return the error message default
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({
			BadRequestException.class,
			DuplicateKeyException.class,
			HttpRequestMethodNotSupportedException.class,
			MethodArgumentNotValidException.class,
			MissingPathVariableException.class,
			MissingRequestHeaderException.class,
			MissingServletRequestParameterException.class,
			MethodArgumentTypeMismatchException.class,
			HttpMessageNotReadableException.class})
	@ResponseBody
	public ErrorMessageDefault badRequest(HttpServletRequest request, Exception exception) {
		return new ErrorMessageDefault(exception, request.getRequestURI());
	}


	/**
	 * Forbidden request error message default.
	 *
	 * @param request   the request
	 * @param exception the exception
	 * @return the error message default
	 */
	@ResponseStatus(HttpStatus.FORBIDDEN)
	@ExceptionHandler({
			ForbiddenExceptionException.class,
	})
	@ResponseBody
	public ErrorMessageDefault forbiddenRequest(HttpServletRequest request, Exception exception) {
		return new ErrorMessageDefault(exception, request.getRequestURI());
	}


	/**
	 * Unautorized.
	 */
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler({
			AccessDeniedException.class,
	})
	@ResponseBody
	public void unautorized() {

	}

	/**
	 * Fatal error error message default.
	 *
	 * @param request   the request
	 * @param exception the exception
	 * @return the error message default
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler({
			Exception.class,
	})
	@ResponseBody
	public ErrorMessageDefault fatalError(HttpServletRequest request, Exception exception) {
		return new ErrorMessageDefault(exception, request.getRequestURI());
	}
}
