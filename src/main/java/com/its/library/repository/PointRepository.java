package com.its.library.repository;

import com.its.library.entity.PointEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointRepository extends JpaRepository<PointEntity,Long> {
}
