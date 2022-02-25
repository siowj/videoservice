package com.ncs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ncs.model.Video;

public interface VideoRepository extends JpaRepository<Video, Integer> {

	Video findByVideoId(Integer videoId);

	List<Video> findAllByTitle(String title);

}
