<%--
  Created by IntelliJ IDEA.
  User: zzy
  Date: 2017/4/19
  Time: 19:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@include file="header.jsp"%>
<%@include file="cart.jsp"%>

<html>
<head>
    <title>Show Orders</title>
    <script src="<%=path %>/js/jquery-1.12.2.min.js"></script>
    <script src="<%=path %>/bootstrap/js/bootstrap.min.js"></script>
    <script src="<%=path %>/js/cart.js"></script>
    <script type="text/javascript">

        function showTip(tip,type){
            var $tip = $('#tip');
            $tip.attr('class', 'alert alert-' + type).text(tip).css('margin-left', - $tip.outerWidth() / 2).fadeIn(500).delay(1000).fadeOut(500);
        }

        function destroyOrder (index, orderid) {

            $.post({
                url:base_url+"admin/orders_destroyOrder",
                data: {
                    'orderid':orderid,
                },
                success:function(msg){
                    console.log(msg);
                    if(msg.success)
                    {
                        showTip('success', 'success');
                        $('.order:eq('+index+')').css('display','none');
                    }
                    else
                    {
                        showTip('You must be an admin! ','danger');
                    }
                }
            });

            //showTip('some thing goes wrong','danger');
        }

    </script>
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
            <h1>&nbsp&nbsp>&nbsp&nbspmy orders</h1>


            <s:if test="#orders == null">
                <h1>Please Login</h1>
            </s:if>

            <s:else>

            <s:iterator value="#orders" var = "od" status="order">
            <div class="order" style="width:100%">
            <ul>
                    <table class="table table-condensed">
                        <tr>
                            <th field="Order id" width="10%">Order id</th>
                            <th field="User id:" width="10%">User id</th>
                            <th field="State" width="5%">State</th>
                        </tr>
                            <td><s:property value="orderid"/></td>
                            <td><s:property value="user.userid"/></td>
                            <td>
                                <s:if test="#od.pucharsed=='N'">Not Pucharsed</s:if>
                                <s:elseif test="#od.pucharsed=='C'">Canceled</s:elseif>
                                <s:else>Pucharsed</s:else>
                            </td>
                        </tr>


                    </table>
                    </ul>
                <table class="table table-condensed">
                    <tr>
                        <th field="title" width="20%">bookname</th>
                        <th field="author" width="10%">author</th>
                        <th field="amount" width="10%">amount</th>
                        <th field="unit_price" width="15%">unit Price</th>
                        <th field="total_price" width="15%">Price</th>
                    </tr>
                    <s:iterator value="books" var="booksorder" status="book">
                        <tr>
                            <td><s:property value="#booksorder.book.bookname"/></td>
                            <td><s:property value="#booksorder.book.author"/></td>
                            <td>
		       				<span class = "amount">
		       					<s:property value="#booksorder.amount"/>
		       				</span>
                            </td>

                            <td><s:property value="#booksorder.book.price/100.0"/></td>
                            <td class="price"><s:property value="#booksorder.book.price*#booksorder.amount/100.0"/></td>
                            <td>

                            </td>
                        </tr>
                    </s:iterator>
                </table>
                <s:if test="#od.pucharsed == 'N'">
                    <div class="detail_button"><a href="<%=path%>/order/action_cancelOrder?orderid=<s:property value="#od.orderid"/>">Cancle Order</a>
                    </div>
                    <div class="buy_now_button"><a href="<%=path%>/order/action_confirmOrder?orderid=<s:property value="#od.orderid"/>">Confirm Order</a>
                    </div>
            </s:if>
                <s:else>
                        <button class="btn" onclick="destroyOrder(<s:property value='#order.index'/>, <s:property value='orderid'/>)" >Delete Order</button>
                </s:else>
            </div>
            </s:iterator>
            </s:else>
            <div id="tip"></div>

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
