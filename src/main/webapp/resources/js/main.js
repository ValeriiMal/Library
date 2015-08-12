var book = function() {
    return {
        add : {
            title : $('#inputBookTitle').val(),
            authors: $('#inputBookAuthors').val(),
            year: $('#inputBookYear').val(),
            genre: $('#inputBookGenre').val(),
            amount: $('#inputBookAmount').val()
        }
    }
};


// variables
var readersFindInputs = document.getElementById('readers-find-input').getElementsByTagName('input');


// functions

function showReaders() {
    $('#reader-table tbody').load("reader/load");
}

function showBooks() {
    $('#books-table tbody').load("book/load");
}

function showReport() {
    $('#records-table tbody').load("report/load");
}

function showReadersFind(params) {
    $('#reader-table tbody').load('reader/find?' + params);
}

// functions

function getBookById(url, id) {
    $.ajax({
        url: url,
        type: 'GET',
        contentType: 'text',
        dataType: 'json',
        data: {id: id},
        success: function (data, textStatus, jqXHR) {
            alert(data);
        },
        error: function (jqXHR, statusText, errorThrown) {
            alert(statusText + "\n" + errorThrown)
        }
    })
}

// REPORT


// BOOKS

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
            genre: booksFindInputs.get(4).value
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
            amount: $('#inputBookAmount').val()
        }),
        success: function (data) {
            $('#addBookResult').text(data);
        }
    });
}

booksFindInputs.keyup(findBooks);

$('#add-book').click(addBook);

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
                //alert(textStatus);
                var book = data;
                $('#editBookTitle').val(book.title);
                $('#editBookYear').val(book.year);
                $('#editBookAuthors').val(book.authors);
                $('#editBookGenre').val(book.genre);
                $('#editBookCount').val(book.amount);
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
            amount: $('#editBookCount').val()
        }),
        success: function (text) {
            $('#editBookResult').text(text);
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
                $('#removeBookTitle').val(book.title);
                $('#removeBookYear').val(book.year);
                $('#removeBookAuthors').val(book.authors);
            },
            error: function(jqXHR, textStatus, errorThrown){
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
                $('#detailsBookTitle').val(data.title);
                $('#detailsBookYear').val(data.year);
                $('#detailsBookAuthors').val(data.authors);
                $('#detailsBookGenre').val(data.genre);
                $('#detailsBookAmount').val(data.amount);
            },
            error: function(jqXHR, textStatus, errorThrown){
                alert(textStatus + '\n' + errorThrown);
            }
        });
    } else {
        $('#booksDetailsModal input').val("")
    }
});

$('#show_book_readers').click(function () {
    $.ajax({
        url : 'book/readers',
        contentType : 'text/html',
        dataType: 'json',
        data: {id : $('#detailsBookSearchId').val()},
        success: function (data) {

            var content = "";
            for(var i = 0; i < data.length; i++){
                content += "<tr>" +
                    "<td>" + data[i]['id'] + "</td>" +
                    "<td>" + data[i]['fName'] + "</td>" +
                    "<td>" + data[i]['mName'] + "</td>" +
                    "<td>" + data[i]['lName'] + "</td>" +
                    "</tr>";
            }

            $('#bookReaders tbody').html(content);
        },
        error: function(jqXHR, textStatus, errorThrown){
            alert(textStatus + "\n" + errorThrown);
        }
    })
});

// READERS

// обробник onkeyup для кожного reader input
function readersFind() {
    var params = "";
    if (isInputsEmpty(readersFindInputs)) {
        showReaders();
    } else {
        // визначення існуючих параметрів в inputs та
        // складання відповідного рядку запиту params (тільки існуючі параметри)
        params = "";
        readersFindInputs[0].value == "" ? params += "id=" : params += "id=" + readersFindInputs[0].value;
        readersFindInputs[1].value == "" ? params += "&fName=" : params += "&fName=" + readersFindInputs[1].value;
        readersFindInputs[2].value == "" ? params += "&mName=" : params += "&mName=" + readersFindInputs[2].value;
        readersFindInputs[3].value == "" ? params += "&lName=" : params += "&lName=" + readersFindInputs[3].value;
        readersFindInputs[4].value == "" ? params += "&phone=" : params += "&phone=" + readersFindInputs[4].value;

        showReadersFind(params);
    }

}
for (var i = 0; i < readersFindInputs.length; i++) {
    readersFindInputs[i].onkeyup = readersFind;
}


//add reader button

$('#add-reader').click(function () {
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
            alert('add reader: ' + '\n' +
                textStatus + '\n' +
                errorThrown);
        }
    });
});

//add report button
$('#add_record').click(function () {
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
});

//edit reader button
$('#editReaderSearchId').keyup(function () {
    var searchIdInput = $('#editReaderSearchId').val();
    if (!!searchIdInput) {
        var params = 'id=' + searchIdInput;
        var editObj;
        $.get('reader/findReaderJSON?' + params, function (data) {
            editObj = $.parseJSON(data);
            $('#editReaderFName').val(editObj.fName);
            $('#editReaderMName').val(editObj.mName);
            $('#editReaderLName').val(editObj.lName);
            $('#editReaderPhone').val(editObj.phone);
            $('#editReaderCountry').val(editObj.counrty);
            $('#editReaderCity').val(editObj.city);
            $('#editReaderStreet').val(editObj.street);
            $('#editReaderHouse').val(editObj.house);
            $('#editReaderBirth').val(editObj.dateOfBirth);
        });
    } else {
        $('#readersEditModal input').each(function () {
            $(this).val("");
        })
    }
});
// обробник кнопки модального вікна для зміни даних читача
$('#edit-reader').click(function () {
    var params = 'id=' + $('#editReaderSearchId').val() +
        '&fName=' + $('#editReaderFName').val() +
        '&mName=' + $('#editReaderMName').val() +
        '&lName=' + $('#editReaderLName').val() +
        '&phone=' + $('#editReaderPhone').val() +
        '&country=' + $('#editReaderCountry').val() +
        '&city=' + $('#editReaderCity').val() +
        '&street=' + $('#editReaderStreet').val() +
        '&house=' + $('#editReaderHouse').val() +
        '&birth=' + $('#editReaderBirth').val();
    $.get('reader/edit?' + params, function (data) {
        $('#editReaderResult').text(data);
    })
});


// remove reader button

$('#removeReaderSearchId').keyup(function () {
    var removeId = $('#removeReaderSearchId').val();
    if (!!removeId) {
        var params = 'id=' + removeId;
        var editObj;
        $.get('reader/findReaderJSON?' + params, function (data) {
            editObj = $.parseJSON(data);
            $('#removeReaderFName').val(editObj.fName);
            $('#removeReaderMName').val(editObj.mName);
            $('#removeReaderLName').val(editObj.lName);
        });
    } else {
        $('#readersRemoveModal input').each(function () {
            $(this).val("");
        })
    }
});

$('#remove-reader').click(function () {
    var params = 'id=' + $('#removeReaderSearchId').val();
    $.get('reader/remove?' + params, function (data) {
        $('#removeReaderResult').text(data);
    })
});

//remove book
// пошук за ID при keyup


// readers details
$('#detailsReaderSearchId').keyup(function () {
    var idInput = $('#detailsReaderSearchId').val();
    if (!!idInput) {
        var params = "id=" + idInput;
        $.get('reader/findReaderJSON?' + params, function (data) {
            var obj = $.parseJSON(data);
            $('#detailsReaderFName').val(obj.fName);
            $('#detailsReaderMName').val(obj.mName);
            $('#detailsReaderLName').val(obj.lName);
            $('#detailsReaderPhone').val(obj.phone);
            $('#detailsReaderCountry').val(obj.counrty);
            $('#detailsReaderCity').val(obj.city);
            $('#detailsReaderStreet').val(obj.street);
            $('#detailsReaderHouse').val(obj.house);
            $('#detailsReaderBirth').val(obj.dateOfBirth);
            $('#detailsReaderRegistrationDate').val(obj.registrationDate);
        })
    } else {
        $('#readersDetailsModal input').each(function () {
            $(this).val("");
        })
    }
});

// books details


// показ дефолтної таблички readers
showReaders();
// показ дефолтної таблички books
//showBooks();
findBooks();
// показ дефолтної таблички report
showReport();

// READERS-SECTION-END

//прокрутки
function scroll2(whoID, whereID) {
    $(whoID).click(function () {
        $('html body').animate({
            scrollTop: $(whereID).offset().top - 50
        }, 1000)
    });
}

scroll2('#menu_item_report', '#report_section');
scroll2('#menu_item_readers', '#readers-section');
scroll2('#menu_item_books', '#books-section');
scroll2('#menu_item_contacts', '#contacts-section');