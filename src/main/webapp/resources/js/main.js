
// READERS-SECTION-BEGIN

// variables
var readersFindInputs = document.getElementById('readers-find-input').getElementsByTagName('input');
var booksFindInputs = document.getElementById('books-find-input').getElementsByTagName('input');

// functions

// вивід невідфільтрованого набору записів
function showReaders(){
    $('#reader-table tbody').load("reader/load");
}

function showBooks(){
    $('#books-table tbody').load("book/load");
}

function showReport(){
    $('#records-table tbody').load("report/load");
}

// вивід відфільтрованого за id набору записів
function showReadersFind(params){
    $('#reader-table tbody').load('reader/find?' + params);
}

// перевірка відсутності значень в кожному з input заданого масиву
function isInputsEmpty(inputs){
    for(var i = 0; i < readersFindInputs.length; i++){
        if(inputs[i].value != "") return false;
    }

    return true;
}

// обробник onkeyup для кожного reader input
function readersFind() {
    var params = "";
    if(isInputsEmpty(readersFindInputs)){
        showReaders();
    }else{
        // визначення існуючих параметрів в inputs та
        // складання відповідного рядку запиту params (тільки існуючі параметри)
        params = "";
        readersFindInputs[0].value == "" ? params += "id=" : params += "id=" + readersFindInputs[0].value ;
        readersFindInputs[1].value == "" ? params += "&fName=" : params += "&fName=" + readersFindInputs[1].value ;
        readersFindInputs[2].value == "" ? params += "&mName=" : params += "&mName=" + readersFindInputs[2].value ;
        readersFindInputs[3].value == "" ? params += "&lName=" : params += "&lName=" + readersFindInputs[3].value ;
        readersFindInputs[4].value == "" ? params += "&phone=" : params += "&phone=" + readersFindInputs[4].value ;

        showReadersFind(params);
    }

}

for(var i = 0; i < readersFindInputs.length; i++){
    readersFindInputs[i].onkeyup = readersFind;
}

// books finding

for(var i = 0; i < booksFindInputs.length; i++){
    booksFindInputs[i].onkeyup = function() {
        if(isInputsEmpty(booksFindInputs)){
            showBooks();
        } else {
            var params = "";
            booksFindInputs[0].value == "" ? params += "id=" : params += "id=" + booksFindInputs[0].value;
            booksFindInputs[1].value == "" ? params += "&title=" : params += "&title=" + booksFindInputs[1].value;
            booksFindInputs[2].value == "" ? params += "&authors=" : params += "&authors=" + booksFindInputs[2].value;
            booksFindInputs[3].value == "" ? params += "&year=" : params += "&year=" + booksFindInputs[3].value;
            booksFindInputs[4].value == "" ? params += "&genre=" : params += "&genre=" + booksFindInputs[4].value;
            $('#books-table tbody').load('book/find?' + params);
        }
    }
}

//add reader button
$('body').on('click', '#add-reader', function() {
    var params = "" +
        "&fName=" + document.getElementById('inputReaderFName').value +
        "&mName=" + document.getElementById('inputReaderMName').value +
        "&lName=" + document.getElementById('inputReaderLName').value +
        "&phone=" + document.getElementById('inputReaderPhone').value +
        "&country=" + document.getElementById('inputReaderCountry').value +
        "&city=" + document.getElementById('inputReaderCity').value +
        "&street=" + document.getElementById('inputReaderStreet').value +
        "&house=" + document.getElementById('inputReaderHouse').value +
        "&birth=" + document.getElementById('inputReaderBirth').value;

    $('#addReaderResult').load('reader/add?' + params);
});

//add book button
$('html body #add-book').click(function () {
    if($('#inputBookCount').val() == ""){
        $('#inputBookCount').val(0);
    }

    $('#addBookResult').load('book/add?' +
    "title=" + $('#inputBookTitle').val() +
    "&authors=" + $('#inputBookAuthors').val() +
    "&year=" + $('#inputBookYear').val() +
    "&genre=" + $('#inputBookGenre').val() +
    "&count=" + $('#inputBookCount').val()
    );
});

//add report button
function reportAddClick() {
    $('#addRecordResult').load("report/add?" +
        "bookId=" + $('#inputBookId').val() +
        "&readerId=" + $('#inputReaderId').val() +
        "&returnDate=" + $('#inputReturnDate').val()
    );
}

$('#add_record').click(reportAddClick);

//edit reader button
$('#editReaderSearchId').keyup(function() {
    var searchIdInput = $('#editReaderSearchId').val();
    if(!!searchIdInput){
        var params = 'id=' + searchIdInput;
        var editObj;
        $.get('reader/findReaderJSON?' + params, function(data){
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
    }else{
        $('#readersEditModal input').each(function(){
            $(this).val("");
        })
    }
});
// обробник кнопки модального вікна для зміни даних читача
$('#edit-reader').click(function() {
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

//edit book
// пошук за ID при keyup
$('#editBookSearchId').keyup(function () {
    if(!!$('#editBookSearchId').val()){
        $.get("book/findBookByIdJSON?id=" + $('#editBookSearchId').val(), function (data) {
            var obj = $.parseJSON(data);
            $('#editBookTitle').val(obj.title);
            $('#editBookYear').val(obj.year);
            $('#editBookAuthors').val(obj.authors);
            $('#editBookGenre').val(obj.genre);
            $('#editBookCount').val(obj.amount);
        })
    }else{
        $('#booksEditModal input').each(function () {
            $(this).val("");
        })
    }
});
// відправка редагованих даних для апдейту
$('#edit_book').click(function () {
    $.get('book/edit?' +
        "id=" + $('#editBookSearchId').val() +
        "&title=" + $('#editBookTitle').val() +
        "&authors=" + $('#editBookAuthors').val() +
        "&year=" + $('#editBookYear').val() +
        "&genre=" + $('#editBookGenre').val() +
        "&count=" + $('#editBookCount').val()
    , function (data) {
            $('#editBookResult').text(data);
        });
});

// remove reader button

$('#removeReaderSearchId').keyup(function () {
var removeId = $('#removeReaderSearchId').val();
if(!!removeId){
    var params = 'id=' + removeId;
    var editObj;
    $.get('reader/findReaderJSON?' + params, function(data){
        editObj = $.parseJSON(data);
        $('#removeReaderFName').val(editObj.fName);
        $('#removeReaderMName').val(editObj.mName);
        $('#removeReaderLName').val(editObj.lName);
    });
}else{
    $('#readersRemoveModal input').each(function(){
        $(this).val("");
    })
}
});

$('#remove-reader').click(function() {
    var params = 'id=' + $('#removeReaderSearchId').val();
    $.get('reader/remove?' + params, function (data) {
        $('#removeReaderResult').text(data);
    })
});

//remove book
// пошук за ID при keyup
$('#removeBookSearchId').keyup(function () {
    if(!!$('#removeBookSearchId').val()){
        $.get("book/findBookByIdJSON?id=" + $('#removeBookSearchId').val(), function (data) {
            var obj = $.parseJSON(data);
            $('#removeBookTitle').val(obj.title);
            $('#removeBookYear').val(obj.year);
            $('#removeBookAuthors').val(obj.authors);
        })
    }else{
        $('#booksRemoveModal input').each(function () {
            $(this).val("");
        })
    }
});

$('#remove-book').click(function () {
    $('#removeBookResult').load("/book/remove?id=" + $('#removeBookSearchId').val())
});

// readers details
$('#detailsReaderSearchId').keyup(function() {
    var idInput = $('#detailsReaderSearchId').val();
    if(!!idInput){
        var params = "id=" + idInput;
        $.get('reader/findReaderJSON?' + params, function(data){
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
    }else{
        $('#readersDetailsModal input').each(function(){
            $(this).val("");
        })
    }
});

// books details
$('#detailsBookSearchId').keyup(function () {
    if(!!$('#detailsBookSearchId').val()){
        $.get("book/findBookByIdJSON?id=" + $('#detailsBookSearchId').val(), function (data) {
            var obj = $.parseJSON(data);
            $('#detailsBookTitle').val(obj.title);
            $('#detailsBookYear').val(obj.year);
            $('#detailsBookAuthors').val(obj.authors);
            $('#detailsBookGenre').val(obj.genre);
            $('#detailsBookCount').val(obj.amount);
        })
    }else{
        $('#booksDetailsModal input').val("")
    }
});

// показ дефолтної таблички readers
showReaders();
// показ дефолтної таблички books
showBooks();
// показ дефолтної таблички report
showReport();

// READERS-SECTION-END

//прокрутки
function scroll2(whoID, whereID){
    $(whoID).click(function() {
        $('html body').animate({
            scrollTop: $(whereID).offset().top - 50
        }, 1000)
    });
}

scroll2('#menu_item_report', '#report_section');
scroll2('#menu_item_readers', '#readers-section');
scroll2('#menu_item_books', '#books-section');
scroll2('#menu_item_contacts', '#contacts-section');