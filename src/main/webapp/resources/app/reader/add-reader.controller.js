(({ model }) => {
    angular
        .module('app')
        .controller('addReaderController', addReaderController)
    ;

    addReaderController.$inject = ['$scope', '$http'];
    function addReaderController(   $scope,   $http) {

        this.reader = new model.Reader();

        this.close = (result) => $scope.$close({ $value: result });

        this.add = () => this.close(this.reader);
    }
})(window.app = window.app || {});
