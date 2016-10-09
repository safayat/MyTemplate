var myApp = angular.module('RoutingApp', ['ngRoute','720kb.datepicker','LocalStorageModule','angucomplete-alt'])
	.config( ['$routeProvider', function($routeProvider) {
		$routeProvider
			.when('/signup', {
				templateUrl: 'pages/signup/signup.html',
				controller: 'signupController'
			})
			.when('/login', {
				templateUrl: 'pages/login/login.html',
				controller: 'loginController'
			})
			.otherwise({
				redirectTo: '/login'
			});
	}])
	.constant('appConfig',{
		"baseUrl" : getbaseUrl()
	});


console.log(getbaseUrl());
function getbaseUrl(){
	var splitUrl = window.location.href.split("//");
	var domainAndPath = splitUrl.length > 1 ? splitUrl[1] : splitUrl[0];
	var domainAndPathSplit = domainAndPath.split("/");
	var baseUrl = splitUrl.length > 1 ? (splitUrl[0]+"//") : "";
	baseUrl = baseUrl + domainAndPathSplit[0] + "/" + domainAndPathSplit[1];
	return baseUrl;
}



