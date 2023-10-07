// Has an object representing a table
// Change the table state dynamically
import getCountryFlag from "../country.js"

/**
 * Represents a table
 * @class DataTable
 * @classdesc Represents a table.
 * @property {Object} table - The table (a JQuery object).
 * @property {Object} headers - The headers (keys are column names and values are callbacks).
 * @example
 * headers :
 * [
 *     {
 *         "name": "personName",
 *         "func": {
 *             "value": (data) => { return ... },
 *             "html": (data) => { return ...},
 *             "columnName": "Name"
 *         }
 *     },
 *     {...},
 * ]
 */
export class DataTable {

	/**
	 * Create a table object in the selected query selector.
	 * @param {String} querySelector - The query selector of the container of the table.
	 * @param {Object} headers - The headers (keys are column names and values are callbacks).
	 */
	constructor(querySelector, headers) {

		if (headers === undefined) {
			throw new Error("Headers are undefined");
		}

		// Check if the table exists
		if ($(querySelector).find("table").length !== 0) {

			// Delete the table
			$(querySelector).find("table").remove();

		}

		this.headers = headers;

		// Create the table in the query selector
		this.table = $(`<table class="table"></table>`).appendTo(querySelector);

		// Add the table header and body
		$(`<thead><tr></tr></thead><tbody id="tbody"></tbody>`).appendTo(this.table);

		this.tbody = this.table.find("tbody");
		this.thead = this.table.find("tr");

		this.addCell(this.thead, `<p>#</p>`);

		// Set the headers
		headers.forEach((header) => {

			this.addCell(this.thead, header);

		});

		this.tableLength = 0;
		this.elements = [];
	}

	/**
	 * Get value from a data object.
	 * @param {Object} data - The data (keys are column names and unknown values).
	 * @param {String} header - The header.
	 * @param {String} parent - The parent.
	 * @static
	 * @returns {Object} The value.
	 */
	static getValue(data, header, parent = undefined) {
		let value = data[header];

		// If it's undefined, search recursively for objects in the data
		if (value === undefined) {

			for (let key in data) {

				if (data[key] instanceof Object) {

					value = DataTable.getValue(data[key], header, key);

					if (value !== undefined) {

						break;

					}

				}

			}
		}

		let result;

		if (value instanceof Object) {
			result = value["name"];
		} else {
			result = value;
		}

		if ("nationality".localeCompare(header) === 0) {
			result = `<img src="${getCountryFlag(result)}" alt="${result}">`;
		}

		return result;
	}

	/**
	 * Get html from a data object.
	 * @param {Object} data - The data (keys are column names and unknown values).
	 * @param {String} header - The header.
	 * @static
	 * @returns {Object} The html.
	 */
	static getHTML(data, header) {
		return `<p>${DataTable.getValue(data, header)}</p>`;
	}

	/**
	 * Add a row to the table.
	 * @param {Object} data - The data (keys are column names and unknown values).
	 * @example
	 * data :
	 * {
	 *    "personName": "Ahmed",
	 *    "personAge": 20,
	 *    "personNationality": "Egyptian",
	 *    ...
	 *    "birthDate": "2000-01-01"
	 * }
	 */
	addRow(data) {

		// Create the row
		let row = $(`<tr></tr>`).appendTo(this.tbody);

		this.addCell(row, `<p>${++this.tableLength}</p>`);

		// Add the cells
		this.headers.forEach((header) => {

			this.addCell(row, DataTable.getHTML(data, header));

		});

	}

	/**
	 * Add a cell to the table.
	 * @param {Object} row - The row (a JQuery object).
	 * @param {String} html - The html of the cell.
	 * @example
	 * html : `<p>Ahmed</p>`
	 * @example
	 * html : `<p>20</p>`
	 */
	addCell(row, html) {

		// Create the cell
		let cell = $(`<td></td>`).appendTo(row);

		// Add the html
		cell.html(html);

	}

	/**
	 * Empty the table.
	 * @example
	 * table.empty();
	 */
	empty() {

		// Empty the table
		this.tbody.empty();

	}

	/**
	 * Update the table.
	 */
	update() {

		// Empty the table
		this.empty();

		// For each row
		this.elements.forEach((row) => {

			// Add the row
			this.addRow(row);
		});

	}

	/**
	 * Set the elements.
	 * @param {Object[]} elements - The elements.
	 */
	setElements(elements) {
		this.elements = elements;
	}

	/**
	 * Filter the table.
	 * @param {String} queryString - The query string.
	 * @example
	 * table.filter("Ahmed");
	 */
	filter(queryString) {

		if (queryString === "") {

			this.tbody.find("tr").show();
			
			return;

		}


		// For each row, if there is no cell that contains the query string, hide the row
		this.tbody.find("tr").each((index, row) => {

			let found = false;

			$(row).find("td").each((index, cell) => {

				if ($(cell).html().toLowerCase().includes(queryString.toLowerCase())) {

					found = true;

				}

			});

			if (found) {

				$(row).show();

			} else {

				$(row).hide();

			}

		});

	}
}