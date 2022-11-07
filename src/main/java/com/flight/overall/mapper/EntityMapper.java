package com.flight.overall.mapper;


import com.flight.overall.dto.*;
import com.flight.overall.entity.*;
import com.flight.overall.utils.DateUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;


@Component
public class EntityMapper {

    public ProfileDTO toProfileDTO(Profile profile, Account account, List<Category> categories,
                                   List<Rating> ratings, List<Grade> grades) {
        return new ProfileDTO(
                profile.getId(),
                profile.getFirstName(),
                profile.getSecondName(),
                profile.getUsername(),
                DateUtils.dateToPrettyString(profile.getDateOfBirth()),
                profile.getOverallRating(),
                profile.getPlaceOfResidence(),
                profile.getDescription(),
                toProfileImage(profile),
                toRatingsDTO(categories, ratings, grades),
                toShowedContacts(profile.getContacts()),
                canAddToContacts(profile, account)
        );
    }

    public ProfileDTO toProfileDTO(Profile profile) {
        return new ProfileDTO(
                profile.getId(),
                profile.getFirstName(),
                profile.getSecondName(),
                profile.getUsername(),
                DateUtils.dateToPrettyString(profile.getDateOfBirth()),
                profile.getDescription(),
                profile.getEmail(),
                profile.getPlaceOfResidence()
        );
    }

    private List<ProfileDTO> toShowedContacts(List<Profile> contacts) {
        List<ProfileDTO> contactsDTO = new ArrayList<>();
        contacts.stream().sorted(Comparator.comparing(Profile::getOverallRating).reversed())
                .limit(10)
                .forEach(contact -> contactsDTO.add(toContactDTO(contact)));

        return contactsDTO;
    }

    public ProfileDTO toProfileContacts(Profile profile, List<Profile> contacts) {
        ProfileDTO profileDTO = toProfileDTO(profile);
        List<ProfileDTO> contactsDTO = new ArrayList<>();
        contacts.forEach(contact -> contactsDTO.add(toContactDTO(contact)));
        profileDTO.setContacts(contactsDTO);

        return profileDTO;
    }

    private ProfileDTO toContactDTO(Profile contact) {
        return new ProfileDTO(
                contact.getId(),
                contact.getFirstName(),
                contact.getSecondName(),
                contact.getUsername(),
                contact.getOverallRating(),
                toProfileImage(contact)
        );
    }

    public List<RatingDTO> toRatingsDTO(List<Category> categories, List<Rating> ratings, List<Grade> grades) {
        Map<Long, Rating> categoryToRating = new HashMap<>();
        for (Rating rating : ratings)
            categoryToRating.put(rating.getCategory().getId(), rating);

        Map<Long, Grade> ratingToGrade = new HashMap<>();
        for (Grade grade : grades)
            ratingToGrade.put(grade.getRating().getId(), grade);

        List<RatingDTO> ratingDTOS = new ArrayList<>();

        for (Category category : categories) {
            Rating rating = categoryToRating.get(category.getId());
            Grade grade = rating == null ? null : ratingToGrade.get(rating.getId());

            ratingDTOS.add(toRatingDTO(category, rating, grade));
        }

        return ratingDTOS;
    }

    private RatingDTO toRatingDTO(Category category, Rating rating, Grade grade) {
        if (rating == null)
            return new RatingDTO(toCategoryDTO(category));

        return new RatingDTO(
                rating.getId(),
                toCategoryDTO(category),
                rating.getRating(),
                toGradeDTO(grade)
        );
    }

    public CategoryDTO toCategoryDTO(Category category) {
        return new CategoryDTO(
                category.getId(),
                category.getTitle(),
                toCategoryGroupDTO(category.getCategoryGroup()),
                category.getDescription(),
                category.getWeight()
        );
    }

    public CategoryGroupDTO toCategoryGroupDTO(CategoryGroup categoryGroup) {
        if (categoryGroup == null)
            return new CategoryGroupDTO();

        return new CategoryGroupDTO(
                categoryGroup.getId(),
                categoryGroup.getTitle(),
                categoryGroup.getDescription()
        );
    }

    public List<CategoryGroupDTO> toCategoryGroups(List<CategoryGroup> categoryGroups) {
        List<CategoryGroupDTO> groups = new ArrayList<>();
        categoryGroups.forEach(categoryGroup -> groups.add(toCategoryGroupDTO(categoryGroup)));
        return groups;
    }

    public CategoriesDTO toCategories(List<Category> categories) {
        List<CategoryDTO> categoryDTOList = new ArrayList<>();
        categories.forEach(category -> categoryDTOList.add(toCategoryDTO(category)));
        return new CategoriesDTO(categoryDTOList);
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

    public SettingsDTO toSettingsDTO(Settings settings, Account account) {
        ProfileDTO profileDTO = toProfileDTO(account.getProfile());
        AccountDTO accountDTO = toAccountDTO(account);

        return settings == null ? new SettingsDTO(accountDTO, profileDTO) :
                new SettingsDTO(settings.getId(),
                        accountDTO, profileDTO,
                        settings.isProfileClosed(),
                        settings.isGradesClosed()
                );
    }

    private String toProfileImage(Profile profile) {
        Image image = profile.getProfileImage();
        return image == null ? null : Base64.getEncoder().encodeToString(image.getContent());
    }

    private boolean canAddToContacts(Profile profile, Account account) {
        boolean unauthorized = account == null;
        boolean ownProfile = account != null && profile.getId() == account.getProfile().getId();
        boolean alreadyInContacts = account != null && profile.getContacts().contains(account.getProfile());

        return !unauthorized && !ownProfile && !alreadyInContacts;
    }
}
