package com.flight.overall.repository;

import com.flight.overall.entity.Contact;
import com.flight.overall.entity.Profile;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContactRepository extends CrudRepository<Contact, Long> {


    @Query("SELECT p " +
            " FROM Profile p " +
            " LEFT JOIN Rating r ON (r.profile.id = p.id AND r.category.id =:categoryId) " +
            "WHERE (" +
            "       p.id IN (SELECT c.profile2.id" +
            "                  FROM Contact c " +
            "                 WHERE c.profile1.id =:profileId" +
            "                )" +
            "        OR p.id IN (SELECT c.profile1.id " +
            "                      FROM Contact c" +
            "                     WHERE c.profile2.id =:profileId" +
            "                    )" +
            "       ) " +
            "ORDER BY r.rating DESC NULLS LAST")
    List<Profile> getSortedContacts(@Param("profileId") long profileId,
                                    @Param("categoryId") long categoryId);

}
