(function(){
'use strict';
 
angular.module('dashboard.login')
 
.factory('LoginService', ['$http', '$cookieStore', '$rootScope', '$timeout','$resource','localStorageService',
    function LoginService($http, $cookieStore, $rootScope, $timeout, $resource,localStorageService) 
	{
        var service = {};                              
        var loginURI=  'user';              
        var response={};            
        
        service.Login = function (user, callback) {
        	
        	$http({
                method  : 'POST',
                url     : 'spring',
                transformRequest: function(obj) {
                    var str = [];
                    for(var p in obj)
                    str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
                    return str.join("&");
                },
                data    : {user: user.name, password:user.password},
                headers : {'Content-Type': 'application/x-www-form-urlencoded'} 
               })
                .success(function(data, status, headers, config) {
                	response.success=false;
                	$http.get('user')
                		.success(function (data) {
                			if(typeof data === 'string')
                			{
                				response.success=false;
                				response.message="Invalid Credentials";
                				callback(response) && callback();
                			}
    		    			if(data.userName!=null||data.userName!=undefined)
    		    			{
    		    				response.success=true; 
    		    				response.message="Login Succesful"; 
    		    				$rootScope.csl=data.userName;
						$rootScope.role=data.role;
    		    			}            	        	
    		    			if(!response.success)
    		    			{		    				    		    				
    		    				response.message="Invalid Credentials";
    		    			}
    		    			callback(response) && callback();
                		})
                		.error(function () {                			
                			response.success=false;
                			response.message="Invalid Credentials";
                			callback(response) && callback();
                			
                		});
                }).error(function () {                	
                	response.success=false;
        			response.message="Invalid Credentials";
        			callback(response) && callback();
                });
        	
        };
		service.Logout = function(successCallback, failureCallback){			
			$http.post('logout', {}).success(successCallback).error(failureCallback);
			$rootScope.csl=null;
		}
 
        return service;
    }])

})();
