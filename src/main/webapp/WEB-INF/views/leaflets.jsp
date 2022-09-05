<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pl">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <title>Quick Start - Leaflet</title>

  <link rel="shortcut icon" type="image/x-icon" href="docs/images/favicon.ico"/>

  <link rel="stylesheet" href="https://unpkg.com/leaflet@1.8.0/dist/leaflet.css"
        integrity="sha512-hoalWLoI8r4UszCkZ5kL8vayOGVae1oxXe/2A4AO6J9+580uKHDO3JdHb7NzwwzK5xr/Fs0W40kiNHxM9vyTtQ=="
        crossorigin=""/>
  <script src="https://unpkg.com/leaflet@1.8.0/dist/leaflet.js"
          integrity="sha512-BB3hKbKWOc9Ez/TAwyWxNXeoV9c1v6FIeYiBieIWkpLjauysF18NzgR1MBNBXf8/KABdlkX68nAhlwcDFLGPCQ=="
          crossorigin=""></script>


  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/leaflet.draw/0.4.2/leaflet.draw.css"/>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/leaflet.draw/0.4.2/leaflet.draw.js"></script>


  <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>


  <style>
    html, body {
      height: 100%;
      margin: 0;
    }

    .leaflet-container {
      height: 400px;
      width: 600px;
      max-width: 100%;
      max-height: 100%;
    }


  </style>


</head>
<body>


<div id="map" style="width: 80%; height: 80%;"></div>

<form:form action="/api/leaflet" method="post" modelAttribute="coordinates">
  Data początkowa:
  <input type="date" name="Data">
  <br>
  Data końcowa:
  <input type="date" name="Data">
  <br>
  Ilość:
  <input type="text" name="Ilość">
  <br>
  Format ulotki:
  <input type="text" name="Format">
  <br>
  Waga 1000szt:
  <input type="text" name="Waga">
  <br>
  Uwagi:
  <input type="text" name="Ilość">
  <br>
  <input type="submit" value="Save">
</form:form>


<script>


  function drawMap(data) {


    var center = [51.1154, 17.0704];

    // Create the map
    var map = L.map('map').setView(center, 14);

    // Set up the OSM layer
    L.tileLayer(
            'https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
              maxZoom: 18
            }).addTo(map);

    // add a marker in the given location
    L.marker(center).addTo(map);

    // Initialise the FeatureGroup to store editable layers
    var editableLayers = new L.FeatureGroup();
    map.addLayer(editableLayers);

    // define custom marker
    var MyCustomMarker = L.Icon.extend({
      options: {
        shadowUrl: null,
        iconAnchor: new L.Point(12, 12),
        iconSize: new L.Point(24, 24),
        iconUrl: 'https://upload.wikimedia.org/wikipedia/commons/6/6b/Information_icon4_orange.svg'
      }
    });

    var drawPluginOptions = {
      position: 'topleft',
      draw: {
        polyline: {
          shapeOptions: {
            color: '#f357a1',
            weight: 10
          }
        },
        polygon: {
          allowIntersection: true, // Restricts shapes to simple polygons
          drawError: {
            color: '#e1e100', // Color the shape will turn when intersects
            message: '<strong>Polygon draw does not allow intersections!<strong> (allowIntersection: false)' // Message that will show when intersect
          },
          shapeOptions: {
            color: '#2e2c2c'
          }
        },
        circle: false, // Turns off this drawing tool
        rectangle: {
          shapeOptions: {
            clickable: false
          }
        },
        marker: {
          icon: new MyCustomMarker()
        }
      },
      edit: {
        featureGroup: editableLayers, //REQUIRED!!
        remove: false
      }
    };


    // Initialise the draw control and pass it the FeatureGroup of editable layers
    var drawControl = new L.Control.Draw(drawPluginOptions);
    map.addControl(drawControl);


    var editableLayers = new L.FeatureGroup();
    map.addLayer(editableLayers);


    console.log(data.coordinates);


    var polygon = L.polygon(data.coordinates, {color: 'red'}).addTo(map);
    console.log(polygon.getBounds());

// zoom the map to the polygon
    //  map.fitBounds(polygon.getBounds());


    map.on('draw:created', function (e) {
      var type = e.layerType,
              layer = e.layer;

      if (type === 'marker') {
        layer.bindPopup('A popup!');
      }

      coordinates = [];
      latlngs = layer.getLatLngs();



      for (var i = 0, j = 1; i < latlngs[0].length; j++, i = i + 2) {
        coordinates.push([latlngs[0][i].lat, latlngs[0][i].lng])
        // console.log('Współrzędne nr: ' + j)
        // console.log(latlngs[0][i].lng);
        // console.log(latlngs[0][i].lat);
      }



      ////////////////
      editableLayers.addLayer(layer);
      //return geojson;


      let napis = "napis";
      let napis2 = "napis";

      let user = {
        'name' : 'test',
        'surname' : 'nazwisko',
        'age' : 11,
        'points': 50
      }

      let offer = {
        'quantity': 5000,
        'leafletWidth': 150,
        'leafletHeight': 220,
        'kilogramPer1000Pieces': 1.5,
        // 'earliestDistributionDate': "2022-09-12",
        // 'latestDistributionDate': "16-09-2022",
        'status': 0
      }

      let myData = {
        'coordinates': coordinates,
        //'user': user
        'offer': offer

      }
      // var myData = {
      //   'coordinates': coordinates
      // }
      $.ajax({
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        url: "/api/leaflet",
        type: "POST",
        dataType: "JSON",
        contentType: "application/json",

        data: JSON.stringify(myData),

        //data: myData,
        success: function (data) {
          //console.log(data)
          console.log(myData);
        }
      })


      //////////////////////////


      let koordynaty = document.getElementById("koordynaty");
      for (var i = 0, j = 1; i < coordinates.length; i++, j++) {
        // koordynaty.innerHTML("Wspolrzedna X: "+coordinates[i][0]+ "     Współrzędna Y: "+ coordinates[i][1]);
        // console.log("Wspolrzedna X: " + coordinates[i][0] + " Wspolrzedna Y: " + coordinates[i][1]);
        //koordynaty.innerHTML(coordinates[i][0]+ coordinates[i][1]);

        let newLi = document.createElement("li");
        newLi.innerHTML = j + " Wspolrzedna X: " + coordinates[i][0] + "   " + "      Wspolrzedna Y: " + coordinates[i][1];
        koordynaty.appendChild(newLi);


      }
      // console.log(coordinates);
    });

  }

  $(document).ready(function () {

    $.ajax({
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      url: "/api/leaflet",
      type: "GET",
      dataType: "JSON",
      contentType: "application/json",
      success: function (data) {

        drawMap(data)
      }
    })

  });

</script>

<ul id="koordynaty"><h2>Wspolrzedne:</h2></ul>


</body>
</html>



