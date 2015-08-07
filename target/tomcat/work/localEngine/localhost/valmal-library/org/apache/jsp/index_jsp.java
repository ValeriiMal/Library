package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.release();
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("    <meta charset=\"utf-8\">\r\n");
      out.write("    <script src=\"");
      if (_jspx_meth_c_005furl_005f0(_jspx_page_context))
        return;
      out.write(" \"></script>\r\n");
      out.write("    <script src=\"");
      if (_jspx_meth_c_005furl_005f1(_jspx_page_context))
        return;
      out.write(" \"></script>\r\n");
      out.write("\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"");
      if (_jspx_meth_c_005furl_005f2(_jspx_page_context))
        return;
      out.write("\"/>\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"");
      if (_jspx_meth_c_005furl_005f3(_jspx_page_context))
        return;
      out.write("\"/>\r\n");
      out.write("\r\n");
      out.write("    <title>Library</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<nav class=\"header\" style=\"position: fixed; width: 1000px; top: 0px;\">\r\n");
      out.write("    <ul class=\"menu_container\">\r\n");
      out.write("        <li class=\"menu_item\" id=\"menu_item_report\">\r\n");
      out.write("            Report\r\n");
      out.write("        </li>\r\n");
      out.write("        <li class=\"menu_item\" id=\"menu_item_queue\">\r\n");
      out.write("            Queue\r\n");
      out.write("        </li>\r\n");
      out.write("        <li class=\"menu_item\" id=\"menu_item_books\">\r\n");
      out.write("            Books\r\n");
      out.write("        </li>\r\n");
      out.write("        <li class=\"menu_item\" id=\"menu_item_readers\">\r\n");
      out.write("            Readers\r\n");
      out.write("        </li>\r\n");
      out.write("        <li class=\"menu_item\" id=\"menu_item_contacts\">\r\n");
      out.write("            Contacts\r\n");
      out.write("        </li>\r\n");
      out.write("    </ul>\r\n");
      out.write("</nav>\r\n");
      out.write("\r\n");
      out.write("<div id=\"empty_row\" style=\"height: 50px;\"></div>\r\n");
      out.write("\r\n");
      out.write("<!--reader section-->\r\n");
      out.write("<section id=\"readers-section\" style=\"text-align: center\">\r\n");
      out.write("    <h3>Readers</h3>\r\n");
      out.write("\r\n");
      out.write("    <div>\r\n");
      out.write("        <button id=\"readers-add-button\" type=\"button\" class=\"btn btn-info btn-lg\" data-toggle=\"modal\"\r\n");
      out.write("                data-target=\"#readersAddModal\">Add\r\n");
      out.write("        </button>\r\n");
      out.write("        <button id=\"readers-edit-button\" type=\"button\" class=\"btn btn-info btn-lg\" data-toggle=\"modal\"\r\n");
      out.write("                data-target=\"#readersEditModal\">Edit\r\n");
      out.write("        </button>\r\n");
      out.write("        <button id=\"readers-remove-button\" type=\"button\" class=\"btn btn-info btn-lg\" data-toggle=\"modal\"\r\n");
      out.write("                data-target=\"#readersRemoveModal\">Remove\r\n");
      out.write("        </button>\r\n");
      out.write("        <button id=\"readers-find-button\" type=\"button\" class=\"btn btn-info btn-lg\">Find</button>\r\n");
      out.write("\r\n");
      out.write("        <button id=\"readers-details-button\" type=\"button\" class=\"btn btn-info btn-lg\" data-toggle=\"modal\"\r\n");
      out.write("                data-target=\"#readersDetailsModal\">Details</button>\r\n");
      out.write("    </div>\r\n");
      out.write("    ");
      out.write("\r\n");
      out.write("    <div id=\"readers-find-input\" style=\"display: none;\">\r\n");
      out.write("        <form class=\"form-horizontal\" >\r\n");
      out.write("            <div class=\"form-group\" >\r\n");
      out.write("                <div class=\"col-sm-2\">\r\n");
      out.write("                    <input type=\"text\" class=\"form-control\" placeholder=\"ID\">\r\n");
      out.write("                </div>\r\n");
      out.write("                <div class=\"col-sm-2\">\r\n");
      out.write("                    <input type=\"text\" class=\"form-control\" placeholder=\"First name\">\r\n");
      out.write("                </div>\r\n");
      out.write("                <div class=\"col-sm-2\">\r\n");
      out.write("                    <input type=\"text\" class=\"form-control\" placeholder=\"Middle name\">\r\n");
      out.write("                </div>\r\n");
      out.write("                <div class=\"col-sm-2\">\r\n");
      out.write("                    <input type=\"text\" class=\"form-control\" placeholder=\"Last name\">\r\n");
      out.write("                </div>\r\n");
      out.write("                <div class=\"col-sm-2\">\r\n");
      out.write("                    <input type=\"text\" class=\"form-control\" placeholder=\"Phone\">\r\n");
      out.write("                </div>\r\n");
      out.write("                ");
      out.write("\r\n");
      out.write("                    ");
      out.write("\r\n");
      out.write("                ");
      out.write("\r\n");
      out.write("            </div>\r\n");
      out.write("        </form>\r\n");
      out.write("    </div>\r\n");
      out.write("    ");
      out.write("\r\n");
      out.write("    <div id=\"readers-container\">\r\n");
      out.write("        <table class=\"table table-hover\" id=\"reader-table\">\r\n");
      out.write("            <thead>\r\n");
      out.write("            <tr>\r\n");
      out.write("                <th class=\"col-sm-2\">Id</th>\r\n");
      out.write("                <th class=\"col-sm-2\">First name</th>\r\n");
      out.write("                <th class=\"col-sm-2\">Middle name</th>\r\n");
      out.write("                <th class=\"col-sm-2\">Last name</th>\r\n");
      out.write("                <th class=\"col-sm-2\">Phone</th>\r\n");
      out.write("                ");
      out.write("\r\n");
      out.write("            </tr>\r\n");
      out.write("            </thead>\r\n");
      out.write("            <tbody>\r\n");
      out.write("            </tbody>\r\n");
      out.write("        </table>\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("    ");
      out.write("\r\n");
      out.write("    ");
      out.write("\r\n");
      out.write("    <div id=\"readersAddModal\" class=\"modal fade\" role=\"dialog\" style=\"top: 100px; \">\r\n");
      out.write("        <div class=\"modal-dialog\">\r\n");
      out.write("            <div class=\"modal-content\">\r\n");
      out.write("                <div class=\"modal-header\">\r\n");
      out.write("                    <button type=\"button\" class=\"close\" data-dismiss=\"modal\">&times;</button>\r\n");
      out.write("                    <h4 class=\"modal-title\">Add reader </h4>\r\n");
      out.write("                </div>\r\n");
      out.write("                <div class=\"modal-body\">\r\n");
      out.write("                    <form class=\"form-horizontal\">\r\n");
      out.write("                        <div class=\"form-group\">\r\n");
      out.write("                            <label for=\"inputReaderFName\" class=\"col-sm-4 control-label\">First name</label>\r\n");
      out.write("\r\n");
      out.write("                            <div class=\"col-sm-8\">\r\n");
      out.write("                                <input type=\"text\" class=\"form-control\" id=\"inputReaderFName\" placeholder=\"First name\">\r\n");
      out.write("                            </div>\r\n");
      out.write("                        </div>\r\n");
      out.write("                        <div class=\"form-group\">\r\n");
      out.write("                            <label for=\"inputReaderMName\" class=\"col-sm-4 control-label\">Middle name</label>\r\n");
      out.write("\r\n");
      out.write("                            <div class=\"col-sm-8\">\r\n");
      out.write("                                <input type=\"text\" class=\"form-control\" id=\"inputReaderMName\" placeholder=\"Middle name\">\r\n");
      out.write("                            </div>\r\n");
      out.write("                        </div>\r\n");
      out.write("                        <div class=\"form-group\">\r\n");
      out.write("                            <label class=\"col-sm-4 control-label\" for=\"inputReaderLName\">Last name</label>\r\n");
      out.write("\r\n");
      out.write("                            <div class=\"col-sm-8\">\r\n");
      out.write("                                <input type=\"text\" class=\"form-control\" placeholder=\"Last name\" id=\"inputReaderLName\"/>\r\n");
      out.write("                            </div>\r\n");
      out.write("                        </div>\r\n");
      out.write("                        <div class=\"form-group\">\r\n");
      out.write("                            <label class=\"col-sm-4 control-label\" for=\"inputReaderPhone\">Phone</label>\r\n");
      out.write("\r\n");
      out.write("                            <div class=\"col-sm-8\">\r\n");
      out.write("                                <input type=\"text\" class=\"form-control\" placeholder=\"Phone\" id=\"inputReaderPhone\"/>\r\n");
      out.write("                            </div>\r\n");
      out.write("                        </div>\r\n");
      out.write("                        <div class=\"form-group\">\r\n");
      out.write("                            <label class=\"col-sm-4 control-label\" for=\"inputReaderCountry\">Country</label>\r\n");
      out.write("\r\n");
      out.write("                            <div class=\"col-sm-8\">\r\n");
      out.write("                                <input type=\"text\" class=\"form-control\" placeholder=\"Country\" id=\"inputReaderCountry\"/>\r\n");
      out.write("                            </div>\r\n");
      out.write("                        </div>\r\n");
      out.write("                        <div class=\"form-group\">\r\n");
      out.write("                            <label class=\"col-sm-4 control-label\" for=\"inputReaderCity\">City</label>\r\n");
      out.write("\r\n");
      out.write("                            <div class=\"col-sm-8\">\r\n");
      out.write("                                <input type=\"text\" class=\"form-control\" placeholder=\"City\" id=\"inputReaderCity\"/>\r\n");
      out.write("                            </div>\r\n");
      out.write("                        </div>\r\n");
      out.write("                        <div class=\"form-group\">\r\n");
      out.write("                            <label class=\"col-sm-4 control-label\" for=\"inputReaderStreet\">Street</label>\r\n");
      out.write("\r\n");
      out.write("                            <div class=\"col-sm-8\">\r\n");
      out.write("                                <input type=\"text\" class=\"form-control\" placeholder=\"Street\" id=\"inputReaderStreet\"/>\r\n");
      out.write("                            </div>\r\n");
      out.write("                        </div>\r\n");
      out.write("                        <div class=\"form-group\">\r\n");
      out.write("                            <label class=\"col-sm-4 control-label\" for=\"inputReaderHouse\">House</label>\r\n");
      out.write("\r\n");
      out.write("                            <div class=\"col-sm-8\">\r\n");
      out.write("                                <input type=\"text\" class=\"form-control\" placeholder=\"House\" id=\"inputReaderHouse\"/>\r\n");
      out.write("                            </div>\r\n");
      out.write("                        </div>\r\n");
      out.write("                        <div class=\"form-group\">\r\n");
      out.write("                            <label class=\"col-sm-4 control-label\" for=\"inputReaderBirth\">Date of birth</label>\r\n");
      out.write("\r\n");
      out.write("                            <div class=\"col-sm-8\">\r\n");
      out.write("                                <input type=\"text\" class=\"form-control\" placeholder=\"Date of birth\" id=\"inputReaderBirth\"/>\r\n");
      out.write("                            </div>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </form>\r\n");
      out.write("                </div>\r\n");
      out.write("                <div class=\"modal-footer\">\r\n");
      out.write("                    <div id=\"addReaderResult\" style=\"display: inline-block\"></div>\r\n");
      out.write("                    <button id=\"add-reader\" type=\"button\" class=\"btn btn-default\">Add</button>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("    ");
      out.write("\r\n");
      out.write("    <div id=\"readersEditModal\" class=\"modal fade\" role=\"dialog\" style=\"top: 100px; \">\r\n");
      out.write("        <div class=\"modal-dialog\">\r\n");
      out.write("            <div class=\"modal-content\">\r\n");
      out.write("                <div class=\"modal-header\">\r\n");
      out.write("                    <button type=\"button\" class=\"close\" data-dismiss=\"modal\">&times;</button>\r\n");
      out.write("                    <h4 class=\"modal-title\">Edit reader </h4>\r\n");
      out.write("                </div>\r\n");
      out.write("                <div class=\"modal-body\">\r\n");
      out.write("                    <form class=\"form-horizontal\">\r\n");
      out.write("                        <div class=\"form-group\">\r\n");
      out.write("                            <label for=\"editReaderSearchId\" class=\"col-sm-4 control-label\">ID</label>\r\n");
      out.write("\r\n");
      out.write("                            <div class=\"col-sm-8\">\r\n");
      out.write("                                <input type=\"text\" class=\"form-control\" id=\"editReaderSearchId\"\r\n");
      out.write("                                       placeholder=\"Enter reader id\">\r\n");
      out.write("                            </div>\r\n");
      out.write("                        </div>\r\n");
      out.write("                        <div class=\"form-group\">\r\n");
      out.write("                            <label for=\"editReaderFName\" class=\"col-sm-4 control-label\">First name</label>\r\n");
      out.write("\r\n");
      out.write("                            <div class=\"col-sm-8\">\r\n");
      out.write("                                <input type=\"text\" class=\"form-control\" id=\"editReaderFName\" placeholder=\"First name\">\r\n");
      out.write("                            </div>\r\n");
      out.write("                        </div>\r\n");
      out.write("                        <div class=\"form-group\">\r\n");
      out.write("                            <label for=\"editReaderMName\" class=\"col-sm-4 control-label\">Middle name</label>\r\n");
      out.write("\r\n");
      out.write("                            <div class=\"col-sm-8\">\r\n");
      out.write("                                <input type=\"text\" class=\"form-control\" id=\"editReaderMName\" placeholder=\"Middle name\">\r\n");
      out.write("                            </div>\r\n");
      out.write("                        </div>\r\n");
      out.write("                        <div class=\"form-group\">\r\n");
      out.write("                            <label class=\"col-sm-4 control-label\" for=\"editReaderLName\">Last name</label>\r\n");
      out.write("\r\n");
      out.write("                            <div class=\"col-sm-8\">\r\n");
      out.write("                                <input type=\"text\" class=\"form-control\" placeholder=\"Last name\" id=\"editReaderLName\"/>\r\n");
      out.write("                            </div>\r\n");
      out.write("                        </div>\r\n");
      out.write("                        <div class=\"form-group\">\r\n");
      out.write("                            <label class=\"col-sm-4 control-label\" for=\"editReaderPhone\">Phone</label>\r\n");
      out.write("\r\n");
      out.write("                            <div class=\"col-sm-8\">\r\n");
      out.write("                                <input type=\"text\" class=\"form-control\" placeholder=\"Phone\" id=\"editReaderPhone\"/>\r\n");
      out.write("                            </div>\r\n");
      out.write("                        </div>\r\n");
      out.write("                        <div class=\"form-group\">\r\n");
      out.write("                            <label class=\"col-sm-4 control-label\" for=\"editReaderCountry\">Country</label>\r\n");
      out.write("\r\n");
      out.write("                            <div class=\"col-sm-8\">\r\n");
      out.write("                                <input type=\"text\" class=\"form-control\" placeholder=\"Country\" id=\"editReaderCountry\"/>\r\n");
      out.write("                            </div>\r\n");
      out.write("                        </div>\r\n");
      out.write("                        <div class=\"form-group\">\r\n");
      out.write("                            <label class=\"col-sm-4 control-label\" for=\"editReaderCity\">City</label>\r\n");
      out.write("\r\n");
      out.write("                            <div class=\"col-sm-8\">\r\n");
      out.write("                                <input type=\"text\" class=\"form-control\" placeholder=\"City\" id=\"editReaderCity\"/>\r\n");
      out.write("                            </div>\r\n");
      out.write("                        </div>\r\n");
      out.write("                        <div class=\"form-group\">\r\n");
      out.write("                            <label class=\"col-sm-4 control-label\" for=\"editReaderStreet\">Street</label>\r\n");
      out.write("\r\n");
      out.write("                            <div class=\"col-sm-8\">\r\n");
      out.write("                                <input type=\"text\" class=\"form-control\" placeholder=\"Street\" id=\"editReaderStreet\"/>\r\n");
      out.write("                            </div>\r\n");
      out.write("                        </div>\r\n");
      out.write("                        <div class=\"form-group\">\r\n");
      out.write("                            <label class=\"col-sm-4 control-label\" for=\"editReaderHouse\">House</label>\r\n");
      out.write("\r\n");
      out.write("                            <div class=\"col-sm-8\">\r\n");
      out.write("                                <input type=\"text\" class=\"form-control\" placeholder=\"House\" id=\"editReaderHouse\"/>\r\n");
      out.write("                            </div>\r\n");
      out.write("                        </div>\r\n");
      out.write("                        <div class=\"form-group\">\r\n");
      out.write("                            <label class=\"col-sm-4 control-label\" for=\"editReaderBirth\">Date of birth</label>\r\n");
      out.write("\r\n");
      out.write("                            <div class=\"col-sm-8\">\r\n");
      out.write("                                <input type=\"text\" class=\"form-control\" placeholder=\"Date of birth\" id=\"editReaderBirth\"/>\r\n");
      out.write("                            </div>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </form>\r\n");
      out.write("                </div>\r\n");
      out.write("                <div class=\"modal-footer\">\r\n");
      out.write("                    <div id=\"editReaderResult\" style=\"display: inline-block\"></div>\r\n");
      out.write("                    <button id=\"edit-reader\" type=\"button\" class=\"btn btn-default\">Edit</button>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("    ");
      out.write("\r\n");
      out.write("    <div id=\"readersRemoveModal\" class=\"modal fade\" role=\"dialog\" style=\"top: 100px; \">\r\n");
      out.write("        <div class=\"modal-dialog\">\r\n");
      out.write("            <div class=\"modal-content\">\r\n");
      out.write("                <div class=\"modal-header\">\r\n");
      out.write("                    <button type=\"button\" class=\"close\" data-dismiss=\"modal\">&times;</button>\r\n");
      out.write("                    <h4 class=\"modal-title\">Remove reader </h4>\r\n");
      out.write("                </div>\r\n");
      out.write("                <div class=\"modal-body\">\r\n");
      out.write("                    <form class=\"form-horizontal\">\r\n");
      out.write("                        <div class=\"form-group\">\r\n");
      out.write("                            <label for=\"removeReaderSearchId\" class=\"col-sm-4 control-label\">ID</label>\r\n");
      out.write("\r\n");
      out.write("                            <div class=\"col-sm-8\">\r\n");
      out.write("                                <input type=\"text\" class=\"form-control\" id=\"removeReaderSearchId\"\r\n");
      out.write("                                       placeholder=\"Enter reader id\">\r\n");
      out.write("                            </div>\r\n");
      out.write("                        </div>\r\n");
      out.write("                        <div class=\"form-group\">\r\n");
      out.write("                            <label for=\"removeReaderFName\" class=\"col-sm-4 control-label\">First name</label>\r\n");
      out.write("\r\n");
      out.write("                            <div class=\"col-sm-8\">\r\n");
      out.write("                                <input type=\"text\" class=\"form-control\" id=\"removeReaderFName\" placeholder=\"First name\">\r\n");
      out.write("                            </div>\r\n");
      out.write("                        </div>\r\n");
      out.write("                        <div class=\"form-group\">\r\n");
      out.write("                            <label for=\"removeReaderMName\" class=\"col-sm-4 control-label\">Middle name</label>\r\n");
      out.write("\r\n");
      out.write("                            <div class=\"col-sm-8\">\r\n");
      out.write("                                <input type=\"text\" class=\"form-control\" id=\"removeReaderMName\"\r\n");
      out.write("                                       placeholder=\"Middle name\">\r\n");
      out.write("                            </div>\r\n");
      out.write("                        </div>\r\n");
      out.write("                        <div class=\"form-group\">\r\n");
      out.write("                            <label class=\"col-sm-4 control-label\" for=\"removeReaderLName\">Last name</label>\r\n");
      out.write("\r\n");
      out.write("                            <div class=\"col-sm-8\">\r\n");
      out.write("                                <input type=\"text\" class=\"form-control\" placeholder=\"Last name\" id=\"removeReaderLName\"/>\r\n");
      out.write("                            </div>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </form>\r\n");
      out.write("                </div>\r\n");
      out.write("                <div class=\"modal-footer\">\r\n");
      out.write("                    <div id=\"removeReaderResult\" style=\"display: inline-block\"></div>\r\n");
      out.write("                    <button id=\"remove-reader\" type=\"button\" class=\"btn btn-default\">Remove</button>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("    ");
      out.write("\r\n");
      out.write("    <div id=\"readersDetailsModal\" class=\"modal fade\" role=\"dialog\" style=\"top: 100px; \">\r\n");
      out.write("        <div class=\"modal-dialog\">\r\n");
      out.write("            <div class=\"modal-content\">\r\n");
      out.write("                <div class=\"modal-header\">\r\n");
      out.write("                    <button type=\"button\" class=\"close\" data-dismiss=\"modal\">&times;</button>\r\n");
      out.write("                    <h4 class=\"modal-title\">Reader details</h4>\r\n");
      out.write("                </div>\r\n");
      out.write("                <div class=\"modal-body\">\r\n");
      out.write("                    <form class=\"form-horizontal\">\r\n");
      out.write("                        <div class=\"form-group\">\r\n");
      out.write("                            <label for=\"detailsReaderFName\" class=\"col-sm-4 control-label\">First name</label>\r\n");
      out.write("\r\n");
      out.write("                            <div class=\"col-sm-8\">\r\n");
      out.write("                                <input type=\"text\" class=\"form-control\" id=\"detailsReaderFName\" placeholder=\"First name\">\r\n");
      out.write("                            </div>\r\n");
      out.write("                        </div>\r\n");
      out.write("                        <div class=\"form-group\">\r\n");
      out.write("                            <label for=\"detailsReaderMName\" class=\"col-sm-4 control-label\">Middle name</label>\r\n");
      out.write("\r\n");
      out.write("                            <div class=\"col-sm-8\">\r\n");
      out.write("                                <input type=\"text\" class=\"form-control\" id=\"detailsReaderMName\" placeholder=\"Middle name\">\r\n");
      out.write("                            </div>\r\n");
      out.write("                        </div>\r\n");
      out.write("                        <div class=\"form-group\">\r\n");
      out.write("                            <label class=\"col-sm-4 control-label\" for=\"detailsReaderLName\">Last name</label>\r\n");
      out.write("\r\n");
      out.write("                            <div class=\"col-sm-8\">\r\n");
      out.write("                                <input type=\"text\" class=\"form-control\" placeholder=\"Last name\" id=\"detailsReaderLName\"/>\r\n");
      out.write("                            </div>\r\n");
      out.write("                        </div>\r\n");
      out.write("                        <div class=\"form-group\">\r\n");
      out.write("                            <label class=\"col-sm-4 control-label\" for=\"detailsReaderPhone\">Phone</label>\r\n");
      out.write("\r\n");
      out.write("                            <div class=\"col-sm-8\">\r\n");
      out.write("                                <input type=\"text\" class=\"form-control\" placeholder=\"Phone\" id=\"detailsReaderPhone\"/>\r\n");
      out.write("                            </div>\r\n");
      out.write("                        </div>\r\n");
      out.write("                        <div class=\"form-group\">\r\n");
      out.write("                            <label class=\"col-sm-4 control-label\" for=\"detailsReaderCountry\">Country</label>\r\n");
      out.write("\r\n");
      out.write("                            <div class=\"col-sm-8\">\r\n");
      out.write("                                <input type=\"text\" class=\"form-control\" placeholder=\"Country\" id=\"detailsReaderCountry\"/>\r\n");
      out.write("                            </div>\r\n");
      out.write("                        </div>\r\n");
      out.write("                        <div class=\"form-group\">\r\n");
      out.write("                            <label class=\"col-sm-4 control-label\" for=\"detailsReaderCity\">City</label>\r\n");
      out.write("\r\n");
      out.write("                            <div class=\"col-sm-8\">\r\n");
      out.write("                                <input type=\"text\" class=\"form-control\" placeholder=\"City\" id=\"detailsReaderCity\"/>\r\n");
      out.write("                            </div>\r\n");
      out.write("                        </div>\r\n");
      out.write("                        <div class=\"form-group\">\r\n");
      out.write("                            <label class=\"col-sm-4 control-label\" for=\"detailsReaderStreet\">Street</label>\r\n");
      out.write("\r\n");
      out.write("                            <div class=\"col-sm-8\">\r\n");
      out.write("                                <input type=\"text\" class=\"form-control\" placeholder=\"Street\" id=\"detailsReaderStreet\"/>\r\n");
      out.write("                            </div>\r\n");
      out.write("                        </div>\r\n");
      out.write("                        <div class=\"form-group\">\r\n");
      out.write("                            <label class=\"col-sm-4 control-label\" for=\"detailsReaderHouse\">House</label>\r\n");
      out.write("\r\n");
      out.write("                            <div class=\"col-sm-8\">\r\n");
      out.write("                                <input type=\"text\" class=\"form-control\" placeholder=\"House\" id=\"detailsReaderHouse\"/>\r\n");
      out.write("                            </div>\r\n");
      out.write("                        </div>\r\n");
      out.write("                        <div class=\"form-group\">\r\n");
      out.write("                            <label class=\"col-sm-4 control-label\" for=\"detailsReaderBirth\">Date of birth</label>\r\n");
      out.write("\r\n");
      out.write("                            <div class=\"col-sm-8\">\r\n");
      out.write("                                <input type=\"text\" class=\"form-control\" placeholder=\"Date of birth\" id=\"detailsReaderBirth\"/>\r\n");
      out.write("                            </div>\r\n");
      out.write("                        </div>\r\n");
      out.write("                        <div class=\"form-group\">\r\n");
      out.write("                            <label class=\"col-sm-4 control-label\" for=\"detailsReaderRegistrationDate\">Registration date</label>\r\n");
      out.write("\r\n");
      out.write("                            <div class=\"col-sm-8\">\r\n");
      out.write("                                <input type=\"text\" class=\"form-control\" placeholder=\"Registration date\" id=\"detailsReaderRegistrationDate\"/>\r\n");
      out.write("                            </div>\r\n");
      out.write("                        </div>\r\n");
      out.write("                        <div class=\"modal-footer\">\r\n");
      out.write("                            <div></div>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </form>\r\n");
      out.write("                </div>\r\n");
      out.write("\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("    ");
      out.write("\r\n");
      out.write("    <div id=\"readersStatus\"></div>\r\n");
      out.write("</section>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!--contacts section-->\r\n");
      out.write("<section id=\"contacts-section\" style=\"text-align: center; height: 700px; \">\r\n");
      out.write("    <h3>Contacts</h3>\r\n");
      out.write("\r\n");
      out.write("    <div class=\"form-horizontal \">\r\n");
      out.write("        <div class=\"form-group\">\r\n");
      out.write("            <div class=\"col-sm-2\"><h4> Phone: </h4></div>\r\n");
      out.write("            <div class=\"col-sm-10\"><h4>00000000000</h4></div>\r\n");
      out.write("        </div>\r\n");
      out.write("        <div class=\"form-group\">\r\n");
      out.write("            <div class=\"col-sm-2\"><h4>Address: </h4></div>\r\n");
      out.write("            <div class=\"col-sm-10\"><h4>Ukraine, Cherkassy</h4></div>\r\n");
      out.write("        </div>\r\n");
      out.write("        <div class=\"form-group\">\r\n");
      out.write("            <div class=\"col-sm-2\"><h4>Email: </h4></div>\r\n");
      out.write("            <div class=\"col-sm-10\"><h4>libCherk@example.com</h4></div>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("</section>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<script src=\"resources/js/main.js\"></script>\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
      out.write("\r\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_c_005furl_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:url
    org.apache.taglibs.standard.tag.rt.core.UrlTag _jspx_th_c_005furl_005f0 = (org.apache.taglibs.standard.tag.rt.core.UrlTag) _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.UrlTag.class);
    _jspx_th_c_005furl_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005furl_005f0.setParent(null);
    // /index.jsp(7,17) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005furl_005f0.setValue("resources/js/jquery-1.11.3.js");
    int _jspx_eval_c_005furl_005f0 = _jspx_th_c_005furl_005f0.doStartTag();
    if (_jspx_th_c_005furl_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005furl_005f1(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:url
    org.apache.taglibs.standard.tag.rt.core.UrlTag _jspx_th_c_005furl_005f1 = (org.apache.taglibs.standard.tag.rt.core.UrlTag) _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.UrlTag.class);
    _jspx_th_c_005furl_005f1.setPageContext(_jspx_page_context);
    _jspx_th_c_005furl_005f1.setParent(null);
    // /index.jsp(8,17) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005furl_005f1.setValue("resources/js/bootstrap.js");
    int _jspx_eval_c_005furl_005f1 = _jspx_th_c_005furl_005f1.doStartTag();
    if (_jspx_th_c_005furl_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f1);
    return false;
  }

  private boolean _jspx_meth_c_005furl_005f2(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:url
    org.apache.taglibs.standard.tag.rt.core.UrlTag _jspx_th_c_005furl_005f2 = (org.apache.taglibs.standard.tag.rt.core.UrlTag) _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.UrlTag.class);
    _jspx_th_c_005furl_005f2.setPageContext(_jspx_page_context);
    _jspx_th_c_005furl_005f2.setParent(null);
    // /index.jsp(10,33) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005furl_005f2.setValue("resources/css/bootstrap.css");
    int _jspx_eval_c_005furl_005f2 = _jspx_th_c_005furl_005f2.doStartTag();
    if (_jspx_th_c_005furl_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f2);
    return false;
  }

  private boolean _jspx_meth_c_005furl_005f3(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:url
    org.apache.taglibs.standard.tag.rt.core.UrlTag _jspx_th_c_005furl_005f3 = (org.apache.taglibs.standard.tag.rt.core.UrlTag) _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.UrlTag.class);
    _jspx_th_c_005furl_005f3.setPageContext(_jspx_page_context);
    _jspx_th_c_005furl_005f3.setParent(null);
    // /index.jsp(11,33) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005furl_005f3.setValue("resources/css/index.css");
    int _jspx_eval_c_005furl_005f3 = _jspx_th_c_005furl_005f3.doStartTag();
    if (_jspx_th_c_005furl_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f3);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f3);
    return false;
  }
}
