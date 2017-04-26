<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@include file="header.jsp"%>
<%@include file="cart.jsp"%>



<!DOCTYPE html>
<html >
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Search</title>
    <script src="<%=path %>/js/jquery-1.12.2.min.js"></script>
    <script src="<%=path %>/bootstrap/js/bootstrap.min.js"></script>
    <script src="<%=path %>/js/cart.js"></script>
</head>
<body>
<div id="templatemo_container">
    <div id="templatemo_menu">
        <ul>
            <li><a href="<%=basePath%>index">Home</a></li>
            <li><a href="<%=basePath%>403.jsp">admin</a></li>
            <li>
                <a><form class="search-form" role="search" method="get" action="<%=path %>/item/action_search">
                    <button hidden type="submit"></button>
                    <input type="text" name="searchName" id="searchName" class="form-control" placeholder="Search Books" style="width:100%">
                </form></a>


            </li>
            <s:if test="#session.user == null">
                <li><a style="float: right" href="<%=basePath%>register">Register</a></li>
                <li><a style="float: right" href="<%=path %>/auth/action_login">Login</a></li>
            </s:if>
            <s:else>
                <li><a style="float: right; color:red" href="<%=path%>/auth/action_logout">exit</a></li>
                <li><a  style="float: right" href="<%=path %>/order/action_allOrders">Welcome:
                    <s:property value="#session.user.username"/></a></li>
            </s:else>
            <li><a style="float: right" href="#" onclick="show_cart()">Cart</a></li>
        </ul>
    </div> <!-- end of menu -->

    <div id="templatemo_header">
        <div id="templatemo_special_offers">

        </div>


        <div id="templatemo_new_books">
            <ul>

            </ul>
        </div>
    </div> <!-- end of header -->



    <div id="templatemo_content">

        <div id="templatemo_content_left">
            <div class="templatemo_content_left_section">
                <h1>Genre</h1>
                <ul>
                    <li><a href="subpage.html">Maths</a></li>
                    <li><a href="subpage.html">English</a></li>
                    <li><a href="subpage.html">Computer Science</a></li>
                </ul>
            </div>



        </div> <!-- end of content left -->

        <div id="templatemo_content_right">
            <s:if test="#books==null">
             <h1>No Book Found.</h1>
            </s:if>
            <s:else>


                <s:iterator value="#books" status="st">
                    <div class="templatemo_product_box">

                        <h1>Book:<s:property value="bookname"/><span> Author:<s:property value="author"/></span></h1>
                        <img src="<%=path%>/<s:property value="image"/>" alt="image" />
                        <div class="product_info">
                            <h3>Price:<s:property value="price/100.0"/></h3>
                            <div class="detail_button"><a href="<%=path%>/item/action_showBookByid?bookid=<s:property value="bookid"/>">Detail</a></div>
                        </div>
                        <div class="cleaner">&nbsp;</div>
                    </div>

                    <div class="<s:if test="#st.even">cleaner_with_height</s:if><s:else>cleaner_with_width</s:else>">&nbsp;</div>
                </s:iterator>



            </s:else>


        </div> <!-- end of content right -->

        <div class="cleaner_with_height">&nbsp;</div>
    </div> <!-- end of content -->

    <div id="templatemo_footer">

        <a href="index">Home</a> | <a href="admin.jsp">Admin</a><br />
        <a>Copyright © 2017 <strong>Zhang Ziyang</strong></a>
    </div>
    <!-- end of footer -->
</div> <!-- end of container -->
</body>
</html>