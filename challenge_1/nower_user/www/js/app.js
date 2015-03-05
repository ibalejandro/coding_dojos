var nower = angular.module('starter', ['ionic'])

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



/*
* Controllers
*/

nower.controller('MapController', function($scope, $rootScope, $compile) {

  google.maps.event.addDomListener(window, 'load', function() {
    var myLatlng = new google.maps.LatLng(6.200168, -75.578862);

    var mapOptions = {
      center: myLatlng,
      zoom: 14,
      mapTypeId: google.maps.MapTypeId.HYBRID
    }

    var map = new google.maps.Map(document.getElementById("map"), mapOptions);


    navigator.geolocation.getCurrentPosition(function(pos) {
      map.setCenter(new google.maps.LatLng(pos.coords.latitude, pos.coords.longitude));

      var contentString = "<div><a>You are here!</a></div>";
      var compiled = $compile(contentString)($scope);

      var infowindow = new google.maps.InfoWindow({
        content: compiled[0]
      });

      var pinImage = new google.maps.MarkerImage("http://maps.google.com/mapfiles/ms/icons/yellow-dot.png");
      var myLocation = new google.maps.Marker({
        position: new google.maps.LatLng(pos.coords.latitude, pos.coords.longitude),
        map: map,
        icon: pinImage,
        title: "My Location",
        animation: google.maps.Animation.DROP
      });

      google.maps.event.addListener(myLocation, 'click', function() {
        if (myLocation.getAnimation() != null) myLocation.setAnimation(null);
        else myLocation.setAnimation(google.maps.Animation.BOUNCE);
        if ($rootScope.currentInfoWindow != null) $rootScope.currentInfoWindow.close();
        $rootScope.currentInfoWindow = infowindow;
        infowindow.open(map, myLocation);
      });

    }, function(error) {
      alert('Unable to get location: ' + error.message);
    });

    $rootScope.map = map;

  });

});

nower.controller('MapRefresher', function($scope, $rootScope, $compile, $ionicPopup, RefreshingPromos) {
  $scope.promos = [];
  $scope.refreshPromos = function() {
    RefreshingPromos.getPromos($scope, $rootScope, $compile, $ionicPopup);
  }
});


/*
* Services
*/

nower.service("RefreshingPromos", ["$http", "$log", RefreshingPromos]);

function RefreshingPromos($http, $log) {
  this.getPromos = function($scope, $rootScope, $compile, $ionicPopup) {
    $http({
      url: "http://castofo.tk/sales.json",
      dataType: "json",
      method: "GET",
      headers: {
        "Content-Type": "application/json"
      }
    }).success(function(result) {
      $scope.promos = result;
      $log.info(JSON.stringify(result));
      showPromos($scope, $rootScope, $compile, $ionicPopup);
    }).
    error(function(data, status, headers, config) {
      // called asynchronously if an error occurs
      // or server returns response with an error status.
      alert("Error al traer el JSON");
    });
  };
}


/*
* Helpers.
*/

function showPromos($scope, $rootScope, $compile, $ionicPopup) {

  var promos = $scope.promos;
  var map = $rootScope.map;

  for (var i in promos) {
    var title = promos[i].title;
    var description = promos[i].description;
    var lat = promos[i].latitude;
    var lng = promos[i].longitude;
    var expiration = promos[i].expiration_date;
    var people = promos[i].people_limit;

    var contentString1 = "<div><a ng-click='onInfoWindowClick()'>";
    var contentString2 = "</a></div>";
    var contentString = contentString1.concat(title, " (People limit: ", people, ")", contentString2);
    var compiled = $compile(contentString)($scope);

    var infowindow = new google.maps.InfoWindow();

    var promo = new google.maps.Marker({
      position: new google.maps.LatLng(lat, lng),
      map: map,
      title: title,
      description: description,
      expiration: expiration,
      people: people
    });

    var handler = google.maps.event.addListener(
                      promo,
                      'click',
                      (function(promo, compiled) {
                        return function() {
                          $rootScope.currentPromo = promo;
                          infowindow.setContent(compiled[0]);
                          if ($rootScope.currentInfoWindow != null) $rootScope.currentInfoWindow.close();
                          $rootScope.currentInfoWindow = infowindow;
                          infowindow.open(map, promo);
                        };
                      })(promo, compiled)
                    );

    promo.handler = handler;
  }

  $rootScope.onInfoWindowClick = function() {
    $rootScope.currentInfoWindow.close();
    showConfirmDialog($ionicPopup, $rootScope, $compile, $scope);
  };

}

function showConfirmDialog($ionicPopup, $rootScope, $compile, $scope) {
  var currentPromo = $rootScope.currentPromo;
  var title = currentPromo.title;
  var description = currentPromo.description;
  var expiration = currentPromo.expiration;
  var people = currentPromo.people;
  var htmlTitle = "<font size='5'>";
  var header = htmlTitle.concat(title, "</font><br>(People limit: ", people, ")");
  var htmlBody = "<p align='justify'>";
  var body = htmlBody.concat(description, "</p>", "<br><center><font color='red' size='2'>Expiration date: ", 
                             expiration, "</font></center>");

  $ionicPopup.confirm({
    title: header,
    template: body,
    buttons: 
    [
      { text: 'Never' },
      { text: '<b>Now</b>',
        type: 'button-positive',
        onTap: function() { 
          takePromoNow($ionicPopup, currentPromo, $rootScope, $compile, $scope); 
        } 
      }
    ]
  });
}

function takePromoNow($ionicPopup, currentPromo, $rootScope, $compile, $scope){
  var htmlCode = "<center><b><font size='6'>"
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
          changePromoStatus(currentPromo, $rootScope, $compile, $scope, promoCode); 
        } 
      }
    ]
  });
}

function generatePromoCode() {
    var code = "";
    var possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    for(var i = 0; i < 5; i++) {
      code += possible.charAt(Math.floor(Math.random() * possible.length));
    }

    return code;
}

function changePromoStatus(currentPromo, $rootScope, $compile, $scope, promoCode) {
  var pinImage = new google.maps.MarkerImage("http://maps.google.com/mapfiles/ms/icons/green-dot.png");
  currentPromo.setIcon(pinImage);
  currentPromo.setAnimation(google.maps.Animation.DROP);
  
  var contentString1 = "<div>This promo is already yours! <br><center>Code: <b><font size='4'>";
  var contentString = contentString1.concat(promoCode, "</center></font></b></div>");
  var compiled = $compile(contentString)($scope);
  var infowindow = new google.maps.InfoWindow();

  //The previous event is deleted so that the new one overwrites it and works properly.
  google.maps.event.removeListener(currentPromo.handler);
  
  var handler = google.maps.event.addListener(
                      currentPromo,
                      'click',
                      (function(currentPromo, compiled) {
                        return function() {
                          $rootScope.currentPromo = currentPromo;
                          infowindow.setContent(compiled[0]);
                          if ($rootScope.currentInfoWindow != null) $rootScope.currentInfoWindow.close();
                          $rootScope.currentInfoWindow = infowindow;
                          infowindow.open($rootScope.map, currentPromo);
                        };
                      })(currentPromo, compiled)
                    );

  currentPromo.handler = handler;
}
