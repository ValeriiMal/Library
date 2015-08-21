<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <%--<meta charset="utf-8">--%>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <script src="<c:url value="resources/js/jquery-1.11.3.js"/> "></script>
    <script src="<c:url value="resources/js/bootstrap.js"/> "></script>

    <link rel="icon" href="resources/img/favicon.ico"/>
    <link rel="stylesheet" href="<c:url value="resources/css/bootstrap.css"/>"/>
    <link rel="stylesheet" href="<c:url value="resources/css/index.css"/>"/>

    <title>Library</title>
</head>
<body>
<nav class="header" style="position: fixed; width: 1000px; top: 0;">
    <ul class="menu_container">
        <li class="menu_item" id="menu_item_report">
            Report
        </li>
        <li class="menu_item" id="menu_item_queue">
            Queue
        </li>
        <li class="menu_item" id="menu_item_books">
            Books
        </li>
        <li class="menu_item" id="menu_item_readers">
            Readers
        </li>
        <li class="menu_item" id="menu_item_contacts">
            Contacts
        </li>
    </ul>
</nav>

<div id="empty_row" style="height: 50px;"></div>

<%--begin report section--%>

<section id="report_section">
    <%--заголовок--%>
    <h3>Report</h3>
    <%--кнопки--%>
    <div>
        <button id="report_add" class="btn btn-info btn-lg" type="button" data-toggle="modal"
                data-target="#recordsAddModal">Add record
        </button>
        <button id="report_edit" class="btn btn-info btn-lg" type="button" data-toggle="modal"
                data-target="#recordsEditModal">Edit record
        </button>
        <button id="report_details" class="btn btn-info btn-lg" type="button" data-toggle="modal"
                data-target="#recordsDetailsModal">Record details
        </button>
    </div>
    <br/>
    <%--поля пошуку--%>
    <div id="report-find-input">
        <form class="form-horizontal">
            <div class="form-group">
                <label for="reportFindId" class="col-sm-2 control-label">Record Id: </label>

                <div class="col-sm-1">
                    <input type="text" class="form-control" placeholder="" id="reportFindId">
                </div>

                <label for="reportFindBookId" class="control-label col-sm-2">Book Id: </label>

                <div class="col-sm-2">
                    <input type="text" class="form-control" placeholder="" id="reportFindBookId">
                </div>

                <label class="col-sm-2 control-label" for="reportFindReaderId">Reader Id: </label>

                <div class="col-sm-2">
                    <input type="text" class="form-control" placeholder="" id="reportFindReaderId">
                </div>
            </div>
            <div class="form-group">
                <label for="reportFindDateFrom" class="col-sm-2 control-label">Date of record: from </label>

                <div class="col-sm-3">
                    <input type="date" class="form-control col-sm-3" placeholder="" id="reportFindDateFrom">
                </div>
                <label for="reportFindDateTo" class="col-sm-1 control-label">to </label>

                <div class="col-sm-3">
                    <input type="date" class="form-control col-sm-3" placeholder="" id="reportFindDateTo">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label" for="reportFindReturnDateFrom">Return date: from </label>

                <div class="col-sm-3">
                    <input type="date" class="form-control" placeholder="" id="reportFindReturnDateFrom">
                </div>

                <label class="col-sm-1 control-label" for="reportFindReturnDateTo">to </label>

                <div class="col-sm-3">
                    <input type="date" class="form-control" placeholder="" id="reportFindReturnDateTo">
                </div>

                <div class="col-sm-3">
                    <select name="" id="reportFindReturned" class="form-control">
                        <option value="all">All</option>
                        <option value="returned">Returned books</option>
                        <option value="notReturned">Not returned books</option>
                    </select>
                </div>

            </div>
        </form>
    </div>
    <br/>
    <%--таблиця записів--%>
    <div id="records_container">
        <table class="table table-hover" id="records-table">
            <thead>
            <tr>
                <th class="col-sm-2">Id</th>
                <th class="col-sm-2">Date</th>
                <th class="col-sm-2">Book</th>
                <th class="col-sm-2">Reader</th>
                <th class="col-sm-2">Returned</th>
                <th class="col-sm-2">Return date</th>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    </div>

    <%--add record modal--%>
    <div id="recordsAddModal" class="modal fade" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h3 class="modal-title">Add record</h3>
                </div>
                <div class="modal-body">
                    <form action="" class="form-horizontal">
                        <div class="form-group">
                            <label for="inputBookId" class="col-sm-4 control-label">Book Id</label>

                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="inputBookId" placeholder="Book Id"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputReaderId" class="col-sm-4 control-label">Reader Id</label>

                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="inputReaderId" placeholder="Reader Id"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputReturnDate" class="col-sm-4 control-label">Return date*</label>

                            <div class="col-sm-8">
                                <input type="date" class="form-control" id="inputReturnDate" placeholder=""/>
                            </div>
                        </div>
                    </form>

                </div>
                <div class="modal-footer">
                    <div id="addRecordResult" style="display: inline-block"></div>
                    <button id="add_record" type="button" class="btn btn-default">Add</button>
                </div>
            </div>
        </div>
    </div>
    <%--edit record modal--%>
    <div id="recordsEditModal" class="modal fade" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h3 class="modal-title">Edit</h3>
                </div>
                <div class="modal-body">
                    <form action="" class="form-horizontal">
                        <div class="form-group">
                            <label for="editRecordSearchId" class="col-sm-4 control-label">Id*</label>

                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="editRecordSearchId" placeholder="Enter id"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="editRecordBookId" class="col-sm-4 control-label">Book Id</label>

                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="editRecordBookId" placeholder="Book Id"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="editRecordReaderId" class="col-sm-4 control-label">Reader Id</label>

                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="editRecordReaderId"
                                       placeholder="Reader Id"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="editRecordchecked" class="col-sm-4 control-label">Returned</label>

                            <div class="col-sm-1">
                                <input type="checkbox" class="form-control" id="editRecordchecked"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="editRecordReturnDate" class="col-sm-4 control-label">Return date</label>

                            <div class="col-sm-8">
                                <input type="date" class="form-control" id="editRecordReturnDate"
                                       placeholder="Return date"/>
                            </div>
                        </div>
                    </form>
                    <div class="modal-footer">
                        <div id="editRecordResult" style="display: inline-block"></div>
                        <button id="edit_record" type="button" class="btn btn-default">Edit</button>
                    </div>

                </div>
            </div>
        </div>
    </div>
    <%--record details modal--%>
    <div id="recordsDetailsModal" class="modal fade" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h3 class="modal-title">Details</h3>
                </div>
                <div class="modal-body">
                    <form action="" class="form-horizontal">
                        <div class="form-group">
                            <label for="detailsRecordSearchId" class="col-sm-4 control-label">Id*: </label>

                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="detailsRecordSearchId"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="detailsRecordBookId" class="col-sm-4 control-label">Book Id: </label>

                            <div class="col-sm-2">
                                <label class="form-control" id="detailsRecordBookId"></label>
                            </div>
                            <label for="detailsRecordBookTitle" class="col-sm-2 control-label">Title: </label>

                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="detailsRecordBookTitle"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="detailsRecordReaderId" class="col-sm-4 control-label">Reader Id: </label>

                            <div class="col-sm-2">
                                <label class="form-control" id="detailsRecordReaderId"></label>
                            </div>

                            <label for="detailsRecordReaderName" class="col-sm-2 control-label">Name: </label>

                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="detailsRecordReaderName"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="detailsRecordchecked" class="col-sm-4 control-label">Returned: </label>

                            <div class="col-sm-1">
                                <input type="checkbox" class="form-control" id="detailsRecordchecked"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="detailsRecordDate" class="col-sm-4 control-label">Record date: </label>

                            <div class="col-sm-8">
                                <label class="form-control" id="detailsRecordDate"></label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="detailsRecordReturnDate" class="col-sm-4 control-label">Return date: </label>

                            <div class="col-sm-8">
                                <label class="form-control" id="detailsRecordReturnDate"></label>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>

<%--end report section--%>

<%--queue section--%>
<section id="queue-section">
    <h3>Queue</h3>

    <div>
        <button id="queue-add" class="btn btn-info btn-lg" type="button" data-toggle="modal"
                data-target="#queueAddModal">Add
        </button>
        <button id="queue-edit" class="btn btn-info btn-lg" type="button" data-toggle="modal"
                data-target="#queueEditModal">Edit
        </button>
        <button id="queue-remove" class="btn btn-info btn-lg" type="button" data-toggle="modal"
                data-target="#queueRemoveModal">Remove
        </button>
    </div>
    <br/>

    <div id="queue-find-input">
        <form class="form-horizontal">
            <div class="form-group">
                <div class="col-sm-2">
                    <input type="text" class="form-control" id="queueFindId" placeholder="id"/>
                </div>
                <div class="col-sm-3">
                    <input type="date" class="form-control" id="queueFindDate"/>
                </div>
                <div class="col-sm-2">
                    <input type="text" class="form-control" id="queueFindBookId" placeholder="book Id"/>
                </div>
                <div class="col-sm-2">
                    <input type="text" class="form-control" id="queueFindReaderId" placeholder="reader id"/>
                </div>
            </div>
        </form>
    </div>

    <div id="queue-container">
        <table id="queue-table" class="table table-hover">
            <thead>
            <tr>
                <th class="col-sm-2">Id</th>
                <th class="col-sm-2">Date</th>
                <th class="col-sm-2">Book Id</th>
                <th class="col-sm-2">Book title</th>
                <th class="col-sm-2">Reader Id</th>
                <th class="col-sm-2">Reader name</th>
            </tr>
            </thead>
            <tbody>

            </tbody>
        </table>
    </div>

    <div id="queueAddModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h3>Add item</h3>
                </div>
                <div class="modal-body">
                    <form action="" class="form-horizontal">
                        <div class="form-group">
                            <label for="queueAddBookId" class="control-label col-sm-4">Book Id: </label>

                            <div class="col-sm-2">
                                <input type="text" class="form-control" id="queueAddBookId"/>
                            </div>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" id="queueAddBookTitle"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="queueAddReaderId" class="control-label col-sm-4">Reader Id: </label>

                            <div class="col-sm-2">
                                <input type="text" class="form-control" id="queueAddReaderId"/>
                            </div>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" id="queueAddReaderName"/>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <div id="addQueueResult" style="display: inline-block;"></div>
                    <button id="add-queue" class="btn btn-default" type="button">Add</button>
                </div>
            </div>
        </div>
    </div>

    <div id="queueEditModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h3>Edit item</h3>
                </div>
                <div class="modal-body">
                    <form action="" class="form-horizontal">

                        <div class="form-group">
                            <label for="queueEditSearchId" class="control-label col-sm-4">Id*: </label>

                            <div class="col-sm-2">
                                <input type="text" class="form-control" id="queueEditSearchId"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="queueEditBookId" class="control-label col-sm-4">Book Id: </label>

                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="queueEditBookId"/>
                            </div>
                            <div class="col-sm-4">
                                <input class="form-control" id="queueEditBookTitle" placeholder="Title"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="queueEditReaderId" class="control-label col-sm-4">Reader Id: </label>

                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="queueEditReaderId"/>
                            </div>
                            <div class="col-sm-4">
                                <input class="form-control" id="queueEditReaderName" placeholder="Name"/>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <div id="editQueueResult" style="display: inline-block;"></div>
                    <button id="edit-queue" class="btn btn-default" type="button">Edit</button>
                </div>
            </div>
        </div>
    </div>

    <div id="queueRemoveModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h3>Remove item</h3>
                </div>
                <div class="modal-body">
                    <form action="" class="form-horizontal">

                        <div class="form-group">
                            <label for="queueRemoveSearchId" class="control-label col-sm-4">Id*: </label>

                            <div class="col-sm-2">
                                <input type="text" class="form-control" id="queueRemoveSearchId"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="queueRemoveBookId" class="control-label col-sm-4">Book Id: </label>

                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="queueRemoveBookId"/>
                            </div>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="queueRemoveBookTitle"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="queueRemoveReaderId" class="control-label col-sm-4">Reader Id: </label>

                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="queueRemoveReaderId"/>
                            </div>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="queueRemoveReaderName"/>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <div id="removeQueueResult" style="display: inline-block;"></div>
                    <button id="remove-queue" class="btn btn-default" type="button">Remove</button>
                </div>
            </div>
        </div>
    </div>
</section>
<%--end queue section--%>

<%--book section--%>
<section id="books-section">
    <h3>Books</h3>
    <%--кнопки--%>
    <div>
        <button id="books-add" type="button" class="btn btn-info btn-lg" data-toggle="modal"
                data-target="#booksAddModal">Add
        </button>
        <button id="books-edit" type="button" class="btn btn-info btn-lg" data-toggle="modal"
                data-target="#booksEditModal">Edit
        </button>
        <button id="books-remove" type="button" class="btn btn-info btn-lg" data-toggle="modal"
                data-target="#booksRemoveModal">Remove
        </button>
        <button id="books-details" type="button" class="btn btn-info btn-lg" data-toggle="modal"
                data-target="#booksDetailsModal">Details
        </button>
    </div>
    <br/>
    <%--поля пошуку--%>
    <div id="books-find-input">
        <form class="form-horizontal">
            <div class="form-group">
                <div class="col-sm-1">
                    <input type="text" class="form-control" placeholder="ID">
                </div>
                <div class="col-sm-2">
                    <input type="text" class="form-control" placeholder="Title">
                </div>
                <div class="col-sm-2">
                    <input type="text" class="form-control" placeholder="Authors">
                </div>
                <div class="col-sm-2">
                    <input type="text" class="form-control" placeholder="Year">
                </div>
                <div class="col-sm-2">
                    <input type="text" class="form-control" placeholder="Genre">
                </div>
                <label for="bookFindScarce" class="control-label col-sm-2">Scarce: </label>

                <div class="col-sm-1">
                    <input type="checkbox" class="form-control" placeholder="Genre" id="bookFindScarce">
                </div>
            </div>
        </form>
    </div>
    <%--таблиця книг--%>
    <div id="books-container">
        <table class="table table-hover" id="books-table">
            <thead>
            <tr>
                <th class="col-sm-2">Id</th>
                <th class="col-sm-2">Title</th>
                <th class="col-sm-2">Authors</th>
                <th class="col-sm-2">Year</th>
                <th class="col-sm-2">Genre</th>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    </div>
    <%--інформація про видані/прийняті книги--%>
    <div>
        <h3>Stat</h3>

        <div>
            <form class="form-horizontal">
                <div class="form-group">
                    <label for="booksDate1" class="col-sm-2 control-label">Date from: </label>

                    <div class="col-sm-3">

                        <input type="date" class="form-control" id="booksDate1"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="booksDate2" class="col-sm-2 control-label">Date to: </label>

                    <div class="col-sm-3">

                        <input type="date" class="form-control" id="booksDate2"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="booksOnlyGiven" class="col-sm-2 control-label">Видані: </label>

                    <div class="col-sm-1">

                        <input type="checkbox" class="check-box form-control" id="booksOnlyGiven"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="booksOnlyTaken" class="col-sm-2 control-label">Отримані: </label>

                    <div class="col-sm-1">
                        <input type="checkbox" class="checkbox form-control" id="booksOnlyTaken"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="booksScarce" class="col-sm-2 control-label">Дефіцитні: </label>

                    <div class="col-sm-1">
                        <input type="checkbox" class="checkbox form-control" id="booksScarce"/>
                    </div>
                </div>
                <button type="button" class="button btn-info btn-lg" id="showBooksStat">Show stat</button>
            </form>
        </div>
        <div id="booksStatContainer">
            <table class="table table-hover scroll-arrow" id="booksStatTable">
                <thead>
                <th class="col-sm-2">Id</th>
                <th class="col-sm-2">Title</th>
                <th class="col-sm-2">Authors</th>
                <th class="col-sm-2">Year</th>
                <th class="col-sm-2">Genre</th>
                </thead>
                <tbody>

                </tbody>
            </table>
        </div>
        <div id="booksStatStatus"></div>
    </div>

    <%--add modal--%>
    <div id="booksAddModal" class="modal fade" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h3 class="modal-title">Add book</h3>
                </div>
                <div class="modal-body">
                    <form action="" class="form-horizontal">
                        <div class="form-group">
                            <label for="inputBookTitle" class="col-sm-4 control-label">Title</label>

                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="inputBookTitle" placeholder="Title"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputBookAuthors" class="col-sm-4 control-label">Authors</label>

                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="inputBookAuthors" placeholder="Authors"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputBookYear" class="col-sm-4 control-label">Year</label>

                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="inputBookYear" placeholder="Year"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputBookGenre" class="col-sm-4 control-label">Genre</label>

                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="inputBookGenre" placeholder="Genre"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputBookAmount" class="col-sm-4 control-label">Amount</label>

                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="inputBookAmount" placeholder="Amount"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputBookScarce" class="col-sm-4 control-label">Scarce: </label>

                            <div class="col-sm-1">
                                <input type="checkbox" class="form-control" id="inputBookScarce" placeholder="Amount"/>
                            </div>
                        </div>
                    </form>

                </div>
                <div class="modal-footer">
                    <div id="addBookResult" style="display: inline-block"></div>
                    <button id="add-book" type="button" class="btn btn-default">Add</button>
                </div>
            </div>
        </div>
    </div>
    <%--edit modal--%>
    <div id="booksEditModal" class="modal fade" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h3 class="modal-title">Edit book</h3>
                </div>
                <div class="modal-body">
                    <form action="" class="form-horizontal">
                        <div class="form-group">
                            <label for="editBookSearchId" class="col-sm-4 control-label">ID</label>

                            <div class="col-sm-8">
                                <input id="editBookSearchId" type="text" class="form-control"
                                       placeholder="Enter book Id here"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="editBookTitle" class="col-sm-4 control-label">Title</label>

                            <div class="col-sm-8">
                                <input id="editBookTitle" type="text" class="form-control" placeholder="Title"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="editBookAuthors" class="col-sm-4 control-label">Authors</label>

                            <div class="col-sm-8">
                                <input id="editBookAuthors" type="text" class="form-control" placeholder="Authors"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="editBookYear" class="col-sm-4 control-label">Year</label>

                            <div class="col-sm-8">
                                <input id="editBookYear" type="text" class="form-control" placeholder="Year"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="editBookGenre" class="col-sm-4 control-label">Genre</label>

                            <div class="col-sm-8">
                                <input id="editBookGenre" type="text" class="form-control" placeholder="Genre"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="editBookAmount" class="col-sm-4 control-label">Amount</label>

                            <div class="col-sm-8">
                                <input id="editBookAmount" type="text" class="form-control" placeholder="Amount"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="editBookScarce" class="col-sm-4 control-label">Scarce: </label>

                            <div class="col-sm-1">
                                <input id="editBookScarce" type="checkbox" class="form-control"/>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <div id="editBookResult" style="display: inline-block"></div>
                    <button id="edit_book" class="btn btn-default" type="button">Edit</button>
                </div>
            </div>
        </div>
    </div>
    <%--remove modal--%>
    <div id="booksRemoveModal" class="modal fade" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h3 class="modal-title">Remove book</h3>
                </div>
                <div class="modal-body">
                    <form action="" class="form-horizontal">
                        <div class="form-group">
                            <label for="removeBookSearchId" class="col-sm-4 control-label">ID</label>

                            <div class="col-sm-8 ">
                                <input type="text" id="removeBookSearchId" class="form-control"
                                       placeholder="Enter book Id here"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="removeBookTitle" class="col-sm-4 control-label">Title</label>

                            <div class="col-sm-8 ">
                                <label id="removeBookTitle" class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="removeBookAuthors" class="col-sm-4 control-label">Authors</label>

                            <div class="col-sm-8 ">
                                <label id="removeBookAuthors" class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="removeBookYear" class="col-sm-4 control-label">Year</label>

                            <div class="col-sm-8 ">
                                <label id="removeBookYear" class="form-control"/>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <div id="removeBookResult" style="display: inline-block"></div>
                    <button id="remove-book" type="button" class="btn btn-default">Remove</button>
                </div>
            </div>
        </div>
    </div>
    <%--details modal--%>
    <div id="booksDetailsModal" class="modal fade" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h3 class="modal-title">Book details</h3>
                </div>
                <div class="modal-body">
                    <form action="" class="form-horizontal">
                        <div class="form-group">
                            <label for="detailsBookSearchId" class="col-sm-4 control-label">Id*: </label>

                            <div class="col-sm-8">
                                <input id="detailsBookSearchId" type="text" class="form-control"
                                       placeholder="Enter book Id here"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="detailsBookTitle" class="col-sm-4 control-label">Title</label>

                            <div class="col-sm-8">
                                <label id="detailsBookTitle" class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="detailsBookAuthors" class="col-sm-4 control-label">Authors</label>

                            <div class="col-sm-8">
                                <label id="detailsBookAuthors" class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="detailsBookYear" class="col-sm-4 control-label">Year</label>

                            <div class="col-sm-8">
                                <label id="detailsBookYear" class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="detailsBookGenre" class="col-sm-4 control-label">Genre</label>

                            <div class="col-sm-8">
                                <label id="detailsBookGenre" class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="detailsBookAmount" class="col-sm-4 control-label">Amount</label>

                            <div class="col-sm-2">
                                <label id="detailsBookAmount" class="form-control"/>
                            </div>
                            <label for="detailsBookRemains" class="col-sm-4 control-label">Remains</label>

                            <div class="col-sm-2">
                                <label id="detailsBookRemains" class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="detailsBookScarce" class="col-sm-4 control-label">Scarce: </label>

                            <div class="col-sm-1">
                                <input id="detailsBookScarce" type="checkbox" class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <button id="show_book_readers" class="btn btn-default" type="button"
                                    data-target="#bookReadersModal" data-toggle="modal">Show readers
                            </button>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">

                </div>
            </div>
        </div>
    </div>
    <%--show readers modal--%>
    <div id="bookReadersModal" class="modal fade" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h3 class="modal-title">Readers</h3>
                </div>
                <div class="modal-body" id="bookReaders">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>Id</th>
                            <th>FName</th>
                            <th>MName</th>
                            <th>LName</th>
                        </tr>
                        </thead>
                        <tbody>

                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</section>
<%--end book section--%>

<!--reader section-->
<section id="readers-section" style="text-align: center;">
    <h3>Readers</h3>

    <div>
        <button id="readers-add-button" type="button" class="btn btn-info btn-lg" data-toggle="modal"
                data-target="#readersAddModal">Add
        </button>
        <button id="readers-edit-button" type="button" class="btn btn-info btn-lg" data-toggle="modal"
                data-target="#readersEditModal">Edit
        </button>
        <button id="readers-remove-button" type="button" class="btn btn-info btn-lg" data-toggle="modal"
                data-target="#readersRemoveModal">Remove
        </button>
        <%--<button id="readers-find-button" type="button" class="btn btn-info btn-lg">Find</button>--%>

        <button id="readers-details-button" type="button" class="btn btn-info btn-lg" data-toggle="modal"
                data-target="#readersDetailsModal">Details
        </button>
    </div>
    <br/>
    <%--поля пошуку --%>
    <div id="readers-find-input">
        <form class="form-horizontal">
            <div class="form-group">
                <div class="col-sm-2">
                    <input type="text" class="form-control" placeholder="ID">
                </div>
                <div class="col-sm-2">
                    <input type="text" class="form-control" placeholder="First name">
                </div>
                <div class="col-sm-2">
                    <input type="text" class="form-control" placeholder="Middle name">
                </div>
                <div class="col-sm-2">
                    <input type="text" class="form-control" placeholder="Last name">
                </div>
                <div class="col-sm-2">
                    <input type="text" class="form-control" placeholder="Phone">
                </div>
            </div>
        </form>
    </div>
    <%--таблиця читачів--%>
    <div id="readers-container">
        <table class="table table-hover" id="reader-table">
            <thead>
            <tr>
                <th class="col-sm-2">Id</th>
                <th class="col-sm-2">First name</th>
                <th class="col-sm-2">Middle name</th>
                <th class="col-sm-2">Last name</th>
                <th class="col-sm-2">Phone</th>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    </div>

    <%--Modal--%>
    <%--модальне вікно для кнопки Add--%>
    <div id="readersAddModal" class="modal fade" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Add reader </h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal">
                        <div class="form-group">
                            <label for="inputReaderFName" class="col-sm-4 control-label">First name</label>

                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="inputReaderFName" placeholder="First name">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputReaderMName" class="col-sm-4 control-label">Middle name</label>

                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="inputReaderMName" placeholder="Middle name">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="inputReaderLName">Last name</label>

                            <div class="col-sm-8">
                                <input type="text" class="form-control" placeholder="Last name" id="inputReaderLName"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="inputReaderPhone">Phone</label>

                            <div class="col-sm-8">
                                <input type="text" class="form-control" placeholder="Phone" id="inputReaderPhone"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="inputReaderCountry">Country</label>

                            <div class="col-sm-8">
                                <input type="text" class="form-control" placeholder="Country" id="inputReaderCountry"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="inputReaderCity">City</label>

                            <div class="col-sm-8">
                                <input type="text" class="form-control" placeholder="City" id="inputReaderCity"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="inputReaderStreet">Street</label>

                            <div class="col-sm-8">
                                <input type="text" class="form-control" placeholder="Street" id="inputReaderStreet"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="inputReaderHouse">House</label>

                            <div class="col-sm-8">
                                <input type="text" class="form-control" placeholder="House" id="inputReaderHouse"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="inputReaderBirth">Date of birth</label>

                            <div class="col-sm-8">
                                <input type="date" class="form-control" placeholder="Date of birth"
                                       id="inputReaderBirth"/>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <div id="addReaderResult" style="display: inline-block"></div>
                    <button id="add-reader" type="button" class="btn btn-default">Add</button>
                </div>
            </div>
        </div>
    </div>
    <%--модальне вікно для кнопки Edit--%>
    <div id="readersEditModal" class="modal fade" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Edit reader </h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal">
                        <div class="form-group">
                            <label for="editReaderSearchId" class="col-sm-4 control-label">ID</label>

                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="editReaderSearchId"
                                       placeholder="Enter reader id">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="editReaderFName" class="col-sm-4 control-label">First name</label>

                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="editReaderFName" placeholder="First name">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="editReaderMName" class="col-sm-4 control-label">Middle name</label>

                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="editReaderMName" placeholder="Middle name">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="editReaderLName">Last name</label>

                            <div class="col-sm-8">
                                <input type="text" class="form-control" placeholder="Last name" id="editReaderLName"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="editReaderPhone">Phone</label>

                            <div class="col-sm-8">
                                <input type="text" class="form-control" placeholder="Phone" id="editReaderPhone"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="editReaderCountry">Country</label>

                            <div class="col-sm-8">
                                <input type="text" class="form-control" placeholder="Country" id="editReaderCountry"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="editReaderCity">City</label>

                            <div class="col-sm-8">
                                <input type="text" class="form-control" placeholder="City" id="editReaderCity"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="editReaderStreet">Street</label>

                            <div class="col-sm-8">
                                <input type="text" class="form-control" placeholder="Street" id="editReaderStreet"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="editReaderHouse">House</label>

                            <div class="col-sm-8">
                                <input type="text" class="form-control" placeholder="House" id="editReaderHouse"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="editReaderBirth">Date of birth</label>

                            <div class="col-sm-8">
                                <input type="date" class="form-control" placeholder="Date of birth"
                                       id="editReaderBirth"/>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <div id="editReaderResult" style="display: inline-block"></div>
                    <button id="edit-reader" type="button" class="btn btn-default">Edit</button>
                </div>
            </div>
        </div>
    </div>
    <%--модальне вікно для кнопки Remove--%>
    <div id="readersRemoveModal" class="modal fade" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Remove reader </h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal">
                        <div class="form-group">
                            <label for="removeReaderSearchId" class="col-sm-4 control-label">Id*: </label>

                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="removeReaderSearchId"
                                       placeholder="Enter reader id">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="removeReaderFName" class="col-sm-4 control-label">First name: </label>

                            <div class="col-sm-8">
                                <label class="form-control" id="removeReaderFName"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="removeReaderMName" class="col-sm-4 control-label">Middle name: </label>

                            <div class="col-sm-8">
                                <label class="form-control" id="removeReaderMName"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="removeReaderLName">Last name: </label>

                            <div class="col-sm-8">
                                <label class="form-control" id="removeReaderLName"/>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <div id="removeReaderResult" style="display: inline-block"></div>
                    <button id="remove-reader" type="button" class="btn btn-default">Remove</button>
                </div>
            </div>
        </div>
    </div>
    <%--модальне вікно для кнопки Details--%>
    <div id="readersDetailsModal" class="modal fade" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Reader details</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal">
                        <div class="form-group">
                            <label for="detailsReaderSearchId" class="col-sm-4 control-label">ID</label>

                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="detailsReaderSearchId"
                                       placeholder="Enter reader id">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="detailsReaderFName" class="col-sm-4 control-label">First name</label>

                            <div class="col-sm-8">
                                <label class="form-control" id="detailsReaderFName"></label>
                                <%--<input type="text" class="form-control" id="detailsReaderFName"--%>
                                <%--placeholder="First name">--%>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="detailsReaderMName" class="col-sm-4 control-label">Middle name</label>

                            <div class="col-sm-8">
                                <label class="form-control" id="detailsReaderMName"></label>
                                <%--<input type="text" class="form-control" id="detailsReaderMName"--%>
                                <%--placeholder="Middle name">--%>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="detailsReaderLName">Last name</label>

                            <div class="col-sm-8">
                                <label class="form-control" id="detailsReaderLName"></label>
                                <%--<input type="text" class="form-control" placeholder="Last name"--%>
                                <%--id="detailsReaderLName"/>--%>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="detailsReaderPhone">Phone</label>

                            <div class="col-sm-8">
                                <label class="form-control" id="detailsReaderPhone"></label>
                                <%--<input type="text" class="form-control" placeholder="Phone" id="detailsReaderPhone"/>--%>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="detailsReaderCountry">Country</label>

                            <div class="col-sm-8">
                                <label class="form-control" id="detailsReaderCountry"></label>
                                <%--<input type="text" class="form-control" placeholder="Country"--%>
                                <%--id="detailsReaderCountry"/>--%>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="detailsReaderCity">City</label>

                            <div class="col-sm-8">
                                <label class="form-control" id="detailsReaderCity"></label>
                                <%--<input type="text" class="form-control" placeholder="City" id="detailsReaderCity"/>--%>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="detailsReaderStreet">Street</label>

                            <div class="col-sm-8">
                                <label class="form-control" id="detailsReaderStreet"></label>
                                <%--<input type="text" class="form-control" placeholder="Street" id="detailsReaderStreet"/>--%>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="detailsReaderHouse">House</label>

                            <div class="col-sm-8">
                                <label class="form-control" id="detailsReaderHouse"></label>
                                <%--<input type="text" class="form-control" placeholder="House" id="detailsReaderHouse"/>--%>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="detailsReaderBirth">Date of birth</label>

                            <div class="col-sm-8">
                                <label class="form-control" id="detailsReaderBirth"></label>
                                <%--<input type="text" class="form-control" placeholder="Date of birth"--%>
                                <%--id="detailsReaderBirth"/>--%>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="detailsReaderRegistrationDate">Registration
                                date</label>

                            <div class="col-sm-8">
                                <label class="form-control" id="detailsReaderRegistrationDate"></label>
                                <%--<input type="text" class="form-control" placeholder="Registration date"--%>
                                <%--id="detailsReaderRegistrationDate"/>--%>
                            </div>
                        </div>
                    </form>
                </div>

            </div>
        </div>
    </div>
    <%--&lt;%&ndash;show reader books&ndash;%&gt;--%>
    <%--<div id="readerBooksModal" class="modal fade" role="dialog">--%>
    <%--<div class="modal-dialog">--%>
    <%--<div class="modal-content">--%>
    <%--<div class="modal-header">--%>
    <%--<button type="button" class="close" data-dismiss="modal">&times;</button>--%>
    <%--<h3 class="modal-title">Books</h3>--%>
    <%--</div>--%>
    <%--<div class="modal-body" id="readerBooks">--%>
    <%--<table class="table">--%>
    <%--<thead>--%>
    <%--<tr>--%>
    <%--<th>Id</th>--%>
    <%--<th>Title</th>--%>
    <%--<th>Authors</th>--%>
    <%--<th>Year</th>--%>
    <%--</tr>--%>
    <%--</thead>--%>
    <%--<tbody>--%>

    <%--</tbody>--%>
    <%--</table>--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--рядок додаткової інформації--%>
    <div id="readersStatus"></div>
</section>
<%--end reader section--%>

<!--contacts section-->
<section id="contacts-section" style="text-align: center; height: 700px; ">
    <h3>Contacts</h3>

    <div class="form-horizontal ">
        <div class="form-group">
            <div class="col-sm-2"><h4> Phone: </h4></div>
            <div class="col-sm-10"><h4>00000000000</h4></div>
        </div>
        <div class="form-group">
            <div class="col-sm-2"><h4>Address: </h4></div>
            <div class="col-sm-10"><h4>Ukraine, Cherkassy</h4></div>
        </div>
        <div class="form-group">
            <div class="col-sm-2"><h4>Email: </h4></div>
            <div class="col-sm-10"><h4>libCherk@example.com</h4></div>
        </div>
    </div>
</section>
<%--end contacts section--%>

<script src="resources/js/main.js"></script>
</body>
</html>

