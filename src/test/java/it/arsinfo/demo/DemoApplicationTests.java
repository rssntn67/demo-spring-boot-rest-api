package it.arsinfo.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.util.Assert;

import java.util.Date;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    AlertRepository repository;

    @Autowired
    AlertController controller;

	@Test
	void contextLoads() {
        Assert.notNull(repository, "repository should not be null");
        Assert.notNull(controller, "controller should not be null");
	}

    private void populate() {
        repository.deleteAll();

        Alert alertA = new Alert();
        alertA.setStatus("CRITICAL");
        alertA.setAttributes("opennms.org/alert");
        alertA.setAlarmAckUser("antonio");
        alertA.setDescription("niente di nuovo sul fronte ovest");
        alertA.setTimestamp(new Date());
        repository.save(alertA);

        Alert alertAA = new Alert();
        alertAA.setStatus("OK");
        alertAA.setAttributes("opennms.org/alert");
        alertAA.setAlarmAckUser("antonio");
        alertAA.setDescription("qualcosa cambia fronte ovest");
        alertAA.setTimestamp(new Date());
        repository.save(alertAA);

        Alert alertB = new Alert();
        alertB.setStatus("WARNING");
        alertB.setAttributes("opennms.org/alert");
        alertB.setAlarmAckUser("roberta");
        alertB.setDescription("niente di nuovo sul fronte ovest");
        alertB.setTimestamp(new Date());
        repository.save(alertB);

        Alert alertBB = new Alert();
        alertBB.setStatus("ACKNOWLEDGED");
        alertBB.setAttributes("opennms.org/alert");
        alertBB.setAlarmAckUser("roberta");
        alertBB.setDescription("updated");
        alertBB.setTimestamp(new Date());
        repository.save(alertBB);

        Alert alertC = new Alert();
        alertC.setStatus("OK");
        alertC.setAttributes("opennms.org/alert/coordinate");
        alertC.setAlarmAckUser("valeria");
        alertC.setDescription("niente di nuovo sul fronte ovest");
        alertC.setTimestamp(new Date());
        repository.save(alertC);


    }

    @Test
    void repositoryTest() {
        populate();
        System.out.println("printing alert by alarm 1L");
        repository.findByAlarm(1L).forEach(System.out::println);

        System.out.println("printing alert by uei upennms.org/alert");
        repository.findByUei("opennms.org/alert").forEach(System.out::println);

        System.out.println("printing alert by label roberta");
        repository.findByLabel("roberta").forEach(System.out::println);

    }

    @Test
    void getReturnProperData() {
        populate();

        AlertList response =    this.restTemplate.getForObject("http://localhost:" + port + "/api/v1/alerts", AlertList.class);
        response.getAlerts().forEach(System.out::println);

    }


    }
