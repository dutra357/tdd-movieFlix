package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.repositories.ReviewRepository;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    public final ReviewRepository repository;
    public ReviewService(ReviewRepository repository) {
        this.repository = repository;
    }

    
}
