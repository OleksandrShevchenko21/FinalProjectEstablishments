package com.finalprojectestablishments.finalprojectestablishments.services;

import com.finalprojectestablishments.finalprojectestablishments.dao.news.*;
import com.finalprojectestablishments.finalprojectestablishments.entity.news.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class NewsService {
    private GeneralNewsDao generalNewsDao;
    private PromotionNewsDao promotionNewsDao;
    private EventNewsDao eventNewsDao;

    public List<GeneralNews> findAllGeneral() {
        return generalNewsDao.findAll();
    }

    public GeneralNews findByIdGeneral(int id) {
        return generalNewsDao.findById(id).get();
    }

    public void saveGeneral(GeneralNews generalNews) {

        generalNewsDao.save(generalNews);
    }

    public void updateGeneral(int id, GeneralNews generalNews) {
        generalNewsDao.save(generalNews);
    }

    public void deleteGeneral(int id) {
        generalNewsDao.deleteById(id);
    }
//    --------------------------------------------------------------------------------------
public List<PromotionNews> findAllPromotion() {
    return promotionNewsDao.findAll();
}

    public PromotionNews findByIdPromotion(int id) {
        return promotionNewsDao.findById(id).get();
    }

    public void savePromotion(PromotionNews promotionNews) {

        promotionNewsDao.save(promotionNews);
    }

    public void updatePromotion(int id, PromotionNews promotionNews) {
        promotionNewsDao.save(promotionNews);
    }

    public void deletePromotion(int id) {
        promotionNewsDao.deleteById(id);
    }
    //    --------------------------------------------------------------------------------------
    public List<EventNews> findAllEvent() {
        return eventNewsDao.findAll();
    }

    public EventNews findByIdEvent(int id) {
        return eventNewsDao.findById(id).get();
    }

    public void saveEvent(EventNews eventNews) {

        eventNewsDao.save(eventNews);
    }

    public void updateEvent(int id, EventNews eventNews) {
        eventNewsDao.save(eventNews);
    }

    public void deleteEvent(int id) {
        eventNewsDao.deleteById(id);
    }

}
