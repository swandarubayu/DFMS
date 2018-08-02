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
@RegisterRowMapper(UserAccountObj.Mapper.class)
public interface UserAccountRepo {
  /**
   * Daftar User Account dengan jumlah maksimal max data dan dimulai dari start.
   */
  @SqlQuery("SELECT * FROM user_account  ORDER BY id DESC LIMIT :max OFFSET :start")
  List<UserAccountObj> list(int start, int max);
  
  /**
   * Daftar User Account dengan jumlah maksimal max data dan dimulai dari start.
   */
  @SqlQuery("SELECT * FROM user_account  ORDER BY id DESC LIMIT 5")
  List<UserAccountObj> listForDashboard();

  // *get all user
  @SqlQuery("SELECT id, username, nama, password, domisili, inrisk FROM user_account ORDER BY id")
  List<UserAccountObj> listAll();

  /**
   * User Account berdasarkan ID.
   */
 @SqlQuery("SELECT * FROM user_account WHERE id=:id")
 UserAccountObj findById(int id);

  /**
   * User Account berdasarkan password.
   */
 @SqlQuery("SELECT * FROM user_account WHERE password=:password")
 UserAccountObj findByPassword(String password);

  /**
   * Password berdasarkan username (untuk login).
   */
 @SqlQuery("SELECT password FROM user_account WHERE username=:username ")
 String findSudoPassword(String username);

  /**
   * Total User Account administrator.
   */
 @SqlQuery("SELECT COUNT(*) FROM user_account ")
 int findTotal();

  /**
   * Cek keberadaan username.
   */
 @SqlQuery("SELECT COUNT(*) FROM user_account WHERE username=:username")
 int isUsernameTaken(String username);

  /**
   * Cek keberadaan username dengan id yang berbeda.
   */
 @SqlQuery("SELECT COUNT(*) FROM user_account WHERE username=:username AND id!=:id")
 int isUsernameTaken(String username, int id);

  /**
   * Menambahkan User Account dan me-return keberhasilannya.
   */
  @SqlUpdate("INSERT INTO user_account(username, nama, password, domisili, inrisk) values(:username, :nama, :password, :domisili, :inrisk)")
  @GetGeneratedKeys
  int insert(@BindBean UserAccountObj UserAccountObj);

  /**
   * Meng-update User Account berdasarkan ID-nya dan me-return keberhasilannya.
   */
  @SqlUpdate("UPDATE user_account SET username=:username, nama=:nama, password=:password, domisili=:domisili, inrisk=:inrisk, WHERE id=:id")
  boolean update(@BindBean UserAccountObj UserAccountObj);

  /**
   * Menghapus User Account berdasar ID-nya dan me-return keberhasilannya.
   */
  @SqlUpdate("DELETE FROM user_account WHERE id=:id")
  boolean delete(int id);


  
}

