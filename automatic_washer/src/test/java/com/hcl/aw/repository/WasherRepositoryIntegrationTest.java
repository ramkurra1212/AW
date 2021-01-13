package com.hcl.aw.repository;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hcl.aw.model.WasherEntity;
import com.hcl.aw.service.WasherServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WasherServiceImpl.class)
public class WasherRepositoryIntegrationTest {

    @Autowired
    WasherRepository repository;

    @Test
    public void myTest() throws Exception {

    	WasherEntity washer = new WasherEntity();
    	washer.setwashingId(10);
    	washer.setModel("LG");
        washer.setSerial("12M");

        repository.save(washer);

        List<?> queryResult = repository.findBywashingId(1);

        assertFalse(queryResult.isEmpty());
        assertNotNull(queryResult.get(0));
    }
}