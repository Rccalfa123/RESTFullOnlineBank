package com.cg.alfabankapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cg.alfabankapp.account.pojo.ErrorResponse;
import com.cg.alfabankapp.exceptions.AccountNotFoundException;
import com.cg.alfabankapp.exceptions.InvalidAccountNumberException;
import com.cg.alfabankapp.exceptions.InvalidAccountOrAmountException;
import com.cg.alfabankapp.exceptions.InvalidAmountException;
import com.cg.alfabankapp.exceptions.NoAccountFoundException;

@ControllerAdvice
public class CentralErrorController {

	@Autowired
	private ErrorResponse errorResponse; 
	
	@ExceptionHandler(AccountNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleAccountNotFoundError(AccountNotFoundException e)
	{
		errorResponse.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorResponse.setErrorMessage(e.getMessage());
		
		return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(NoAccountFoundException.class)
	public ResponseEntity<ErrorResponse> handleNoAccountFoundError(NoAccountFoundException e)
	{
		errorResponse.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorResponse.setErrorMessage(e.getMessage());
		
		return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(InvalidAccountNumberException.class)
	public ResponseEntity<ErrorResponse> handleInvalidAccountNumberError(InvalidAccountNumberException e)
	{
		errorResponse.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorResponse.setErrorMessage(e.getMessage());
		
		return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(InvalidAccountOrAmountException.class)
	public ResponseEntity<ErrorResponse> handleInvalidAccountOrAmountError(InvalidAccountOrAmountException e)
	{
		errorResponse.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorResponse.setErrorMessage(e.getMessage());
		
		return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(InvalidAmountException.class)
	public ResponseEntity<ErrorResponse> handleInvalidAmountError(InvalidAmountException e)
	{
		errorResponse.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorResponse.setErrorMessage(e.getMessage());
		
		return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
