var myApp = angular.module('RoutingApp');
myApp.controller('moduleController', function($scope, $http, appConfig) {


	$scope.fieldData = [];
	$scope.addNewField = function(){
		$scope.fieldData.push({
			name : '',
			type : ''
		});


	}
	$scope.saveModuleInfo = function(){
		$scope.moduleInfo.propertyMap = getPropertyMap();
		console.log($scope.moduleInfo);
		$http({
			method: 'POST',
			url: appConfig.baseUrl + '/module/create',
			data: $scope.moduleInfo
		}).success(function (data) {
			console.log(data);
		});
	}

	function getPropertyMap(){
		var property = {};
		$scope.fieldData.forEach(function(elem){
			property[elem.name] = elem.type;
		})
		return property;
	}



});
