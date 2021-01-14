package com.hcl.aw.service;

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
public class WasherServiceTest {

	@Mock
	WasherRepository washerRepository;

	@Mock
	WasherService washerService;

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
		List<WasherEntity> result = washerService.getAllWahsers();
		Mockito.when(washerRepository.findAll()).thenReturn(listW);
		Assert.assertNotNull(result);
		// Assert.assertEquals(result.size(), listW.size());
	}

	@Test
	public void testFindBywashingId() {
		WasherEntity washer = new WasherEntity();
		when(washerService.getWasherById(Mockito.anyInt())).thenReturn(washer);
		WasherEntity result = washerService.getWasherById(1);
		Assert.assertNotNull(result);
	}

	@Test
	public void testSaveWasher() {
		WasherEntity washer = new WasherEntity();
		washerService.saveOrUpdate(washer);
	}

	@Test
	public void testUpdate() {
		WasherEntity washer = new WasherEntity();
		WasherEntity result = washerService.Update(washer);
		Assert.assertNull(result);
	}

	@Test
	public void testDeleteWasher() {
		washerService.delete(12);
	}

}