package kr.pe.ned.authserver.security;

import kr.pe.ned.authserver.domain.User;
import kr.pe.ned.authserver.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;


@Slf4j
@Component("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findOneByLoginId(username);
        log.info("user : {}", user);
        if (user == null) {
            log.error("not found loginId : {}", username);
            throw new UsernameNotFoundException(String.format("User %s does not exist!", username));
        }

        // role은 나중에 추가
        return new CustomUserDetails(user, new ArrayList<>());
    }

}
