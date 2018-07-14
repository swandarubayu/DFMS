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

public class LoginAdminApi extends Jooby {

  {
  	use(new FlashScope());

  	post("/daftarAdmin",req -> {
                LoginAdminRepo db = require(LoginAdminRepo.class);
                LoginAdminObj loginAdminObj = req.body(LoginAdminObj.class);

                int id;
                boolean toReturn = false;
                
                if(db.isUsernameTaken(loginAdminObj.getUsername()) != 1) {
                    loginAdminObj.setPassword(BCrypt.hashpw(
                        loginAdminObj.getPassword(),
                        BCrypt.gensalt()
                    ));

                    id = db.insert(loginAdminObj);

                    if(id > 0) {
                        toReturn = true;
                    }
                }

                return toReturn;
            });

   post("/loginAdmin", req -> {
                LoginAdminRepo db = require(LoginAdminRepo.class);
                LoginAdminObj login = req.form(LoginAdminObj.class);

                String username = login.getUsername();
                String password = login.getPassword();

                String realPassword = db.findByUser(username);

                if(realPassword != null) {
                    if(BCrypt.checkpw(password, realPassword)) {
                        LoginAdminObj user = db.findByPassword(realPassword);

                        Session session = req.session();
                
                        session.set("ids", user.getId());
                        session.set("username", user.getUsername());
                        
                        return Results.redirect("index.html");
                    }
                }
                
                req.flash("gagal", "true");

                return Results.redirect("login.html");
            });

            //~ Logout
            get("/logout", req -> {
                Session session = req.session();
            
                if(session.isSet("ids")) {
                    session.unset();
                }
                
                return Results.redirect("/login.html");
            });
     }
}

