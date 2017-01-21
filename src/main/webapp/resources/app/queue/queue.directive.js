(() => {
    angular
        .module('app')
        .directive('lmsQueue', lmsQueue)
    ;

    function lmsQueue() {
        return {
            restrict: 'E',
            scope: {},
            templateUrl: 'resources/app/queue/queue.tpl.html',
            controller: () => {},
            controllerAs: 'ctrl',
        };
    }
})();
