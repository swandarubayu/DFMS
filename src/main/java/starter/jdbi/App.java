package starter.jdbi;

import starter.jdbi.object.*;
import starter.jdbi.repository.*;
import starter.jdbi.api.*;
import starter.jdbi.tools.*;
import starter.jdbi.controller.*;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.jooby.Err;
import org.jooby.Jooby;
import org.jooby.Results;
import org.jooby.Status;
import org.jooby.jdbc.Jdbc;
import org.jooby.jdbi.Jdbi3;
import org.jooby.jdbi.TransactionalRequest;
import org.jooby.json.Jackson;
import org.jooby.apitool.ApiTool;
import org.jooby.handlers.CorsHandler;
import org.jooby.hbs.Hbs;
/**
 * jdbi starter project:
 */
public class App extends Jooby {

  {

    port(8787);
    use("*", new CorsHandler());
    use(new Jackson());
    use(new Jdbc());
    use(new Hbs());

    use(new Jdbi3()
      /** Install SqlObjectPlugin */
      .doWith(jdbi -> {
        jdbi.installPlugin(new SqlObjectPlugin());
      })
      /** Creates a transaction per request and attach PetRepository */
      .transactionPerRequest(
        new TransactionalRequest()
          .attach(UserAccountRepo.class)
          .attach(UserDetailRepo.class)
          .attach(UserMovRepo.class)
          .attach(LoginAdminRepo.class)
          .attach(GridMapRepo.class)
          .attach(UserPositionRepo.class)
          
      )
    );

    //~ APIs
    
    use(new UserAccountApi());
    use(new LoginApi());
    use(new RegisterApi());
    use(new UserMovApi());
    use(new LoginAdminApi());
    use(new GridMapApi());
    use(new UserPositionApi());

    
    //~ Routes
    use(new Routes());
    
    //~ Controller
    use(new SudoC());

    //~ Asset script
    // assets("/**");
    assets("/dist/**");
    assets("/bower_components/**");
    assets("/plugins/**");

    
    assets("/coba_input", "coba_input.html");
    assets("/index", "index.html");
    assets("/usertab", "/pages/tables/userdata.html");
    assets("/roatab", "/pages/tables/roa.html");
    assets("/roamaps", "/pages/mapsutil/RoaMaps.html");
    assets("/usermaps", "/pages/mapsutil/UserMaps.html");
    assets("/login", "/pages/examples/login.html");    



    // assets("/login", "login.html");
    // assets("/usertab", "user_table.html");
    // assets("/UserMov", "UserMovMaps.html");
    // assets("/blank", "blank.html");
    // assets("/RiskArea", "RiskArea.html");
    // assets("/lock", "lock_screen.html");
    // assets("/roatab", "roa_table.html");   
    // assets("/compare", "compare.html");  
    }
  
    {
    		use(new ApiTool()
    			.swagger("/swagger")
    			.raml("/raml")
    		);
    	}



  public static void main(final String[] args) {
    run(App::new, args);
  }

}
