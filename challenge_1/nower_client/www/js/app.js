var App = angular.module("App", ["ionic"]);


/*App.service("Nower", ["$http", "$log", Nower]);*/

App.service("SharedVars", [SharedVars]);

App.service("PromoService", ['$http', PromoService]);

App.controller("MapCtrl", ["$scope", "$compile", "SharedVars", MapCtrl]);

App.controller("AppCtrl", ["$scope", "$log", "SharedVars", "PromoService", AppCtrl]);

/*
* Controllers
*/
function MapCtrl ($scope, $compile, SharedVars) {
  google.maps.event.addDomListener(window, 'load', function() {
    var defLat = 6.200168;
    var defLng = -75.578862;
    var myLatlng = new google.maps.LatLng(defLat, defLng);

    var mapOptions = {
      center: myLatlng,
      zoom: 18,
      mapTypeId: google.maps.MapTypeId.HYBRID
    };

    var map = new google.maps.Map(document.getElementById("map"), mapOptions);

    $scope.map = map;
    SharedVars.setLat(defLat);
    SharedVars.setLng(defLng);
  });

  navigator.geolocation.getCurrentPosition(function(pos) {
    var map = $scope.map;
    SharedVars.setLat(pos.coords.latitude);
    SharedVars.setLng(pos.coords.longitude);
    map.setCenter(new google.maps.LatLng(SharedVars.getLat(), SharedVars.getLng()));

    var contentString = "<div><a ng-click='clickTest()'>Your promo goes here</a></div>";
    var compiled = $compile(contentString)($scope);

    var myLocation = createMarker(compiled, map, SharedVars.getLat(), SharedVars.getLng());

  }, function(error) {
    alert('Unable to get location: ' + error.message);
    var map = $scope.map;
    map.setCenter(new google.maps.LatLng(SharedVars.getLat(), SharedVars.getLng()));

    var contentString = "<div><a ng-click='clickTest()'>Draw the marker to your location</a></div>";
    var compiled = $compile(contentString)($scope);

    var myLocation = createMarker(compiled, map, SharedVars.getLat(), SharedVars.getLng());
  }, {enableHighAccuracy: true}
  );

  function createMarker(compiled, map, lat, lng) {
    var pinImage = new google.maps.MarkerImage("http://maps.google.com/mapfiles/ms/icons/yellow-dot.png");
    var marker = new google.maps.Marker({
      position: new google.maps.LatLng(SharedVars.getLat(), SharedVars.getLng()),
      map: map,
      icon: pinImage,
      title: "My Location",
      draggable: true,
      animation: google.maps.Animation.DROP
    });

    var infowindow = new google.maps.InfoWindow({
      content: compiled[0]
    });

    google.maps.event.addListener(marker, 'click', function() {
      if (marker.getAnimation() != null) marker.setAnimation(null);
      else marker.setAnimation(google.maps.Animation.BOUNCE);
      infowindow.open(map, marker);
    });

    google.maps.event.addListener(marker, 'dragend', function(evt) {
      SharedVars.setLat(evt.latLng.lat());
      SharedVars.setLng(evt.latLng.lng());
      console.log("lat: " + SharedVars.getLat() + " lng: " + SharedVars.getLng());
    });
    return marker;
  }
}

function AppCtrl($scope, $log, SharedVars, PromoService) {
  /*$scope.posts = [];
  $scope.refresh = function() {
    Nower.getBlogs($scope);
  }*/
  var date = new Date();
  $scope.years = [];
  $scope.years.push({'name': date.getFullYear(),
                     'value':date.getFullYear()});
  $scope.years.push({'name': date.getFullYear() + 1,
                     'value':date.getFullYear() + 1});
  $scope.year = $scope.years[0];

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


  $scope.createPromo = function(promo) {
    var date = $scope.year.value + "-" +
               $scope.month.value + "-" +
               $scope.day.value;
    var time = $scope.hour.value + ":" +
               $scope.minute.value + ":00";
    var jsonPromo = {
      "title": promo.title ? promo.title : null,
      "description": promo.description ? promo.description : null,
      "latitude": SharedVars.getLat(),
      "longitude": SharedVars.getLng(),
      "expiration_date": date + " " + time,
      "people_limit": promo.people_limit
    };
    var sale = {
      "sale": jsonPromo
    }
    console.log(JSON.stringify(sale));
    alert(JSON.stringify(sale));

    PromoService.sendPromo(sale);
    /*var xhr = createCORSRequest('POST', 'http://localhost:3000/sales.json');
    if (!xhr) {
      alert("CORS not supported");
      return;
    }

    xhr.onload = function() {
      var responseText = xhr.responseText;
      console.log(responseText);
      alert("Sí dio");
    };

    xhr.onerror = function() {
      console.log('There was an error!');
      alert("No dio");
    };

    xhr.withCredentials = true;
    xhr.send(JSON.stringify(sale));*/
    /*$http({
      method:"POST",
      url: "http://fashionadvisor.herokuapp.com/users/sign_in",
      data: {"user":{"email":"pera@pene.com", "password":"12345678"}}
    }).
    then(function(result) {
      alert(result.data);
    });*/
  };

  function createCORSRequest(method, url) {
    var xhr = new XMLHttpRequest();
    if ("withCredentials" in xhr) xhr.open(method, url, true);
    else if (typeof XDomainRequest != "undefined") {
      xhr = new XDomainRequest();
      xhr.open(method, url);
    }
    else xhr = null;
    return xhr;
  }
}

/*
* Services
*/
function SharedVars() {
  var lat;
  var lng;

  return {
    getLat: function () {
      return lat;
    },
    setLat: function(value) {
      lat = value;
    },
    getLng: function () {
      return lng;
    },
    setLng: function(value) {
      lng = value;
    }
  };
}

function PromoService($http) {
  return {
    sendPromo: function(promo){
      return $http({
        method: "POST",
        url: "http://castofo.tk/sales.json",
        data: promo
      }).then(function(result) {
        alert(result.data);
        return result.data;
      });
    }
  };
}

/*function Nower($http, $log) {
  this.getBlogs = function($scope) {
    $http.jsonp("https://public-api.wordpress.com/rest/v1/freshly-pressed?callback=JSON_CALLBACK")
      .success(function(result) {
        $scope.posts = result.posts;
        $scope.$broadcast("scroll.refreshComplete");
    });
  };
}*/

