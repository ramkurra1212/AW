package com.hcl.aw.controller;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.hcl.aw.model.WasherEntity;
import com.hcl.aw.repository.WasherRepository;
import com.hcl.aw.service.WasherService;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ WasherRepository.class })
public class WasherControllerTest {

	@Mock
	WasherRepository washerRepository;

	@Mock
	WasherService washerService;

	@InjectMocks
	WasherController washerController = new WasherController();

	@Before
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}
	@Test
	public void testGetAllWahsers() {
		WasherEntity washer = new WasherEntity();
		washer.setwashingId(1);
		WasherEntity washer1 = new WasherEntity();
		washer1.setwashingId(2);
		List<WasherEntity> listW = new ArrayList<>();
		listW.add(washer);
		listW.add(washer1);
		List<WasherEntity> result = washerController.getAllWahsers();
		Mockito.when(washerRepository.findAll()).thenReturn(listW);
		Assert.assertNotNull(result);
		//Assert.assertEquals(result.size(), listW.size());
	}

	@Test
	public void testFindBywashingId() {
		WasherEntity washer = new WasherEntity();
		when(washerService.getWasherById(Mockito.anyInt())).thenReturn(washer);
		WasherEntity result = washerController.getWasher(1);
		Assert.assertNotNull(result);
	}

	@Test
	public void testSaveWasher() {
		WasherEntity washer = new WasherEntity();
		washerController.saveWasher(washer);
	}

	@Test
	public void testUpdate() {
		WasherEntity washer = new WasherEntity();
		WasherEntity result = washerController.update(washer);
		Assert.assertNotNull(result);
	}

	@Test
	public void testDeleteWasher() {
		washerController.deleteWasher(12);
	}

	@Test
	public void testStart() throws InterruptedException {
		String result = washerController.start();
		Assert.assertNotNull(result);
	}

	@Test
	public void testStop() throws InterruptedException {
		String result = washerController.stop();
		Assert.assertNotNull(result);
	}
}