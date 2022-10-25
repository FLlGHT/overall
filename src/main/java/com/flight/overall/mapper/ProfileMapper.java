package com.flight.overall.mapper;


import com.flight.overall.dto.CategoryDTO;
import com.flight.overall.dto.ProfileDTO;
import com.flight.overall.dto.RatingDTO;
import com.flight.overall.entity.Category;
import com.flight.overall.entity.Profile;
import com.flight.overall.entity.Rating;
import com.flight.overall.utils.DateUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class ProfileMapper {


    public ProfileDTO toProfileDTO(Profile profile, List<Rating> ratings) {
        return new ProfileDTO(
                profile.getId(),
                profile.getFullName(),
                profile.getUsername(),
                DateUtils.dateToPrettyString(profile.getDateOfBirth()),
                profile.getOverallRating(),
                profile.getDescription(),
                toRatingsDTO(ratings)
        );
    }

    public List<RatingDTO> toRatingsDTO(List<Rating> ratings) {
        List<RatingDTO> ratingDTOS = new ArrayList<>();
        for (Rating rating : ratings) {
            RatingDTO ratingDTO = new RatingDTO(
                    rating.getId(),
                    toCategoryDTO(rating.getCategory()),
                    rating.getRating()
            );
            ratingDTOS.add(ratingDTO);
        }

        return ratingDTOS;
    }

    public CategoryDTO toCategoryDTO(Category category) {
        return new CategoryDTO(category.getId(), category.getTitle());
    }
}
