package com.flight.overall.mapper;


import com.flight.overall.dto.*;
import com.flight.overall.entity.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;


@Component
public class EntityMapper {

    public ProfileDTO toProfileDTO(Profile profile, Account account, List<Category> categories,
                                   List<Rating> ratings, List<GroupRating> groupRatings, List<Grade> grades) {
        return new ProfileDTO(
                profile.getId(),
                profile.getFirstName(),
                profile.getSecondName(),
                profile.getUsername(),
                profile.getDateOfBirth(),
                profile.getOverallRating().getRating(),
                profile.getPlaceOfResidence(),
                profile.getDescription(),
                getImage(profile.getProfileImage()),
                toRatingGroups(categories, ratings, groupRatings, grades),
                toShowedContacts(profile.getContacts()),
                profile.getContacts().size(),
                canAddToContacts(profile, account),
                toExternalLinks(profile.getLinks()),
                toCompanyDTO(profile.getCompany()),
                profile.getRole()
        );
    }

    public ProfileDTO toProfileDTO(Profile profile) {
        return new ProfileDTO(
                profile.getId(),
                profile.getFirstName(),
                profile.getSecondName(),
                profile.getUsername(),
                profile.getDateOfBirth(),
                profile.getDescription(),
                profile.getEmail(),
                profile.getPlaceOfResidence(),
                toSettingsLinks(profile.getLinks()),
                toCompanyDTO(profile.getCompany()),
                profile.getRole()
        );
    }

    private List<ContactDTO> toShowedContacts(List<Profile> contacts) {
        List<ContactDTO> contactsDTO = new ArrayList<>();
        contacts.stream()
                .sorted((c1, c2) -> Integer.compare(c2.getOverallRating().getRating(),
                                                    c1.getOverallRating().getRating()))
                .limit(5)
                .forEach(contact -> contactsDTO.add(toContactDTO(contact)));

        return contactsDTO;
    }

    public List<ContactDTO> toDefaultProfileContacts(Profile profile) {
        List<ContactDTO> contactsDTO = new ArrayList<>();

        List<Profile> contacts = profile.getContacts();
        contacts.forEach(contact -> contactsDTO.add(toContactDTO(contact)));
        contactsDTO.sort(Comparator.comparing(ContactDTO::getRating).reversed());

        return contactsDTO;
    }

    public ContactDTO toContactDTO(Profile contact) {
        return new ContactDTO(
                contact.getId(),
                contact.getFirstName(),
                contact.getSecondName(),
                contact.getUsername(),
                contact.getOverallRating().getRating(),
                getImage(contact.getProfileImage())
        );
    }

    public ContactDTO toContactDTO(Profile contact, int categoryRating) {
        return new ContactDTO(
                contact.getId(),
                contact.getFirstName(),
                contact.getSecondName(),
                contact.getUsername(),
                categoryRating,
                getImage(contact.getProfileImage())
        );
    }

    public List<RatingGroupDTO> toRatingGroups(List<Category> categories, List<Rating> ratings,
                                               List<GroupRating> groupRatings, List<Grade> grades) {
        List<RatingDTO> ratingDTOS = toRatingsDTO(categories, ratings, grades);
        Map<Long, RatingGroupDTO> ratingGroupDTOS = toRatingGroups(categories, groupRatings);

        for (RatingDTO rating : ratingDTOS) {
            long groupId = rating.getCategory().getCategoryGroup().getId();
            ratingGroupDTOS.get(groupId).getRatings().add(rating);
        }

        return new ArrayList<>(ratingGroupDTOS.values());
    }

    private Map<Long, RatingGroupDTO> toRatingGroups(List<Category> categories, List<GroupRating> groupRatings) {
        Map<Long, RatingGroupDTO> categoryGroupToRatingGroupDTO = new HashMap<>();
        Map<Long, GroupRating> categoryGroupToRatingGroup = new HashMap<>();

        for (GroupRating groupRating : groupRatings) {
            CategoryGroup categoryGroup = groupRating.getCategoryGroup();
            categoryGroupToRatingGroup.put(categoryGroup.getId(), groupRating);
        }

        for (Category category : categories) {
            CategoryGroup group = category.getCategoryGroup();
            GroupRating groupRating = categoryGroupToRatingGroup.get(group.getId());
            categoryGroupToRatingGroupDTO.putIfAbsent(group.getId(), createRatingGroupDTO(group, groupRating));
        }

        return categoryGroupToRatingGroupDTO;
    }

    private RatingGroupDTO createRatingGroupDTO(CategoryGroup group, GroupRating groupRating) {
        if (groupRating == null)
            return new RatingGroupDTO(
                    group.getTitle(),
                    toCategoryGroupDTO(group),
                    group.getDescription(),
                    group.getColor()
            );

        return new RatingGroupDTO(
                groupRating.getId(),
                group.getTitle(),
                toCategoryGroupDTO(group),
                groupRating.getRating(),
                group.getDescription(),
                group.getColor()
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
                category.getCategoryType(),
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
                categoryGroup.getDescription(),
                categoryGroup.getColor(),
                categoryGroup.getWeight()
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

    public List<CategoryDTO> toCategoriesList(List<Category> categories) {
        List<CategoryDTO> categoryDTOList = new ArrayList<>();
        categories.forEach(category -> categoryDTOList.add(toCategoryDTO(category)));
        return categoryDTOList;
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

    private boolean canAddToContacts(Profile profile, Account account) {
        boolean unauthorized = account == null;
        boolean ownProfile = account != null && profile.getId() == account.getProfile().getId();
        boolean alreadyInContacts = account != null && profile.getContacts().contains(account.getProfile());

        return !unauthorized && !ownProfile && !alreadyInContacts;
    }

    public List<ExternalLinkDTO> toExternalLinks(List<ExternalLink> externalLinks) {
        if (externalLinks == null)
            return new ArrayList<>();

        List<ExternalLinkDTO> links = new ArrayList<>();
        externalLinks.stream()
                     .filter(externalLink -> externalLink.getLink() != null && !externalLink.getLink().isEmpty())
                     .forEach(externalLink -> links.add(toExternalLink(externalLink)));

        return links;
    }

    public List<ExternalLinkDTO> toSettingsLinks(List<ExternalLink> externalLinks) {
        if (externalLinks == null || externalLinks.isEmpty())
            return emptyLinks();

        List<ExternalLinkDTO> links = new ArrayList<>();
        externalLinks.forEach(externalLink -> links.add(toExternalLink(externalLink)));

        return links;
    }

    public List<ExternalLinkDTO> emptyLinks() {
        List<ExternalLinkDTO> emptyLinks = new ArrayList<>();

        for (int i = 0; i < 3; ++i) {
            emptyLinks.add(new ExternalLinkDTO());
        }

        return emptyLinks;
    }

    public ExternalLinkDTO toExternalLink(ExternalLink externalLink) {
        return new ExternalLinkDTO(
                externalLink.getId(),
                externalLink.getTitle(),
                externalLink.getLink()
        );
    }

    public CompanyDTO toCompanyDTO(Company company) {
        if (company == null)
            return new CompanyDTO();

        return new CompanyDTO(
                company.getId(),
                company.getName(),
                company.getDescription()
        );
    }

    public static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException | DataFormatException ioe) {
            //
        }
        return outputStream.toByteArray();
    }

    public ImageDTO toImage(Image image) {
        return new ImageDTO(image.getId(), image.getName(), decompressBytes(image.getContent()));
    }

    public ImageDTO getImage(Image image) {
        if (image == null)
            return new ImageDTO();

        return toImage(image);
    }
}
