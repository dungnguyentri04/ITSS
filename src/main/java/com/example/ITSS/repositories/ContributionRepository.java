package com.example.ITSS.repositories;

import com.example.ITSS.models.Contribution;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContributionRepository extends JpaRepository<Contribution, Long> {
}
