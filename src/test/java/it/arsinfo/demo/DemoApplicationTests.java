package it.arsinfo.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.util.Assert;

import java.util.List;

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
        alertA.setAlarm(1L);
        alertA.setUei("opennms.org/alert");
        alertA.setLabel("antonio");
        alertA.setMsg("niente di nuovo sul fronte ovest");
        repository.save(alertA);

        Alert alertAA = new Alert();
        alertAA.setAlarm(1L);
        alertAA.setUei("opennms.org/alert");
        alertAA.setLabel("antonio");
        alertAA.setMsg("qualcosa cambia fronte ovest");
        repository.save(alertAA);

        Alert alertB = new Alert();
        alertB.setAlarm(2L);
        alertB.setUei("opennms.org/alert");
        alertB.setLabel("roberta");
        alertB.setMsg("niente di nuovo sul fronte ovest");
        repository.save(alertB);

        Alert alertBB = new Alert();
        alertBB.setAlarm(2L);
        alertBB.setUei("opennms.org/alert");
        alertBB.setLabel("roberta");
        alertBB.setMsg("updated");
        repository.save(alertBB);

        Alert alertC = new Alert();
        alertC.setAlarm(3L);
        alertC.setUei("opennms.org/alert/coordinate");
        alertC.setLabel("valeria");
        alertC.setMsg("niente di nuovo sul fronte ovest");
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
