package com.example.swe645;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Swe645Repository extends JpaRepository<SurveyModel, Long> {
}

