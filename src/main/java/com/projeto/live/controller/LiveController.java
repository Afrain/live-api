package com.projeto.live.controller;

/**
 * @author AFRAIN
 * Date    28 de jul de 2020
 * E-mail  calixtoafrain@gmail.com
 */

import java.time.LocalDateTime;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.live.document.LiveDocument;
import com.projeto.live.service.LiveService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/live")
public class LiveController {

	@Autowired
	LiveService liveService;

	@GetMapping()
	public ResponseEntity<Page<LiveDocument>> getAllLives(
			@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable,
			@RequestParam(required = false) String flag) {
		Page<LiveDocument> livePage = liveService.findAll(pageable, flag);
		if (livePage.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Page<LiveDocument>>(livePage, HttpStatus.OK);
		}
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<LiveDocument> getOneLive(@PathVariable String id) {
		Optional<LiveDocument> live = liveService.findById(id);
		if (!live.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<LiveDocument>(live.get(), HttpStatus.OK);
		}
	}

	@PostMapping()
	public ResponseEntity<LiveDocument> saveLive(@Valid @RequestBody LiveDocument live) {
		live.setDataCadastro(LocalDateTime.now());
		return new ResponseEntity<LiveDocument>(this.liveService.save(live), HttpStatus.CREATED);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteLive(@PathVariable String id) {
		Optional<LiveDocument> live = this.liveService.findById(id);
		if (!live.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			this.liveService.delete(live.get());
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<LiveDocument> updateLive(@Valid @RequestBody LiveDocument newlive, @PathVariable String id) {
		Optional<LiveDocument> liveBuscada = this.liveService.findById(id);
		if (!liveBuscada.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			newlive.setId(liveBuscada.get().getId());
			newlive.setDataCadastro(LocalDateTime.now());
			return new ResponseEntity<LiveDocument>(this.liveService.save(newlive), HttpStatus.OK);
		}
	}
}
