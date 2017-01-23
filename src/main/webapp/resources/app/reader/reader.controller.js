(({ model }) => {
    angular
        .module('app')
        .controller('readerController', readerController)
    ;

    readerController.$inject = ['$uibModal', '$http'];
    function readerController(   $uibModal,   $http) {

        this.readers = [];
        this.filter = new model.Reader();
        this.showAddReaderModal = () => openAddReaderModal($uibModal);

        function openAddReaderModal(uibModal) {
            uibModal
                .open({
                    controller: 'addReaderController',
                    controllerAs: 'ctrl',
                    templateUrl: 'resources/app/reader/add-reader.tpl.html',
                })
                .result.then(({ $value }) => addReader($http, $value));
        }

        function addReader($http, reader) {
            return $http
                .post(
                    'reader/add',
                    reader
                )
                .then(
                    (response) =>  {
                        console.log(response);
                        this.readers = [
                            ...this.readers,
                            reader
                        ];
                    },
                    response => console.log(response)
                )
            ;
        }

        function readersJsonToRow(readers) {
            // var content = "";
            // var length = readers.length;
            // if (length != 0) {
            //     for (var i = 0; i < length; i++) {
            //         content +=
            //             '<tr>' +
            //             '<td>' + readers[i]['id'] + '</td>' +
            //             '<td>' + readers[i]['fName'] + '</td>' +
            //             '<td>' + readers[i]['mName'] + '</td>' +
            //             '<td>' + readers[i]['lName'] + '</td>' +
            //             '<td>' + readers[i]['phone'] + '</td>' +
            //             '</tr>';
            //     }
            // } else {
            //     content += '<tr><td>no data to show</td></tr>';
            // }
            // return content;
        }

        this.findReaders = () => {
            // $('#reader-table tbody').html('');
            // $.ajax({
            //     url: 'reader/findByExample',
            //     type: 'POST',
            //     contentType: 'application/json',
            //     dataType: 'json',
            //     data: JSON.stringify({
            //         id: this.filter.id,
            //         fName: this.filter.fName,
            //         mName: this.filter.mName,
            //         lName: this.filter.lName,
            //         phone: this.filter.phone
            //     }),
            //     success: function (data) {
            //         $('#reader-table tbody').html(readersJsonToRow(data));
            //     },
            //     error: function (jqXHR, textStatus, errorThrown) {
            //         $('#reader-table tbody').text('error');
            //         alert(textStatus + "\n" + errorThrown)
            //     }
            // });
        };

        $('#editReaderSearchId').keyup(function () {
            var id = $('#editReaderSearchId').val();

            if (id != '' && id != '0') {
                $.ajax({
                    url: 'reader/findReaderJSON',
                    type: 'GET',
                    contentType: 'text/html',
                    dataType: 'json',
                    data: {id: id},
                    success: function (data) {
                        var date = new Date(data.dateOfBirth);
                        var year = date.getFullYear();
                        var m = date.getMonth() + 1;
                        var month = m < 10 ? '0' + m : m;
                        var day = date.getDate();

                        $('#editReaderFName').val(data.fName);
                        $('#editReaderMName').val(data.mName);
                        $('#editReaderLName').val(data.lName);
                        $('#editReaderPhone').val(data.phone);
                        $('#editReaderCountry').val(data['address']['country']);
                        $('#editReaderCity').val(data['address'].city);
                        $('#editReaderStreet').val(data['address'].street);
                        $('#editReaderHouse').val(data['address'].house);
                        $('#editReaderBirth').val(year + '-' + month + '-' + day);
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        alert(textStatus + '\n' + errorThrown);
                    }
                })
            } else {
                $('#readersEditModal input').each(function () {
                    $(this).val("");
                })
            }
        });
        $('#edit-reader').click(function () {
            $('#editReaderResult').text('');
            $.ajax({
                url: 'reader/edit',
                type: 'POST',
                contentType: 'application/json',
                dataType: 'text',
                data: JSON.stringify({
                        id: $('#editReaderSearchId').val(),
                        fName: $('#editReaderFName').val(),
                        mName: $('#editReaderMName').val(),
                        lName: $('#editReaderLName').val(),
                        phone: $('#editReaderPhone').val(),
                        address: {
                            country: $('#editReaderCountry').val(),
                            city: $('#editReaderCity').val(),
                            street: $('#editReaderStreet').val(),
                            house: $('#editReaderHouse').val()
                        },
                        dateOfBirth: $('#editReaderBirth').val()
                    }
                ),
                success: function (data) {
                    $('#editReaderResult').text(data);
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert(textStatus + '\n' + errorThrown);
                }
            });
        });
        $('#removeReaderSearchId').keyup(function () {
            var id = $('#removeReaderSearchId').val();
            if (id != '' && id != '0') {
                $.ajax({
                    url: 'reader/findReaderJSON',
                    type: 'GET',
                    contentType: 'text/html',
                    dataType: 'json',
                    data: {id: id},
                    success: function (data) {
                        $('#removeReaderFName').text(data['fName']);
                        $('#removeReaderMName').text(data['mName']);
                        $('#removeReaderLName').text(data['lName']);
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        alert(textStatus + '\n' + errorThrown);
                    }
                })
            } else {
                $('#readersRemoveModal input').each(function () {
                    $(this).val("");
                })
            }
        });
        $('#remove-reader').click(function () {
            $('#removeReaderResult').text('');
            $.get(
                'reader/remove',
                {
                    id: $('#removeReaderSearchId').val()
                },
                function (data) {
                    $('#removeReaderResult').text(data);
                },
                'text');
        });
        $('#detailsReaderSearchId').keyup(function () {
            var id = $('#detailsReaderSearchId').val();
            if (id != '' && id != '0') {
                $.get(
                    'reader/findReaderJSON',
                    {
                        id: id
                    },
                    function (data) {
                        var date = new Date(data.dateOfBirth),
                            year = date.getFullYear(),
                            m = date.getMonth() + 1,
                            day = date.getDate(),
                            month = m < 10 ? '0' + m : m;
                        var rDate = new Date(data.registrationDate),
                            rYear = rDate.getFullYear(),
                            rM = rDate.getMonth() + 1,
                            rDay = rDate.getDate(),
                            rMonth = rM < 10 ? '0' + rM : rM;
                        $('#detailsReaderFName').text(data['fName']);
                        $('#detailsReaderMName').text(data['mName']);
                        $('#detailsReaderLName').text(data['lName']);
                        $('#detailsReaderPhone').text(data['phone']);
                        $('#detailsReaderCountry').text(data['address']['country']);
                        $('#detailsReaderCity').text(data['address']['city']);
                        $('#detailsReaderStreet').text(data['address']['street']);
                        $('#detailsReaderHouse').text(data['address']['house']);
                        $('#detailsReaderBirth').text(year + '-' + month + '-' + day);
                        $('#detailsReaderRegistrationDate').text(rYear + '-' + rMonth + '-' + rDay);
                    },
                    'json'
                );
            } else {
                $('#readersDetailsModal input').each(function () {
                    $(this).val("");
                })
            }
        });
    }
})(window.app);
