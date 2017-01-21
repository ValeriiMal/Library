(() => {
    angular
        .module('app')
        .directive('lmsNavbar', lmsNavbar)
    ;

    function lmsNavbar() {
        return {
            restrict: 'E',
            scope: {},
            templateUrl: 'resources/app/navbar/navbar.tpl.html',
            controller: () => {},
            controllerAs: 'ctrl',
        };
    }
})();
