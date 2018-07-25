package starter.jdbi.repository;

import starter.jdbi.object.*;

import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

/**
 * Akses database untuk tabel user_account.
 */
@RegisterRowMapper(UserMovObj.Mapper.class)
public interface UserMovRepo {
  /**
   * List pets using start/max limits.
   *
   * @param start Start offset.
   * @param max Max number of rows.
   * @return Available pets.
   */
  /**
   * Find a pet by ID.
   *
   * @param id Pet ID.
   * @return Pet or null.
   */
  @SqlQuery("SELECT id, user_id, ST_AsText(location), updatedat FROM user_mov WHERE id=id ORDER BY id DESC LIMIT 1")
  UserMovObj findById();

  
  @SqlUpdate("INSERT INTO user_mov(user_id, location, updatedat) values(:user_id, ST_GeomFromText(:location, 4326), to_timestamp(:updatedat, 'YYYY-MM-DD HH24-MI-MS'))")
  @GetGeneratedKeys
  int insert(@BindBean UserMovObj UserMovObj);

  @SqlUpdate("DELETE user_mov WHERE id=:id")
  boolean delete(long id);
}
