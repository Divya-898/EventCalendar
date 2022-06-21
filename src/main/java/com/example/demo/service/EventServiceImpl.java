package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.model.EventModel;
import com.example.demo.repository.EventRepository;


@Service
public class EventServiceImpl implements EventService{
@Autowired
 private EventRepository repository;
	
	@Override
	public List<EventModel> getAllEvent() {
		// TODO Auto-generated method stub
		
		return repository.findAll();
	}

	@Override
	public void saveEvent(EventModel event) {
		// TODO Auto-generated method stub
		this.repository.save(event);
		
	}

	@Override
	public void formForDelete(long id) {
		// TODO Auto-generated method stub
		this.repository.deleteById(id);
		
	}

	@Override
	public Page<EventModel> findpaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.repository.findAll(pageable);
	}


	
	
}
