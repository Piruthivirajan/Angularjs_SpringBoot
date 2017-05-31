(function () {
    'use strict';

    angular
        .module('userapp')
        .controller('userController', userController);

    userController.$inject = ['$scope','User']; 

    function userController($scope, User) {
         $scope.Users = User.query();
         $scope.result = "";
         $scope.ucheck=1;
         $scope.udelete=1;
         $scope.checklogin=1;
             
        $scope.saveUser = function() {    
            User.save($scope.User,function(success){ 
             $scope.result = JSON.stringify(success);
              window.location.href = '#/details';
             },function(error){
              
             });
             }   
        $scope.updateUser = function() {    
            User.update($scope.User,function(success){ 
            	for(var i = 0; i < $scope.Users.length; i++) {
            	  if($scope.Users[i].id==$scope.User.id)
            		  {
            		  $scope.ucheck=0;
            		  }
            		}
            	if($scope.ucheck==0){
            		$scope.result = JSON.stringify(success);
            		window.location.href = '#/details';}
            	else
            		alert(JSON.stringify("UserId is Invalid"));
            	
             },function(error){
              
             });
             }
        
        $scope.loginUser = function() {    
        	
        	for(var i = 0; i < $scope.Users.length; i++) {
          	  if($scope.Users[i].name==$scope.User.name && $scope.Users[i].password==$scope.User.password)
          		  {
          		  $scope.checklogin=0;
          		window.location.href = '#/details';
          		  }
          		}
          	if($scope.checklogin==0){
          		 		window.location.href = '#/details';}
          	else
          		alert(JSON.stringify("Username is Invalid"));
        	         	  
             } 
        
        $scope.deleteUser = function() {    
            User.delete($scope.User,function(success){ 
            	for(var i = 0; i < $scope.Users.length; i++) {
              	  if($scope.Users[i].id==$scope.User.id)
              		  {
              		  $scope.dcheck=0;
              		  }
              		}
              	if($scope.dcheck==0){
              			$scope.result = JSON.stringify(success);
              			window.location.href = '#/details';}
              	else
              		alert(JSON.stringify("UserId is Invalid"));
             },function(error){
              
             });
             }   
        
        $scope.getUser=function (id) {
            for (i in Users) {
                if (Users[i].id == id) {
                    return Users[i];               }
            }
        }
      
  }}
 
)();
