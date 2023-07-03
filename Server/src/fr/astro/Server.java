package fr.astro;

import static spark.Spark.*;

import fr.astro.dao.database.Initializer;
import fr.astro.gui.staticPages.Error404GUI;
import fr.astro.gui.staticPages.IndexGUI;
import fr.astro.gui.staticPages.TemplateChooserGUI;

/**
 * Server
 * 
 * Main class
 */
public class Server {

	/**
	 * Main
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// Configure Spark
		staticFiles.location("/static/");
		port(8081);

		Initializer.Init();

		defineGETs();

	}

	/**
	 * Define GETs
	 * 
	 * Define all the GETs routes
	 */
	public static void defineGETs() {

		get("/", (request, response) -> {
			System.out.println("GET /");
			return IndexGUI.getInstance().renderPage(request, response);
		});

		get("/default-template", (request, response) -> {
			System.out.println("GET /default-template");

			return IndexGUI.getInstance().renderPage(request, response);
		});

		get("/template-chooser", (request, response) -> {

			System.out.println("GET /template-chooser");

			return TemplateChooserGUI.getInstance().renderPage(request, response);
			
		});

		notFound((req, res) -> {
			System.out.println("GET 404");
			return Error404GUI.getInstance().renderPage(req, res);
		});

	}

}
