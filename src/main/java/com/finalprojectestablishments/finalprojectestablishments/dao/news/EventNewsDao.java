package com.finalprojectestablishments.finalprojectestablishments.dao.news;

import com.finalprojectestablishments.finalprojectestablishments.entity.news.EventNews;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventNewsDao extends JpaRepository<EventNews,Integer> {
}
