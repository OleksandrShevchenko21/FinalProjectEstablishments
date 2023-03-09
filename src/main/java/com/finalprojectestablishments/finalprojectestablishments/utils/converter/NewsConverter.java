package com.finalprojectestablishments.finalprojectestablishments.utils.converter;

import com.finalprojectestablishments.finalprojectestablishments.dto.NewsDto;

import com.finalprojectestablishments.finalprojectestablishments.entity.news.EventNews;
import com.finalprojectestablishments.finalprojectestablishments.entity.news.GeneralNews;
import com.finalprojectestablishments.finalprojectestablishments.entity.news.PromotionNews;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class NewsConverter {
    public NewsDto generalNewsToNewsDto(GeneralNews generalNews) {
        NewsDto dto = new NewsDto();
        dto.setId(generalNews.getId());
        dto.setGeneralNews(generalNews.getGeneralNews());
        dto.setRestaurantId(generalNews.getRestaurant().getId());
        return dto;
    }
    public NewsDto promotionNewsToNewsDto(PromotionNews promotionNews) {
        NewsDto dto = new NewsDto();
        dto.setId(promotionNews.getId());
        dto.setPromotionNews(promotionNews.getPromotionNews());
        dto.setRestaurantId(promotionNews.getRestaurant().getId());
        return dto;
    }
    public NewsDto eventNewsToNewsDto(EventNews eventNews) {
        NewsDto dto = new NewsDto();
        dto.setId(eventNews.getId());
        dto.setEventNews(eventNews.getEventNews());
        dto.setRestaurantId(eventNews.getRestaurant().getId());
        return dto;
    }

    public List<NewsDto> generalNewsListToNewsDtoList(List<GeneralNews> news) {
        return news.stream().map(this::generalNewsToNewsDto).collect(Collectors.toList());
    }
    public List<NewsDto> promotionNewsListToNewsDtoList(List<PromotionNews> news) {
        return news.stream().map(this::promotionNewsToNewsDto).collect(Collectors.toList());
    }
    public List<NewsDto> eventNewsListToNewsDtoList(List<EventNews> news) {
        return news.stream().map(this::eventNewsToNewsDto).collect(Collectors.toList());
    }
}

