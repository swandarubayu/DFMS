package starter.jdbi.object;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserPositionObj {

  public static class Mapper implements RowMapper<UserPositionObj> {
    @Override public UserPositionObj map(final ResultSet rs, final StatementContext ctx) throws SQLException {
      return new UserPositionObj(rs.getInt("id"), rs.getInt("id_user"), rs.getInt("id_grid"));
    }
  }

  private int id;
  private int idUser;
  private int idGrid;

  public UserPositionObj(int id, int id_user, int id_grid) {
    this.id = id;
    this.idUser = id_user;
    this.idGrid = id_grid;
  }
  
  public int getId() {
	  return id;
  }
  
  public int getIdUser() {
	  return idUser;
  }
  
  public int getIdGrid() {
	  return idGrid;
  }
  
}
