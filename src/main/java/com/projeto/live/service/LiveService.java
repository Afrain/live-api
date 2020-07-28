package com.projeto.live.service;

/**
 * @author AFRAIN
 * Date    28 de jul de 2020
 * E-mail  calixtoafrain@gmail.com
 */

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.projeto.live.document.LiveDocument;
import com.projeto.live.repository.LiveRepository;

@Service
public class LiveService {

	@Autowired
	private LiveRepository liveRepository;
	
	public Page<LiveDocument> findAll(Pageable pageable, String flag) {
		if (flag != null && flag.equals("next")) {
			return this.liveRepository
					.findByDataLiveAfterOrderByDataLiveAsc(LocalDateTime.now(), pageable);	
		} else if (flag != null && flag.equals("previous")) {
			return this.liveRepository
					.findByDataLiveBeforeOrderByDataLiveDesc(LocalDateTime.now(), pageable);
		} else {
			return this.liveRepository.findAll(pageable);
		}
	}
	
	public Optional<LiveDocument> findById(@PathVariable String id) {
		return this.liveRepository.findById(id);
				
	}
	
	public LiveDocument save(@RequestBody LiveDocument liveDocument) {
		return this.liveRepository.save(liveDocument);
	}
	
	public void delete(LiveDocument liveDocument) {
		this.liveRepository.delete(liveDocument);
	}
	
}
