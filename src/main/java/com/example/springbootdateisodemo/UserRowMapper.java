package com.example.springbootdateisodemo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;

import org.springframework.jdbc.core.RowMapper;

public class UserRowMapper implements RowMapper<User> {

  @Override
  public User mapRow(ResultSet rs, int rowNum) throws SQLException {
    User user = new User();
    user.setId(rs.getInt("id"));
    user.setName(rs.getString("name"));
    user.setDob(rs.getDate("dob").toLocalDate());
    user.setUpdatedDate(
        rs.getTimestamp("updated_date").toLocalDateTime().atZone(ZoneId.of("+07:00")));

    return user;
  }
}
