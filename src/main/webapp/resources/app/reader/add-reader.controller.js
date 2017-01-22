(() => {
    angular
        .module('app')
        .controller('addReaderController', addReaderController)
    ;

    addReaderController.$inject = ['$scope'];
    function addReaderController(   $scope) {
        this.close = () => $scope.$close();
        this.add = () => {
            $('#add-reader').click(function () {
                $('#addReaderResult').text('');
                $.ajax({
                    url: 'reader/add',
                    type: 'POST',
                    contentType: 'application/json',
                    dataType: 'text',
                    data: JSON.stringify({
                        fName: $('#inputReaderFName').val(),
                        mName: $('#inputReaderMName').val(),
                        lName: $('#inputReaderLName').val(),
                        phone: $('#inputReaderPhone').val(),
                        address: {
                            country: $('#inputReaderCountry').val(),
                            city: $('#inputReaderCity').val(),
                            street: $('#inputReaderStreet').val(),
                            house: $('#inputReaderHouse').val()
                        },
                        dateOfBirth: $('#inputReaderBirth').val()
                    }),
                    success: function (data) {
                        $('#addReaderResult').text(data);
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        alert(textStatus + '\n' + errorThrown);
                    }
                });
            });
        };
    }
})();
