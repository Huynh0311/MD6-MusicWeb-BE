package com.musicwebbe.repository;

import com.musicwebbe.model.Genres;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IGenresRepository extends JpaRepository<Genres,Integer> {

}
