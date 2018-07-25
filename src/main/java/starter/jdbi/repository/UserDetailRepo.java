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
@RegisterRowMapper(UserDetailObj.Mapper.class)
public interface UserDetailRepo {
  /**
   * List pets using start/max limits.
   *
   * @param start Start offset.
   * @param max Max number of rows.
   * @return Available pets.
   */
  @SqlQuery("SELECT id, user_id, ST_AsText(location), updatedat FROM user_mov a WHERE updatedat = (select max(updatedat) from user_mov b where a.user_id = b.user_id)")
   List<UserDetailObj> findByUpdatedat();
}
