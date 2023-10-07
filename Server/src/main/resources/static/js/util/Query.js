// To represent a query to find data in a JSON :
// There is a JSON with different keys, each key is a column name.
// The goal is to return only the rows that match the query.

/**
 * @class Query
 * @classdesc Represents a query.
 * @property {Object[]} data - The data (keys are column names and unknown values).
 * @property {Object} headers - The headers (keys are column names and values are callbacks).
 * @property {String} sortKey - The sort key (key is a column name).
 * @example
 * data : [
 *     {
 *          "participantId":0,
 *          "category":{"name":"senior"},
 *          "present":true,
 *          ...,
 *          "nationality":{"name":"egy"}
 *     },
 *     {...},
 * ]
 * headers : {
 *    "participantId": (data) => { return data.participantId; },
 *    "category": (data) => { return data.category.name; },
 *    "present": (data) => { return data.present; },
 *    ...,
 *    nationality: (data) => { return data.name; }
 * }
 * sortKey : "participantId"
 */
export class Query {

	/**
	 * Create a query.
	 * @param {Object[]} data - The data (keys are column names and unknown values).
	 * @param {Object} headers - The headers (keys are column names and values are callbacks).
	 * @param {String} sortKey - The sort key (key is a column name).
	 */
	constructor(data, headers, sortKey) {

		// Set the data, headers and sort key
		this.data = data;
		this.headers = headers;
		this.sortKey = sortKey;
		this.filteredData = data;

	}


	/**
	 * Set the data.
	 * @param {Object[]} data - The data (keys are column names and unknown values).
	 * @returns {Query} The query.
	 * @example
	 * query.setData([
	 *    {
	 *     "participantId":0,
	 *     "category":{"name":"senior"},
	 *     "present":true,
	 *     ...,
	 *     nationality: {"name":"egy"}
	 *     },
	 *     {...},
	 *     {...}
	 * ]);
	 */
	setData(data) {

		// Set the data
		this.data = data;

		// Return the query
		return this;

	}

	/**
	 * Set the headers.
	 * @param {Object} headers - The headers (keys are column names and values are callbacks).
	 * @returns {Query} The query.
	 * @example
	 * query.setHeaders({
	 *   "participantId": (data) => { return data.participantId; },
	 *   "category": (data) => { return data.category.name; },
	 *   "present": (data) => { return data.present; },
	 *   ...,
	 *   nationality: (data) => { return data.name; }
	 * });
	 */
	setHeaders(headers) {

		// Set the headers
		this.headers = headers;

		// Return the query
		return this;

	}

	/**
	 * Set the sort key.
	 * @param {String} sortKey - The sort key (key is a column name).
	 * @returns {Query} The query.
	 * @example
	 * query.setSortKey([
	 *  "participantId",
	 *  "category"
	 * ]);
	 */
	setSortKey(sortKey) {

		// Set the sort key
		this.sortKey = sortKey;

		// Return the query
		return this;

	}

	/**
	 * Get the data.
	 * @returns {Object[]} The data (keys are column names and unknown values).
	 * @example
	 * query.getData();
	 * // [
	 * //     {
	 * //          "participantId":0,
	 * //          "category":{"name":"senior"},
	 * //          "present":true,
	 * //          ...,
	 * //          "nationality":{"name":"egy"}
	 * //     },
	 * //     {...},
	 * // ]
	 */
	getData() {

		// Return the data
		return this.data;

	}

	/**
	 * Sort the data.
	 * @returns {Query} The query.
	 * @example
	 * query.sort();
	 * // The data is sorted.
	 * // The sort key is updated.
	 * // The query is returned.
	 */
	sort() {

		// If the sort key is not defined
		if (this.sortKey === undefined) {

			// Return the query
			return this;

		}

		// Sort the data
		this.data.sort((a, b) => {

			// Get the value of the headers
			let valA = this.headers[this.sortKey].func.value(a);
			let valB = this.headers[this.sortKey].func.value(b);

			// If the value is a string
			if (typeof valA === "string") {

				// Compare the strings
				return valA.localeCompare(valB);

			}

			// Everything else (number, boolean, ...)
			return valA - valB;

		});

		// Return the query
		return this;

	}

	/**
	 * Get the headers.
	 * @returns {Object} The headers (keys are column names and values are callbacks).
	 */
	getHeaders() {

		// Return the headers
		return this.headers;

	}


}