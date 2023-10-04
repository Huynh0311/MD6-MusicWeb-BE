package com.musicwebbe.repository;

import com.musicwebbe.model.Role;
import com.musicwebbe.model.Singer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISingerRepository extends JpaRepository<Singer,Integer> {

}
