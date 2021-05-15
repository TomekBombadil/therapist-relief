package pl.waw.psychologmaja.therapistrelief.validation;

import pl.waw.psychologmaja.therapistrelief.entity.User;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordsMatchValidator implements ConstraintValidator<PasswordsMatch, Object> {
    @Override
    public void initialize(PasswordsMatch constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        User user = (User) o;
        return user.getPassword().equals(user.getPasswordConfirmed());
    }
}
