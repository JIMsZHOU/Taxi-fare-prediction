var map;
var markers = [];
var id = 0;


function initMap() {
    map = new google.maps.Map(document.getElementById("showMap"), {
        center: {lat: 40.7130701, lng: -74.0060417},
        zoom: 12
    });

    map.addListener('click', function (event) {
        var Latitude = event.latLng.lat();
        var Longitude = event.latLng.lng();
        addMarker(event.latLng)
    });
}

function getId() {
    if (id === 2) {
        id = 0;
    }
    return id++;
}

function addMarker(Coordinate) {
    var id = getId();
    delMarker(id);
    var marker = new google.maps.Marker({
        position: Coordinate,
        map: map,
        animation: google.maps.Animation.DROP,
        draggable: true
    });
    markers[id] = marker;
}

function delMarker(id) {
    if (markers[id] != null) {
        markers[id].setMap(null);
        delete markers[id];
    }
}

$("#getCoord").click(function () {
    $("#pickupcoordinate").val(markers[0].position.lat());
    $("#pickupcoordinate1").val(markers[0].position.lng());
    $("#dropoffcoordinate").val(markers[1].position.lat());
    $("#dropoffcoordinate1").val(markers[1].position.lng());
});


