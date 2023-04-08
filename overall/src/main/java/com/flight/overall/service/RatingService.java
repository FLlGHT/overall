package com.flight.overall.service;

import com.flight.overall.dto.*;
import com.flight.overall.entity.*;
import com.flight.overall.repository.GroupRatingRepository;
import com.flight.overall.repository.OverallRatingRepository;
import com.flight.overall.repository.ProfileRepository;
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

    @Autowired
    private OverallRatingRepository overallRatingRepository;

    @Autowired
    private ProfileRepository profileRepository;

    public List<Rating> getProfileRatings(long id) {
        return ratingRepository.getProfileRatings(id);
    }

    public List<GroupRating> getProfileGroupRatings(long id) {
        return groupRatingRepository.getProfileGroupRatings(id);
    }

    public RatingsUpdate updateRatings(RatingsUpdate ratingsUpdate, Account account) {
        Profile profile = profileRepository.findProfile(ratingsUpdate.getProfile().getId());

        GroupRating groupRating = getGroupRating(ratingsUpdate.getRatingGroup(), profile);
        Rating rating = updateRating(ratingsUpdate.getRating(), profile, groupRating);
        updateGroupRating(profile, groupRating);

        Grade grade = updateGrade(ratingsUpdate.getRating().getGrade(), account, rating);
        OverallRating overall = updateOverall(profile);

        return updateModel(ratingsUpdate, rating, groupRating, grade, overall);
    }

    private GroupRating getGroupRating(RatingGroupDTO ratingGroupDTO, Profile profile) {
        GroupRating groupRating = groupRatingRepository.findById(ratingGroupDTO.getId())
                                                       .orElse(createGroupRating(ratingGroupDTO, profile));

        if (groupRating.getId() == 0)
            groupRatingRepository.save(groupRating);

        return groupRating;
    }

    private Rating updateRating(RatingDTO ratingDTO, Profile profile, GroupRating groupRating) {
        Rating rating = ratingRepository.findById(ratingDTO.getId()).orElse(createRating(ratingDTO, groupRating, profile));
        updateRating(ratingDTO, rating);

        return rating;
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
            groupRating.setRating(Math.min(99, (int) Math.round(sum / count)));

        groupRatingRepository.save(groupRating);
    }

    public OverallRating updateOverall(Profile profile) {
        OverallRating overallRating = profile.getOverallRating();

        List<GroupRating> groupRatings = getProfileGroupRatings(profile.getId());
        double sum = 0, count = 0;
        for (GroupRating groupRating : groupRatings) {
            if (groupRating.getRating() > 0) {
                double weight = groupRating.getCategoryGroup().getWeight();
                sum += groupRating.getRating() * weight;
                count += weight;
            }
        }

        if (count > 0)
            overallRating.setRating(Math.min(99, (int) Math.round(sum / count)));

        return overallRatingRepository.save(overallRating);
    }

    private Grade updateGrade(GradeDTO gradeDTO, Account account, Rating rating) {
        return gradeService.saveGrade(account, rating, gradeDTO);
    }

    private RatingsUpdate updateModel(RatingsUpdate ratingsUpdate, Rating rating, GroupRating groupRating, Grade grade, OverallRating overallRating) {
        updateGroupRatingModel(ratingsUpdate, groupRating);
        updateRatingModel(ratingsUpdate, rating);
        updateGradeModel(ratingsUpdate, grade);
        updateProfileModel(ratingsUpdate, overallRating);

        return ratingsUpdate;
    }

    private void updateProfileModel(RatingsUpdate ratingsUpdate, OverallRating overallRating) {
        ProfileDTO profileDTO = ratingsUpdate.getProfile();
        profileDTO.setOverallRating(overallRating.getRating());
    }

    private void updateGradeModel(RatingsUpdate ratingsUpdate, Grade grade) {
        GradeDTO gradeDTO = ratingsUpdate.getRating().getGrade();
        gradeDTO.setId(grade.getId());
        gradeDTO.setPreviousGrade(gradeDTO.getCurrentGrade());
    }

    private void updateRatingModel(RatingsUpdate ratingsUpdate, Rating rating) {
        RatingDTO ratingDTO = ratingsUpdate.getRating();
        ratingDTO.setId(rating.getId());
        ratingDTO.setRating(rating.getRating());
    }

    private void updateGroupRatingModel(RatingsUpdate ratingsUpdate, GroupRating groupRating) {
        RatingGroupDTO ratingGroupDTO = ratingsUpdate.getRatingGroup();
        ratingGroupDTO.setId(groupRating.getId());
        ratingGroupDTO.setGroupRating(groupRating.getRating());
    }

    private Rating createRating(RatingDTO ratingDTO, GroupRating groupRating, Profile profile) {
        Category category = categoryService.findCategory(ratingDTO.getCategory());
        return new Rating(groupRating, category, profile);
    }

    private GroupRating createGroupRating(RatingGroupDTO ratingGroupDTO, Profile profile) {
        CategoryGroup categoryGroup = categoryService.findCategoryGroup(ratingGroupDTO.getCategoryGroup());
        return new GroupRating(categoryGroup, profile);
    }

    public void updateRating(RatingDTO ratingDTO, Rating rating) {
        GradeDTO grade = ratingDTO.getGrade();

        boolean isFirstGrade = grade.getPreviousGrade() == 0;
        long total = rating.getTotal() - grade.getPreviousGrade() + grade.getCurrentGrade();
        long count = rating.getCount() + (isFirstGrade ? 1 : 0);
        int newRating = Math.min(99, (int) Math.round(total * 1.00 / count));

        rating.setTotal(total);
        rating.setCount(count);
        rating.setRating(newRating);

        ratingRepository.save(rating);
    }

    public int getProfileRating(long profileId, long categoryId) {
        Optional<Rating> rating = ratingRepository.findProfileRatingByCategory(profileId, categoryId);
        return rating.map(Rating::getRating).orElse(0);
    }
}
