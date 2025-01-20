package com.devsuperior.movieflix.controllers;

import com.devsuperior.movieflix.dto.GenreDTO;
import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.services.GenreService;
import com.devsuperior.movieflix.services.ReviewService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/reviews")
public class ReviewController {

	private final ReviewService service;

	public ReviewController(ReviewService service) {
		this.service = service;
	}

	@PreAuthorize("hasAnyRole('ROLE_MEMBER')")
	@PostMapping
	public ResponseEntity<ReviewDTO> insertReview(@Valid @RequestBody ReviewDTO newReview) {
		ReviewDTO reviewSaved = service.insertReview(newReview);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
				.path("/{id}").buildAndExpand(reviewSaved.getId()).toUri();

		return ResponseEntity.created(uri).body(reviewSaved);
	}

}
