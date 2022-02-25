package com.ncs.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncs.exception.VideoIdNotFoundException;
import com.ncs.model.Video;
import com.ncs.repository.VideoRepository;

@Service
public class VideoService {
	
	@Autowired
	VideoRepository videoRepository;
	
	public Video findByVideoId(Integer id) {
		return videoRepository.findByVideoId(id);
	}
	
	public Video findById(Integer id) {
		return videoRepository.findById(id).orElseThrow(VideoIdNotFoundException::new);
	}
	
	
	public List<Video> getVideos() {
		return videoRepository.findAll();
	}
	
	public Video addVideo(Video video) {
		return videoRepository.saveAndFlush(video);
	}
	
	public Video updateVideo(Video video) {
		Video found = findById(video.getVideoId());
		
		if(found == null)
			return null;
		else
			return videoRepository.saveAndFlush(video);
	}
	
	public int deleteVideo(Integer id) {
		Video found = findByVideoId(id);
		
		if(found == null)
			return 0;
		else {
			videoRepository.delete(found);
			
			return 1;
		}
	}

	public List<Video> findAllByTag(String tag) {
		// TODO Auto-generated method stub
		
		
		List<Video> videos=getVideos();
		List<Video> found= new ArrayList<Video>();
		
		for (Video video : videos) {
			List<String> tagslist=video.getTags();
			
			for (String t : tagslist) {
				if(t.equals(tag)) {
					found.add(video);
				}
			}
		}
		
		System.out.println(found);
		return found;
	}

	public List<Video> findAllByTitle(String title) {
		// TODO Auto-generated method stub
		return videoRepository.findAllByTitle(title);
	}

	public List<Video> getSortedVideosByTitle() {
		
		List<Video> videos=getVideos();
		
		return videos.stream()
		.sorted(Comparator.comparing(Video::getTitle))
		.collect(Collectors.toList());
	}

	public List<Video> getSortedVideosByCreatedDate() {
		
		List<Video> videos=getVideos();
		
		return videos.stream()
		.sorted(Comparator.comparing(Video::getCreatedDate))
		.collect(Collectors.toList());
	}



	
	

}
