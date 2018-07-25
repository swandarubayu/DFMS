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

public class UserPositionApi extends Jooby {

  {
  	path("/api/user-position", () -> {
      post(req -> {
        UserPositionRepo db = require(UserPositionRepo.class);
        UserPositionObj userPosition = req.body(UserPositionObj.class);
        int id;
        boolean toReturn = false;
        
        if(db.isExist(userPosition.getId()) > 0) {
          id = db.insert(userPosition);

          if(id > 0) {
            toReturn = true;
          }
        } else {
          if(db.update(userPosition) > 0) {
            toReturn = true;
          }
        }

        return toReturn;
      });

  		get(req -> {
  			UserPositionRepo db = require(UserPositionRepo.class);

  			List<UserPositionObj> userPosition = db.listAll();

  			if(userPosition == null) {
  				throw new Err(Status.NOT_FOUND);
  			}

  			return userPosition;
  		});			
  	});

    path("/api/usercount", () -> {
      get(req -> {
        UserPositionRepo db = require(UserPositionRepo.class);

        int id = req.param("id").intValue();
        int usercount = db.countUserByIdGrid(id);
        return usercount;
      });     
    });
  }
}
