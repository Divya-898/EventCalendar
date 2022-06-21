package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.demo.model.EventModel;

public interface EventService {
List <EventModel> getAllEvent();
void saveEvent(EventModel event);
void formForDelete(long id); 
Page<EventModel> findpaginated(int pageno,int pagesize,String sortField, String sortDirection);
}
