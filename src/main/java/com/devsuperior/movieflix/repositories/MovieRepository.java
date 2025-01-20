package com.devsuperior.movieflix.repositories;

import com.devsuperior.movieflix.entities.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    Page<Movie> findByGenreId(Long id, Pageable pageable);
    Page<Movie> findAll(Pageable pageable);

}
