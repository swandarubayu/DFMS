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
          
      )
    );

    //~ APIs
    
    use(new UserAccountApi());
    use(new LoginApi());
    use(new RegisterApi());
    
    //~ Routes
    use(new Routes());
    
    //~ Controller
    use(new SudoC());

    //~ Asset script
    assets("/assets/**");
    assets("/css/**");
    
    assets("/coba_input", "coba_input.html");
    assets("/index", "index.html");
    assets("/user", "user-table.html");
    assets("/predict", "prediction.html");
    assets("/maps", "movement-maps.html");
    assets("/login", "login.html");    
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
