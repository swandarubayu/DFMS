package starter.jdbi.object;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDetailObj {

  public static class Mapper implements RowMapper<UserDetailObj> {
    @Override public UserDetailObj map(final ResultSet rs, final StatementContext ctx) throws SQLException {
      return new UserDetailObj(rs.getInt("id"), rs.getInt("user_id"), rs.getString("st_astext"), rs.getString("updatedAt"));
    }
  }

  private int      id;
  private int user_id;
  private String geom;
  private String date;


 
  public UserDetailObj(int id, int user_id,  String geom, String date){
  this.id=id;
  this.user_id=user_id;
  this.geom=geom;
  this.date=date;
  }

  
public int getId(){
    return id;
  }

  public int getUserId(){
    return user_id;
  }

  public String getGeom(){
      return geom;
  }
  public String getDate(){
        return date;
  }
}
