package com.projeto.live.repository;

/**
 * @author AFRAIN
 * Date    28 de jul de 2020
 * E-mail  calixtoafrain@gmail.com
 */

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.projeto.live.document.LiveDocument;

public interface LiveRepository extends MongoRepository<LiveDocument, String>{
	
	Page<LiveDocument> findByDataLiveAfterOrderByDataLiveAsc(LocalDateTime data, Pageable pageable);
    Page<LiveDocument> findByDataLiveBeforeOrderByDataLiveDesc(LocalDateTime data, Pageable pageable);

}
