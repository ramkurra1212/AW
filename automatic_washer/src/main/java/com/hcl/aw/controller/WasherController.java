package com.hcl.aw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.aw.model.WasherEntity;
import com.hcl.aw.service.WasherExecutorService;
import com.hcl.aw.service.WasherService;
@RequestMapping(value = "/api")
@RestController
public class WasherController {

	@Autowired
	WasherService washerService;
	@Autowired
	WasherExecutorService washerExecutorService;

	@GetMapping("/AllWahsers")
	private List<WasherEntity> getAllWahsers() {
		return washerService.getAllWahsers();
	}

	@GetMapping("/Wahser/{washing_id}")
	private WasherEntity getWasher(@PathVariable("washing_id") int washing_id) {
		return washerService.getWasherById(washing_id);
	}

	@PostMapping("/washer")
	private int saveWasher(@RequestBody WasherEntity washer) {
		washerService.saveOrUpdate(washer);
		return washer.getwashingId();
	}
	

	@PutMapping("/washer")
	private WasherEntity update(@RequestBody WasherEntity washer) {
		washerService.saveOrUpdate(washer);
		return washer;
	}
	@DeleteMapping("/washer/{washing_id}")  
	private void deleteBook(@PathVariable("washing_id") int id)   
	{  
		washerService.delete(id);  
	} 
	@GetMapping("/start}")
    public String start() throws InterruptedException {
        try {
        	washerExecutorService.startProgram();
        } catch (NullPointerException e) {
            return "Set up program first";
        }
        return "Started";
    }
	  @GetMapping("/stop}")
	    public String stop() {
		  washerExecutorService.stopProgram();
	        return "Stopped";
	    }
}
