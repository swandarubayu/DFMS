package starter.jdbi.object;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMovObj {

  public static class Mapper implements RowMapper<UserMovObj> {
    @Override public UserMovObj map(final ResultSet rs, final StatementContext ctx) throws SQLException {
      return new UserMovObj(rs.getInt("id"), rs.getInt("user_id"), rs.getString("st_astext"), rs.getString("updatedAt"));
    }
  }

  private int id;
  private int user_id;
  private String location;
  private String updatedat;

  public UserMovObj(int id, int user_id, String location, String updatedAt){
  this.id=id;
  this.user_id=user_id;
  this.location=location;
  this.updatedat=updatedat;
  }

  public int getId(){
    return id;
  }

  public int getUser_id(){
    return user_id;
  }

  public String getLocation(){
      return location;
  }
  public String getUpdatedat(){
        return updatedat;
  }
}
