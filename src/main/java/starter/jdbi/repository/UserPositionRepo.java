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
@RegisterRowMapper(UserPositionObj.Mapper.class)
public interface UserPositionRepo {

	@SqlUpdate("INSERT INTO user_position(id_user, id_grid) values(:idUser, :idGrid)")
	@GetGeneratedKeys
	int insert(@BindBean UserPositionObj UserPositionObj);

	@SqlQuery("SELECT * FROM user_position")
	List<UserPositionObj> listAll();

	@SqlQuery("SELECT COUNT(*) FROM user_position WHERE id_grid=:id")
	int countUserByIdGrid(int id);

	@SqlQuery("SELECT COUNT(*) FROM user_position WHERE id_user=:id")
	int isExist(int id);

	@SqlUpdate("UPDATE user_position SET id_user=:idUser, id_grid=:idGrid WHERE id_user=:idUser")
	@GetGeneratedKeys
	int update(@BindBean UserPositionObj UserPositionObj);
  
}
