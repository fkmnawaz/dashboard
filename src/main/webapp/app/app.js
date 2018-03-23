'use strict';

angular.module('dashboard.login',[ 'ngAnimate', 'ngAria','ngCookies', 'ngMessages', 'ngResource', 'ngRoute', 'ui.router', 'ngSanitize', 'md.data.table','ngMaterial','dashboard.home','LocalStorageModule']);
angular.module('dashboard.home',['ui.router','ngResource','LocalStorageModule']);
angular.module('dashboard.common',['ui.router','ngResource','LocalStorageModule']);
angular.module('dashboard.admin',['ui.router','ngResource','LocalStorageModule', 'nvd3']);

angular
  .module('dashboard', [
    'ngAnimate',
    'ngAria',
    'ngCookies',
    'ngMessages',
    'ngResource',
    'ngRoute',
    'ui.router',
    'ngSanitize',  
    'ngCsv',
    'md.data.table',    
    'ngMaterial',    
    'LocalStorageModule',    
    'dashboard.login',
    'dashboard.home',    
    'dashboard.common',
    'dashboard.admin',
    'ngMdIcons',
    'ui.bootstrap',
    'ui.bootstrap.datetimepicker',
    'nvd3'
  ])
  .config(function ($stateProvider, $urlRouterProvider, $locationProvider) {
      $urlRouterProvider.otherwise("/");
	  $stateProvider
	    .state('/', {
	      url: '/',
	      templateUrl: "./app/views/Login/login.html",
	      controller: 'LoginController'
	    });	        
  })
  .config(['localStorageServiceProvider',function(localStorageServiceProvider){
		localStorageServiceProvider.setPrefix('dashboard');
	}])
	
  .run(['$rootScope', '$location', '$cookieStore', '$http',
    function ($rootScope, $location, $cookieStore, $http,$state) {        
       	$rootScope.$on('$locationChangeStart', function (event, next, current) {       				 	
            if ($location.$$path!='/' && $rootScope.isAuthenticated==false) {
                $location.path('/');           	
            }            
        });
    }]);    




