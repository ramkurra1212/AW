package com.hcl.aw.service;


import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.hcl.aw.model.WasherEntity;
import com.hcl.aw.repository.WasherRepository;


@RunWith(PowerMockRunner.class)
@PrepareForTest({WasherRepository.class})
public class WasherControllerIntegrationTest {
     @Mock
     private TestRestTemplate restTemplate;

     @LocalServerPort
     private int port;

     private String getRootUrl() {
         return "http://localhost:" + port;
     }

     @Before
     public void contextLoads() {

     }
    // @Test
     public void testGetAllWahsers() {
     HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "Wahser",
        HttpMethod.GET, entity, String.class);  
        assertNotNull(response.getBody());
    }

   // @Test
    public void testGetWasherById() {
    	WasherEntity washer = restTemplate.getForObject(getRootUrl() + "/Wahser/1", WasherEntity.class);
        System.out.println(washer.getwashingId());
        assertNotNull(washer);
    }

    
 //  @Test
    public void testCreateWasher() {
    	WasherEntity washer = new WasherEntity();
    	washer.setModel("LG");
    	washer.setSerial("121212");
    	washer.setwashingId(1);;
        ResponseEntity<WasherEntity> postResponse = restTemplate.postForEntity(getRootUrl() + "/washer", washer, WasherEntity.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
    }

}