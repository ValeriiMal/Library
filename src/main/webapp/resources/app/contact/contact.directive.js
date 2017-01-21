(() => {
    angular
        .module('app')
        .directive('lmsContact', lmsContact)
    ;

    function lmsContact() {
        return {
            restrict: 'E',
            templateUrl: 'resources/app/contact/contact.tpl.html',
            scope: {},
            controller: () => {},
            controllerAs: 'ctrl',
        };
    }
})();
