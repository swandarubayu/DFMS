package starter.jdbi;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.simple.JSONObject;

public class History {

  public static class Mapper implements RowMapper<History> {
    @Override public History map(final ResultSet rs, final StatementContext ctx) throws SQLException {
      return new History(rs.getInt("gid"), rs.getDouble("latitude"), rs.getDouble("longitude"), rs.getString("st_astext"), rs.getString("updatedAt"));
    }
  }

  private int id;
  private double lon;
  private double lat;
  private String geom;
  private String date;


  private String name;

  public History(int id, double lat, double lon, String geom, String date){
  this.id=id;
  this.lon=lon;
  this.lat=lat;
  this.geom=geom;
  this.date=date;
  }

  public int getId(){
    return id;
  }

  public double getLon(){
    return lon;
  }

  public double getLat(){
      return lat;
  }

  public String getGeom(){
      return geom;
  }
  public String getDate(){
        return date;
  }
}
