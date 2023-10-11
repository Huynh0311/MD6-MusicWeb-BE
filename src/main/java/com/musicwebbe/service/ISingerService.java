package com.musicwebbe.service;
import com.musicwebbe.model.Singer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ISingerService extends IService<Singer> {
    Singer findSingerBySongID(int id);
    List<Singer> findListSingerBySongID(int id);

    Singer findSingerByAccountID(int id);
}
