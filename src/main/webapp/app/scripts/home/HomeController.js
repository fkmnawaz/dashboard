'use strict';

angular.module('dashboard.home')
 
.controller('HomeController', ['$scope','$state','$rootScope',
		function HomeController($scope, $state, $rootScope) 
		{   				
				$scope.currentUser = $rootScope.csl;				
								
		}
	]);
	
	
	
	
	
