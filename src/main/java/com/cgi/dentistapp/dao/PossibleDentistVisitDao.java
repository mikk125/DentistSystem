package com.cgi.dentistapp.dao;

import com.cgi.dentistapp.entity.DentistVisitEntity;
import com.cgi.dentistapp.entity.PossibleDentistVisitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PossibleDentistVisitDao extends JpaRepository<PossibleDentistVisitEntity, Long> {

}
