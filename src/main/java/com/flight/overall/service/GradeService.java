package com.flight.overall.service;


import com.flight.overall.dto.GradeDTO;
import com.flight.overall.entity.Account;
import com.flight.overall.entity.Grade;
import com.flight.overall.entity.Rating;
import com.flight.overall.repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class GradeService {
    @Autowired
    private GradeRepository gradeRepository;

    public List<Grade> getAccountGrades(Account account) {
        if (account == null)
            return Collections.emptyList();

        return gradeRepository.findAllAccountGrades(account.getId());
    }

    public void saveGrade(Account account, Rating rating, GradeDTO gradeDTO) {
        Grade grade = gradeRepository.findById(gradeDTO.getId()).orElse(new Grade());

        grade.setRating(rating);
        grade.setAccount(account);
        grade.setValue(gradeDTO.getCurrentGrade());

        gradeRepository.save(grade);
    }
}