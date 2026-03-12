package com.example.burnchuck.domain.notification.repository;

import com.example.burnchuck.common.entity.Follow;
import com.example.burnchuck.common.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    // 팔로워 목록 조회
    @Query("""
        SELECT f
        FROM Follow f
        JOIN FETCH f.follower
        WHERE f.followee = :followee
        """)
    List<Follow> findAllByFollowee(@Param("followee") User followee);
}
