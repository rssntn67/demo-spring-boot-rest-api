package it.arsinfo.demo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlertRepository extends JpaRepository<Alert, Long> {
    List<Alert> findByStatus(String status);

    List<Alert> findByDescription(String description);

    List<Alert> findByAlarmAckUser(String alarmAckUser);

    List<Alert> findByAttributes(String attributes);

}
