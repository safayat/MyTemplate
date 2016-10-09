var myApp = angular.module('RoutingApp');
myApp.controller('loginController', function($scope, $http, appConfig,$rootScope,$routeParams,$location,authService) {

	$rootScope.hideHeader = true;
	$rootScope.hideSideBar = true;
	$scope.loginUser = function(){
		$http({
			method: 'POST',
			url: appConfig.baseUrl + '/j_spring_security_check',
			data: $.param({
				"username" : $scope.username,
				"password" : $scope.password
			}),
			headers: {'Content-Type': 'application/x-www-form-urlencoded'}

		}).success(function (data) {
			console.log("datta:" + data);
			authService.saveLoginInfo(data);
			$rootScope.hideHeader = false;
			$rootScope.hideSideBar = false;
			window.location.href = appConfig.baseUrl + '/#/client/view';
		}).error(function(data){
			$scope.errorMsg = "login failed";
		});
	}

	if($routeParams.logout){
		$http({
			method: 'POST',
			url: appConfig.baseUrl + '/j_spring_security_logout',
			data: $.param({}),
			headers: {'Content-Type': 'application/x-www-form-urlencoded'}

		}).success(function (data) {
			console.log(data);
		}).error(function (data) {
			console.log(data);
		});

	}



});

