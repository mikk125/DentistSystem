package com.cgi.dentistapp.service;

import com.cgi.dentistapp.dao.DentistVisitDao;
import com.cgi.dentistapp.dto.DentistVisitDTO;
import com.cgi.dentistapp.entity.DentistVisitEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class DentistVisitService {

    @Autowired
    private DentistVisitDao dentistVisitDao;

    public void addVisit(DentistVisitDTO dentistVisitDTO) {
        dentistVisitDao.save(new DentistVisitEntity(dentistVisitDTO));
    }

    public void putVisit(DentistVisitDTO newVisit) {
        DentistVisitEntity entity = dentistVisitDao.findOne(newVisit.getId());
        entity.setDentistName(newVisit.getDentistName());
        entity.setPatientName(newVisit.getPatientName());
        entity.setVisitDate(newVisit.getVisitDate());
        entity.setVisitTime(newVisit.getVisitTime());
        dentistVisitDao.save(entity);
    }

    public DentistVisitDTO findById(Long id) {
        DentistVisitEntity entity = dentistVisitDao.findOne(id);
        return entity != null ? new DentistVisitDTO(entity) : null;
    }

    public boolean isDateIsAlreadyRegistered(Date visitDate, Date visitTime) {
        return dentistVisitDao.findAllByVisitDateAndVisitTime(visitDate, visitTime).size() > 0;
    }

    public List<DentistVisitDTO> findAllRegistrations() {
        return dentistVisitDao.findAll().stream().map(DentistVisitDTO::new).collect(Collectors.toList());
    }

    public List<DentistVisitDTO> findRegistrationsByKeyword(String keyword) {
        return dentistVisitDao.findAll().stream()
                .map(DentistVisitDTO::new)
                .filter(v -> isVisitApplicable(keyword, v)
                ).collect(Collectors.toList());
    }

    public void deleteRegistrationById(Long id) {
        dentistVisitDao.delete(id);
    }

    private boolean isVisitApplicable(String keyword, DentistVisitDTO v) {
        return v.getPatientName().contains(keyword) ||
                v.getDentistName().contains(keyword) ||
                v.getVisitTime().toString().contains(keyword) ||
                v.getVisitDate().toString().contains(keyword);
    }
}
