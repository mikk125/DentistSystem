package com.cgi.dentistapp.dto;

import com.cgi.dentistapp.entity.DentistVisitEntity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

public class DentistVisitDTO {

    private Long id;

    @NotNull
    @Size(min = 1, max = 50)
    private String dentistName;

    @NotNull
    @Size(min = 1, max = 50)
    private String patientName;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date visitDate;

    @NotNull
    @DateTimeFormat(pattern = "HH:mm:ss")
    private Date visitTime;

    private String error;

    public DentistVisitDTO() {
    }

    public DentistVisitDTO(DentistVisitEntity dentistVisit) {
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

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
