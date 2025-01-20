package com.devsuperior.movieflix.controllers;

import com.devsuperior.movieflix.dto.MovieCardDTO;
import com.devsuperior.movieflix.services.MovieService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/movies")
public class MovieController {

	private final MovieService service;
	public MovieController(MovieService service) {
		this.service = service;
	}

	@PreAuthorize("hasAnyRole('ROLE_VISITOR', 'ROLE_MEMBER')")
	@GetMapping(value = "/{id}")
	public ResponseEntity<MovieCardDTO> findById(@PathVariable Long id) {
		return ResponseEntity.ok(service.findById(id));
	}

	@PreAuthorize("hasAnyRole('ROLE_VISITOR', 'ROLE_MEMBER')")
	@GetMapping
	public ResponseEntity<Page<MovieCardDTO>> searchAll(Pageable pageable,
														@RequestParam(name = "genreId", defaultValue = "") String genreId) {
		return ResponseEntity.ok().body(service.findAllPaged(genreId, pageable));
	}
}
