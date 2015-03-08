angular.module('starter.controllers', [])


.controller('MapController', function($scope, $rootScope, $compile) {

  //El método addDomListener() enlaza con el objeto window del navegador
  //y permite que el API de Google Maps se comunique con los objetos que están
  //fuera del dominio normal del API.
  google.maps.event.addDomListener(window, 'load', function() {
    //Coordenada que localiza a EAFIT en Google Maps.
    var defLatlng = new google.maps.LatLng(6.200168, -75.578862);

    var mapOptions = {
      center: defLatlng,
      zoom: 14,
      mapTypeId: google.maps.MapTypeId.HYBRID
    }

    //Se crea el mapa y se carga en el div 'map' especificado en el archivo
    //HTML.
    var map = new google.maps.Map(document.getElementById("map"), mapOptions);

    //Con geolocalización, se intenta acceder a la posición actual del usuario.
    navigator.geolocation.getCurrentPosition(function(pos) {
      map.setCenter(new google.maps.LatLng(pos.coords.latitude, 
                                           pos.coords.longitude));

      //Se construye un HTML con el contenido deseado para el InfoWindow.
      var contentString = "<div><a>You are here!</a></div>";

      var infowindow = new google.maps.InfoWindow({
        content: compileContent(contentString, $compile, $scope)
      });

      //Se crea un marcador que tendrá ícono y animación especial.
      var myLocation = createMarker(pos.coords.latitude, pos.coords.longitude,
                                    map, "My location", null, null, null,
                                    true, 
                    "http://maps.google.com/mapfiles/ms/icons/yellow-dot.png");


      //Se adiciona un 'listener' para el click del marcador, el cual abrirá
      //el InfoWindow al ser presionado.
      google.maps.event.addListener(myLocation, 'click', function() {
        if (myLocation.getAnimation() != null) myLocation.setAnimation(null);
        else myLocation.setAnimation(google.maps.Animation.BOUNCE);
        if ($rootScope.currentInfoWindow != null) $rootScope.currentInfoWindow
                                                  .close();
        $rootScope.currentInfoWindow = infowindow;
        infowindow.open(map, myLocation);
      });

    }, function(error) { 
      //Se muestra un mensaje de error si no es posible acceder a la
      //localización del usuario.
      alert('Unable to get location: ' + error.message);
    });

    //Se guarda el mapa dentro de la variable global $rootScope para poder
    //accederlo desde otros métodos.
    $rootScope.map = map;

  });

})

//Este método se usa para que se actualicen y muestren todas las promociones
//disponibles en el momento.
.controller('MapRefresher', function($scope, $rootScope, $compile, 
                                          $ionicPopup, RefreshingPromos) {
  $scope.refreshPromos = function() {
    RefreshingPromos.getPromos($scope, $rootScope, $compile, $ionicPopup);
  }
});
