import {Query} from "./util/Query.js";
import {DataTable} from "./util/DataTable.js";

// ------------------ Specific Configuration ------------------
function isValidHeader(header, element) {

	if ("Unknown".localeCompare(element) === 0) {
		return false;
	}

	// Removes useless / unwanted headers
	if (["personBirthDate", "participantId", "personId", "name"].includes(header)) {
		return false;
	}

	return true;

}

// ------------------ Specific Configuration ------------------


// Default query
let query = new Query(undefined, undefined, undefined);

// Default table
let table = undefined;

// Sort key
let sortKey;

// Jquery load on page load
$(function () {

	// Load the player list
	loadPlayerList();

	// When the search input is changed
	$("#search-input").on("input", function () {

		// Set the search value
		table.filter($(this).val());

	})

});

function loadPlayerList() {

	$.ajax({
		url: "/api/players",
		type: "GET",
		success: function (data) {

			// Get the header
			let header = [];

			let getHeaders = (object, parent = undefined) => {

				for (let key in object) {

					if (object[key] instanceof Object) {

						getHeaders(object[key], key);

					} else {

						if (!header.includes(key) && isValidHeader(key, object[key])) {

							header.push(key);

						} else if ("Unknown".localeCompare(object[key]) !== 0 && "name".localeCompare(key) === 0) {

							header.push(parent);

						}

					}

				}
			}

			getHeaders(data[0]);

			query.setData(data);
			query.setHeaders(header);
			query.setSortKey(sortKey);

		}
	}).then(() => {

		table = new DataTable("main", query.getHeaders());

		table.setElements(query.getData());
		table.update();

	});
}
