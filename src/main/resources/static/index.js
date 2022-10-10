angular.module('market', []).controller('indexController', function ($scope, $http) {
    $scope.fillTable = function () {
        $http.get('http://localhost:8189/market/api/v1/products')
            .then(function (response) {
                $scope.products = response.data[0];
                $scope.cartsProducts = response.data[1];
            });
    };

    $scope.deleteProduct = function (id) {
        $http.delete('http://localhost:8189/market/api/v1/products/' + id)
            .then(function (response) {
                $scope.fillTable();
            });
    }

    $scope.deleteProductOfCart = function (id) {
        $http.delete('http://localhost:8189/market/api/v1/products/cart/' + id)
            .then(function (response) {
                $scope.fillTable();
            });
    }

    $scope.createNewProduct = function () {
        $http.post('http://localhost:8189/market/api/v1/products', $scope.newProduct)
            .then(function (response) {
                $scope.newProduct = null;
                $scope.fillTable();
            });
    }

    $scope.addToCart = function (id) {
        $http.get('http://localhost:8189/market/api/v1/products/' + id)
            .then(function (response){
                $scope.fillTable();
            });
    }

    $scope.fillTable();
});