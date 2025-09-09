package com.tnd.laws.repository;

import com.tnd.laws.model.Law;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LawRepository extends JpaRepository<Law, Long> {

    Page<Law> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String q1, String q2, Pageable page);
    Page<Law> findByTitleYorubaContainingIgnoreCaseOrDescriptionYorubaContainingIgnoreCase(String q1, String q2, Pageable page);

    Page<Law> findByCommon(boolean common, Pageable page);

    @Query("SELECT DISTINCT law FROM Law law LEFT JOIN FETCH law.chapters chapters LEFT JOIN FETCH chapters.sections")
    Page<Law> findAllLawsWithChaptersAndSections(Pageable page);
}
