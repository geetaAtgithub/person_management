   			var app = angular.module("PersonManagement", ['ngRoute']);        
                    	
			 app.config(['$routeProvider', function($routeProvider) {
		  	   $routeProvider		  	   
		      	 .when('/persons', {
		 			templateUrl: 'person.html',
		 			controller: 'PersonController'
		 		})
		 		.when('/address/:personId', {
		 			templateUrl: 'addresses.html',
		 			controller: 'AddressController'
		 		})
		 		.when('/error', {
		 			templateUrl: 'error.html',
		 			//controller: 'AddressController'
		 		})
		 		.otherwise({
		 			redirectTo: '/persons'
		 		});
		  		
		  	}]);         	   
            
            app.controller("PersonController", function($scope, $http,$location) {        
               
                $scope.personList = [];
                $scope.personForm = {
                    id : -1,
                    firstName : "",
                    lastName : ""
                };
         
                _refreshPersonData();
                     
                $scope.submitPerson = function() {         
                    var method = "";
                    var url = "";
                    if ($scope.personForm.id == -1) {
                        method = "POST";
                        url = '/addPerson';
                    } else {
                        method = "PUT";
                        url = '/updatePerson';
                    }
         
                    $http({
                        method : method,
                        url : url,
                        data : angular.toJson($scope.personForm),
                        headers : {
                            'Content-Type' : 'application/json'
                        }
                    }).then( _success, _error );
                };
         
                $scope.goToAddress = function(person) {
                	 $location.path('/address/'+person.id);
                };
                
                $scope.deletePerson = function(person) {
                    $http({
                        method : 'DELETE',
                        url : '/deletePerson/' + person.id
                    }).then(_success, _error);
                };
 
                $scope.editPerson = function(person) {
                  
                    $scope.personForm.firstName = person.firstName;
                    $scope.personForm.lastName = person.lastName;
                    $scope.personForm.id = person.id;
                };         
            
                function _refreshPersonData() {
                    $http({
                        method : 'GET',
                        url : 'http://localhost:8080/getAllPersons'
                    }).then(function successCallback(response) {
                        $scope.personList = response.data;
                    }, function errorCallback(response) {
                        console.log(response.statusText);
                    });
                }
         
                function _success(response) {
                    _refreshPersonData();
                    _clearFormData()
                }
         
                function _error(response) {
                    console.log(response.statusText);
                    $location.path('/error/');
                }
         
                //Clear the form
                function _clearFormData() {
                    $scope.personForm.id = -1;
                    $scope.personForm.firstName = "";
                    $scope.personForm.lastName = "";
                
                };
            });
            
            app.controller("AddressController", function($scope, $http,$routeParams) {
            	
             	$scope.personId=$routeParams.personId;
             	$scope.stateNames = ["Andhra Pradesh", "Arunachal Pradesh", "Assam","Bihar","Delhi","Goa","Telangana","TamiNadu"];
             	 $scope.addressList = [];
             	 $scope.addressForm = {
                         id : -1,
                         personId:$routeParams.personId,
                         street : "",
                         state : $scope.stateNames[0],
                         city : "",
                         postalCode: "",

                     };
             	 
             	$scope.checkselection = function () {
             		if ($scope.addressForm.state != "--Select--" && $scope.addressForm.state != undefined)
             			return true;
             		else{
             			$scope.msg = 'Please Select state';
             			return false;}
             	}
             	 
                 _refreshAddressData();
                 _clearAddressFormData();

             	$scope.submitAddress = function() {                    
                    var method = "";
                    var url = "";
                    if ($scope.addressForm.id == -1) {
                        method = "POST";
                        url = '/addAddress';
                    } else {
                        method = "PUT";
                        url = '/updateAddress';
                    }
         
                    $http({
                        method : method,
                        url : url,
                        data : angular.toJson($scope.addressForm),
                        headers : {
                            'Content-Type' : 'application/json'
                        }
                    }).then( _success, _error );
                };
                
                function _success(response) {
                    _refreshAddressData();
                    _clearAddressFormData()
                }
         
                function _error(response) {
                    console.log(response.statusText);
                }
             	
                function _refreshAddressData() {
                    $http({
                        method : 'GET',
                        url : 'http://localhost:8080//getAllAddresses/'+$scope.personId
                    }).then(function successCallback(response) {
                        $scope.addressList = response.data;
                    }, function errorCallback(response) {
                        console.log(response.statusText);
                    });
                }
                
                function _clearAddressFormData() {
                    $scope.addressForm.id = -1;
                    $scope.addressForm.street = "";
                    $scope.addressForm.city = "";
                  //  $scope.addressForm.state ="";
                   $scope.addressForm.state = "--Select--";
                  //  $scope.addressForm.state = $scope.stateNames[0];
                    $scope.addressForm.postalCode = "";
                };
                
                $scope.editAddress = function(address) {                    
                    $scope.addressForm.street = address.street;
                    $scope.addressForm.city = address.city;
                    $scope.addressForm.state = address.state;
                    $scope.addressForm.postalCode = address.postalCode;
                    $scope.addressForm.id = address.id;
                    $scope.addressForm.personId = $routeParams.personId;
                };
                
                $scope.deleteAddress = function(address) {
                    $http({
                        method : 'DELETE',
                        url : '/deleteAddress/' + address.id
                    }).then(_success, _error);
                };
         
          
                function _refreshPersonData() {
                    $http({
                        method : 'GET',
                        url : 'http://localhost:8080/getAllPersons'
                    }).then(function successCallback(response) {
                        $scope.personList = response.data;
                    }, function errorCallback(response) {
                        console.log(response.statusText);
                    });
                }
         
             });
            
            