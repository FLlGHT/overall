package com.flight.overall.service;

import com.flight.overall.dto.GradeDTO;
import com.flight.overall.dto.RatingDTO;
import com.flight.overall.entity.*;
import com.flight.overall.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author FLIGHT
 * @date 22.10.2022
 */

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private GradeService gradeService;

    public List<Rating> getProfileRatings(long id) {
        return ratingRepository.getProfileRatings(id);
    }

    public void updateRatings(Account account, List<Rating> ratings, List<RatingDTO> ratingDTOs) {
        Map<Long, GradeDTO> ratingToGrade = new HashMap<>();
        for (RatingDTO rating : ratingDTOs)
            ratingToGrade.put(rating.getId(), rating.getGrade());

        for (Rating rating : ratings) {
            GradeDTO grade = ratingToGrade.get(rating.getId());

            updateRating(rating, grade);
            gradeService.saveGrade(account, rating, grade);
        }
    }

    public void updateRating(Rating rating, GradeDTO grade) {
        boolean firstGrade = grade.getPreviousGrade() == 0 && grade.getCurrentGrade() > 0;
        long total = rating.getTotal() - grade.getPreviousGrade() + grade.getCurrentGrade();
        long count = rating.getCount() + (firstGrade ? 1 : 0);

        int newRating = Math.min(99, (int) Math.round(total * 1.00 / count));

        rating.setTotal(total);
        rating.setCount(count);
        rating.setRating(newRating);

        ratingRepository.save(rating);
    }

    public void updateOverall(Profile profile) {
        List<Rating> ratings = ratingRepository.getProfileRatings(profile.getId());

        long sum = 0;
        for (Rating rating : ratings)
            sum += rating.getRating();

        profile.setOverallRating(Math.min(99, (int) Math.round(sum * 1.00 / ratings.size())));
    }

    public void createRatings(Profile profile) {
        List<Rating> ratings = new ArrayList<>();
        Iterable<Category> categories = categoryService.getCategories();

        for (Category category : categories) {
            Rating rating = new Rating(category, profile, 0, 0, 0);
            ratings.add(rating);
        }

        ratingRepository.saveAll(ratings);
    }
}
