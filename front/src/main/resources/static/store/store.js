angular.module('market').controller('storeController', function ($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:5555/core/';
    const cartContextPath = 'http://localhost:5555/cart/';

    $scope.fillTable = function (page =1) {
        $http({
            url: contextPath + 'api/v1/products',
            method: 'GET',
            params: {
                p: page,
                title_part: $scope.filter ? $scope.filter.title_part : null,
                min_price: $scope.filter ? $scope.filter.min_price : null,
                max_price: $scope.filter ? $scope.filter.max_price : null
            }
        }).then(function (response) {
            $scope.productsPage = response.data;
            $scope.generatePagesList($scope.productsPage.totalPages);

            $scope.previoustPage = page-1;
            if($scope.previoustPage < 1) {
                $scope.previousPage = 1;
            }
            $scope.nextPage = page+1;
            if($scope.nextPage > $scope.pagesList.length) {
                $scope.nextPage = $scope.pagesList.length;
            }
        });
    };

    $scope.generatePagesList = function (totalPages) {
        out = [];
        for (let i = 0; i < totalPages; i++) {
            out.push(i + 1);
        }
        $scope.pagesList = out;
    }

    $scope.createNewProduct = function () {
        $http.post(contextPath + 'api/v1/products', $scope.newProduct)
            .then(function (response) {
                $scope.newProduct = null;
                $scope.fillTable();
            });
    }

    $scope.deleteProduct = function (id) {
        $http.delete(contextPath + 'api/v1/products/' + id)
            .then(function (response) {
                $scope.fillTable();
            });
    }

    $scope.addToCart = function (id) {
        $http.get(cartContextPath + 'api/v1/cart/add/' + id)
            .then(function (response) {
                $scope.fillCartTable();
            });
    }

    $scope.fillTable();
});