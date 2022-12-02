package com.flight.overall.service;

import com.flight.overall.dto.GradeDTO;
import com.flight.overall.dto.RatingDTO;
import com.flight.overall.dto.RatingGroupDTO;
import com.flight.overall.entity.*;
import com.flight.overall.repository.GroupRatingRepository;
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
    private GroupRatingRepository groupRatingRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private GradeService gradeService;

    public List<Rating> getProfileRatings(long id) {
        return ratingRepository.getProfileRatings(id);
    }

    public List<GroupRating> getProfileGroupRatings(long id) {
        return groupRatingRepository.getProfileGroupRatings(id);
    }

    public void updateRatings(Account account, Profile profile, List<RatingGroupDTO> ratingGroups) {
        Map<Long, Rating> ratingMap = toRatingMap(profile);
        Map<Long, GroupRating> groupRatingMap = toGroupRatingMap(profile);

        for (RatingGroupDTO ratingGroupDTO : ratingGroups) {
            GroupRating groupRating = getGroupRating(profile, groupRatingMap, ratingGroupDTO);

            for (RatingDTO ratingDTO : ratingGroupDTO.getRatings())
                updateRating(account, profile, ratingMap, groupRating, ratingDTO);

            updateGroupRating(profile, groupRating);
        }
    }

    private GroupRating getGroupRating(Profile profile, Map<Long, GroupRating> groupRatingMap, RatingGroupDTO ratingGroupDTO) {
        GroupRating groupRating = groupRatingMap.getOrDefault(ratingGroupDTO.getId(), createGroupRating(ratingGroupDTO, profile));

        if (groupRating.getId() == 0)
            groupRatingRepository.save(groupRating);

        return groupRating;
    }

    private void updateGroupRating(Profile profile, GroupRating groupRating) {
        List<Rating> ratings = ratingRepository.findRatingsFromGroup(profile.getId(), groupRating.getId());

        double sum = 0, count = 0;
        for (Rating rating : ratings) {
            Category category = rating.getCategory();
            CategoryType categoryType = category.getCategoryType();
            if (rating.getRating() > 0 && categoryType != CategoryType.NOT_AFFECT) {
                double weight = category.getWeight();
                count += weight;

                double ratingWithWeight = rating.getRating() * weight;
                if (categoryType == CategoryType.IN_DIRECT_RATIO)
                    sum += ratingWithWeight;
                else
                    sum += (100 - ratingWithWeight);
            }
        }

        if (count > 0)
            groupRating.setRating((int) Math.round(sum / count));

        groupRatingRepository.save(groupRating);
    }


    private void updateRating(Account account, Profile profile, Map<Long, Rating> ratingMap, GroupRating groupRating, RatingDTO ratingDTO) {
        GradeDTO grade = ratingDTO.getGrade();

        if (grade.getCurrentGrade() > 0) {
            Rating rating = ratingMap.getOrDefault(ratingDTO.getId(), createRating(ratingDTO, groupRating, profile));
            updateRating(rating, grade);
            gradeService.saveGrade(account, rating, grade);
        }
    }

    private Map<Long, GroupRating> toGroupRatingMap(Profile profile) {
        List<GroupRating> groupRatings = getProfileGroupRatings(profile.getId());
        Map<Long, GroupRating> groupRatingMap = new HashMap<>();
        for (GroupRating groupRating : groupRatings)
            groupRatingMap.put(groupRating.getId(), groupRating);

        return groupRatingMap;
    }

    private Map<Long, Rating> toRatingMap(Profile profile) {
        List<Rating> ratings = getProfileRatings(profile.getId());
        Map<Long, Rating> ratingMap = new HashMap<>();
        for (Rating rating : ratings)
            ratingMap.put(rating.getId(), rating);

        return ratingMap;
    }

    private GroupRating createGroupRating(RatingGroupDTO ratingGroupDTO, Profile profile) {
        CategoryGroup categoryGroup = categoryService.findCategoryGroup(ratingGroupDTO.getCategoryGroup());
        return new GroupRating(categoryGroup, profile);
    }

    private Rating createRating(RatingDTO ratingDTO, GroupRating groupRating, Profile profile) {
        Category category = categoryService.findCategory(ratingDTO.getCategory());
        return new Rating(groupRating, category, profile);
    }

    public void updateRating(Rating rating, GradeDTO grade) {
        boolean isFirstGrade = grade.getPreviousGrade() == 0;

        long total = rating.getTotal() - grade.getPreviousGrade() + grade.getCurrentGrade();
        long count = rating.getCount() + (isFirstGrade ? 1 : 0);

        int newRating = Math.min(99, (int) Math.round(total * 1.00 / count));

        rating.setTotal(total);
        rating.setCount(count);
        rating.setRating(newRating);

        ratingRepository.save(rating);
    }

    public void updateOverall(Profile profile) {
        List<Rating> ratings = ratingRepository.getAffectsRatings(profile.getId());

        long sum = 0, count = 0;
        for (Rating rating : ratings) {
            CategoryType categoryType = rating.getCategory().getCategoryType();
            if (rating.getRating() > 0) {

                if (categoryType == CategoryType.IN_DIRECT_RATIO)
                    sum += rating.getRating();
                else
                    sum += (100 - rating.getRating());

                count++;
            }
        }

        profile.setOverallRating(Math.min(99, (int) Math.round(sum * 1.00 / count)));
    }

    public int getProfileRating(long profileId, long categoryId) {
        Optional<Rating> rating = ratingRepository.findProfileRatingByCategory(profileId, categoryId);
        return rating.map(Rating::getRating).orElse(0);
    }
}
