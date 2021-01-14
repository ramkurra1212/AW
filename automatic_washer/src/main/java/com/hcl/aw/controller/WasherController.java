package com.hcl.aw.controller;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

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
import com.hcl.aw.service.WasherService;
import com.hcl.aw.util.States;

@RequestMapping(value = "/api")
@RestController
public class WasherController {

	@Autowired
	WasherService washerService;
	AtomicBoolean isWashing = new AtomicBoolean(false);
	ReentrantLock lock = new ReentrantLock();

	@GetMapping("/AllWahsers")
	public List<WasherEntity> getAllWahsers() {
		return washerService.getAllWahsers();
	}

	@GetMapping("/Wahser/{washing_id}")
	public WasherEntity getWasher(@PathVariable("washing_id") int washing_id) {
		return washerService.getWasherById(washing_id);
	}

	@PostMapping("/washer")
	public int saveWasher(@RequestBody WasherEntity washer) {
		washerService.saveOrUpdate(washer);
		return washer.getwashingId();
	}

	@PutMapping("/washer")
	public WasherEntity update(@RequestBody WasherEntity washer) {
		washerService.saveOrUpdate(washer);
		return washer;
	}

	@DeleteMapping("/washer/{washing_id}")
	public void deleteWasher(@PathVariable("washing_id") int id) {
		washerService.delete(id);
	}

	@GetMapping("/start}")
	public String start() throws InterruptedException {

		lock.lock();

		try {
			if (!isWashing.get()) {
				isWashing.set(true);
				new StringBuffer(States.WASHING.toString());

			}

		} catch (NullPointerException e) {
			return "Set up program first";
		}
		return "Started";
	}

	@GetMapping("/stop}")
	public String stop() {
		lock.lock();
		try {
			if (isWashing.get()) {
				isWashing.set(false);
				new StringBuffer(States.OFF.toString());
			}
		} finally {
			lock.unlock();
		}
		return "Stopped";
	}
}
