package starter.jdbi.object;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginCheck {

  public static class Mapper implements RowMapper<LoginCheck> {
    @Override public LoginCheck map(final ResultSet rs, final StatementContext ctx) throws SQLException {
      return new LoginCheck(rs.getInt("id"), rs.getString("username"), rs.getString("domisili"));
    }
  }

  private int id;
  private String username;
  private boolean error;
  private String domisili;

  public LoginCheck(int id, String username, String domisili) {
    this.id = id;
    this.username = username;
    this.domisili = domisili;
    this.error = true;
  }
  
  public int getId() {
	  return id;
  }
  
  public String getUsername() {
	  return username;
  }

  public String getDomisili() {
    return domisili;
  }

  public boolean getError(){
    return error;

  }

  public void setError(boolean error){

    this.error = error; 

  }

}