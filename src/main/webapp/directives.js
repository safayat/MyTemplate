var myApp = angular.module('RoutingApp');
myApp.directive('pagination', pagination);
myApp.directive('easySelect', easySelect);

function pagination(){
	return {
		restrict: 'EA', //E = element, A = attribute, C = class, M = comment
		scope: {
			offset : "=",
			limit : "@",
			loaddata : "&",
		},
		controller: function ($scope) {
			$scope.updateOffset = function(pageCount){
				$scope.offset = (pageCount-1)*$scope.limit;
				$scope.loaddata();
				console.log($scope.loaddata);
				console.log($scope.offset);
			};
		}, //DOM manipulation
		templateUrl: "pages/template/pagination.html",
		//@ reads the attribute value, = provides two-way binding, & works with functions
	}
};

function easySelect(){
	return {
		restrict: 'EA', //E = element, A = attribute, C = class, M = comment
		scope: {
			selectData : "=",
			dataUrl : "@",
			internalPropName : "@",
			externalPropName : "@",
			selectModel : "="
			//loaddata : "&",
		},
		controller: function ($scope,$http) {
			$http.get($scope.url).success(function(data){
				$scope.selectData = data;
				console.log($scope.selectData);
			});

		}, //DOM manipulation
		templateUrl: "<select ng-model='selectModel'><option ng-repeat='item in selectData' value='{{item[internalPropName]}}'>{{item[externalPropName]}}</option></select>",
		//@ reads the attribute value, = provides two-way binding, & works with functions
	}
};



