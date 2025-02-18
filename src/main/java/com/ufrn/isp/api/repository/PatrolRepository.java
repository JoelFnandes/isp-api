package com.ufrn.isp.api.repository;

import com.ufrn.isp.api.domain.Patrol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatrolRepository extends JpaRepository<Patrol, Long> {
}
