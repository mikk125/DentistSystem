package com.cgi.dentistapp.entity;

import com.cgi.dentistapp.dto.DentistVisitDTO;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "dentist_visit")
public class DentistVisitEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "dentist_name")
    private String dentistName;

    @Column(name = "patient_name")
    private String patientName;

    @Basic
    @Temporal(TemporalType.DATE)
    @Column(name = "visit_date")
    private Date visitDate;

    @Basic
    @Temporal(TemporalType.TIME)
    @Column(name = "visit_time")
    private Date visitTime;

    public DentistVisitEntity() {
    }

    public DentistVisitEntity(DentistVisitDTO dentistVisit) {
        this.id = dentistVisit.getId();
        this.dentistName = dentistVisit.getDentistName();
        this.patientName = dentistVisit.getPatientName();
        this.visitDate = dentistVisit.getVisitDate();
        this.visitTime = dentistVisit.getVisitTime();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDentistName() {
        return dentistName;
    }

    public void setDentistName(String dentistName) {
        this.dentistName = dentistName;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }

    public Date getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(Date visitTime) {
        this.visitTime = visitTime;
    }

}
