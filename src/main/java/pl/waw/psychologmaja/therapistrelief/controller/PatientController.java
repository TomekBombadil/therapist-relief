package pl.waw.psychologmaja.therapistrelief.controller;

import org.apache.commons.fileupload.FileUploadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import pl.waw.psychologmaja.therapistrelief.entity.Patient;
import pl.waw.psychologmaja.therapistrelief.service.PatientService;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import javax.validation.Validator;
import java.io.IOException;

@Controller
@RequestMapping(value = "/patient", produces = "text/html; charset=utf-8")
public class PatientController {

    private final Logger logger = LoggerFactory.getLogger(PatientController.class);
    private PatientService patientService;
    private Validator validator;

    public PatientController(PatientService patientService, Validator validator) {
        this.patientService = patientService;
        this.validator = validator;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String showAddForm(Model model) {
        model.addAttribute("newpatient", new Patient());
        return "patient/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String processAddForm(@ModelAttribute("newpatient") @Valid Patient patient, BindingResult result, @RequestParam("rodoFileToUpload") CommonsMultipartFile[] file) {
        if (result.hasErrors()) {
            logger.error(result.toString());
            return "patient/add";
        }
        if (file != null && file.length > 0) {
            patient.setRodoFileName(file[0].getOriginalFilename());
            patient.setRodoFile(file[0].getBytes());
        }
        patientService.save(patient);
        return "redirect:/patient/all";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String showEditForm(Model model, @RequestParam long id) {
        Patient patient = patientService.read(id).orElseThrow(EntityNotFoundException::new);
        model.addAttribute("patienttoedit", patient);
        return "patient/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String processEditForm(@ModelAttribute("patienttoedit") @Valid Patient patient, BindingResult result, @RequestParam("rodoFileToUpload") CommonsMultipartFile[] file) {
        if (result.hasErrors()) {
            logger.error(result.toString());
            return "patient/edit";
        }
        if (file != null && file.length > 0 && file[0].getSize()>0) {
            patient.setRodoFileName(file[0].getOriginalFilename());
            patient.setRodoFile(file[0].getBytes());
        } else {
            Patient patientFromDb = patientService.read(patient.getId()).orElseThrow(EntityNotFoundException::new);
            patient.setRodoFileName(patientFromDb.getRodoFileName());
            patient.setRodoFile(patientFromDb.getRodoFile());
        }
        patientService.save(patient);
        return "redirect:/patient/all";
    }


}
