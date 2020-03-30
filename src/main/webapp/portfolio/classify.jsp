<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html>
    <head>
        <!-- head -->
        <%@include file="/WEB-INF/jsp/include/head.jspf"  %>

        <script>
            $(document).ready(function () {
                $("#myTable").on("click", "tr", function () {
                    var id = $(this).find('td').eq(0).text().trim();
                    //console.log(id);
                    $.get("/SpringMVC/mvc/portfolio/classify/" + id, function (data, status) {
                        console.log(JSON.stringify(data));
                        $("#myform").find("#id").val(data.id);
                        $("#myform").find("#name").val(data.name);
                        $("#myform").find("#transaction").prop('checked', data.transaction);
                    });
                });
                $("#add").on("click", function () {
                    var jsonObj = $('#myform').serializeObject();
                    var jsonStr = JSON.stringify(jsonObj);
                    console.log(jsonStr);
                    $.ajax({
                        url: "/SpringMVC/mvc/portfolio/classify/",
                        type: "POST",
                        contentType: "application/json; charset=utf-8",
                        data: jsonStr,
                        async: true,
                        cache: false,
                        processData: false,
                        success: function (resposeJsonObject) {
                            table_list();
                        }
                    });
                });
                $("#upt").on("click", function () {
                    var jsonObj = $('#myform').serializeObject();
                    var jsonStr = JSON.stringify(jsonObj);
                    console.log(jsonStr);
                    $.ajax({
                        url: "/SpringMVC/mvc/portfolio/classify/" + jsonObj.id,
                        type: "PUT",
                        contentType: "application/json; charset=utf-8",
                        data: jsonStr,
                        async: true, 
                        cache: false,
                        processData: false,
                        success: function (resposeJsonObject) {
                            table_list();
                        }
                    });
                });
                $("#del").on("click", function () {
                    var id = $("#myform").find("#id").val();
                    $.ajax({
                        url: "/SpringMVC/mvc/portfolio/classify/" + id,
                        type: "DELETE",
                        async: true,
                        cache: false,
                        processData: false,
                        success: function (resposeJsonObject) {
                            table_list();
                        }
                    });
                });
                
                // 資料列表
                table_list();
            });

            function table_list() {
                $.get("/SpringMVC/mvc/portfolio/classify/", function (datas, status) {
                    console.log("Datas: " + datas);
                    $("#myTable tbody > tr").remove();
                    $.each(datas, function (i, item) {
                        var html = '<tr><td>{0}</td><td>{1}</td><td>{2}</td></tr>';
                        $('#myTable').append(String.format(html,
                                item.id,
                                item.name,
                                item.transaction
                                ));
                    });
                });
            }
        </script>
    </head>
    <body>
        <div id="layout">
            <!-- Menu toggle -->
            <%@include file="/WEB-INF/jsp/include/toggle.jspf"  %>

            <!-- Menu -->
            <%@include file="/WEB-INF/jsp/include/menu.jspf"  %>

            <div id="main">
                <div class="header">
                    <h1>Classify</h1>
                    <h2>商品分類</h2>
                </div>
                <table>
                    <td valign="top">
                        <div class="content">
                            <form id="myform" class="pure-form">
                                <fieldset>
                                    <legend> <h2 class="content-subhead">資料維護</h2></legend>
                                    <input id="id" vslue="0" name="id" placeholder="ID" readonly="true"/><p />
                                    <input id="name" name="name" placeholder="分類名稱"/><p />
                                    <input id="transaction" name="transaction" type="checkbox" /> Transaction<p />
                                    <button id="add" type="button" class="pure-button pure-button-primary">新增</button>
                                    <button id="upt" type="button" class="pure-button pure-button-primary">修改</button>
                                    <button id="del" type="button" class="pure-button pure-button-primary">刪除</button>
                                </fieldset>
                            </form>
                        </div>
                    </td>    
                    <td valign="top">    
                        <div class="content">
                            <form class="pure-form">
                                <fieldset>
                                    <legend><h2 class="content-subhead">資料列表</h2></legend>
                                    <table id="myTable" class="pure-table pure-table-bordered">
                                        <thead>
                                            <tr>
                                                <th>id</th>
                                                <th>name</th>
                                                <th>transaction</th>
                                            </tr>
                                        </thead>

                                        <tbody>

                                        </tbody>
                                    </table> 
                                </fieldset>
                            </form>
                        </div>    
                    </td>
                </table>


            </div>
        </div>
        <!-- Foot -->
        <%@include file="/WEB-INF/jsp/include/foot.jspf"  %>   
    </body>
</html>
