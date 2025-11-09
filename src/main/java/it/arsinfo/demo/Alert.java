package it.arsinfo.demo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name="alerts")
public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "status")
    private String status;

    public Long getId() {
        return this.id;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAlarmAckUser() {
        return alarmAckUser;
    }

    public void setAlarmAckUser(String alarmAckUser) {
        this.alarmAckUser = alarmAckUser;
    }

    public String getAttributes() {
        return attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "timestamp")
    private Date timestamp;

    @Column(name = "description")
    private String description;

    @Column(name = "alarmAckUser")
    private String alarmAckUser;

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Alert alert)) return false;
        return id == alert.id && Objects.equals(status, alert.status) && Objects.equals(timestamp, alert.timestamp) && Objects.equals(description, alert.description) && Objects.equals(alarmAckUser, alert.alarmAckUser) && Objects.equals(attributes, alert.attributes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, timestamp, description, alarmAckUser, attributes);
    }

    @Column(name = "attributes")
    private String attributes;

    @Override
    public String toString() {
        return "Alert{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", timestamp=" + timestamp +
                ", description='" + description + '\'' +
                ", alarmAckUser='" + alarmAckUser + '\'' +
                ", attributes='" + attributes + '\'' +
                '}';
    }
}