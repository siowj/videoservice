package com.ncs.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import com.ncs.exception.VideoIDMismatchException;
import com.ncs.exception.VideoTagNotFoundException;
import com.ncs.exception.VideoTitleNotFoundException;
import com.ncs.model.Video;
import com.ncs.service.VideoService;

@CrossOrigin
@RestController
@RequestMapping("/videos")
public class VideoController {
	
	private static final Logger logger = LoggerFactory.getLogger(VideoController.class);
	
	@Autowired
	VideoService videoService;
	
	@GetMapping
	public ResponseEntity<List<Video>>  getVideos() {
		logger.info("Find all videos information ");
		return ResponseEntity.ok(videoService.getVideos());
	}

	
	@PostMapping
	public ResponseEntity<Video> addVideo(@Valid @RequestBody Video video) {
		logger.info("Add Video :"+video);
		return ResponseEntity.status(HttpStatus.CREATED).body(videoService.addVideo(video));
	}

	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Video> updateVideo(@Valid @RequestBody Video video,@PathVariable Integer id) {
		logger.info("Update Video :"+video);
		//return accountService.updateAccount(account,accountId);
		
		if(id != video.getVideoId())
			throw new VideoIDMismatchException();
		Video videoResult = videoService.updateVideo(video);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(videoResult.getVideoId()).toUri();
		return  ResponseEntity.created(location).body(video);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable Integer id) {
		logger.info("delete by video id:"+id);
		if(videoService.deleteVideo(id)==1)
			return  ResponseEntity.noContent().build();
		else
			return ResponseEntity.badRequest().body("Something unexpdected");
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Video> findByVideoId(@PathVariable Integer id) {
		logger.info("Find by Video ID :"+id);
		return ResponseEntity.ok(videoService.findById(id));
	}
	
	@GetMapping("/tags/{tag}")
	public ResponseEntity<List<Video>> getVideosByTags(@PathVariable String tag){
		logger.info("Find by Video tag :"+tag);
		
		List<Video> videoResult = videoService.findAllByTag(tag);
		
		if(videoResult.isEmpty()) {
			throw new VideoTagNotFoundException();
			//return new ResponseEntity<List<Account>>(HttpStatus.NOT_FOUND);
		}
		else
		return ResponseEntity.ok(videoResult);
	}
	
	@GetMapping("/titles/{title}")
	public ResponseEntity<List<Video>> getVideosByTitle(@PathVariable String title){
		logger.info("Find by Video Title :"+title);
		
		List<Video> videoResult = videoService.findAllByTitle(title);
		
		if(videoResult.isEmpty()) {
			throw new VideoTitleNotFoundException();
			//return new ResponseEntity<List<Account>>(HttpStatus.NOT_FOUND);
		}
		else
		return ResponseEntity.ok(videoResult);
	}
	
	@GetMapping("/sortedtitle")
	public ResponseEntity<List<Video>> getSortedVideosByTitle(){
		logger.info("Sorted Video Title :");
		
		return ResponseEntity.ok(videoService.getSortedVideosByTitle());
		
	}
	
	@GetMapping("/sortedcreateddate")
	public ResponseEntity<List<Video>> getSortedVideosByCreatedDate(){
		logger.info("Sorted Created Date :");
		
		return ResponseEntity.ok(videoService.getSortedVideosByCreatedDate());
		
	}

}
