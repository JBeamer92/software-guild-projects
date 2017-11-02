$(document).ready(function () {

    $("#add-sight-button").click(event, function () {
        displayAddSightForm();
    });

    $("#add-sight-cancel").click(event, function () {
        clearForm();
        $("#add-sight-div").hide();
        $("#sight-add-head").show();
        $("#sight-list-div").show();
        $("#map").show();
    });

});

function displayAddSightForm() {
    $("#sight-list-div").hide();
    $("#sight-add-head").hide();
    $("#map").hide();
    $("#add-sight-div").show();
}

function clearForm() {
    $("#sightDate").val("");
    $("#sightLocation").val("");
    $("#sightHeroes").val("");
}


