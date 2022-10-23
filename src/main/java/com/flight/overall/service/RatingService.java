package com.flight.overall.service;

import com.flight.overall.entity.Rating;
import com.flight.overall.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
