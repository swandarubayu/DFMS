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

public class UserMovApi extends Jooby {

  {
  	use(new FlashScope());
 path("/api/usermov", ()  -> {
  
  get( req -> {
    UserMovRepo db = require(UserMovRepo.class);

    // int id = req.param("id").intValue();

    UserMovObj usermov = db.findById();
    
    if (usermov == null) {
      throw new Err(Status.NOT_FOUND);
    }

    return usermov;
  });

  post(req -> {
    UserMovRepo db = require(UserMovRepo.class);
    UserPositionRepo upr = require(UserPositionRepo.class);
    GridMapRepo gmr = require(GridMapRepo.class);
    UserMovObj usermov = req.body(UserMovObj.class);
    int id;
    boolean toReturn = false;

    id = db.insert(usermov);

    if(id > 0) {
      UserPositionObj userPosition = new UserPositionObj(1, usermov.getUser_id(), gmr.getGridId(usermov.getLocation()));

      if(upr.isExist(usermov.getUser_id()) > 0) {
        if(upr.update(userPosition) > 0) {
          toReturn = true;
        }
      } else {
        id = upr.insert(userPosition);

        if(id > 0) {
          toReturn = true;
        }
      }
    }

    return toReturn;
  });

  });

 // path("/api/LatestLocation", () -> {


 //      get(req -> {
 //        UserMovRepo db = require(UserMovRepo.class);

 //        UserMovObj  = db.findByUpdatedat();

 //        if(findByUpdatedat == null) {
 //          throw new Err(Status.NOT_FOUND);
 //        }

 //        return findByUpdatedat;
 //      });     
 //    });


path("/api/userloc", () -> {
		get(req -> {
			UserDetailRepo db = require(UserDetailRepo.class);

			return db.findByUpdatedat();
		});
	});

  }
}

