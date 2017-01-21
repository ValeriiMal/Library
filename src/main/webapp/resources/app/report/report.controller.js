(function() {
    angular
        .module('app')
        .controller('reportController', reportController)
    ;

    reportController.$inject = ['reportService'];
    function reportController() {
        this.title = 'Report from controller';
    }
})();
