// Jquery
$(document).ready(function () {
    // Fetch from the server the list of templates
    fetchAndBuildTemplates();

    // When a template is clicked, change the selected template (input hidden)
    $(document).on("click", ".template", function () {
        // Get the template name
        var templateName = $(this)
            .find(".template-name")
            .text()
            .replace(" ", "_");

        // Set the input hidden value
        $("#selected-template").val(templateName);

        // Remove the selected class from all templates
        $(".template").removeClass("selected");

        // Add the selected class to the clicked template
        $(this).addClass("selected");

        // Enable the submit button
        $("#submit-button").prop("disabled", false);
    });
});

function fetchAndBuildTemplates() {
    $.ajax({
        url: "/assets/templates.json",
        type: "GET",
        dataType: "json",
        success: function (data) {
            console.log(data);
            buildTemplates(data);
        },
        error: function (error) {
            console.log(error);
        },
    });
}

function createTemplateDiv(template) {
    return `<div class="template">
                <img src="${template.image}" alt="${template.name}">
                <p class="template-name">${template.name}</p>
                <p class="template-description">${template.description}</p>
                <p class="template-author">${template.author}</p>
            </div>`;
}

function buildTemplates(templates) {
    // For each template, create a div
    $.each(templates, function (index, template) {
        // New div :
        /*
        <div class="template">
            <img src="template.png" alt="template">
            <p>Template name</p>
            <p>Template description</p>
            <p>Template author</p>
        </div>
        */

        var templateDiv = createTemplateDiv(template);

        // Add the div to the template-chooser div
        $("#template-container").append(templateDiv);
    });
}
