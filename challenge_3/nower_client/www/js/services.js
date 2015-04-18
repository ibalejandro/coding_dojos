angular.module('App.services', [])

//Este servicio almacena los valores de latitud y longitud registradas por
//el marcador del mapa.
.service('SharedVars', function () {
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
})

//Servicio para gestionar la publicación de una nueva promoción
.service('PromoService', ['$http', function ($http) {
  return {
    sendPromo: function(promo) {
      // Se envía la petición POST para crear la promoción
      return $.post("http://castofo.tk/promos", promo,
      function(response) {
        console.log(JSON.stringify(response));
        //Se responde de acuerdo a lo que el servidor retorna.
        var data = {success: response.success};
        return data;
      })
      .fail(function(xhr, text, exception) {
        console.log(xhr.responseText);
        //Se tuvieron problemas con el método.
        var data = {success: false};
        return data;
      });
    }
  };
}]);
