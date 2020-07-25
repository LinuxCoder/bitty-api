package com.levoncorp.bitty.database.repository;

import com.levoncorp.bitty.database.entity.BtcValuePrediction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface BtcValuePredictionRepository extends JpaRepository<BtcValuePrediction, Long> {
    List<BtcValuePrediction> findByTime(long time);

    BtcValuePrediction findTopByOrderByTimeDesc();
}
