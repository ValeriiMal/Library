function showReport() {
    $('#records-table tbody').load("report/load");
}

//-------------------------------------------------- REPORT -----------------------------------------------

function recordsJsonToRows(data) {
    var content = "";

    if (data.length === 0) {
        return "<tr><td>nothing to show</td></tr>";
    }

    for (var i = 0; i < data.length; i++) {
        var date = new Date(data[i]['date']),
            dYear = date.getFullYear(),
            dM = date.getMonth() + 1,
            dMonth = dM < 10 ? '0' + dM : dM,
            dDay = date.getDate();
        var rDate = new Date(data[i]['returnDate']),
            rYear = rDate.getFullYear(),
            rM = rDate.getMonth(),
            rMonth = rM < 10 ? '0' + rM : rM,
            rDay = rDate.getDate();
        data[i]['date'] = dYear + '-' + dMonth + '-' + dDay;
        data[i]['returnDate'] = rYear + '-' + rMonth + '-' + rDay;
        content +=
            "<tr>" +
            "<td>" + data[i]['id'] + "</td>" +
            "<td>" + data[i]['date'] + "</td>" +
            "<td>" + data[i]['book']['id'] + "</td>" +
            "<td>" + data[i]['reader']['id'] + "</td>" +
            "<td>" + data[i]['checked'] + "</td>" +
            "<td>" + data[i]['returnDate'] + "</td>" +
            "</tr>";
    }

    return content;
}

function findRecords() {
    $('#records-table tbody').html('');

    if ($('#reportFindId').val() === "0"
        | $('#reportFindBookId').val() === "0"
        | $('#reportFindReaderId').val() === "0") {
        alert("id can't be zero");
    } else {
        $.ajax({
            url: 'report/find',
            type: 'POST',
            contentType: 'application/json',
            dataType: 'json',
            data: JSON.stringify({
                id: $('#reportFindId').val(),
                book_id: $('#reportFindBookId').val(),
                reader_id: $('#reportFindReaderId').val(),
                date_from: $('#reportFindDateFrom').val(),
                date_to: $('#reportFindDateTo').val(),
                return_from: $('#reportFindReturnDateFrom').val(),
                return_to: $('#reportFindReturnDateTo').val(),
                returned: $('#reportFindReturned').val()
            }),
            success: function (data) {
                // дата до нормального вигляду

                // заповнюємо таблицю
                $('#records-table tbody').html(recordsJsonToRows(data));
            },
            error: function (jqXHR, statusText, errorThrown) {
                alert(statusText + '\n' + errorThrown);
                $('#records-table tbody').text('error');
            }
        });
    }

}

$('#report-find-input input[type=text]').keyup(findRecords);
$('#report-find-input input[type=date]').change(findRecords);
//$('#report-find-input input[type=checkbox]').change(findRecords);
$('#reportFindReturned').change(findRecords);

$('#add_record').click(function () {
    $('#addRecordResult').text('');
    if ($('#inputReturnDate').val() === "") {
        alert('You must enter "Return date" value');
    } else {

        $.ajax({
            url: 'report/add',
            type: 'GET',
            contentType: 'text',
            dataType: 'text',
            data: {
                bookId: $('#inputBookId').val(),
                readerId: $('#inputReaderId').val(),
                returnDate: $('#inputReturnDate').val()
            },
            success: function (data) {
                $('#addRecordResult').text(data);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(textStatus + '\n' + errorThrown);
            }
        })
    }
});

$('#editRecordSearchId').keyup(function () {
    var id = $('#editRecordSearchId').val();
    if (!!id) {
        $.ajax({
            url: 'report/findById',
            type: 'GET',
            contentType: 'text/html',
            dataType: 'json',
            data: {id: id},
            success: function (data, statusText, jqXHR) {
                var date = new Date(data['returnDate']),
                    year = date.getFullYear(),
                    m = date.getMonth() + 1,
                    month = m < 10 ? '0' + m : m,
                    day = date.getDate();

                $('#editRecordBookId').val(data['book']['id']);
                $('#editRecordReaderId').val(data['reader']['id']);
                $('#editRecordchecked').prop('checked', data['checked']);
                $('#editRecordReturnDate').val(year + '-' + month + '-' + day);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(textStatus + "\n" + errorThrown);
            }
        })
    } else {
        $('#recordsEditModal input').each(function () {
            $(this).val("");
        })
    }
});

$('#edit_record').click(function () {
    $.ajax({
        url: 'report/edit',
        type: 'GET',
        contentType: 'text/html',
        dataType: 'text',
        data: {
            id: $('#editRecordSearchId').val(),
            bookId: $('#editRecordBookId').val(),
            readerId: $('#editRecordReaderId').val(),
            checked: $('#editRecordchecked').prop('checked'),
            returnDate: $('#editRecordReturnDate').val()
        },
        success: function (data, textStatus, jqXHR) {
            $('#editRecordResult').text(data);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(textStatus + "\n" + errorThrown);
        }
    })
});

$('#detailsRecordSearchId').keyup(function () {
    var id = $('#detailsRecordSearchId').val();
    if (!!id) {
        $.ajax({
            url: 'report/findById',
            type: 'GET',
            contentType: 'text/html',
            dataType: 'json',
            data: {id: id},
            success: function (data, statusText, jqXHR) {
                var date = new Date(data['date']),
                    year = date.getFullYear(),
                    m = date.getMonth() + 1,
                    month = m < 10 ? '0' + m : m,
                    day = date.getDate();
                var rDate = new Date(data['returnDate']),
                    ryear = rDate.getFullYear(),
                    rm = rDate.getMonth() + 1,
                    rmonth = m < 10 ? '0' + m : m,
                    rday = rDate.getDate();

                $('#detailsRecordBookId').text(data['book']['id']);
                $('#detailsRecordBookTitle').val(data['book']['title']);
                $('#detailsRecordReaderId').text(data['reader']['id']);
                $('#detailsRecordReaderName').val(data['reader']['fName'] + ' ' + data['reader']['mName'] + ' ' + data['reader']['lName']);
                $('#detailsRecordchecked').prop('checked', data['checked']);
                $('#detailsRecordDate').text(year + '-' + month + '-' + day);
                $('#detailsRecordReturnDate').text(ryear + '-' + rmonth + '-' + rday);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(textStatus + "\n" + errorThrown);
            }
        })
    } else {
        $('#recordsEditModal input').each(function () {
            $(this).val("");
        })
    }
});

//--------------------------------------------------- QUEUE -----------------------------------------------

$('#add-queue').click(function () {
    $('#addQueueResult').text("");
    var book_id = $('#queueAddBookId').val(),
        reader_id = $('#queueAddReaderId').val();
    if (book_id != "" && reader_id != "") {
        $.ajax({
            url: 'queue/add',
            type: 'GET',
            contentType: 'text/html',
            dataType: 'text',
            data: {
                book_id: book_id,
                reader_id: reader_id
            },
            success: function (data) {
                $('#addQueueResult').text(data);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(textStatus + "\n" + errorThrown);
            }
        })
    } else {
        alert('you must enter both "book_id" and "reader_id" values');
    }
});

$('#queueEditSearchId').keyup(function () {
    var id = $('#queueEditSearchId').val();
    if(id != ""){
        $.ajax({
            url: 'queue/findById',
            type: 'GET',
            contentType: 'text/html',
            dataType: 'json',
            data: {id: id},
            success: function (data) {
                $('#queueEditBookId').val(data['book']['id']);
                $('#queueEditBookTitle').val(data['book']['title']);
                $('#queueEditReaderId').val(data['reader']['id']);
                $('#queueEditReaderName').val(data['reader']['fName'] + ' ' + data['reader']['mName'] + ' ' + data['reader']['lName']);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(textStatus + "\n" + errorThrown);
            }
        })
    } else {
        $('#queueEditModal input').val("");
    }
});

$('#queueEditBookId').keyup(function () {
    var id = $('#queueEditBookId').val();
    if(id != "") {
        $.ajax({
            url: 'book/findBookByIdJSON',
            type: 'GET',
            contentType: 'text/html',
            dataType: 'json',
            data: {id: id},
            success: function (data) {
                $('#queueEditBookTitle').val(data['title']);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(textStatus + "\n" + errorThrown);
            }
        })
    } else {
        $('#queueEditBookTitle').val("");
    }
});

$('#queueEditReaderId').keyup(function () {
    var id = $('#queueEditReaderId').val();
    if(id != "") {
        $.ajax({
            url: 'reader/findReaderJSON',
            type: 'GET',
            contentType: 'text/html',
            dataType: 'json',
            data: {id: id},
            success: function (data) {
                $('#queueEditReaderName').val(data['fName'] + ' ' + data['mName'] + ' ' + data['lName']);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(textStatus + "\n" + errorThrown);
            }
        })
    } else {
        $('#queueEditReaderName').val("");
    }
});

$('#edit-queue').click(function () {
    $('#editQueueResult').text("");
    var id = $('#queueEditSearchId').val(),
        book_id = $('#queueAddBookId').val(),
        reader_id = $('#queueAddReaderId').val();
    if(id != "") {
        $.ajax({
            url: 'queue/edit',
            type: 'GET',
            contentType: 'text/html',
            dataType: 'text',
            data: {
                id: id,
                book_id: book_id,
                reader_id: reader_id
            },
            success: function (data) {
                $('#editQueueResult').text(data);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(textStatus + "\n" + errorThrown);
            }
        })
    } else {
        alert('you must enter "Id" value in order to edit it');
    }
});

$('#queueRemoveSearchId').keyup(function () {
    $('#removeQueueResult').val("");
    var id = $('#queueRemoveSearchId').val();
    if(id != ""){
        $.ajax({
            url: 'queue/findById',
            type: 'GET',
            contentType: 'text/html',
            dataType: 'json',
            data: {
                id: id
            },
            success: function (data) {
                $('#queueRemoveBookId').val(data['book']['id']);
                $('#queueRemoveBookTitle').val(data['book']['title']);
                $('#queueRemoveReaderId').val(data['reader']['id']);
                $('#queueRemoveReaderName').val(data['reader']['fName'] + '-' + data['reader']['mName'] + '-' + data['reader']['lName']);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(textStatus + "\n" + errorThrown);
            }
        })
    } else {
        $('#queueRemoveModal input').val("");
    }
});

$('#remove-queue').click(function () {
    $('#removeQueueResult').val("");
    var id = $('#queueRemoveSearchId').val();
    if(id != ""){
        $.ajax({
            url: 'queue/remove',
            type: 'GET',
            contentType: 'text/html',
            dataType: 'text',
            data: {
                id: id
            },
            success: function (data) {
                $('#removeQueueResult').val(data);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(textStatus + "\n" + errorThrown);
            }
        })
    } else {
        alert('you must enter "Id*" value to remove');
    }

});

//--------------------------------------------------- BOOKS -----------------------------------------------

$('#bookFindScarce').prop('checked', false);
var booksFindInputs = $('#books-find-input input');

function findBooks() {
    $.ajax({
        url: 'book/find',
        type: 'POST',
        contentType: 'application/json',
        dataType: 'text',
        data: JSON.stringify({
            id: booksFindInputs.get(0).value,
            title: booksFindInputs.get(1).value,
            authors: booksFindInputs.get(2).value,
            year: booksFindInputs.get(3).value,
            genre: booksFindInputs.get(4).value,
            isScarce: $('#bookFindScarce').prop('checked')
        }),
        success: function (data, textStatus, jqXHR) {
            $('#books-table tbody').html(data);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(textStatus + "\n" + errorThrown)
        }
    })
}

function addBook() {
    $.ajax({
        url: 'book/add',
        type: 'POST',
        contentType: 'application/json',
        dataType: 'text',
        data: JSON.stringify({
            title: $('#inputBookTitle').val(),
            authors: $('#inputBookAuthors').val(),
            year: $('#inputBookYear').val(),
            genre: $('#inputBookGenre').val(),
            amount: $('#inputBookAmount').val(),
            isScarce: $('#inputBookScarce').prop('checked')
        }),
        success: function (data) {
            $('#addBookResult').text(data);
        },
        error: function (jqXHR, statusText, errorThrown) {
            alert(statusText + "\n" + errorThrown)
        }
    });
}

booksFindInputs.keyup(findBooks);
$('#bookFindScarce').change(findBooks);

$('#add-book').click(function () {
    $('#addBookResult').text('');
    if ($('#inputBookAmount').val() == '' && $('#inputBookAmount').val() == '') {
        alert('You must enter "Amount" value \n Amount > 0');
    } else {
        addBook();
    }

});

$('#editBookSearchId').keyup(function () {
    var id = $('#editBookSearchId').val();

    if (id != '' && id != '0') {
        $.ajax({
            url: 'book/getBookById',
            type: 'GET',
            contentType: 'text',
            dataType: 'json',
            data: {id: id},
            success: function (data, textStatus, jqXHR) {
                $('#editBookTitle').val(data.title);
                $('#editBookYear').val(data.year);
                $('#editBookAuthors').val(data.authors);
                $('#editBookGenre').val(data.genre);
                $('#editBookAmount').val(data.amount);
                $('#editBookScarce').prop('checked', data.scarce);
            },
            error: function (jqXHR, statusText, errorThrown) {
                alert(statusText + "\n" + errorThrown)
            }
        })
    } else {
        $('#booksEditModal input').each(function () {
            $(this).val("");
        })
    }
});

$('#edit_book').click(function () {
    $('#editBookResult').text('');
    $.ajax({
        url: 'book/edit',
        type: 'POST',
        contentType: 'application/json',
        dataType: 'text',
        data: JSON.stringify({
            id: $('#editBookSearchId').val(),
            title: $('#editBookTitle').val(),
            authors: $('#editBookAuthors').val(),
            year: $('#editBookYear').val(),
            genre: $('#editBookGenre').val(),
            amount: $('#editBookAmount').val(),
            isScarce: $('#editBookScarce').prop('checked')
        }),
        success: function (data) {
            $('#editBookResult').text(data);
        },
        error: function (jqXHR, statusText, errorThrown) {
            alert(statusText + "\n" + errorThrown)
        }
    });
});

$('#removeBookSearchId').keyup(function () {
    var id = $('#removeBookSearchId').val();
    if (!!id) {
        $.ajax({
            url: 'book/getBookById',
            type: 'GET',
            contentType: 'application/json',
            dataType: 'text',
            data: {id: id},
            success: function (data, textStatus, jqXHR) {
                var book = $.parseJSON(data);
                $('#removeBookTitle').text(book.title);
                $('#removeBookYear').text(book.year);
                $('#removeBookAuthors').text(book.authors);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(textStatus + '\n' + errorThrown);
            }
        });
    } else {
        $('#booksRemoveModal input').each(function () {
            $(this).val("");
        })
    }
});

$('#remove-book').click(function () {
    $('#removeBookResult').load("book/remove?id=" + $('#removeBookSearchId').val())
});

$('#detailsBookSearchId').keyup(function () {
    var id = $('#detailsBookSearchId').val();
    if (!!id) {
        $.ajax({
            url: 'book/getBookById',
            type: 'GET',
            contentType: 'application/json',
            dataType: 'json',
            data: {id: id},
            success: function (data, textStatus, jqXHR) {
                $('#detailsBookTitle').text(data.title);
                $('#detailsBookYear').text(data.year);
                $('#detailsBookAuthors').text(data.authors);
                $('#detailsBookGenre').text(data.genre);
                $('#detailsBookAmount').text(data.amount);
                $('#detailsBookScarce').prop('checked', data.scarce);
                $.ajax({
                    url: 'book/readersCount',
                    contentType: 'text/html',
                    dataType: 'text',
                    data: {id: $('#detailsBookSearchId').val()},
                    success: function (data2) {
                        $('#detailsBookRemains').text(data2);
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        alert(textStatus + "\n" + errorThrown);
                    }
                })
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(textStatus + '\n' + errorThrown);
            }
        });
    } else {
        $('#booksDetailsModal input').val("")
    }
});

$('#show_book_readers').click(function () {
    $.ajax({
        url: 'book/readers',
        contentType: 'text/html',
        dataType: 'json',
        data: {id: $('#detailsBookSearchId').val()},
        success: function (data) {

            var content = "";
            for (var i = 0; i < data.length; i++) {
                content += "<tr>" +
                    "<td>" + data[i]['id'] + "</td>" +
                    "<td>" + data[i]['fName'] + "</td>" +
                    "<td>" + data[i]['mName'] + "</td>" +
                    "<td>" + data[i]['lName'] + "</td>" +
                    "</tr>";
            }

            $('#bookReaders tbody').html(content);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(textStatus + "\n" + errorThrown);
        }
    })
});

function jsonBooksToRows(data) {
    var content = "";
    if (data.length != 0) {
        for (var i = 0; i < data.length; i++) {
            content += "<tr>" +
                "<td>" + data[i]['id'] + "</td>" +
                "<td>" + data[i]['title'] + "</td>" +
                "<td>" + data[i]['authors'] + "</td>" +
                "<td>" + data[i]['Year'] + "</td>" +
                "<td>" + data[i]['genre'] + "</td>" +
                "</tr>";
        }
    }
    $('#booksStatStatus').text('no books');
    return content;
}

// how = {all/given/taken}
function booksByDate(how) {
    if ($('#booksDate1').val() === "") {
        alert("You must enter 'Date from' value")
    } else {

        $.ajax({
            url: 'report/allBooksByDate',
            type: 'GET',
            contentType: 'text/html',
            dataType: 'json',
            data: {
                checked: how,
                dateFrom: $('#booksDate1').val(),
                dateTo: $('#booksDate2').val(),
                scarce: $('#booksScarce').prop('checked')
            },
            success: function (data) {

                $('#booksStatTable tbody').html(jsonBooksToRows(data));
                $('#booksStatStatus').text(' ' + data.length + ' books');
            },
            error: function (jqXHR, textStatus, errorThrown) {
                $('#booksStatTable tbody').html('');
                $('#booksStatStatus').text('error');
                alert(textStatus + "\n" + errorThrown);
            }
        })
    }
}

$('#showBooksStat').click(function () {
    if ($('#booksOnlyGiven').prop('checked')) {
        if ($('#booksOnlyTaken').prop('checked')) {
            //    дія якщо обидва вибрані
            booksByDate('all');
        } else {
            //    дія якщо тыльки Given
            booksByDate('given');
        }
    } else if ($('#booksOnlyTaken').prop('checked')) {
        //    дія якщо тільки Taken
        booksByDate('taken');
    } else {
        $('#booksStatTable tbody').html('');
    }
});


//--------------------------------------------------- READERS -------------------------------------------------

var readersFindInputs = $('#readers-find-input input');

function readersJsonToRow(readers) {
    var content = "";
    var length = readers.length;
    if (length != 0) {
        for (var i = 0; i < length; i++) {
            content +=
                '<tr>' +
                '<td>' + readers[i]['id'] + '</td>' +
                '<td>' + readers[i]['fName'] + '</td>' +
                '<td>' + readers[i]['mName'] + '</td>' +
                '<td>' + readers[i]['lName'] + '</td>' +
                '<td>' + readers[i]['phone'] + '</td>' +
                '</tr>';
        }
    } else {
        content += '<tr><td>no data to show</td></tr>';
    }
    return content;
}

function findReaders() {
    $('#reader-table tbody').html('');
    $.ajax({
        url: 'reader/findByExample',
        type: 'POST',
        contentType: 'application/json',
        dataType: 'json',
        data: JSON.stringify({
            id: readersFindInputs.get(0).value,
            fName: readersFindInputs.get(1).value,
            mName: readersFindInputs.get(2).value,
            lName: readersFindInputs.get(3).value,
            phone: readersFindInputs.get(4).value
        }),
        success: function (data) {
            $('#reader-table tbody').html(readersJsonToRow(data));
        },
        error: function (jqXHR, textStatus, errorThrown) {
            $('#reader-table tbody').text('error');
            alert(textStatus + "\n" + errorThrown)
        }
    });

}

readersFindInputs.keyup(findReaders);

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
                //$('#detailsReaderFName').val(data['fName']);
                //$('#detailsReaderMName').val(data['mName']);
                //$('#detailsReaderLName').val(data['lName']);
                //$('#detailsReaderPhone').val(data['phone']);
                //$('#detailsReaderCountry').val(data['address']['country']);
                //$('#detailsReaderCity').val(data['address']['city']);
                //$('#detailsReaderStreet').val(data['address']['street']);
                //$('#detailsReaderHouse').val(data['address']['house']);
                //$('#detailsReaderBirth').val(year + '-' + month + '-' + day);
                //$('#detailsReaderRegistrationDate').val(rYear + '-' + rMonth + '-' + rDay);
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

// ------------------------------------------------ ON DOCUMENT READY -----------------------------------

function scroll2(menu_item, section) {
    $(menu_item).click(function () {
        $('html, body').animate({
            scrollTop: $(section).offset().top - 50
        }, 1000)
    })
}

$(document).ready(function () {
    findReaders();
    findBooks();
    findRecords();

    scroll2('#menu_item_report', '#report_section');
    scroll2('#menu_item_queue', '#queue-section');
    scroll2('#menu_item_books', '#books-section');
    scroll2('#menu_item_readers', '#readers-section');
    scroll2('#menu_item_contacts', '#contacts-section');
});