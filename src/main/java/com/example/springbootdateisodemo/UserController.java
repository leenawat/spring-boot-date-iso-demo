package com.example.springbootdateisodemo;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbootdateisodemo.error.UserNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class UserController {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserJpaRepository userJpaRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping(path = "/api/v1/user/{id}")
    public User select(@PathVariable Integer id) {
        return userJpaRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @GetMapping(path = "/api/v2/user/{id}")
    public User selectV2(@PathVariable Integer id) {
        try {
            return userRepository.findByIdV2(id);
        } catch (EmptyResultDataAccessException e) {
            throw new UserNotFoundException(id);
        }
    }

    @GetMapping(path = "/api/v3/user/{id}")
    public Map<String, Object> selectV3(@PathVariable Integer id) {
        try {
            return userRepository.findByIdV3(id);
        } catch (EmptyResultDataAccessException e) {
            throw new UserNotFoundException(id);
        }
    }

    @PostMapping(path = "/api/v1/user")
    public User save(@RequestBody User user) {
        return userJpaRepository.save(user);
    }

    @PostMapping(path = "/api/v2/user")
    public User save(@RequestParam String user) throws JsonMappingException, JsonProcessingException {
        User u = objectMapper.readValue(user, User.class);
        return userJpaRepository.save(u);
    }

    @PutMapping(path = "/api/v2/user/{id}")
    public Optional<User> save(@RequestParam String user, @PathVariable Integer id)
            throws JsonMappingException, JsonProcessingException {
        Optional<User> optional = userJpaRepository.findById(id);
        User u1 = objectMapper.readValue(user, User.class);
        if (optional.isPresent()) {
            User u2 = optional.get();
            u2.setDob(u1.getDob());
            userJpaRepository.save(u2);
        }
        return userJpaRepository.findById(id);
    }
}
