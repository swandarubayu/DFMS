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

public class GridMapApi extends Jooby {

  {
  	path("/api/gridmap", () -> {


  		get(req -> {
  			GridMapRepo db = require(GridMapRepo.class);
        UserPositionRepo upr = require(UserPositionRepo.class);

  			List<GridMapObj> gridMap = db.grid();

        for(int i=0; i<gridMap.size(); i++) {
          gridMap.get(i).setUserCount(
            upr.countUserByIdGrid(gridMap.get(i).getId())
          );
        }

  			if(gridMap == null) {
  				throw new Err(Status.NOT_FOUND);
  			}

  			return gridMap;
  		});			
  	});
  }

}
