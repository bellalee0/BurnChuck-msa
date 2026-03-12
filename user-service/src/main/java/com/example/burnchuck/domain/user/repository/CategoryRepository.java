package com.example.burnchuck.domain.user.repository;

import com.example.burnchuck.common.entity.Category;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findByCodeIn(List<String> categoryCodeList);
}
