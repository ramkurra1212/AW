package com.hcl.aw.service;

import java.util.List;

import com.hcl.aw.model.WasherEntity;

public interface WasherService {


	List<WasherEntity> getAllWahsers();

	WasherEntity getWasherById(int id);

	void saveOrUpdate(WasherEntity washer);

	void delete(int id);

}
