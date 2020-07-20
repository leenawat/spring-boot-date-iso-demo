package com.example.springbootdateisodemo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, Integer>{}
