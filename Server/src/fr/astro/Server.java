package fr.astro;

import static spark.Spark.*;

import java.util.List;

import fr.astro.dao.database.Initializer;
import fr.astro.dao.human.ParticipantDAO;
import fr.astro.entity.human.ParticipantEntity;
import fr.astro.gui.staticPages.Error404GUI;
import fr.astro.gui.staticPages.IndexGUI;
import fr.astro.gui.staticPages.PlayerListGUI;
import fr.astro.gui.staticPages.TemplateChooserGUI;

import com.google.gson.Gson;
import fr.astro.test.content.human.participant.ParticipantFullTest;

/**
 * Server
 * Main class
 */
public class Server {

	/**
	 * Main
	 * 
	 * @param args Arguments
	 */
	public static void main(String[] args) throws Exception {
		// Configure Spark
		staticFiles.location("/static/");
		port(8081);

		Initializer.Init();

		// Create a test for Participant
		ParticipantFullTest participantFullTest = new ParticipantFullTest();

		defineGETs();

		definePOSTs();


	}

	/**
	 * Define GETs
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

		get("/player-list", (request, response) -> {
			System.out.println("GET /player-list");

			return PlayerListGUI.getInstance().renderPage(request, response);
		});

		get("/api/players", (request, response) -> {

			// Return a json with all the players
			response.type("application/json");

			List<ParticipantEntity> participants = ParticipantDAO.getInstance().getAll();

			return new Gson().toJson(participants);

		});

	}

	/**
	 * Define POSTs
	 * Define all the POSTs routes
	 */
	public static void definePOSTs() {

		post("/template-chooser", (request, response) -> {

			String theme = request.queryParams("template");

			System.out.println(theme);

			if (theme == null || theme.isEmpty()) {
				response.redirect("/template-chooser");
				return null;
			}

			System.out.println("POST /template-chooser");

			response.cookie("theme", theme);

			response.redirect("/");

			return null;

		});

	}

}
