(() => {
    angular
        .module('app')
        .directive('lmsBook', lmsBook)
    ;

    function lmsBook() {
        return {
            restrict: 'E',
            scope: {},
            templateUrl: 'resources/app/book/book.tpl.html',
            controller: () => {},
            controllerAs: 'ctrl',
        };
    }
})();
