package pl.waw.psychologmaja.therapistrelief.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.waw.psychologmaja.therapistrelief.entity.Authority;
import pl.waw.psychologmaja.therapistrelief.repository.AuthorityRepository;


public class AuthorityConverter implements Converter<String, Authority> {

    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public Authority convert(String s) {
        return authorityRepository.findById(Long.parseLong(s)).get();
    }
}
