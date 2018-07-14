package starter.jdbi.object;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GridMapObj {

  public static class Mapper implements RowMapper<GridMapObj> {
    @Override public GridMapObj map(final ResultSet rs, final StatementContext ctx) throws SQLException {
      return new GridMapObj(rs.getInt("id"), rs.getString("st_astext"), rs.getDouble("left"), rs.getDouble("bottom"), rs.getDouble("right"), rs.getDouble("top"));
    }
  }

  private int id;
  private String geom;
  private Double left;
  private Double bottom;
  private Double right;
  private Double top;

  public GridMapObj(int id, String geom, Double left, Double bottom, Double right, Double top) {
    this.id = id;
    this.geom = geom;
    this.left = left;
    this.bottom = bottom;
    this.right = right;
    this.top = top;
  }
  
  public int getId() {
	  return id;
  }
  
  public String getGeom() {
	  return geom;
  }
  
  public Double getLeft() {
	  return left;
  }
  
  public Double getBottom() {
	  return bottom;
  }

  public Double getRight() {
    return right;
  }

public Double getTop() {
    return top;
  }
    
  
}
