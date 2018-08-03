package starter.jdbi.object;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterObj {

  public static class Mapper implements RowMapper<RegisterObj> {
    @Override public RegisterObj map(final ResultSet rs, final StatementContext ctx) throws SQLException {
      return new RegisterObj(rs.getString("username"), rs.getString("nama"), rs.getString("password"), rs.getString("domisili"));
    }
  }

  private String username;
  private String nama;
  private String password;
  private String domisili;
  

  public RegisterObj(String username, String nama, String password, String domisili) {
    this.username = username;
    this.nama = nama;
    this.password = password;
    this.domisili = domisili;
    
  }
  
  public String getUsername() {
	  return username;
  }

  public String getNama() {
    return nama;
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

}
