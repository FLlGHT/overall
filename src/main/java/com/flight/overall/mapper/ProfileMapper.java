package com.flight.overall.mapper;


import com.flight.overall.dto.CategoryDTO;
import com.flight.overall.dto.GradeDTO;
import com.flight.overall.dto.ProfileDTO;
import com.flight.overall.dto.RatingDTO;
import com.flight.overall.entity.Category;
import com.flight.overall.entity.Grade;
import com.flight.overall.entity.Profile;
import com.flight.overall.entity.Rating;
import com.flight.overall.utils.DateUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class ProfileMapper {


    public ProfileDTO toProfileDTO(Profile profile, List<Rating> ratings, List<Grade> grades) {
        return new ProfileDTO(
                profile.getId(),
                profile.getFullName(),
                profile.getUsername(),
                DateUtils.dateToPrettyString(profile.getDateOfBirth()),
                profile.getOverallRating(),
                profile.getDescription(),
                toRatingsDTO(ratings, grades)
        );
    }

    public List<RatingDTO> toRatingsDTO(List<Rating> ratings, List<Grade> grades) {
        Map<Long, Grade> ratingToGrade = new HashMap<>();
        for (Grade grade : grades)
            ratingToGrade.put(grade.getRating().getId(), grade);

        List<RatingDTO> ratingDTOS = new ArrayList<>();
        for (Rating rating : ratings) {
            Grade grade = ratingToGrade.get(rating.getId());
            RatingDTO ratingDTO = new RatingDTO(
                    rating.getId(),
                    toCategoryDTO(rating.getCategory()),
                    rating.getRating(),
                    toGradeDTO(grade)
            );

            ratingDTOS.add(ratingDTO);
        }

        return ratingDTOS;
    }

    public CategoryDTO toCategoryDTO(Category category) {
        return new CategoryDTO(
                category.getId(),
                category.getTitle()
        );
    }

    public GradeDTO toGradeDTO(Grade grade) {
        if (grade == null)
            return new GradeDTO();

        return new GradeDTO(
                grade.getId(),
                grade.getValue(),
                grade.getValue()
        );
    }
}
