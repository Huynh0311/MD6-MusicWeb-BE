package com.musicwebbe.repository;

import com.musicwebbe.model.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILikesRepository extends JpaRepository<Likes,Integer> {

}
