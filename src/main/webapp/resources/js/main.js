
// READERS-SECTION-BEGIN

// variables
var readersFindInputs = document.getElementById('readers-find-input').getElementsByTagName('input');
//var readersFindInputs = document.getElementById('readers-section').getElementsByTagName('input');
var readersAddInputs = document.getElementById('readersAddModal').getElementsByTagName('input');

// functions

// вивід невідфільтрованого набору записів
function showReaders(){
    $('#reader-table tbody').load("reader/load");
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

// обробник onkeyup для кожного input
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
        //readersFindInputs[5].value == "" ? params += "&address=" : params += "&address=" + readersFindInputs[5].value ;

        showReadersFind(params);
    }

}

for(var i = 0; i < readersFindInputs.length; i++){
    readersFindInputs[i].onkeyup = readersFind;
}

//add button
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
        //"&registrationDate=" + document.getElementById('inputReaderRegistrationDate').value +
        "&birth=" + document.getElementById('inputReaderBirth').value;

    $('#addReaderResult').load('reader/add?' + params);
});

//edit button
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

// remove button

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

// details button
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

// показ дефолтної таблички readers при записку сторінки
showReaders();
//$('#readersStatus').load('http://localhost:8080/servlet?action=readersCount');

//відкриття/приховування блоку з полями пошуку для readers
document.getElementById('readers-find-button').onclick = function () {
    var el = document.getElementById('readers-find-input');
    if(el.style.display == 'none'){
        el.style.display = 'block';
        el.style.marginTop = '10px';
    }else{
        el.style.display = 'none';
    }
};

// прокрутка до Readers
$('#menu_item_readers').click(function() {
    window.location.href = '#readers-section';
    document.body.scrollTop = 20;
});

// READERS-SECTION-END

// CONTACTS-SECTION-BEGIN

// прокрутка до Contacts
$('#menu_item_contacts').click(function () {
    window.location.href = '#contacts-section';
    document.body.scrollTop = window.pageYOffset - 50;
});

// CONTACTS-SECTION-END