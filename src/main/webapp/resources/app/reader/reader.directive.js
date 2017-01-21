(() => {
    angular
        .module('app')
        .directive('lmsReader', lmsReader)
    ;

    function lmsReader() {
        return {
            restrict: 'E',
            scope: {},
            templateUrl: 'resources/app/reader/reader.tpl.html',
            controller: 'readerController',
            controllerAs: 'ctrl',
        };
    }
})();
