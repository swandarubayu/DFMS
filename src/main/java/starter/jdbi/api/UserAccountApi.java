package starter.jdbi.api;

import starter.jdbi.repository.*;
import starter.jdbi.object.*;

import org.jooby.Err;
import org.jooby.Jooby;
import org.jooby.Results;
import org.jooby.Status;
import org.jooby.jdbc.Jdbc;
import org.jooby.jdbi.Jdbi3;
import org.jooby.jdbi.TransactionalRequest;
import org.jooby.json.Jackson;

import org.mindrot.jbcrypt.BCrypt;

import java.util.List;
import java.util.ArrayList;

public class UserAccountApi extends Jooby {

  {
    path("/api/user-account", () -> {
		/**
		 * Menambahkan User Account dan me-return statusnya.
		 */
		post(req -> {
			UserAccountRepo db = require(UserAccountRepo.class);
			UserAccountObj userAccount = req.body(UserAccountObj.class);
			int id;
			boolean toReturn = false;
			
			
			if(db.isUsernameTaken(userAccount.getUsername()) != 1) {
				userAccount.setPassword(BCrypt.hashpw(
					userAccount.getPassword(),
					BCrypt.gensalt()
				));

				id = db.insert(userAccount);

				if(id > 0) {
					toReturn = true;
				}
			}

			return toReturn;
		});
      
		/**
		 * Daftar User Account dengan jumlah maksimal max data dan dimulai dari start.
		 */
		get(req -> {
			UserAccountRepo userAccount = require(UserAccountRepo.class);
			int halaman = req.param("halaman").intValue(1);
			int start = (halaman-1)*10;
			int max = 10;

			return userAccount.list(start, max);
		});
		
		/**
		 * User Account berdasarkan ID.
		 */
		get("/:id", req -> {
			UserAccountRepo db = require(UserAccountRepo.class);

			int id = req.param("id").intValue();

			UserAccountObj userAccount = db.findById(id);

			if(userAccount == null) {
				throw new Err(Status.NOT_FOUND);
			}

			return userAccount;
		});
		
		/**
		 * Update User Account berdasarkan ID.
		 */
		put(req -> {
			UserAccountRepo db = require(UserAccountRepo.class);
			UserAccountObj userAccount = req.body(UserAccountObj.class);
			boolean status = false;
			
			if(db.isUsernameTaken(userAccount.getUsername(), userAccount.getId()) != 1) {
				userAccount.setPassword(
					db.findById(userAccount.getId()).getPassword()
				);
				
				if (!db.update(userAccount)) {
					throw new Err(Status.NOT_FOUND);
				} else {
					status = true;
				}
			}
			
			return status;
		});
		
		/**
		 * Delete User Account berdasarkan ID.
		 */
		delete("/:id", req -> {
			UserAccountRepo db = require(UserAccountRepo.class);
			int id = req.param("id").intValue();
			boolean status = false;

			if (!db.delete(id)) {
				throw new Err(Status.NOT_FOUND);
			} else {
				status = true;
			}

			return status;
		});
	});
	
    path("/api/jumlah/user", () -> {
		/**
		 * Mendapatkan total jumlah User Account.
		 */
		get(req -> {
			UserAccountRepo db = require(UserAccountRepo.class);
			int jumlahTotal = db.findTotal();

			return jumlahTotal;
		});
	});
	
    path("/api/dashboard/user", () -> {
		/**
		 * Mendapatkan 5 User Account untuk ditampilkan di dashboard.
		 */
		get(req -> {
			UserAccountRepo db = require(UserAccountRepo.class);

			return db.listForDashboard();
		});
	});
	
    path("/api/max-hal/user", () -> {
		/**
		 * Mendapatkan maksimal halaman User Account.
		 */
		get(req -> {
			UserAccountRepo db = require(UserAccountRepo.class);
			double jumlahTotal = (double) db.findTotal();
			double halamanTotal = jumlahTotal/10;
		
			if((halamanTotal%1) > 0) {
				halamanTotal = halamanTotal - (halamanTotal%1) + 1;
			}

			return (int) halamanTotal;
		});
	});

  }
}

