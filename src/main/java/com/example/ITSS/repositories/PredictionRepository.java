package com.example.ITSS.repositories;

import com.example.ITSS.models.Prediction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PredictionRepository extends JpaRepository<Prediction, String> {
}
