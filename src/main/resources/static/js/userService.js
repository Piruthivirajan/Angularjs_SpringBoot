(function () {
    'use strict';
    var userService = angular.module('userapp');
    userService.factory('User', ['$resource',
    function ($resource) {
        
        return $resource('users/:id', {id:'@id'},
         {
             save:{method:"POST"},
             update:{method:"POST"},
             remove:{method:"DELETE"},
             get:{method:"GET"}
        });
    }
    ]);
})();