'use strict';

angular.module('dashboard.admin')


.controller('AdminController', ['$scope','$state','$rootScope','AdminService','$mdDialog',
		function AdminController($scope, $state, $rootScope,AdminService,$mdDialog) 
		{   				

				$scope.currentUser = $rootScope.csl;
				$scope.role=$rootScope.role;
				if($rootScope.csl==null||$rootScope.csl==undefined)
					$state.go("/");
				$scope.height=screen.height - 50 + "px";
				$scope.width=screen.width - 100 + "px";																
				
				
					
		}
	]);
	
	
	
	
	
