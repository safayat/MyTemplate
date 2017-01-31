var myApp = angular.module('RoutingApp');
myApp.controller('#module_name#Controller', function($scope, $http,appConfig,ajaxService,$routeParams,authService) {
    var vm = $scope;
    vm.#module_name# = {};
    vm.update#ca_module_name# = update#ca_module_name#;
    authService.init();

    function update#ca_module_name#(){
        vm.successMessage = undefined;
        vm.errorMsg = undefined;
        ajaxService.post(appConfig.baseUrl + '/private/api/#module_name#'
            , vm.#module_name#
            , function(data){
                console.log(data);
                vm.#module_name# = data;
                vm.successMessage = "Updated";

            }, function(data,status){
                vm.errorMsg = "Failed to update";
            });
    }


});
