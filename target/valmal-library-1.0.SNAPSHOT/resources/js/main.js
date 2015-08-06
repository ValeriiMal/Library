
// READERS-SECTION-BEGIN

// variables
//var readersFindInputs = document.getElementById('readers-section').getElementsByTagName('input');
//var params = "";

// functions

// вивід невідфільтрованого набору записів
function showReaders(){
    $('#reader-table tbody').load("reader/load");
}

// вивід відфільтрованого за id набору записів
//function showReadersFind(params){
//    $('#reader-table tbody').load('http://localhost:8080/servlet?' + params);

//}

// перевірка відсутності значень в кожному з input заданого масиву
//function isInputsEmpty(inputs){
//    for(var i = 0; i < readersFindInputs.length; i++){
//        if(inputs[i].value != "") return false;
//    }
//
//    return true;
//}

// обробник onkeyup для кожного input
//function readersFind() {
//    if(isInputsEmpty(readersFindInputs)){
//        params = "";
//        showReaders();
//    }else{
//        // визначення існуючих параметрів в inputs та
//        // складання відповідного рядку запиту params (тільки існуючі параметри)
//        params = "";
//        readersFindInputs[0].value == "" ? params += "" : params += "&id=" + readersFindInputs[0].value ;
//        readersFindInputs[1].value == "" ? params += "" : params += "&fName=" + readersFindInputs[1].value ;
//        readersFindInputs[2].value == "" ? params += "" : params += "&mName=" + readersFindInputs[2].value ;
//        readersFindInputs[3].value == "" ? params += "" : params += "&lName=" + readersFindInputs[3].value ;
//        readersFindInputs[4].value == "" ? params += "" : params += "&phone=" + readersFindInputs[4].value ;
//
//        showReadersFind(params);
//    }
//
//}
//
//for(var i = 0; i < readersFindInputs.length; i++){
//    readersFindInputs[i].onkeyup = readersFind;
//}

//add button
//$('body').on('click', '#add-reader', function() {
//    var params = "action=add" +
//        "&fName=" + document.getElementById('inputReaderFName').value +
//        "&mName=" + document.getElementById('inputReaderMName').value +
//        "&lName=" + document.getElementById('inputReaderLName').value +
//        "&phone=" + document.getElementById('inputReaderPhone').value +
//        "&address=" + document.getElementById('inputReaderAddress').value;
//
//    $('#addReaderResult').load('http://localhost:8080/servlet?' + params);
//});

//edit button
//$('#editReaderSearchId').keyup(function() {
//    var searchIdInput = $('#editReaderSearchId').val();
//    if(!!searchIdInput){
//        var params = 'action=findEdit&id=' + searchIdInput;
//        var editObj;
//        $.get('http://localhost:8080/servlet?' + params, function(data){
//            editObj = $.parseJSON(data);
//            $('#editReaderFName').val(editObj.fName);
//            $('#editReaderMName').val(editObj.mName);
//            $('#editReaderLName').val(editObj.lName);
//            $('#editReaderPhone').val(editObj.phone);
//            $('#editReaderAddress').val(editObj.address);
//        });
//    }else{
//        $('#readersEditModal input').each(function(){
//            $(this).val("");
//        })
//    }
//});

//$('#edit-reader').click(function() {
//    var params = 'action=edit' +
//        '&id=' + $('#editReaderSearchId').val() +
//        '&fName=' + $('#editReaderFName').val() +
//        '&mName=' + $('#editReaderMName').val() +
//        '&lName=' + $('#editReaderLName').val() +
//        '&phone=' + $('#editReaderPhone').val() +
//        '&address=' + $('#editReaderAddress').val();
//    $.get('http://localhost:8080/servlet?' + params, function (data) {
//        $('#editReaderResult').text(data);
//    })
//});

// remove button

//$('#removeReaderSearchId').keyup(function () {
//var removeId = $('#removeReaderSearchId').val();
//if(!!removeId){
//    var params = 'action=findRemove&id=' + removeId;
//    var editObj;
//    $.get('http://localhost:8080/servlet?' + params, function(data){
//        editObj = $.parseJSON(data);
//        $('#removeReaderFName').val(editObj.fName);
//        $('#removeReaderMName').val(editObj.mName);
//        $('#removeReaderLName').val(editObj.lName);
//    });
//}else{
//    $('#readersRemoveModal input').each(function(){
//        $(this).val("");
//    })
//}
//});

//$('#remove-reader').click(function() {
//    var params = 'action=remove' +
//        '&id=' + $('#removeReaderSearchId').val();
//    $.get('http://localhost:8080/servlet?' + params, function (data) {
//        $('#editReaderResult').text(data);
//    })
//});

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