package starter.jdbi.object;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserAccountObj {

  public static class Mapper implements RowMapper<UserAccountObj> {
    @Override public UserAccountObj map(final ResultSet rs, final StatementContext ctx) throws SQLException {
      return new UserAccountObj(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("domisili"));
    }
  }

  private int id;
  private String username;
  private String password;
  private String domisili;

  public UserAccountObj(int id, String username, String password, String domisili) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.domisili = domisili;
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
  
  public String getDomisili() {
	  return domisili;
  }
    
  public void setPassword(String password) {
	  this.password = password;
  }
}
