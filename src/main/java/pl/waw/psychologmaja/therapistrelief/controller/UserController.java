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
import pl.waw.psychologmaja.therapistrelief.entity.Authority;
import pl.waw.psychologmaja.therapistrelief.entity.User;
import pl.waw.psychologmaja.therapistrelief.service.AuthorityService;
import pl.waw.psychologmaja.therapistrelief.service.UserService;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import javax.validation.Validator;
import java.util.List;

@Controller
@RequestMapping(value = "/user", produces = "text/html;charset=UTF-8")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    private UserService userService;
    private AuthorityService authorityService;
    private Validator validator;

    public UserController(UserService userService, AuthorityService authorityService, Validator validator) {
        this.userService = userService;
        this.authorityService = authorityService;
        this.validator = validator;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String showAll(Model model) {
        List<User> users = userService.returnAll();
        model.addAttribute("allusers", users);
        return "user/all";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String showAddForm(Model model) {
        model.addAttribute("newuser", new User());
        return "user/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String processAddForm(@ModelAttribute("newuser") @Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            logger.error(result.toString());
            return "user/add";
        }
        userService.save(user);
        return "redirect:/user/all";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String showEditForm(Model model, @RequestParam long id) {
        User user = userService.read(id).orElseThrow(EntityNotFoundException::new);
        model.addAttribute("usertoedit", user);
        return "user/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String processEditForm(@ModelAttribute("usertoedit") @Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            logger.error(result.toString());
            return "user/edit";
        }
        userService.save(user);
        return "redirect:/user/all";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String showDeleteConfirmationRequest(@RequestParam long id, Model model) {
        User user = userService.read(id).orElseThrow(EntityNotFoundException::new);
        model.addAttribute("usertodelete", user);
        return "user/delete";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String processDelete(User user) {
        userService.delete(user);
        return "redirect:/user/all";
    }

    @ModelAttribute("auths")
    public List<Authority> availableAuths() {
        return authorityService.returnAll();
    }

}
