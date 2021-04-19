package pl.waw.psychologmaja.therapistrelief.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.waw.psychologmaja.therapistrelief.entity.Patient;
import pl.waw.psychologmaja.therapistrelief.service.PatientService;

@Controller
@RequestMapping(value = "/patient", produces = "text/html; charset=utf-8")
public class PatientController {

    private PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String showAddForm(Model model) {
        model.addAttribute("newpatient", new Patient());
        return "patient/add";
    }

    @RequestMapping(value="/add", method=RequestMethod.POST)
    public String processAddForm(){

        return "redirect:/";
    }


}
