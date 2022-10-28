package com.flight.overall.mapper;


import com.flight.overall.dto.*;
import com.flight.overall.entity.*;
import com.flight.overall.utils.DateUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;


@Component
public class EntityMapper {

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

    public ProfileDTO toProfileDTO(Profile profile) {
        return new ProfileDTO(
                profile.getId(),
                profile.getFullName(),
                profile.getUsername(),
                DateUtils.dateToPrettyString(profile.getDateOfBirth()),
                profile.getDescription(),
                profile.getEmail(),
                profile.getPlaceOfResidence()
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

    public AccountDTO toAccountDTO(Account account) {
        return new AccountDTO(
                account.getId(),
                account.getUsername(),
                new BCryptPasswordEncoder().encode(account.getPassword())
        );
    }

    public SettingsDTO toSettingsDTO(Account account) {
        ProfileDTO profileDTO = toProfileDTO(account.getProfile());
        AccountDTO accountDTO = toAccountDTO(account);

        return new SettingsDTO(accountDTO, profileDTO);
    }
}
