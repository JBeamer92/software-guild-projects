$(document).ready(function () {

    $("#add-loc-button").click(event, function () {
        displayAddLocForm();
    });

    $("#add-loc-cancel").click(event, function () {
        clearForm();
        $("#add-loc-div").hide();
        $("#loc-list-div").show();
        $("#loc-add-head").show();
        $("#map").show();
    });

});

function toggleDescription(orgId) {
    $("#desc-" + orgId).toggle();
}

function displayAddLocForm() {
    $("#loc-list-div").hide();
    $("#loc-add-head").hide();
    $("#map").hide();
    $("#add-loc-div").show();
}

function clearForm() {
    $("#locName").val("");
    $("#locDesc").val("");
    $("#locAddress").val("");
    $("#locCity").val("");
    $("#locRegion").val("");
    $("#locCountry").val("");
    $("#locLat").val("");
    $("#locLong").val("");
}

function getLatAndLng() {
    var city = $("#locCity").val();
    var region = $("#locRegion").val();
    var country = $("#locCountry").val();
    var latitude;
    var longitude;

    $.ajax({
        method: "GET",
        url: "https://maps.googleapis.com/maps/api/geocode/json?address="
                + city + ",+" + region + "+" + country
                + "&key=AIzaSyCK3E8tkZmq46UVxqvVc_ATGHrWNTmiEaQ",
        success: function (data) {
            latitude = data.results[0].geometry.location.lat;
            longitude = data.results[0].geometry.location.lng;
            $("#locLat").val(latitude);
            $("#locLong").val(longitude);
            console.log("Lat:" + data.results[0].geometry.location.lat);
            console.log("Lon" + data.results[0].geometry.location.lng);
            $("#add-loc").show();
        },
        error: function () {
            alert("Error getting lat/lon data.");
        }

    });
}

//function submitForm() {
//
//    $.ajax({
//        method: "POST",
//        url: "http://localhost:8081/SuperHeroSightings/location",
//        data: JSON.stringify({
//            name: $("#locName").val(),
//            description: $("#locDesc").val(),
//            address: $("#locAddress").val(),
//            city: $("#locCity").val(),
//            region: $("#locRegion").val(),
//            country: $("#locCountry").val(),
//            latitude: $("#locLat").val(),
//            longitude: $("#locLong").val()
//        }),
//        headers: {
//            "Accept": "application/json",
//            "Content-Type": "application/json"
//        },
//        "dataType": "json",
//        success: function () {
//            $("#add-loc").show();
//        },
//        error: function () {
//            alert("Could not add location.");
//        }
//    });
//
//}

//function loadLocations() {
//
//    $.ajax({
//        method: "GET",
//        url: "http://localhost:8081/SuperHeroSightings/allLocations",
//        success: function (locations) {
//            $("#location-rows").empty();
//
//            $.each(locations, function (index, loc) {
//
//                var row1 = "<tr><td><a onclick='displayCoordInMap(" + loc.latitude
//                        + "," + loc.longitude + ")'>" + loc.name + "</a>"
//                        + "<div class='text-right' style='display:inline;float:right;'>"
//                        + "<a onclick='toggleDescription(" + loc.id + ")'> &#9432"
//                        + "</a></div></td>";
//                row1 += "<td>" + loc.address + "</td>";
//                row1 += "<td>" + loc.city + "</td>";
//                row1 += "<td>" + loc.region + "</td>";
//                row1 += "<td>" + loc.country + "</td>";
//                row1 += "<td>" + loc.latitude + "</td>";
//                row1 += "<td>" + loc.longitude + "</td>";
//                row1 += "<td><a href='editLocationForm?locId=" + loc.id + "'>Edit</a></td>";
//                row1 += "<td><a href='deleteLocation?locId=" + loc.id + "'>Delete</a></td></tr>";
//
//                var row2 = "<tr> id='desc-" + loc.id + "' style='display:none'>";
//                row2 = "<td colspan='9'>" + loc.description + "</td></td>";
//
//                $("#add-loc-div").hide();
//                $("#loc-list-div").show();
//                $("#loc-add-head").show();
//                $("#map").show();
//
//            });
//        },
//        error: function () {
//            alert("Could not load locations.");
//        }
//    });
//
//}