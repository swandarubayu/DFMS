package starter.jdbi;

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

/**
 * jdbi starter project:
 */
public class App extends Jooby {

  {

    //~ Asset script
    assets("/assets/**");
    assets("/css/**");
    
    assets("/coba_input", "coba_input.html");
    assets("/index", "index.html");
    assets("/chart", "chart.html");
    assets("/panel", "tab-panel.html");
    assets("/empty", "empty.html");
    assets("/form", "form.html");
    assets("/table", "table.html");
    assets("/ui_elements", "ui-elements.html");
    assets("/login", "login.html");    
    }

  {
    use(new Jackson());

    use(new Jdbc("db.pg2"));

    use(new Jdbi3("db.pg2")
        /** Install SqlObjectPlugin */
        .doWith(jdbi -> {
          jdbi.installPlugin(new SqlObjectPlugin());
        })
        /** Creates a transaction per request and attach PetRepository */
        .transactionPerRequest(
            new TransactionalRequest()
                .attach(HistoryRepo.class)
        )
    );

    /** Create table + pets: */
    onStart(() -> {
      Jdbi jdbi = require("db.pg2",Jdbi.class);
    });

    path("/api/history", ()  -> {
        get (req -> {
            HistoryRepo db = require(HistoryRepo.class);

            int start = req.param("start").intValue(0);
            int max = req.param("max").intValue(20);

            return db.list(start, max);
        });
        get("/:id", req -> {
                HistoryRepo db = require(HistoryRepo.class);

                int id = req.param("id").intValue();

               History history = db.findById(id);
                if (history == null) {
                  throw new Err(Status.NOT_FOUND);
                }

                return history;
              });

         post(req -> {
             HistoryRepo db = require(HistoryRepo.class);
             History history = req.body(History.class);

             int id;
             boolean toReturn = false;

             id = db.insert(history);

             if(id > 0) {
                toReturn = true;
             }

             return toReturn;
        });
   });
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
