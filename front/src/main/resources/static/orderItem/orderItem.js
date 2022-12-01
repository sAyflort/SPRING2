angular.module('market').controller('orderItemController', function ($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:5555/core/';
    $scope.orderId = window.location.href.split('?id=',2)[1];

    $scope.fillOrderItemTable = function () {
        $http.get(contextPath + 'api/v1/orders/' + $scope.orderId)
            .then(function (response) {
                $scope.order = response.data;
            });
    }

    $scope.fillOrderItemTable();
});