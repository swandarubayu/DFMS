package starter.jdbi.object;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginAdminObj {

  public static class Mapper implements RowMapper<LoginAdminObj> {
    @Override public LoginAdminObj map(final ResultSet rs, final StatementContext ctx) throws SQLException {
      return new LoginAdminObj(rs.getInt("id"), rs.getString("username"), rs.getString("password"));
    }
  }
  private int    id;
  private String username;
  private String password;

  public LoginAdminObj(int id, String username, String password) {
    this.id=id;
    this.username = username;
    this.password = password;
  }
  
  public int getId() {
    return id;
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
    
}
