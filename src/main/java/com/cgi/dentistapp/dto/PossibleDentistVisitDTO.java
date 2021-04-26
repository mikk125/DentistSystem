package com.cgi.dentistapp.dto;

import com.cgi.dentistapp.entity.DentistVisitEntity;
import com.cgi.dentistapp.entity.PossibleDentistVisitEntity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class PossibleDentistVisitDTO {

    private Long id;

    @NotNull
    @Size(min = 1, max = 50)
    private String dentistName;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date visitDate;

    @NotNull
    @DateTimeFormat(pattern = "HH:mm:ss")
    private Date visitTime;

    public PossibleDentistVisitDTO() {
    }

    public PossibleDentistVisitDTO(PossibleDentistVisitEntity dentistVisit) {
        this.id = dentistVisit.getId();
        this.dentistName = dentistVisit.getDentistName();
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
