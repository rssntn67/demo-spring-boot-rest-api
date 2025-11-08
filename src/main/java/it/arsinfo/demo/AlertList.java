package it.arsinfo.demo;

import java.util.ArrayList;
import java.util.List;

public class AlertList {
    private List<Alert> alerts;

    public List<Alert> getAlerts() {
        return alerts;
    }

    public void setAlerts(List<Alert> alerts) {
        this.alerts = alerts;
    }

    public void add(Alert alert) {
        if (alerts == null)
            alerts = new ArrayList<>();
        alerts.add(alert);
    }
}
