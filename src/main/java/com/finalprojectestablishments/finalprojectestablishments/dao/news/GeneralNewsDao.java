package com.finalprojectestablishments.finalprojectestablishments.dao.news;

import com.finalprojectestablishments.finalprojectestablishments.entity.news.GeneralNews;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GeneralNewsDao extends JpaRepository<GeneralNews,Integer> {

}
