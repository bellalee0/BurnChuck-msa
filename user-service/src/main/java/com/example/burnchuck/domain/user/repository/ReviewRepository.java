package com.example.burnchuck.domain.user.repository;

import com.example.burnchuck.common.entity.Review;
import com.example.burnchuck.common.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("""
          SELECT AVG(r.rating)
          FROM Review r
          WHERE r.reviewee = :reviewee
          """)
    Double findAvgRatesByReviewee(@Param("reviewee") User reviewee);
}
