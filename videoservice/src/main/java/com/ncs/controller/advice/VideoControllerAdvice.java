package com.ncs.controller.advice;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import com.ncs.exception.ErrorResponse;
import com.ncs.exception.VideoIDMismatchException;
import com.ncs.exception.VideoIdNotFoundException;
import com.ncs.exception.VideoTagNotFoundException;
import com.ncs.exception.VideoTitleNotFoundException;


@RestControllerAdvice
public class VideoControllerAdvice {
	
	
	
	@ExceptionHandler(VideoIDMismatchException.class)
	 public ResponseEntity<ErrorResponse> handleVideoIDMismatchException(VideoIDMismatchException e)
	 {
		 
		 ErrorResponse er= new ErrorResponse("Product-400", "Video ID mismatch!", new Date());
		  return new ResponseEntity<ErrorResponse>(er,HttpStatus.BAD_REQUEST);
	 }
	
	@ExceptionHandler(VideoIdNotFoundException.class)
	 public ResponseEntity<ErrorResponse> handleVideoIdNotFoundException(VideoIdNotFoundException e)
	 {
		 
		 ErrorResponse er= new ErrorResponse("Video-404", "Video with the specified ID Not found!", new Date());
		  return new ResponseEntity<ErrorResponse>(er,HttpStatus.NOT_FOUND);
	 }
	
	@ExceptionHandler(VideoTagNotFoundException.class)
	 public ResponseEntity<ErrorResponse> handleVideoTagNotFoundException(VideoTagNotFoundException e)
	 {
		 
		 ErrorResponse er= new ErrorResponse("Video-404", "Video with the specified Tag Not found!", new Date());
		  return new ResponseEntity<ErrorResponse>(er,HttpStatus.NOT_FOUND);
	 }
	
	@ExceptionHandler(VideoTitleNotFoundException.class)
	 public ResponseEntity<ErrorResponse> handleVideoTitleNotFoundException(VideoTitleNotFoundException e)
	 {
		 
		 ErrorResponse er= new ErrorResponse("Video-404", "Video with the specified Title Not found!", new Date());
		  return new ResponseEntity<ErrorResponse>(er,HttpStatus.NOT_FOUND);
	 }
	
	
	
	

}
