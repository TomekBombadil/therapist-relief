package pl.waw.psychologmaja.therapistrelief.service;


import org.springframework.stereotype.Service;
import pl.waw.psychologmaja.therapistrelief.entity.User;
import pl.waw.psychologmaja.therapistrelief.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    public List<User> returnAll(){
        return userRepository.findAll();
    }

    public Optional<User> read(long id){
        return userRepository.findById(id);
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public void delete(User user){
        userRepository.delete(user);
    }
}
