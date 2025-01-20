package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.MovieDetailsDTO;
import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepository repository;
    private final ReviewRepository reviewRepository;
    public MovieService(MovieRepository repository, ReviewRepository reviewRepository) {
        this.repository = repository;
        this.reviewRepository = reviewRepository;
    }

    public Page<MovieDetailsDTO> findAllPaged(String id, Pageable pageable) {
        PageRequest page = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("title"));

        if (id.equals("")) {
            return repository.findAll(page).map(MovieDetailsDTO::new);
        } else {
            return repository.findByGenreId(Long.parseLong(id), page).map(MovieDetailsDTO::new);
        }
    }

    public MovieDetailsDTO findById(Long id) {
        Movie movie = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Movie not found."));
        return new MovieDetailsDTO(movie);
    }

    public List<ReviewDTO> findReviewByMovieId(Long id) {
        return reviewRepository.findByMovieId(id).stream().map(ReviewDTO::new).toList();
    }
}
