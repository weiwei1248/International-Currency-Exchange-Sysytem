package ca.uwindsor.ices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.uwindsor.ices.repository.UserRepository;

@Service
public class BindingService {

    private UserRepository userRepository;

    @Autowired
    BindingService(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }
}