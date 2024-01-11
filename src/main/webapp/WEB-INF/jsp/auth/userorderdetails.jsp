<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../include/header.jsp" />
<style>
  .product__cart__item {
    display: flex;
    align-items: center; /* Align items vertically in the flex container */
  }

  .product__cart__item__pic {
    display: flex; /* Nested flex container for images */
    gap: 10px; /* Adjust the gap between images as needed */
  }

  .product__cart__item__pic img {
    width: 50px;
    height: auto;
  }
</style>
<div class="container">
  <div class="row">
    <div class="col-lg-8">
      <div class="shopping__cart__table">
        <table>
          <thead>
            <tr>
              <th>Product</th>
              <th>Order Number</th>
              <th>Order Date</th>
              <th>Status&nbsp;&nbsp;</th>
              <th>Address</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach items="${orderlist}" var="order">
              <tr>
                <td class="product__cart__item">
                  <div class="product__cart__item__pic">
                    <c:forEach items="${order.orderDetail}" var="orderDetail">
                      <img
                        src="../../..${orderDetail.productDetail.mainProduct.productImage}"
                      />
                    </c:forEach>
                  </div>
                </td>
                <td class="cart__price">${order.orderNumber}</td>
                <td class="cart__price">${order.orderDate}</td>
                <td class="cart__price">
                  ${order.orderStatus}&nbsp;&nbsp;&nbsp;&nbsp;
                </td>
                <td class="cart__price">${order.address}</td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</div>
<!-- Checkout Section End -->
<jsp:include page="../include/footer.jsp" />
