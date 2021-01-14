package com.hcl.aw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.aw.model.WasherEntity;
import com.hcl.aw.repository.WasherRepository;

@Service
public class WasherServiceImpl implements WasherService {
	
	@Autowired  
	WasherRepository washerRepository; 

	@Override
	public List<WasherEntity> getAllWahsers() {
		return	washerRepository.findAll();
	}

	@Override
	public WasherEntity getWasherById(int id) {
		return washerRepository.findById(id).get();  
	}

	@Override
	public void saveOrUpdate(WasherEntity washer) {
		washerRepository.save(washer);  

	}

	@Override
	public void delete(int id) {
		washerRepository.deleteById(id);  

	}

}
