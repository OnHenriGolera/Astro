package src.fr.astro;

import src.fr.astro.dao.Initializer;
import static spark.Spark.*;

public class Server {
	
	public static void main(String[] args) {
		// Configure Spark
		staticFiles.location("/static/");
		port(8081);

        Initializer.Init();

	}

}
