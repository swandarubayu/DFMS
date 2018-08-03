package starter.jdbi.object;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserAccountObj {

  public static class Mapper implements RowMapper<UserAccountObj> {
    @Override public UserAccountObj map(final ResultSet rs, final StatementContext ctx) throws SQLException {
      return new UserAccountObj(rs.getInt("id"), rs.getString("username"), rs.getString("nama"), rs.getString("password"), rs.getString("domisili"), rs.getFloat("inrisk"));
    }
  }

  private int id;
  private String username;
  private String nama;
  private String password;
  private String domisili;
  private Float inrisk;

  public UserAccountObj(int id, String username, String nama, String password, String domisili, Float inrisk) {
    this.id = id;
    this.username = username;
    this.nama = nama;
    this.password = password;
    this.domisili = domisili;
    this.inrisk = inrisk;
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

  public String getNama() {
    return nama;
  }
  
  public String getDomisili() {
	  return domisili;
  }

  public Float getInrisk() {
    return inrisk;
  }
    
  public void setPassword(String password) {
	  this.password = password;
  }
}
