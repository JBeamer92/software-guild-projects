var map;
var marker;
var loc;

function initMap() {
    loc = {lat: 38.2543, lng: -85.7483};
    map = new google.maps.Map(document.getElementById('map'), {
        zoom: 5,
        center: loc
    });
    marker = new google.maps.Marker({
        position: loc,
        map: map
    });
}

function displayCoordInMap(latitude, longitude) {
    latitude = parseFloat(latitude);
    longitude = parseFloat(longitude);

    loc = new google.maps.LatLng(latitude, longitude);
    
    marker.setPosition(loc);
    map.panTo(loc);
}