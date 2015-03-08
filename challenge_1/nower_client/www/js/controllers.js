angular.module("App.controllers", [])

/*
* Se inyecta: $scope - para variables globales.
*             $compile - para compilar el contenido del infowindows.
*             SharedVars - para la latitud y longitud compartida con servicio.
*/
.controller('MapCtrl', ['$scope', '$compile', 'SharedVars',
                        function ($scope, $compile, SharedVars) {
  //El método addDomListener() enlaza con el objeto window del navegador
  //y permite que el API de Google Maps se comunique con los objetos que están
  //fuera del dominio normal del API.
  google.maps.event.addDomListener(window, 'load', function() {
    //Se definen una latitud y longitud por defecto, haciendo referencia a
    //la Universidad EAFIT.
    var defLat = 6.200168;
    var defLng = -75.578862;
    var defLatlng = new google.maps.LatLng(defLat, defLng);

    var mapOptions = {
      center: defLatlng,
      zoom: 15,
      mapTypeId: google.maps.MapTypeId.HYBRID
    };

    //Se crea el mapa y se carga en el div 'map' especificado en el archivo
    //HTML.
    var map = new google.maps.Map(document.getElementById("map"), mapOptions);

    //Se almacena el mapa en el scope global, para ser accedido en diferentes
    //funciones sin perder su referencia.
    $scope.map = map;

    //Se almacena en el servicio de variables compartidas la latitud y la
    //longitud que se establecen por defecto.
    SharedVars.setLat(defLat);
    SharedVars.setLng(defLng);
  });

  //Con geolocalización, se intenta acceder a la posición actual del usuario.
  navigator.geolocation.getCurrentPosition(function(pos) {
    //Si se pudo recuperar la información de la posición satisfactoriamente,
    //se recupera el mapa de las variables globales para ser usado.
    var map = $scope.map;

    //En el servicio de variables compartidas, se almacenan los valores
    //obetenidos de latitud y longitud, además, se centra el mapa en dichas
    //coordenadas.
    SharedVars.setLat(pos.coords.latitude);
    SharedVars.setLng(pos.coords.longitude);
    map.setCenter(new google.maps.LatLng(SharedVars.getLat(),
    SharedVars.getLng()));

    //Se construye un HTML con el contenido deseado para el InfoWindow y se
    //compila para ser asignado a un marcador.
    var contentString = "<div>Your promo goes here</div>";
    var compiled = $compile(contentString)($scope);

    //Se crea un marcador con la función createMarker.
    var myLocation = createMarker(compiled, map,
      SharedVars.getLat(),
      SharedVars.getLng());

    },
    function(error) {
      //Si no fue posible obtener la localización del usuario se enseña un
      //mensaje con el error.
      alert('Unable to get location: ' + error.message);
      var map = $scope.map;

      //En el servicio de variables compartidas, se almacenan los valores
      //obetenidos de latitud y longitud, además, se centra el mapa en dichas
      //coordenadas.
      map.setCenter(new google.maps.LatLng(SharedVars.getLat(),
      SharedVars.getLng()));

      //Se construye un HTML con el contenido deseado para el InfoWindow
      //indicando que debe ser arrastrado para indicar la localización del
      //usuario y lugar donde será visualizada la promoción.
      var contentString = "<div>Drag the marker to your location</a></div>";
      var compiled = $compile(contentString)($scope);

      //Se crea un marcador con la función createMarker.
      var myLocation = createMarker(compiled, map,
        SharedVars.getLat(),
        SharedVars.getLng());
    }, {enableHighAccuracy: true}
  );

  /*
  * Se recibe: compiled - el mensaje en HTML compilado del InfoWindow.
  *            map - mapa para dibujar el marcador.
  *            lat - latitud del marcador.
  *            lng - longitud del marcador.
  */
  function createMarker(compiled, map, lat, lng) {
    //Se selecciona un ícono amarillo para el marcador, se toma del sitio
    //oficial de Google Maps.
    var pinImage = new google.maps
    .MarkerImage("http://maps.google.com/mapfiles/ms/icons/yellow-dot.png");

    //Se crea el marcador con las características especificadas.
    var marker = new google.maps.Marker({
      position: new google.maps.LatLng(SharedVars.getLat(),
      SharedVars.getLng()),
      map: map,
      icon: pinImage,
      title: "My Location",
      draggable: true, //El marcador puede ser arrastrable.
      animation: google.maps.Animation.DROP
    });

    //Se crea el InfoWindow con el compilado HTML.
    var infowindow = new google.maps.InfoWindow({
      content: compiled[0]
    });

    //Se adiciona un 'listener' para el clic del marcador, el cual abrirá
    //el InfoWindow al ser presionado.
    google.maps.event.addListener(marker, 'click', function() {
      infowindow.open(map, marker);
    });

    //Se adiciona un evento para almacenar las nuevas coordenadas del marcador
    //una vez este haya sido terminado de arrastrar.
    google.maps.event.addListener(marker, 'dragend', function(evt) {
      SharedVars.setLat(evt.latLng.lat());
      SharedVars.setLng(evt.latLng.lng());
    });
    return marker;
  }
}])

/*
* Se inyecta: $scope - para variables globales.
*             $window - para poder recargar la vista (se tiene la ventana)
*             SharedVars - para la latitud y longitud compartida con servicio.
*             PromoService - gestiona el servicio para publicar la promoción.
*/
.controller('AppCtrl', ['$scope', '$window', 'SharedVars', 'PromoService',
                        function ($scope, $window, SharedVars, PromoService) {
  //Se obtiene un objeto con la fecha actual.
  var date = new Date();

  //A continuación, se crean los objetos, variables, nombres y valores que
  //hacen referencia a la fecha de vencimiento de la promoción. Se crea campo
  //por campo con el fin de lograr facilidad a la hora de recuperar la
  //información del formulario.
  $scope.years = [];
  $scope.years.push({'name': date.getFullYear(),
  'value':date.getFullYear()});
  $scope.years.push({'name': date.getFullYear() + 1,
  'value':date.getFullYear() + 1});
  $scope.year = $scope.years[0];

  //Se guardan los nombres de los meses.
  var monthNames = ["January", "February", "March", "April", "May", "June",
  "July", "August", "September", "October", "November", "December" ];
  $scope.months = [];
  for (var i = 1; i <= 12; ++i) {
    $scope.months.push({'name': monthNames[i - 1],
    'value': (i < 10 ? '0' + i : i)});
  }
  $scope.month = $scope.months[date.getMonth()];

  $scope.days = [];
  for (var i = 1; i <= 31; ++i) {
    $scope.days.push({'name': (i < 10 ? '0' + i : i),
    'value': (i < 10 ? '0' + i : i)});
  }
  $scope.day = $scope.days[date.getDay()];

  $scope.hours = [];
  //Se obtiene la diferencia entre el tiempo Universal (UTC) y el local, para
  //luego hacer un mapeo de la hora mostrada al usuario y la hora enviada
  //al servidor (Se quiere tener siempre la hora UTC en el servidor).
  var offset = date.getTimezoneOffset() / 60;
  for (var i = 0; i <= 23; ++i) {
    var utcHour = (24 + i + offset) % 24;
    $scope.hours.push({'name': (i < 10 ? '0' + i : i),
    'value': (utcHour < 10 ? '0' + utcHour : utcHour)});
  }
  $scope.hour = $scope.hours[Math.min(date.getHours() + 5, 23)];

  $scope.minutes = [];
  for (var i = 0; i <= 59; ++i) {
    $scope.minutes.push({'name': (i < 10 ? '0' + i : i),
    'value': (i < 10 ? '0' + i : i)});
  }
  $scope.minute = $scope.minutes[date.getMinutes()];

  //Esta función es llamada al presionar el botón 'Send promo' desde la vista.
  //En 'promo' se tiene toda la información del formulario.
  //Se asume que el objeto promo está completo puesto que el formulario tiene
  //los campos obligatorios.
  $scope.createPromo = function(promo) {
    //Se recupera el título de la promoción y se codifica en formato URI.
    var title = encodeURI(promo.title);
    title = title.replace('/', '%2F');
    title = title.split('.').join("");

    //Se recupera la descripción de la promoción y se codifica en formato URI.
    var description = encodeURI(promo.description);
    description = description.replace('/', '%2F');
    description = description.split('.').join("");

    //Se recupera la latitud y longitud del servicio de variables compartidas,
    //se convierten a string y luego se reemplazan los punto por comas,
    //con el fin de no tener problemas con la URL.
    var latitude = SharedVars.getLat();
    latitude = latitude.toString().replace('.',',');
    var longitude = SharedVars.getLng();
    longitude = longitude.toString().replace('.',',');

    //Se construye la fecha de vencimiento a partir de los selects.
    var date = $scope.year.value + "-" +
    $scope.month.value + "-" +
    $scope.day.value;
    var time = $scope.hour.value + ":" +
    $scope.minute.value + ":00";
    var expiration_date = encodeURI(date + " " + time);
    expiration_date = expiration_date.replace('/', '%2F');

    //Se recupera el valor de personas límite.
    var people_limit = promo.people_limit;

    //Se construye un JSON con las variables recuperadas.
    var jsonPromo = {
      "title": title,
      "description": description,
      "latitude": latitude,
      "longitude": longitude,
      "expiration_date": expiration_date,
      "people_limit": people_limit
    }
    var sale = {
      "sale": jsonPromo
    }

    //Se utiliza el mecanismo de Promise para llamar al método y esperar la
    //respuesta de este para poder avanzar.
    var sendPromoPromise = PromoService.sendPromo(sale);
    sendPromoPromise.then(function(result) {
      if(result.success == true) { //Promoción publicada exitosamente.
        alert("Promo published successfully");
        $window.location.reload();
      }
      else { //Fracaso al publicar promoción.
        alert("Unable to publish the promo");
      }
    });
  };
}]);
