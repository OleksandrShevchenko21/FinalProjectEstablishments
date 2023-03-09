package com.finalprojectestablishments.finalprojectestablishments.controllers;

import com.finalprojectestablishments.finalprojectestablishments.dto.NewsDto;
import com.finalprojectestablishments.finalprojectestablishments.entity.Restaurant;
import com.finalprojectestablishments.finalprojectestablishments.entity.news.EventNews;
import com.finalprojectestablishments.finalprojectestablishments.entity.news.GeneralNews;
import com.finalprojectestablishments.finalprojectestablishments.entity.news.PromotionNews;
import com.finalprojectestablishments.finalprojectestablishments.services.NewsService;
import com.finalprojectestablishments.finalprojectestablishments.services.RestaurantService;
import com.finalprojectestablishments.finalprojectestablishments.utils.converter.NewsConverter;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/news")
public class NewsController {

    private NewsConverter newsConverter;
    private NewsService newsService;
    private RestaurantService restaurantService;
    //    ----------------------------------------------------------------
    @GetMapping("/general")
    public ResponseEntity<List<NewsDto>> getAllGeneralNews() {
        List<GeneralNews> news = newsService.findAllGeneral();
        List<NewsDto> newsDtoList = newsConverter.generalNewsListToNewsDtoList(news);
        return new ResponseEntity<>(newsDtoList, HttpStatusCode.valueOf(200));
    }

    @GetMapping("/general/{id}")
    public ResponseEntity<NewsDto> getOneGeneralNews(@PathVariable int id) {
        GeneralNews generalNews = newsService.findByIdGeneral(id);
        NewsDto newsDto = newsConverter.generalNewsToNewsDto(generalNews);
        return new ResponseEntity<>(newsDto, HttpStatusCode.valueOf(200));
    }

    @PostMapping("/general/{restaurantId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveGeneralNews(@PathVariable int restaurantId,
                                @RequestBody NewsDto newsDto) {

        GeneralNews generalNews = new GeneralNews();
        Restaurant restaurant = restaurantService.findById(restaurantId);
        generalNews.setRestaurant(restaurant);
        generalNews.setGeneralNews(newsDto.getGeneralNews());

        newsService.saveGeneral(generalNews);
    }

    @PatchMapping("/general/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void updateGeneralNews(@PathVariable int id,
                                  @RequestBody NewsDto newsDto) {

        GeneralNews generalNews = newsService.findByIdGeneral(id);
        generalNews.setGeneralNews(newsDto.getGeneralNews());
        newsService.saveGeneral(generalNews);
    }

    @DeleteMapping("/general/{id}")
    public void deleteNews(@PathVariable int id) {

        newsService.deleteGeneral(id);
    }
//  ------------------------------------------------------------------------------------------
@GetMapping("/promotion")
public ResponseEntity<List<NewsDto>> getAllPromotionNews() {
    List<PromotionNews> news = newsService.findAllPromotion();
    List<NewsDto> newsDtoList = newsConverter.promotionNewsListToNewsDtoList(news);
    return new ResponseEntity<>(newsDtoList, HttpStatusCode.valueOf(200));
}

    @GetMapping("/promotion/{id}")
    public ResponseEntity<NewsDto> getOnePromotionNews(@PathVariable int id) {
        PromotionNews promotionNews = newsService.findByIdPromotion(id);
        NewsDto newsDto = newsConverter.promotionNewsToNewsDto(promotionNews);
        return new ResponseEntity<>(newsDto, HttpStatusCode.valueOf(200));
    }

    @PostMapping("/promotion/{restaurantId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void savePromotionNews(@PathVariable int restaurantId,
                                @RequestBody NewsDto newsDto) {

        PromotionNews promotionNews = new PromotionNews();
        Restaurant restaurant = restaurantService.findById(restaurantId);
        promotionNews.setRestaurant(restaurant);
        promotionNews.setPromotionNews(newsDto.getPromotionNews());

        newsService.savePromotion(promotionNews);
    }

    @PatchMapping("/promotion/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void updatePromotionNews(@PathVariable int id,
                           @RequestBody NewsDto newsDto) {

        PromotionNews promotionNews = newsService.findByIdPromotion(id);
        promotionNews.setPromotionNews(newsDto.getPromotionNews());
        newsService.savePromotion(promotionNews);
    }

    @DeleteMapping("/promotion/{id}")
    public void deletePromotionNews(@PathVariable int id) {

        newsService.deletePromotion(id);
    }
    //  ------------------------------------------------------------------------------------------
    @GetMapping("/event")
    public ResponseEntity<List<NewsDto>> getAllEventNews() {
        List<EventNews> news = newsService.findAllEvent();
        List<NewsDto> newsDtoList = newsConverter.eventNewsListToNewsDtoList(news);
        return new ResponseEntity<>(newsDtoList, HttpStatusCode.valueOf(200));
    }

    @GetMapping("/event/{id}")
    public ResponseEntity<NewsDto> getOneEventNews(@PathVariable int id) {
        EventNews eventNews = newsService.findByIdEvent(id);
        NewsDto newsDto = newsConverter.eventNewsToNewsDto(eventNews);
        return new ResponseEntity<>(newsDto, HttpStatusCode.valueOf(200));
    }

    @PostMapping("/event/{restaurantId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveEventNews(@PathVariable int restaurantId,
                                  @RequestBody NewsDto newsDto) {

        EventNews eventNews = new EventNews();
        Restaurant restaurant = restaurantService.findById(restaurantId);
        eventNews.setRestaurant(restaurant);
        eventNews.setEventNews(newsDto.getEventNews());

        newsService.saveEvent(eventNews);
    }

    @PatchMapping("/event/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void updateEventNews(@PathVariable int id,
                                    @RequestBody NewsDto newsDto) {

        EventNews eventNews = newsService.findByIdEvent(id);
        eventNews.setEventNews(newsDto.getEventNews());
        newsService.saveEvent(eventNews);
    }

    @DeleteMapping("/event/{id}")
    public void deleteEventNews(@PathVariable int id) {

        newsService.deleteEvent(id);
    }
}
