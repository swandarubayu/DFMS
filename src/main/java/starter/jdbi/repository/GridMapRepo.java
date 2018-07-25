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
@RegisterRowMapper(GridMapObj.Mapper.class)
public interface GridMapRepo {
  
 @SqlQuery("SELECT id, ST_AsText(geom), gridsby.left, bottom, gridsby.right, top FROM gridsby")
 List<GridMapObj> grid();

 @SqlQuery("SELECT id FROM gridsby WHERE ST_Contains(geom, ST_GeomFromText(:lokasi, 4326))")
 int getGridId(String lokasi);
}

