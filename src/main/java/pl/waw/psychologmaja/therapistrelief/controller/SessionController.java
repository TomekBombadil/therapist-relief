package pl.waw.psychologmaja.therapistrelief.controller;

import com.itextpdf.text.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.waw.psychologmaja.therapistrelief.entity.Patient;
import pl.waw.psychologmaja.therapistrelief.entity.Session;
import pl.waw.psychologmaja.therapistrelief.entity.User;
import pl.waw.psychologmaja.therapistrelief.service.InvoiceService;
import pl.waw.psychologmaja.therapistrelief.service.PatientService;
import pl.waw.psychologmaja.therapistrelief.service.SessionService;
import pl.waw.psychologmaja.therapistrelief.service.UserService;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.Validator;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/session", produces = "text/html;charset=UTF-8")
public class SessionController {

    private final Logger logger = LoggerFactory.getLogger(SessionController.class);
    private SessionService sessionService;
    private Validator validator;
    private UserService userService;
    private PatientService patientService;
    private InvoiceService invoiceService;

    public SessionController(SessionService sessionService, Validator validator, UserService userService,
                             PatientService patientService, InvoiceService invoiceService) {
        this.sessionService = sessionService;
        this.validator = validator;
        this.userService = userService;
        this.patientService = patientService;
        this.invoiceService = invoiceService;
    }

    @RequestMapping(value="/upcoming", method=RequestMethod.GET)
    public String showHomePage(Model model){
        List<Session> upcoming = sessionService.returnUserUpcoming();
        model.addAttribute("upcoming", upcoming);
        return "session/upcoming";
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String showAll(Model model) {
        List<Session> sessions = sessionService.returnAll();
        model.addAttribute("allsessions", sessions);
        return "session/all";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String showAddForm(Model model) {
        Session session = new Session();
        session.setUser((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        model.addAttribute("newsession", session);
        return "session/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String processAddForm(@ModelAttribute("newsession") @Valid Session session, BindingResult result) {
        if (result.hasErrors()) {
            logger.error(result.toString());
            return "session/add";
        }
        sessionService.save(session);
        return "redirect:/session/all";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String showEditForm(Model model, @RequestParam long id) {
        Session session = sessionService.read(id).orElseThrow(EntityNotFoundException::new);
        model.addAttribute("sessiontoedit", session);
        return "session/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String processEditForm(@ModelAttribute("sessiontoedit") @Valid Session session, BindingResult result) {
        if (result.hasErrors()) {
            logger.error(result.toString());
            return "session/edit";
        }
        sessionService.save(session);
        return "redirect:/session/all";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String showDeleteConfirmationRequest(@RequestParam long id, Model model) {
        Session session = sessionService.read(id).orElseThrow(EntityNotFoundException::new);
        model.addAttribute("sessiontodelete", session);
        return "session/delete";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String processDelete(Session session) {
        sessionService.delete(session);
        return "redirect:/session/all";
    }

    @RequestMapping(value = "/invoice", produces = "application/pdf;charset=UTF-8")
    @ResponseBody
    public void generateInvoice(Session session, BindingResult result, HttpServletResponse response) throws DocumentException, IOException {
        invoiceService.getInvoice(sessionService.read(session.getId()).orElseThrow(EntityNotFoundException::new), response);
    }

    @ModelAttribute("availableusers")
    public Set<User> getAllUsers() {
        return userService.returnAllWithAuthorities().stream().collect(Collectors.toSet());
    }

    @ModelAttribute("availablepatients")
    public List<Patient> getAllPatients() {
        return patientService.returnAll();
    }

    @ModelAttribute("availablehours")
    public List<String> getAvailableHours() {
        return Arrays.asList("08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00"
                , "16:00", "17:00", "18:00", "19:00", "20:00");
    }

}
