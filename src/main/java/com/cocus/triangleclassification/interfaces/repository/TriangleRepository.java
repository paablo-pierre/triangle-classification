package com.cocus.triangleclassification.interfaces.repository;

import com.cocus.triangleclassification.domain.Triangle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TriangleRepository extends JpaRepository<Triangle, Long> {

}
