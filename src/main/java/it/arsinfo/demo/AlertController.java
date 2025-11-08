package it.arsinfo.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class AlertController {

    @Autowired
    private AlertRepository alertRepository;

    @GetMapping("/alerts")
    public AlertList getAllAlerts() {
        final AlertList alertList = new AlertList();
        alertRepository.findAll().forEach(alertList::add);
        return alertList;
    }

    @GetMapping("/alerts?uei={uei}")
    public List<Alert> getAlertsByUei(@PathVariable String uei) {
        return alertRepository.findByUei(uei);
    }

    @GetMapping("/alerts?alarm={alarm}")
    public List<Alert> getAlertsByAlarm(@PathVariable Long alarm) {
        return alertRepository.findByAlarm(alarm);
    }

    @GetMapping("/alerts/{id}")
    public ResponseEntity<Alert> getAlertById(@PathVariable Long id) {
        Alert alert =  alertRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Alert not exist with id :" + id));
        return ResponseEntity.ok(alert);
    }

    @PostMapping("/alerts")
    public ResponseEntity<Void> createAlert(@RequestBody Alert alert) {
        Alert saved = alertRepository.save(alert);
        var url = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .build(saved.getId());
        return ResponseEntity.created(url).build();
    }

    @PutMapping("/alert/{id}")
    public ResponseEntity<Void> updateAlert(@PathVariable Long id, @RequestBody Alert alertDetails){
        Alert alert = alertRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Alert not exist with id :" + id));

        alert.setAlarm(alertDetails.getAlarm());
        alert.setUei(alertDetails.getUei());
        alert.setLabel(alertDetails.getLabel());
        alert.setMsg(alertDetails.getMsg());

        alertRepository.save(alert);
        return ResponseEntity.noContent().build();
    }

    // delete alert rest api
    @DeleteMapping("/alerts/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteAlert(@PathVariable Long id){
        Alert alert = alertRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Alert not exist with id :" + id));

        alertRepository.delete(alert);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

}
