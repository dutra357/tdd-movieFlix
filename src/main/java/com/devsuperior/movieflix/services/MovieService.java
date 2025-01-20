package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.MovieCardDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.repositories.MovieRepository;
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
    public MovieService(MovieRepository repository) {
        this.repository = repository;
    }

    public Page<MovieCardDTO> findAllPaged(String id, Pageable pageable) {
        PageRequest page = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("title"));

        if (id.equals("")) {
            return repository.findAll(page).map(MovieCardDTO::new);
        } else {
            return repository.findByGenreId(Long.parseLong(id), page).map(MovieCardDTO::new);
        }
    }

    public MovieCardDTO findById(Long id) {
        Movie movie = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Movie not found."));
        return new MovieCardDTO(movie);
    }
}
