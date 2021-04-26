package com.cgi.dentistapp.service;

import com.cgi.dentistapp.dao.DentistVisitDao;
import com.cgi.dentistapp.dao.PossibleDentistVisitDao;
import com.cgi.dentistapp.dto.DentistVisitDTO;
import com.cgi.dentistapp.dto.PossibleDentistVisitDTO;
import com.cgi.dentistapp.entity.DentistVisitEntity;
import com.cgi.dentistapp.entity.PossibleDentistVisitEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class PossibleDentistVisitService {

    @Autowired
    private PossibleDentistVisitDao possibleDentistVisitDao;


    public void addData(PossibleDentistVisitDTO possibleDentistVisitDTO) {
        possibleDentistVisitDao.save(new PossibleDentistVisitEntity(possibleDentistVisitDTO));
    }

    public List<PossibleDentistVisitDTO> findAllPossibleVisits() {
        return this.possibleDentistVisitDao.findAll()
                .stream()
                .map(PossibleDentistVisitDTO::new)
                .collect(Collectors.toList());
    }
}
