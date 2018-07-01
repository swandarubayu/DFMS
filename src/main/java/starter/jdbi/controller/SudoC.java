package starter.jdbi.controller;

import starter.jdbi.repository.*;
import starter.jdbi.object.*;

import org.jooby.Err;
import org.jooby.FlashScope;
import org.jooby.Jooby;
import org.jooby.Results;
import org.jooby.Status;
import org.jooby.Session;
import org.jooby.jdbc.Jdbc;
import org.jooby.jdbi.Jdbi3;
import org.jooby.jdbi.TransactionalRequest;
import org.jooby.json.Jackson;

import org.mindrot.jbcrypt.BCrypt;

public class SudoC extends Jooby {

  {
	cookieSession();
	use(new FlashScope());
	
	//~ Login
	post("/sudo/login/do", req -> {
		UserAccountRepo db = require(UserAccountRepo.class);
		LoginObj login = req.form(LoginObj.class);

		String username = login.getUsername();
		String password = login.getPassword();

		String realPassword = db.findSudoPassword(username);
		
		if(realPassword != null) {
			if(BCrypt.checkpw(password, realPassword)) {
				UserAccountObj user = db.findByPassword(realPassword);
				Session session = req.session();
				
				session.set("ids", user.getId());
				session.set("domisili", user.getDomisili());
				
				return Results.redirect("/sudo");
			}
		}
		
		req.flash("gagal", "true");

		return Results.redirect("/sudo/login");
	});
	
	//~ Logout
	get("/sudo/logout", req -> {
		Session session = req.session();
	
		if(session.isSet("ids")) {
			session.unset();
		}
		
		return Results.redirect("/sudo");
	});
	
	//~ Notif berhasil
	get("/sudo/lokasi/sukses", req -> {
		Session session = req.session();
		
		if(session.isSet("ids")) {
			req.set("session", session);
			req.flash("sukses", "true");
			return Results.redirect("/sudo/lokasi");
		} else {
			return Results.redirect("/sudo/login");
		}
	});
  }
}
