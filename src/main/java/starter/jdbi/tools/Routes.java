package starter.jdbi.tools;

import org.jooby.Err;
import org.jooby.FlashScope;
import org.jooby.Jooby;
import org.jooby.Results;
import org.jooby.Status;
import org.jooby.Session;

public class Routes extends Jooby {

  {
	cookieSession();
	use(new FlashScope());
	
	//~ Sudo
	get("/sudo/login", req -> Results.html("sudo/login"));
	
	get("/sudo", req -> {
		Session session = req.session();
		
		if(session.isSet("ids")) {
			req.set("session", session);
			return Results.html("sudo/dashboard");
		} else {
			return Results.redirect("/sudo/login");
		}
	});
	get("/sudo/pengaturan", req -> {
		Session session = req.session();
		
		if(session.isSet("ids")) {
			req.set("session", session);
			return Results.html("sudo/setting");
		} else {
			return Results.redirect("/sudo/login");
		}
	});
	get("/sudo/lokasi", req -> {
		Session session = req.session();
		
		if(session.isSet("ids")) {
			req.set("session", session);
			return Results.html("sudo/list-features");
		} else {
			return Results.redirect("/sudo/login");
		}
	});
	get("/sudo/lokasi/tambah", req -> {
		Session session = req.session();
		
		if(session.isSet("ids")) {
			req.set("session", session);
			return Results.html("sudo/add-features");
		} else {
			return Results.redirect("/sudo/login");
		}
	});
	get("/sudo/user", req -> {
		Session session = req.session();
		
		if(session.isSet("ids")) {
			req.set("session", session);
			return Results.html("sudo/list-user");
		} else {
			return Results.redirect("/sudo/login");
		}
	});
	get("/sudo/user/tambah", req -> {
		Session session = req.session();
		
		if(session.isSet("ids")) {
			req.set("session", session);
			req.flash("tambah", "true");
			return Results.redirect("/sudo/user");
		} else {
			return Results.redirect("/sudo/login");
		}
	});
  }
}
