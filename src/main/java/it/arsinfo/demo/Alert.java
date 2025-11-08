package it.arsinfo.demo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="alerts")
public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "alarm")
    private long alarm;

    @Column(name = "uei")
    private String uei;

    @Column(name = "label")
    private String label;

    @Column(name = "msg")
    private String msg;

    public long getId() {
        return id;
    }

    public long getAlarm() {
        return alarm;
    }

    public void setAlarm(long alarmid) {
        this.alarm = alarmid;
    }

    public String getUei() {
        return uei;
    }

    public void setUei(String uei) {
        this.uei = uei;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Alert{" +
                "id=" + id +
                ", alarm=" + alarm +
                ", uei='" + uei + '\'' +
                ", label='" + label + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}