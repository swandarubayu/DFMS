package starter.jdbi.object;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterObj {

  public static class Mapper implements RowMapper<RegisterObj> {
    @Override public RegisterObj map(final ResultSet rs, final StatementContext ctx) throws SQLException {
      return new RegisterObj(rs.getString("username"), rs.getString("password"), rs.getString("domisili"), rs.getInt("role"));
    }
  }

  private String username;
  private String password;
  private String domisili;
  private int role;

  public RegisterObj(String username, String password, String domisili, int role) {
    this.username = username;
    this.password = password;
    this.domisili = domisili;
    this.role = role;
  }
  
  public String getUsername() {
	  return username;
  }
  
  public String getPassword() {
	  return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getDomisili() {
    return domisili;
  }

  public int getRole() {
    return role;
  }
}
