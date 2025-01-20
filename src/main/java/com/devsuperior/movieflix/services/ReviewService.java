package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    public final ReviewRepository repository;
    public final MovieRepository movieRepository;
    public final UserService userService;
    public ReviewService(ReviewRepository repository,
                         MovieRepository movieRepository,
                         UserService userService) {
        this.repository = repository;
        this.movieRepository = movieRepository;
        this.userService = userService;
    }

    public ReviewDTO insertReview(ReviewDTO newReview) {
        Review review = new Review();
        review.setText(newReview.getText());
        review.setMovie(movieRepository.getReferenceById(newReview.getMovieId()));

        review.setUser(userService.authenticated());

        return new ReviewDTO(repository.save(review));
    }
}
