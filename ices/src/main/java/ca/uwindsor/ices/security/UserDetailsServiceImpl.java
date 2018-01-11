package ca.uwindsor.ices.security;


import java.util.Optional;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;

import ca.uwindsor.ices.jpa.User;
import ca.uwindsor.ices.repository.UserRepository;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly=true)
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> optional = userRepository.findByEmail(userName);

        User user = optional.orElseThrow(() -> new UsernameNotFoundException("User not Found : " + userName));

        return new UserDetailsImpl(user);
    }
}
