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

public class RegisterApi extends Jooby {

  {
    	post("/api/register", req -> {
			UserAccountRepo db = require(UserAccountRepo.class);
			UserAccountObj login = req.body(UserAccountObj.class);

			int id;
			boolean toReturn = false;
			
			
			if(db.isUsernameTaken(login.getUsername()) != 1) {
				login.setPassword(BCrypt.hashpw(
					login.getPassword(),
					BCrypt.gensalt()
				));

				id = db.insert(login);

				if(id > 0) {
					toReturn = true;
				}
			}

			return toReturn;
		});
	}
}

