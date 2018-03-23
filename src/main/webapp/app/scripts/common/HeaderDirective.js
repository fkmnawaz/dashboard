	'use strict';

	angular.module('dashboard.common')
	.directive('mdHeader', function HeaderDirective(){
		return {
			restrict:"E",
			templateUrl:'./app/views/Common/Header.html',
			controller:"HeaderController"
		}
	});
