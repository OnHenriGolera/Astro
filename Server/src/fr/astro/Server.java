package src.fr.astro;

import static spark.Spark.*;

import src.fr.astro.dao.database.Initializer;
import src.fr.astro.gui.staticPages.IndexGUI;

public class Server {
	
	public static void main(String[] args) {
		// Configure Spark
		staticFiles.location("/static/");
		port(8081);

		Initializer.Init();

		defineGETs();

	}

	public static void defineGETs() {

		get("/", (request, response) -> {

			return IndexGUI.getInstance().renderPage();

		});

	}

}
