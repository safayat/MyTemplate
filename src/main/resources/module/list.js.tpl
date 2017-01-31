var myApp = angular.module('RoutingApp');
myApp.controller('#module_name#ListController', function($scope, $http,appConfig,ajaxService,authService) {
    var vm = $scope;

    authService.init();
    ajaxService.get(appConfig.baseUrl + '/private/#module_name#/list',{},function(data){
        vm.#module_name#List = data;
    });



});
