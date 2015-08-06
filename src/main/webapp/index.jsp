
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <script src="<c:url value="resources/js/jquery-1.11.3.js"/> "></script>
    <script src="<c:url value="resources/js/bootstrap.js"/> "></script>

    <link rel="stylesheet" href="<c:url value="resources/css/bootstrap.css"/>"/>
    <link rel="stylesheet" href="<c:url value="resources/css/index.css"/>"/>

    <title>Library</title>
</head>
<body>
<nav class="header" style="position: fixed; width: 1000px; top: 0px;">
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

<!--reader section-->
<section id="readers-section" style="text-align: center">
    <h3>Readers</h3>
    <div>
        <button id="readers-add-button" type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#readersAddModal">Add</button>
        <button id="readers-edit-button" type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#readersEditModal">Edit</button>
        <button id="readers-remove-button" type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#readersRemoveModal">Remove</button>
        <button id="readers-find-button" type="button" class="btn btn-info btn-lg">Find</button>
    </div>
    <div id="readers-find-input" style="display: none;">
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
                <div class="col-sm-2">
                    <input type="text" class="form-control" placeholder="Address">
                </div>
            </div>

        </form>
    </div>
    <div id="readers-container">
        <table class="table table-hover" id="reader-table">
            <thead>
            <tr>
                <th class="col-sm-2">Id</th>
                <th class="col-sm-2">First name</th>
                <th class="col-sm-2">Middle name</th>
                <th class="col-sm-2">Last name</th>
                <th class="col-sm-2">Phone</th>
                <th class="col-sm-2">Address</th>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    </div>

    <%--Modal--%>

    <div id="readersAddModal" class="modal fade" role="dialog" style="top: 100px; ">
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
                            <label class="col-sm-4 control-label" for="inputReaderAddress">Address</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" placeholder="Address" id="inputReaderAddress"/>
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

    <div id="readersEditModal" class="modal fade" role="dialog" style="top: 100px; ">
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
                                <input type="text" class="form-control" id="editReaderSearchId" placeholder="Enter reader id">
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
                            <label class="col-sm-4 control-label" for="editReaderAddress">Address</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" placeholder="Address" id="editReaderAddress"/>
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

    <div id="readersRemoveModal" class="modal fade" role="dialog" style="top: 100px; ">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Remove reader </h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal">
                        <div class="form-group">
                            <label for="removeReaderSearchId" class="col-sm-4 control-label">ID</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="removeReaderSearchId" placeholder="Enter reader id">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="removeReaderFName" class="col-sm-4 control-label">First name</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="removeReaderFName" placeholder="First name">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="removeReaderMName" class="col-sm-4 control-label">Middle name</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="removeReaderMName" placeholder="Middle name">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label" for="removeReaderLName">Last name</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" placeholder="Last name" id="removeReaderLName"/>
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

