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
  @SqlQuery("SELECT  a.user_id, a.location  FROM user_mov a, user_account b WHERE a.user_id=b.id ")
 List<UserDetailObj> list();
}
