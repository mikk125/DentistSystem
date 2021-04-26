package com.cgi.dentistapp.controller;

import com.cgi.dentistapp.dao.PossibleDentistVisitDao;
import com.cgi.dentistapp.dto.DentistVisitDTO;
import com.cgi.dentistapp.dto.DentistVisitSearchDTO;
import com.cgi.dentistapp.dto.PossibleDentistVisitDTO;
import com.cgi.dentistapp.service.DentistVisitService;
import com.cgi.dentistapp.service.PossibleDentistVisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@EnableAutoConfiguration
public class DentistAppController extends WebMvcConfigurerAdapter {

    @Autowired
    private DentistVisitService dentistVisitService;

    @Autowired
    private PossibleDentistVisitService possibleDentistVisitService;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/results").setViewName("results");
        registry.addViewController("/deleteResults").setViewName("deletionResults");
        registry.addViewController("/modificationResults").setViewName("modificationResults");
        registry.addViewController("/newDataResults").setViewName("newDataResults");
        registry.addViewController("/modify").setViewName("form");
        registry.addViewController("/newData").setViewName("dataForm");
        registry.addViewController("/registrations").setViewName("allRegistrations");
    }

    @GetMapping("/")
    public String showRegisterForm(DentistVisitDTO dentistVisitDTO, Model model) {
        getPossibleDentistVisitData(model);
        model.addAttribute("buttonName", "register.visit");
        model.addAttribute("isNew", true);
        return "form";
    }

    @GetMapping("/newData")
    public String showNewDataRegisterForm(PossibleDentistVisitDTO possibleDentistVisitDTO, Model model) {
        getPossibleDentistVisitData(model);
        model.addAttribute("isNew", true);
        return "dataForm";
    }

    @GetMapping("/registrations")
    public String showAllRegistrations(DentistVisitSearchDTO dentistVisitSearchDTO, Model model) {
        model.addAttribute("registrations", dentistVisitService.findAllRegistrations());
        model.addAttribute("isSearch", false);
        return "allRegistrations";
    }

    @GetMapping("/delete/{id}")
    public String deleteRegistrationById(@PathVariable("id") Long id) {
        dentistVisitService.deleteRegistrationById(id);
        return "redirect:/deleteResults";
    }

    @GetMapping("/modify/{id}")
    public String putRegistrationById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("dentistVisitDTO", dentistVisitService.findById(id));
        getPossibleDentistVisitData(model);
        model.addAttribute("buttonName", "modify.visit");
        model.addAttribute("isNew", false);
        return "form";
    }

    @PostMapping("/")
    public String postRegisterForm(@Valid DentistVisitDTO dentistVisitDTO, BindingResult bindingResult, Model model) {
        return modifyVisit(dentistVisitDTO, bindingResult, model, true);
    }

    @PostMapping("/newData")
    public String postNewDataRegisterForm(@Valid PossibleDentistVisitDTO possibleDentistVisitDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            if (!possibleDentistVisitDTO.getDentistName().contains(" ")) {
                bindingResult.addError(new FieldError("possibleDentistVisitDTO", "missingSpace", "Arsti nimi peab olema eesnimi ja perenimi!"));
            }
            return "dataForm";
        }

        try {
            possibleDentistVisitService.addData(possibleDentistVisitDTO);
        } catch (Exception ex) {
            bindingResult.addError(new FieldError("possibleDentistVisitDTO", "somethingWentWrong", "Salvestamine ebaõnnestus!"));
            return "dataForm";
        }
        return "redirect:/newDataResults";
    }

    @PostMapping("/modify")
    public String modifyRegisterForm(@Valid DentistVisitDTO dentistVisitDTO, BindingResult bindingResult, Model model) {
        return modifyVisit(dentistVisitDTO, bindingResult, model, false);
    }

    @PostMapping("/search")
    public String searchVisitsByKeyword(DentistVisitSearchDTO dentistVisitSearchDTO, Model model) {
        if (dentistVisitSearchDTO.getKeyword().isEmpty()) {
            model.addAttribute("registrations", dentistVisitService.findAllRegistrations());
        } else {
            model.addAttribute("registrations", dentistVisitService.findRegistrationsByKeyword(dentistVisitSearchDTO.getKeyword()));
        }
        model.addAttribute("isSearch", true);
        return "allRegistrations";
    }

    private void getPossibleDentistVisitData(Model model) {
        List<PossibleDentistVisitDTO> possibleData = possibleDentistVisitService.findAllPossibleVisits();
        List<String> possibleDentists = possibleData.stream().map(PossibleDentistVisitDTO::getDentistName).collect(Collectors.toList());
        List<Date> possibleVisitDates = possibleData.stream().map(PossibleDentistVisitDTO::getVisitDate).collect(Collectors.toList());
        List<Date> possibleVisitTimes = possibleData.stream().map(PossibleDentistVisitDTO::getVisitTime).collect(Collectors.toList());

        model.addAttribute("possibleDentists", possibleDentists);
        model.addAttribute("possibleVisitDates", possibleVisitDates);
        model.addAttribute("possibleVisitTimes", possibleVisitTimes);
    }

    private String modifyVisit(DentistVisitDTO dentistVisitDTO, BindingResult bindingResult, Model model, boolean isNew) {
        if (bindingResult.hasErrors() || !dentistVisitDTO.getPatientName().matches("^[a-zA-Z]+ [a-zA-Z]+$") || (dentistVisitService.isDateIsAlreadyRegistered(dentistVisitDTO.getVisitDate(), dentistVisitDTO.getVisitTime()) && isNew)) {
            if (!dentistVisitDTO.getPatientName().matches("^[a-zA-Z]+ [a-zA-Z]+$")) {
                bindingResult.addError(new FieldError("dentistVisitDTO", "missingSpace", "Patsiendi nimi peab olema eesnimi ja perenimi!"));
            }
            if (dentistVisitService.isDateIsAlreadyRegistered(dentistVisitDTO.getVisitDate(), dentistVisitDTO.getVisitTime()) && isNew) {
                bindingResult.addError(new FieldError("dentistVisitDTO", "timeTaken", "See aeg on juba broneeritud!"));
            }
            getPossibleDentistVisitData(model);
            if (isNew) {
                model.addAttribute("buttonName", "register.visit");
            } else {
                model.addAttribute("buttonName", "modify.visit");
            }
            model.addAttribute("isNew", isNew);
            return "form";
        }

        try {
            if (isNew) {
                dentistVisitService.addVisit(dentistVisitDTO);
            } else {
                dentistVisitService.putVisit(dentistVisitDTO);
            }
        } catch (Exception ex) {
            if (isNew) {
                bindingResult.addError(new FieldError("dentistVisitDTO", "somethingWentWrong", "Salvestamine ebaõnnestus!"));
            } else {
                bindingResult.addError(new FieldError("dentistVisitDTO", "somethingWentWrong", "Muutmine ebaõnnestus!"));
            }
            if (isNew) {
                model.addAttribute("buttonName", "register.visit");
            } else {
                model.addAttribute("buttonName", "modify.visit");
            }
            model.addAttribute("isNew", isNew);
            return "form";
        }
        if (isNew) {
            return "redirect:/results";
        }
        return "redirect:/modificationResults";
    }
}
