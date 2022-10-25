package com.flight.overall.service;

import com.flight.overall.dto.RatingDTO;
import com.flight.overall.entity.Profile;
import com.flight.overall.entity.Rating;
import com.flight.overall.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<Rating> getProfileRatings(long id) {
        return ratingRepository.getProfileRatings(id);
    }

    public void updateRatings(List<Rating> ratings, List<RatingDTO> grades) {
        Map<Long, RatingDTO> gradeMap = new HashMap<>();
        for (RatingDTO grade : grades)
            gradeMap.put(grade.getId(), grade);

        for (Rating rating : ratings)
            updateRating(rating, gradeMap.get(rating.getId()));
    }

    public void updateRating(Rating rating, RatingDTO grade) {
        long total = rating.getTotal() + grade.getGrade();
        long count = rating.getCount() + 1;

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
}
