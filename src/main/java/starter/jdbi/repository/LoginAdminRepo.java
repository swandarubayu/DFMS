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
@RegisterRowMapper(LoginAdminObj.Mapper.class)
public interface LoginAdminRepo {
  /**
   * Daftar User Account dengan jumlah maksimal max data dan dimulai dari start.
   */
  
  @SqlQuery("select password from admin where username=:username")
  String findByUser(String username);

  /**
   * User Account berdasarkan ID.
   */
 @SqlQuery("SELECT * FROM admin WHERE id=:id")
 LoginAdminObj findById(int id);

  /**
   * User Account berdasarkan password.
   */
 @SqlQuery("SELECT * FROM admin WHERE password=:password")
 LoginAdminObj findByPassword(String password);

  
    /**
   * Menambahkan User Account dan me-return keberhasilannya.
   */
  @SqlUpdate("INSERT INTO admin(username, password) values(:username, :password)")
  @GetGeneratedKeys
  int insert(@BindBean LoginAdminObj LoginAdminObj);

  /**
   * Meng-update User Account berdasarkan ID-nya dan me-return keberhasilannya.
   */
  @SqlUpdate("UPDATE admin SET username=:username, password=:password, WHERE id=:id")
  boolean update(@BindBean LoginAdminObj LoginAdminObj);

@SqlQuery("SELECT COUNT(*) FROM admin WHERE username=:username")
 int isUsernameTaken(String username);

  /**
   * Menghapus User Account berdasar ID-nya dan me-return keberhasilannya.
   */
  @SqlUpdate("DELETE FROM admin WHERE id=:id")
  boolean delete(int id);


  
}

