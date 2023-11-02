package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Transactional
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Transactional
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public void saveUser(User user) {
        User userFromDb = userRepository.findByUsername(user.getUsername());

        if (userFromDb != null) {
            if (!userFromDb.getPassword().equals(user.getPassword())) {
                user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            }
            userRepository.save(user);
            return;
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User getById(int id) {
        Optional<User> userFromDb = userRepository.findById(id);
        return userFromDb.orElse(new User());
    }

    @Transactional
    public void deleteUserById(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found :" + username);
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername()
                , user.getPassword()
                , user.getAuthorities());
    }


}
