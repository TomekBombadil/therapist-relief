package pl.waw.psychologmaja.therapistrelief.service;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.waw.psychologmaja.therapistrelief.entity.User;
import pl.waw.psychologmaja.therapistrelief.exception.UserAlreadyExistsException;
import pl.waw.psychologmaja.therapistrelief.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> returnAll() {
        return userRepository.findAll();
    }

    public List<User> returnAllWithAuthorities() {
        return userRepository.findAllWithAuthorities();
    }

    public Optional<User> read(long id) {
        return userRepository.findById(id);
    }

    public void save(User user){
        if(emailExists(user.getEmail())){
            throw new UserAlreadyExistsException("There is an account with that email address " + user.getEmail());
        }
        if(usernameExists(user.getUsername())){
            throw new UserAlreadyExistsException("There is an account with that username " + user.getUsername());
        }
        if (user.getId() == null || !user.getPassword().equals(read(user.getId()).get().getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        } else {
            user.setPassword(read(user.getId()).get().getPassword());
        }
        userRepository.save(user);
    }

    public void delete(User user) {
        userRepository.delete(user);
    }

    public boolean emailExists(String email){
        return userRepository.findByEmail(email).orElse(null) != null;
    }

    public boolean usernameExists(String username){
        return userRepository.findByUsername(username).orElse(null) != null;
    }
}
