package pl.waw.psychologmaja.therapistrelief.controller;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.waw.psychologmaja.therapistrelief.exception.UserAlreadyExistsException;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ErrorControllerAdvice {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public String handleEntityNotFoundException(HttpServletRequest req, Exception exception, Model model) {
        model.addAttribute("exception", exception);
        model.addAttribute("url", req.getRequestURL());
        model.addAttribute("errorMessage", exception.getMessage());
        return "exception-page";
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public String handleUserAlreadyExistsException(HttpServletRequest req, Exception exception, Model model) {
        model.addAttribute("exception", exception);
        model.addAttribute("url", req.getRequestURL());
        model.addAttribute("errorMessage", exception.getMessage());
        return "exception-page";
    }
}
