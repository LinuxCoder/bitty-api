package com.levoncorp.bitty.database.repository;

import com.levoncorp.bitty.database.entity.BtcValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface BtcValueRepository extends JpaRepository<BtcValue, Long> {
    BtcValue findTopByOrderByIdDesc();
}
