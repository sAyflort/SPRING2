angular.module('market').controller('cartController', function ($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:5555/cart/';
    const coreContextPath = 'http://localhost:5555/core/';

    $scope.fillCartTable = function () {
        $http.get(contextPath + 'api/v1/cart/' + $localStorage.marchMarketGuestCartId)
            .then(function (response) {
                $scope.cart = response.data;
            });
    }

    $scope.deleteProductOfCart = function (id) {
        $http.delete(contextPath + 'api/v1/cart/' + $localStorage.marchMarketGuestCartId + '/delete/' + id)
            .then(function (response) {
                $scope.fillCartTable();
            });
    }

    $scope.changeQuantityOfProduct = function (id, change) {
        $http.get(contextPath + 'api/v1/cart/' + $localStorage.marchMarketGuestCartId + '/change/' + id + '/' + change)
            .then(function (response){
                $scope.fillCartTable();
            });
    }

    $scope.dropCartsProducts = function () {
        $http.delete(contextPath + 'api/v1/cart/' + $localStorage.marchMarketGuestCartId + '/clear')
            .then(function (response) {
                $scope.fillCartTable();
            });
    }

    // Работа с заказом
    $scope.createOrder = function () {
        $http.post(coreContextPath + 'api/v1/orders', $scope.orderDetails)
            .then(function (response){
                alert("Заказ сделан")
                $scope.fillCartTable();
            });
    }

    $scope.fillCartTable();
});