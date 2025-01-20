package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.GenreDTO;
import com.devsuperior.movieflix.repositories.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {

    private final GenreRepository repository;
    public GenreService(GenreRepository repository) {
        this.repository = repository;
    }

    public List<GenreDTO> findAll() {
        return repository.findAll().stream().map(genre -> new GenreDTO(genre)).toList();
    }
}
