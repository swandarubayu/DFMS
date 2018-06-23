package starter.jdbi;

import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

/**
 * Database access for pets.
 */
@RegisterRowMapper(History.Mapper.class)
public interface HistoryRepo {
  /**
   * List pets using start/max limits.
   *
   * @param start Start offset.
   * @param max Max number of rows.
   * @return Available pets.
   */
  @SqlQuery("SELECT gid, latitude, longitude, ST_AsText(geom), updatedAt FROM history")
  List<History> list(int start, int max);

  /**
   * Find a pet by ID.
   *
   * @param id Pet ID.
   * @return Pet or null.
   */
  @SqlQuery("SELECT gid, latitude, longitude, ST_AsText(geom), updatedAt FROM history WHERE id=:id")
  History findById(long id);

  /**
   * Insert a pet and returns the generated PK.
   *
   * @param pet Pet to insert.
   * @return Primary key.
   */
  @SqlUpdate("INSERT INTO history(longitude, latitude, geom, updatedat) values(:lon, :lat, ST_GeomFromText(:geom, 4326), to_date(:date, 'YYYY-MM-DD'))")
  @GetGeneratedKeys
  int insert(@BindBean History History);

  /**
   * Update a pet and returns true if the pets was updated.
   *
   * @param pet Pet to update.
   * @return True if the pet was updated.
   */
  /**@SqlUpdate("UPDATE history SET name=:name where id=:id")
  boolean update(@BindBean Pet pet);*/

  /**
   * Delete a pet by ID.
   *
   * @param id Pet ID.
   * @return True if the pet was deleted.
   */
  @SqlUpdate("DELETE history WHERE id=:id")
  boolean delete(long id);
}
