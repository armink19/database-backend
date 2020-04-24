package com.database.demo.repository;

import com.database.demo.model.Sample;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SampleRepository extends JpaRepository<Sample,Long> {
}
