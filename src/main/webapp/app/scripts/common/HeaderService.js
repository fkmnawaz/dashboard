(function(){
	'use strict';

	angular.module('dashboard.common')
	.service('HeaderService',['LoginService', function(LoginService){
		this.header = {};
		this.header.isNavigationVisisble = false;
		this.calculateHeader = function() {			
		

			this.header.menu = [
			                    	{
			                    				name: "Activity",
			                    				link: "activity",
			                    				restricted:false,
			                    				focus: true
			                    	}
			                    ];
		
		}
		
	}]);
})();