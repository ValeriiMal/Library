(function() {
    angular
        .module('app')
        .directive('lmsReport', lmsReportDirective)
    ;

    function lmsReportDirective() {
        return {
            restrict: 'E',
            scope: {},
            templateUrl: 'resources/app/report/report.tpl.html',
            controller: 'reportController',
            controllerAs: 'ctrl'
        };
    }
})();
