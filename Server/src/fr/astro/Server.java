package fr.astro;

import static spark.Spark.*;

import fr.astro.dao.database.Initializer;
import fr.astro.gui.staticPages.Error404GUI;
import fr.astro.gui.staticPages.IndexGUI;

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
			System.out.println("GET /");
			return IndexGUI.getInstance().renderPage();
		});

		notFound((req, res) -> {
			System.out.println("GET 404");
			return Error404GUI.getInstance().renderPage();
		});

	}

}
