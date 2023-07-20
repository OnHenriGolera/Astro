const HEADERS = {
    initialLocalRanking: {
        name: "Rank",
        val: (object) => {
            return object.initialLocalRanking;
        }
    },
    name: {
        name: "Name",
        val: (object) => {
            return object.personEntity.name;
        }

    },
    surname: {
        name: "Surname",
        val: (object) => {
            return object.personEntity.surname;
        }
    },
    gender: {
        name: "Gender",
        val: (object) => {
            return object.personEntity.gender.name;
        }
    },
    league: {
        name: "League",
        val: (object) => {
            return object.league.name;
        }
    },
    club: {
        name: "Club",
        val: (object) => {
            return object.club.name;
        }
    },
    nationality: {
        name: "Nationality",
        val: (object) => {
            return object.nationality.name;
        }
    },
    category: {
        name: "Category",
        val: (object) => {
            return object.category.name;
        }
    },
    present: {
        name: "Present",
        val: (object) => {
            return object.present;
        }
    }
};

let header = {};
let playerList = [];
let sortKey = { val: HEADERS.nationality.val, name: "Nationality" };
// Jquery load on page load
$(function () {
    // Get the player list
    getPlayerList();

    // If a header is clicked
    $("#thead").on("click", "th", function () {

        // Get the header name (find the header name from the header object)
        let headerName = Object.keys(header).find(key => header[key].name === $(this).text());

        // If the header is not present
        if (headerName === undefined) {
            return;
        }

        // If the sort key is different from the header value
        if (sortKey.name.localeCompare(header[headerName].name) !== 0) {

            // sort the player list
            playerList.sort(function (a, b) {

                // Get the value of the header
                let valA = header[headerName].val(a);
                let valB = header[headerName].val(b);

                // If the value is a string
                if (typeof valA === "string") {
                    return valA.localeCompare(valB);
                }

                // Everything else
                return valA - valB;
            });

            // Set the sort key
            sortKey = header[headerName];

        } else {

            playerList.reverse();

        }


        // Clear the table
        $("#tbody").empty();

        // Show the player list
        showPlayerList();

    });

    // When search bar is changed
    $("#search-bar").on("input", function () {

        showPlayerList();

    });
});

// Show the player list
function showPlayerList() {

    // Get the search bar value
    let search = $("#search-input").val().toLowerCase();

    // Clear the table
    $("#tbody").empty();

    // For each player
    for (let player of playerList) {

        // If the player can be shown
        if (canShowPlayer(player, search)) {

            // Append the player to the table
            $("#tbody").append(playerToHTML(player));

        }
    }
}

// Check if the player can be shown
function canShowPlayer(player, search) {

    if (search === "") {
        return true;
    }

    let name = HEADERS.name.val(player).toLowerCase();
    let surname = HEADERS.surname.val(player).toLowerCase();
    let club = HEADERS.club.val(player).toLowerCase();

    return name.includes(search) || surname.includes(search) || club.includes(search);
}

// Get the player list
function getPlayerList() {

    // GET request to /api/players
    $.get("/api/players", function (data) {

        // Save the player list
        playerList = data;

        // Clear the table
        $("#thead").empty();

        // Get the header
        getHeader(data);

        // For each header
        $.each(header, function (index, header) {

            // Append the header
            $("#thead").append(`<th>${header.name}</th>`);
        });

        // Show the player list
        showPlayerList();

    });

}

// Get header of the table (see if there is at least one non "Uknown" value, add the header)
function getHeader(players) {

    header = {};

    // For each player
    for (let player of players) {

        // For each header
        for (let key in HEADERS) {

            // If the header is not present
            if (header[key] === undefined) {

                let val = HEADERS[key].val(player);

                // If the value is not "Unknown" or undefined or null
                if (val !== "Unknown" && val !== undefined && val !== null) {

                    // Add the header
                    header[key] = {name: HEADERS[key].name, val: HEADERS[key].val};
                }
            }
        }
    }

}

// Player (json) to HTML
/*

{
cateogory: {"name": "categoryName"},
present: true,
personEntity: {"name": "personName", "surname":personSurname, "gender": {"name":genderName}},
initialLocalRanking: 1,
league: {"name": "leagueName"},
club: {"name": "clubName"},
nationality: {"name": "nationalityName"}
}

Order : initialLocalRank, name, surname, gender, league, club, nationality, category, present

 */
function playerToHTML(player) {

    let html = "<tr>";

    // For each header
    $.each(header, function (index, header) {

        // Append the value
        html += `<td>${header.val(player)}</td>`;

    });

    html += "</tr>";

    return html;

}
