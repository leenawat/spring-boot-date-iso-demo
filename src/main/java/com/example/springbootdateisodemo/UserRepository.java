package com.example.springbootdateisodemo;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
  @Autowired private JdbcTemplate jdbcTemplate;

  public User findByIdV2(Integer id) {
    return jdbcTemplate.queryForObject(
        "select id, name, dob, updated_Date from user where id = ? ",
        new Object[] {id},
        new UserRowMapper());
  }

  public Map<String, Object> findByIdV3(Integer id) {
    return jdbcTemplate.queryForMap(
        "select id, name, dob, updated_date from user where id = ? ", id);
  }
}
