package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.EventModel;

@Repository
public interface EventRepository extends JpaRepository<EventModel, Long>{

}