var myApp = angular.module('RoutingApp');
myApp.controller('signupController', function($scope, $http,appConfig) {
	$scope.saveUser = function(){
		console.log($scope.formData);
		$http({
			method: 'POST',
			url: appConfig.baseUrl + '/public/api/signup',
			data: $scope.formData
		}).success(function (data) {
			console.log(data);

		});
	}



});
