package src.fr.astro;

import static spark.Spark.*;

import src.fr.astro.dao.database.Initializer;

public class Server {
	
	public static void main(String[] args) {
		// Configure Spark
		staticFiles.location("/static/");
		port(8081);

        Initializer.Init();

	}

}
