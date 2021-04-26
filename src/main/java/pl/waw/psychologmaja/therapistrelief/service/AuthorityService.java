package pl.waw.psychologmaja.therapistrelief.service;

import org.springframework.stereotype.Service;
import pl.waw.psychologmaja.therapistrelief.entity.Authority;
import pl.waw.psychologmaja.therapistrelief.entity.Patient;
import pl.waw.psychologmaja.therapistrelief.repository.AuthorityRepository;

import java.util.List;

@Service
public class AuthorityService {

    private final AuthorityRepository authorityRepository;

    public AuthorityService(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    public List<Authority> returnAll(){
        return authorityRepository.findAll();
    }

}
