(function () {
    'use strict';

    angular.module('userapp', [
        'ngRoute','ngResource'

        
    ]).config(['$routeProvider', function ($routeProvider) {
  $routeProvider
   
    .when("/", { templateUrl: "partial/login.html", controller: "userController" })
    .when("/details", { templateUrl: "partial/details.html", controller: "userController" })
    .when("/create", { templateUrl: "partial/create.html", controller: "userController" })
     .when("/update", { templateUrl: "partial/Update.html", controller: "userController" })
     .when("/delete", { templateUrl: "partial/delete.html", controller: "userController" })
     .when("/get", { templateUrl: "partial/getbyid.html", controller: "userController" })
    .otherwise("/404", {templateUrl: "partials/404.html"});
}]);
})();