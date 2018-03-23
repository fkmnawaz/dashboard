(function(){
'use strict';
angular.module('dashboard.login') 
.controller('LoginController',['$scope', '$rootScope', '$location', 'LoginService','$state','localStorageService',
    function ($scope, $rootScope, $location, LoginService,$state,localStorageService) 
	{        
        $scope.user={};
        $scope.LoginText="LOGIN";
        $scope.dataLoading = false;		
        $scope.login = function () 
		{
            $scope.dataLoading = true;
            $scope.LoginText="Authenticating";            
            LoginService.Login($scope.user, function(response) 
			{			            		
					if(response.success) 
					{																									
						$scope.LoginText="Redirecting";
						$scope.dataLoading = true;						
						$state.go('home.dashboard');						
						
					} 
					else 
					{
						$scope.LoginText="LOGIN";
						$scope.dataLoading = false;
						$scope.error = "Invalid username or password";
						setTimeout(function() {                    	
	                    	$scope.error = undefined;
	                    }, 1000);
					}
			});
        };        
    }]);

})();
