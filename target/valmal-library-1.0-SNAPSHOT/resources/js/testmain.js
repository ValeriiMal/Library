
//// READERS-SECTION
//
//// variables
//var readersSection = document.getElementById('readers-section');
//var readersTbody = document.getElementById('reader-table').getElementsByTagName('tbody')[0];
//var readersFindInputs = document.getElementById('readers-section').getElementsByTagName('input');
//var params = "";
//
//// functions
//
//// ���� ����������������� ������ ������
//function showReaders(){
//    var xhr;
//    if(window.XMLHttpRequest){
//        xhr = new XMLHttpRequest();
//    }else{
//        xhr = new ActiveXObject("Microsoft.XMLHTTP");
//    }
//
//    xhr.open('GET', 'http://localhost:8080/servlet', true);
//
//    xhr.send();
//    xhr.onreadystatechange = function() {
//        if(xhr.readyState == 4 && xhr.status == 200){
//            readersTbody.innerHTML = xhr.responseText;
//        }
//    };
//}
//
//// ���� ��������������� �� id ������ ������
//function showReadersFind(params){
//    var xhr;
//    if(window.XMLHttpRequest){
//        xhr = new XMLHttpRequest();
//    }else{
//        xhr = new ActiveXObject("Microsoft.XMLHTTP");
//    }
//
//    xhr.open('GET', 'http://localhost:8080/servlet?' + params, true);
//
//    xhr.send();
//    xhr.onreadystatechange = function() {
//        if(xhr.readyState == 4 && xhr.status == 200){
//            readersTbody.innerHTML = xhr.responseText;
//        }
//    };
//
//}
//
//// �������� ��������� ������� � ������� � input �������� ������
//function isInputsEmpty(inputs){
//    for(var i = 0; i < readersFindInputs.length; i++){
//        if(inputs[i].value != "") return false;
//    }
//    return true;
//}
//
//// �������� onkeyup ��� ������� input
//function readersFind() {
//    var idInput = readersFindInputs[0].value;
//
//    if(isInputsEmpty(readersFindInputs)){
//        params = "";
//        showReaders();
//    }else{
//        // ���������� �������� ��������� � inputs ��
//        // ��������� ���������� ����� ������ params (����� ������� ���������)
//
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
//
//
//
//// ����� �������� �������� readers ��� ������� �������
//showReaders();
//��������/������������ ����� � ������ ������ ��� readers
document.getElementById('readers-find-button').onclick = function () {
    var el = document.getElementById('readers-find-input');
    if(el.style.display == 'none'){
        el.style.display = 'block';
        el.style.marginTop = '10px';
    }else{
        el.style.display = 'none';
    }
};

$('#menu_item_readers').click(function() {
    window.location.href = '#readers-section';
    document.body.scrollTop = 20;
});
