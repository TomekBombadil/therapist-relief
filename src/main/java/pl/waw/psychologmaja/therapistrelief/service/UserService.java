package pl.waw.psychologmaja.therapistrelief.service;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.waw.psychologmaja.therapistrelief.entity.User;
import pl.waw.psychologmaja.therapistrelief.exception.UserAlreadyExistsException;
import pl.waw.psychologmaja.therapistrelief.repository.AuthorityRepository;
import pl.waw.psychologmaja.therapistrelief.repository.SessionRepository;
import pl.waw.psychologmaja.therapistrelief.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class UserService {

    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private AuthorityRepository authorityRepository;
    private SessionRepository sessionRepository;

    public UserService(UserRepository userRepository, AuthorityRepository authorityRepository,
                       SessionRepository sessionRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authorityRepository = authorityRepository;
        this.sessionRepository = sessionRepository;
    }

    public List<User> returnAll() {
        return userRepository.findAll();
    }

    public Set<User> returnAllWithAuthorities() {
        return userRepository.findAllWithAuthorities();
    }

    public Optional<User> read(long id) {
        return userRepository.findById(id);
    }

    public Optional<User> readWithAuthorities(long id) {
        return userRepository.findByIdWithAuthorities(id);
    }

    public void save(User user) {
        if (user.getId()==null && emailExists(user.getEmail())) {
            throw new UserAlreadyExistsException("There is an account with that email address " + user.getEmail());
        }
        if (user.getId()==null && usernameExists(user.getUsername())) {
            throw new UserAlreadyExistsException("There is an account with that username " + user.getUsername());
        }
        if (user.getId() == null || !user.getPassword().equals(read(user.getId()).get().getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setPasswordConfirmed(user.getPassword());
        } else {
            user.setPassword(read(user.getId()).get().getPassword());
            user.setPasswordConfirmed(user.getPassword());
        }
        userRepository.save(user);
    }

    public void delete(User user) {
        authorityRepository.deleteAuthoritiesByUserId(user.getId());
        sessionRepository.setSessionUserToNull(user.getId());
        userRepository.delete(user);
    }

    public boolean emailExists(String email) {
        return userRepository.findByEmail(email).orElse(null) != null;
    }

    public boolean usernameExists(String username) {
        return userRepository.findByUsername(username).orElse(null) != null;
    }
}
