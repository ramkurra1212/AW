package com.hcl.aw.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hcl.aw.model.WasherEntity;

@Repository
public interface WasherRepository extends CrudRepository<WasherEntity, Integer> {

	List<WasherEntity> findAll();

	List<WasherEntity> findBywashingId(int id);
	

}
