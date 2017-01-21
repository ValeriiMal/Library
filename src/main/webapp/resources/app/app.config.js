(function() {
    angular
        .module('app')
        .config(appConfig)
    ;

    function appConfig($urlRouterProvider, $stateProvider) {
        $stateProvider
            .state('LMS', {
                abstract: true,
                url: '',
                template: '<div ui-view></div>',
            })
            .state('LMS.REPORT', {
                url: '/reports',
                template: '<lms-report></lms-report>',
            })
            .state('LMS.QUEUE', {
                url: '/queues',
                template: '<lms-queue></lms-queue>',
            })
            .state('LMS.BOOK', {
                url: '/books',
                template: '<lms-book></lms-book>',
            })
            .state('LMS.READER', {
                url: '/readers',
                template: '<lms-reader></lms-reader>',
            })
            .state('LMS.CONTACT', {
                url: '/contact',
                template: '<lms-contact></lms-contact>',
            })
        ;
        $urlRouterProvider.otherwise('/reports');
    };
})();
