package com.qa.repo;

import com.qa.domain.Box;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoxesRepository extends JpaRepository<Box, Long> {

    Box findByBoxName(String boxName);

}
