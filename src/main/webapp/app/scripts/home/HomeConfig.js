(function(){
	angular.module('dashboard.home').config(HeaderConfig);
	function HeaderConfig($stateProvider){
	$stateProvider
	    .state('home', {
	      url: '/home',
	      abstract: true,
	      templateUrl: "./app/views/Home/home.html",
	      controller: 'HomeController'
	    })
	    .state('home.dashboard', {
	    	url: '/dashboard',
	    	templateUrl: './app/views/Home/admin.html',
	    	controller: 'AdminController'
	    })	  	   
	};
	
})();


