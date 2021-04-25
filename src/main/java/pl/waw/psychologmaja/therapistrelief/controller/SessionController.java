package pl.waw.psychologmaja.therapistrelief.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.waw.psychologmaja.therapistrelief.entity.Session;
import pl.waw.psychologmaja.therapistrelief.service.SessionService;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import javax.validation.Validator;
import java.util.List;

@Controller
@RequestMapping(value = "/session", produces = "text/html;charset=UTF-8")
public class SessionController {

    private final Logger logger = LoggerFactory.getLogger(SessionController.class);
    private SessionService sessionService;
    private Validator validator;

    public SessionController(SessionService sessionService, Validator validator) {
        this.sessionService = sessionService;
        this.validator = validator;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String showAll(Model model) {
        List<Session> sessions = sessionService.returnAll();
        model.addAttribute("allsessions", sessions);
        return "session/all";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String showAddForm(Model model) {
        model.addAttribute("newsession", new Session());
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


}
