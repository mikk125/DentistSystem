package com.cgi.dentistapp.dao;

import com.cgi.dentistapp.dto.DentistVisitDTO;
import com.cgi.dentistapp.entity.DentistVisitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface DentistVisitDao extends JpaRepository<DentistVisitEntity, Long> {

    List<DentistVisitEntity> findAllByVisitDateAndVisitTime(Date visitDate, Date visitTime);
}
