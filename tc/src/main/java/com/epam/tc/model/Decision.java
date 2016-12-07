package com.epam.tc.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "decision")
public class Decision implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "decisionId")
    private int id;

    @ManyToOne
    @JoinColumn(name = "KmId")
    private User km;

    @ManyToOne
    @JoinColumn(name = "DmId")
    private User dm;

    private String km_decision;
    private String km_comment;
    private String dm_decision;
    private String dm_comment;

    public void setKm_comment(String km_comment) {
        this.km_comment = km_comment;
    }

    public void setDm_comment(String dm_comment) {
        this.dm_comment = dm_comment;
    }

    public String getKm_comment() {
        return km_comment;
    }

    public String getDm_comment() {
        return dm_comment;
    }

    public Decision() {
    }

    public User getDm() {
        return dm;
    }

    public String getDm_decision() {
        return dm_decision;
    }

    public int getId() {
        return id;
    }

    public User getKm() {
        return km;
    }

    public String getKm_decision() {
        return km_decision;
    }

    public void setDm(User dm) {
        this.dm = dm;
    }

    public void setDm_decision(String dm_decision) {
        this.dm_decision = dm_decision;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setKm(User km) {
        this.km = km;
    }

    public void setKm_decision(String km_decision) {
        this.km_decision = km_decision;
    }
}
