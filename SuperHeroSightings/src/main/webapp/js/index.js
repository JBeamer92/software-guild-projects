$(document).ready(function() {
    populateMap();
});

var map;
var cent = {lat: 30.000, lng: 0.0000};


function populateMap() {
    
    $.ajax({
        
        method: "GET",
        url: "http://localhost:8081/SuperHeroSightings/recentSightings",
        success: function(sightList){
            $.each(sightList, function(index, sight){
                
                var latitude = sight.location.latitude;
                var longitude = sight.location.longitude;
                
                loc = new google.maps.LatLng(latitude, longitude);
                
                new google.maps.Marker({
                    position: loc,
                    map: map
                });
                
            });
        },
        error: function() {
            
        }
        
    });
    
}

function initMap() {
    map = new google.maps.Map(document.getElementById('map'), {
        zoom: 1,
        center: cent
    });
}

function displayCoordInMap(latitude, longitude) {
    latitude = parseFloat(latitude);
    longitude = parseFloat(longitude);

    loc = new google.maps.LatLng(latitude, longitude);
    
    marker.setPosition(loc);
    map.panTo(loc);
}