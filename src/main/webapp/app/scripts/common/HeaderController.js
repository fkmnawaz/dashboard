
'use strict';

angular.module('dashboard.common',[])
 
.controller('HeaderController', ['$mdSidenav','$scope','$state','$rootScope','LoginService','HeaderService','AdminService',
		function HeaderController($mdSidenav,$scope, $state,$rootScope,LoginService,HeaderService, AdminService) 
		{      
			
			
			$scope.role=$rootScope.role;
			
			$scope.dashboard=function(){				 									
						$state.go('home.admin');					
			}
			
			
			$scope.logout=function()
			{
				HeaderService.header.isNavigationVisisble=false;
				LoginService.Logout(function(){						
				    $state.go("/");
				}, function(){
					$rootScope.fullName = null;
				});
			};			
		}
	]);
	
	
	
	
	
