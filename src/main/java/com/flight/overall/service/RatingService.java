package com.flight.overall.service;

import com.flight.overall.dto.GradeDTO;
import com.flight.overall.dto.RatingDTO;
import com.flight.overall.dto.RatingGroupDTO;
import com.flight.overall.entity.*;
import com.flight.overall.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    public void updateRatings(Account account, Profile profile, List<Rating> ratings, List<RatingGroupDTO> ratingGroups) {
        Map<Long, Rating> idToRating = new HashMap<>();
        for (Rating rating : ratings)
            idToRating.put(rating.getId(), rating);

        for (RatingGroupDTO ratingGroup : ratingGroups) {
            for (RatingDTO ratingDTO : ratingGroup.getRatings()) {
                Rating rating = idToRating.getOrDefault(ratingDTO.getId(), createRating(ratingDTO, profile));
                GradeDTO grade = ratingDTO.getGrade();

                updateRating(rating, grade);
                gradeService.saveGrade(account, rating, grade);
            }
        }
    }

    private Rating createRating(RatingDTO ratingDTO, Profile profile) {
        Category category = categoryService.findCategory(ratingDTO.getCategory());
        return new Rating(category, profile);
    }

    public void updateRating(Rating rating, GradeDTO grade) {
        boolean isFirstGrade = grade.getPreviousGrade() == 0 && grade.getCurrentGrade() > 0;

        long total = rating.getTotal() - grade.getPreviousGrade() + grade.getCurrentGrade();
        long count = rating.getCount() + (isFirstGrade ? 1 : 0);

        int newRating = Math.min(99, (int) Math.round(total * 1.00 / count));

        rating.setTotal(total);
        rating.setCount(count);
        rating.setRating(newRating);

        ratingRepository.save(rating);
    }

    public void updateOverall(Profile profile) {
        List<Rating> ratings = ratingRepository.getProfileRatings(profile.getId());

        long sum = 0, count = 0;
        for (Rating rating : ratings) {
            if (rating.getRating() > 0) {
                sum += rating.getRating();
                count++;
            }
        }

        profile.setOverallRating(Math.min(99, (int) Math.round(sum * 1.00 / count)));
    }

    public void createRatings(Profile profile) {
        List<Rating> ratings = new ArrayList<>();
        Iterable<Category> categories = categoryService.findAllActiveCategories();

        for (Category category : categories) {
            Rating rating = new Rating(category, profile);
            ratings.add(rating);
        }

        ratingRepository.saveAll(ratings);
    }

    public int getProfileRating(long profileId, long categoryId) {
        Optional<Rating> rating = ratingRepository.findProfileRatingByCategory(profileId, categoryId);
        return rating.map(Rating::getRating).orElse(0);
    }
}
