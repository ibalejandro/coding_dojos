angular.module('starter', ['ionic', 'starter.controllers', 'starter.services'])

.run(function($ionicPlatform) {
  $ionicPlatform.ready(function() {
    if (window.cordova && window.cordova.plugins.Keyboard) {
      cordova.plugins.Keyboard.hideKeyboardAccessoryBar(true);
    }
    if (window.StatusBar) {
      StatusBar.styleDefault();
    }
  });
});


function compileContent(contentString, $compile, $scope) {
  //Se compila el contenido deseado para que quede perteneciendo al
  //InfoWindow que está siendo creado.
  var compiled = $compile(contentString)($scope);
  return compiled[0];
}

function createMarker(lat, lng, map, title, description, expiration, people, 
                      specialIcon, color) {

  //Se crea un marcador de acuerdo a los parámetros recibidos.
  var marker = new google.maps.Marker({
      position: new google.maps.LatLng(lat,lng),
      map: map,
      title: title,
      description: description,
      expiration: expiration,
      people: people
    });

  if (specialIcon) setSpecialIconAndAnimation(color, marker);

  return marker;
}

function setSpecialIconAndAnimation(color, marker) {
  //Se selecciona un ícono del color indicado para el marcador,
  //tomado del sitio oficial de Google Maps.
  var pinImage = new google.maps.MarkerImage(color);
    
  marker.setIcon(pinImage);
  //Todo marker con ícono especial tendrá la animación de DROP.
  marker.setAnimation(google.maps.Animation.DROP);
}

function showPromos($scope, $rootScope, $compile, $ionicPopup) {

  var promos = $scope.promos;
  var map = $rootScope.map;

  //Para cada promoción obtenida, se muestra un marcador dentro del mapa.
  for (var i in promos) {
    var title = promos[i].title;
    var description = promos[i].description;
    var lat = promos[i].latitude;
    var lng = promos[i].longitude;
    var expiration = promos[i].expiration_date;
    var people = promos[i].people_limit;

    var contentString1 = "<div><a ng-click='onInfoWindowClick()'>";
    var contentString2 = "</a></div>";
    var contentString = contentString1.concat(title, " (People limit: ", 
                                              people, ")", contentString2);
    var compiled = $compile(contentString)($scope);

    var infowindow = new google.maps.InfoWindow();

    var promo = createMarker(lat, lng, map, title, description, 
                             expiration, people, false, "");

    //El evento asociado al marcador que se está creando es almacenado en
    //un handler, el cual podrá ser removido al momento de querer reemplazar
    //la acción que se ejecuta al presionar el marcador.
    var handler = google.maps.event.addListener(
                    promo,
                    'click',
                    (function(promo, compiled) {
                      return function() {
                        //Se almacenará en el $rootScope la promoción (Marker
                        //e Infowindow) que se encuentra observando el 
                        //usuario en el momento. 
                        $rootScope.currentPromo = promo;
                        infowindow.setContent(compiled[0]);
                        if ($rootScope.currentInfoWindow != null) {
                          $rootScope.currentInfoWindow.close();
                        }
                        $rootScope.currentInfoWindow = infowindow;
                        //Se abrirá la InfoWindow que contiene la información
                        //detallada de al la promoción.
                        infowindow.open(map, promo);
                      };
                    })(promo, compiled)
                  );

    promo.handler = handler;
  }

  //Esta será la acción que se ejecutará al momento de presionar la
  //Infowindow de alguna promoción.
  $rootScope.onInfoWindowClick = function() {
    $rootScope.currentInfoWindow.close();
    //Este método hará visible una ventana en la que el usuario podrá
    //decidir si accede o no a la promoción.
    showConfirmDialog($ionicPopup, $rootScope, $compile, $scope);
  };

}

function showConfirmDialog($ionicPopup, $rootScope, $compile, $scope) {
  var currentPromo = $rootScope.currentPromo;
  var title = currentPromo.title;
  var description = currentPromo.description;
  var expiration = currentPromo.expiration;
  var people = currentPromo.people;

  //Se crea un texto en formato HTML para presentar la información detallada 
  //de la promoción, de manera que sea más entendible y agradable para el
  //usuario.
  var htmlTitle = "<font size='5'>";
  var header = htmlTitle.concat(title, "</font><br>(People limit: ", people, 
                                ")");
  var htmlBody = "<p align='justify'>";
  var body = htmlBody.concat(description, "</p>", 
                    "<br><center><font color='red' size='2'>Expiration date: ", 
                             expiration, "</font></center>");

  //El mensaje de confirmación es un diálogo emergente que ofrece ionic y 
  //se usa a través de la variable $ionicPopup.
  $ionicPopup.confirm({
    title: header,
    template: body,
    buttons: 
    [
      { text: 'Never' },
      { text: '<b>Now</b>',
        type: 'button-positive',
        onTap: function() { 
          //Cuando el usuario presione el botón "Now", se hará acreedor
          //de la promoción y se ejecutará la función takePromoNow() con el fin
          //de asignarle un código único para que pueda redimrla. 
          //En caso contrario, simplemente se cerrará el diálogo.
          takePromoNow($ionicPopup, currentPromo, $rootScope, $compile, $scope); 
        } 
      }
    ]
  });
}

function takePromoNow($ionicPopup, currentPromo, $rootScope, $compile, $scope){
  var htmlCode = "<center><b><font size='6'>"
  //Se invoca la función generatePromoCode() para generar un código aleatorio
  //de 5 caracteres alfanuméricos que simule la obtención de la promoción.
  var promoCode = generatePromoCode();
  var code = htmlCode.concat(promoCode, "</font></b></center>")
  var header = "Your promo code is";
  $ionicPopup.confirm({
    title: header,
    template: code,
    buttons: 
    [
      { text: '<b>OK</b>',
        type: 'button-positive',
        onTap: function() { 
          //Al presionar el botón "OK", la promoción actual tendrá una
          //apariencia diferente para el usuario, con el fin de que este
          //identifique las promociones a las cuales ha accedido hasta el
          //momento.
          changePromoStatus(currentPromo, $rootScope, $compile, $scope, 
                            promoCode); 
        } 
      }
    ]
  });
}

//Función que genera un código aleatorio de 5 caracteres alfanuméricos.
function generatePromoCode() {
    var code = "";
    var possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    for(var i = 0; i < 5; i++) {
      code += possible.charAt(Math.floor(Math.random() * possible.length));
    }

    return code;
}

function changePromoStatus(currentPromo, $rootScope, $compile, $scope, 
                           promoCode) {
  //Esta función cambia el ícono de la promoción y le pone animación
  //para que el usuario identifique que accedió a ella.
  setSpecialIconAndAnimation(
        "http://maps.google.com/mapfiles/ms/icons/green-dot.png", currentPromo);
  
  var contentString1 = 
      "<div>This promo is already yours! <br><center>Code: <b><font size='4'>";
  var contentString = contentString1.concat(promoCode, 
                                                  "</center></font></b></div>");
  
  var infowindow = new google.maps.InfoWindow({
      content: compileContent(contentString, $compile, $scope)
  });

  //Se borra el evento que existía para reemplazar por uno nuevo que muestre
  //una InfoWindow diferente. Esta nueva Infowindow indicará que al usuario
  //ya le pertenecen determinadas promociones.
  google.maps.event.removeListener(currentPromo.handler);
  
  var handler = google.maps.event.addListener(
                  currentPromo,
                  'click',
                  (function(currentPromo) {
                    return function() {
                      //Se actualizan tanto Marker como InfoWindow debido
                      //a que su información interna fue modificada.
                      $rootScope.currentPromo = currentPromo;
                      if ($rootScope.currentInfoWindow != null) {
                        $rootScope.currentInfoWindow.close();
                      }
                      $rootScope.currentInfoWindow = infowindow;
                      infowindow.open($rootScope.map, currentPromo);
                    };
                  })(currentPromo)
                );

  //Con esto se logra la actualización del evento al que responde el click
  //sobre el marcador.
  currentPromo.handler = handler;
}
