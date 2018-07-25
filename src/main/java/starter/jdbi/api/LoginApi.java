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
import org.jooby.Session;
import org.jooby.Jooby.Module;
import org.jooby.FlashScope;

import org.mindrot.jbcrypt.BCrypt;

import java.util.List;
import java.util.ArrayList;

public class LoginApi extends Jooby {

  {
  	use(new FlashScope());

   post("/api/login", req -> {
		UserAccountRepo db = require(UserAccountRepo.class);
		LoginObj login = req.body(LoginObj.class);

		String username = login.getUsername();
		String password = login.getPassword();

		String realPassword = db.findSudoPassword(username);
		System.out.println(realPassword);

		
		if(realPassword != null) {
			if(BCrypt.checkpw(password, realPassword)) {
				UserAccountObj user = db.findByPassword(realPassword);

				LoginCheck userCheck = new LoginCheck(user.getId(), user.getUsername(), user.getDomisili());

				//Session session = req.session();
				
				//session.set("ids", user.getId());
				//session.set("domisili", user.getDomisili());

				userCheck.setError(false);

				return userCheck;
			}
		}
		
		LoginCheck userCheck = new LoginCheck(0, "", "");
		//req.flash("gagal", "true");

		return userCheck;
	});
      
      get("/api/logout", req -> {
		Session session = req.session();
	
		if(session.isSet("ids")) {
			session.unset();
		}
		
		return true;
	});
     }
}

