<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../include/header.jsp" />

<!-- Css Styles -->

<link
  rel="stylesheet"
  href="../../../pub/css/products/productsstyle.css"
  type="text/css"
/>

<!-- Shopping Cart Section Begin -->
<section class="shopping-cart spad">
  <c:if test="${not empty success}">
    <div class="row justify-content-center">
      <div class="col-6 text-center">
        <div class="alert alert-success" role="alert">${success}</div>
      </div>
    </div>
  </c:if>
  <div class="container">
    <div class="row">
      <div class="col-lg-8">
        <div class="shopping__cart__table">
          <table>
            <thead>
              <tr>
                <th>Product</th>
                <th>Quantity</th>
                <th>Total</th>
                <th></th>
              </tr>
            </thead>
            <tbody>
              <c:forEach items="${cartproducts}" var="cart">
                <tr>
                  <form
                    action="/order/updateCartItem/${cart.id}"
                    method="post"
                    id="cartForm"
                  >
                    <td class="product__cart__item">
                      <div class="product__cart__item__pic">
                        <img
                          src="../../..${cart.product.mainProduct.productImage}"
                        />
                        <div class="product__cart__item__text">
                          <h6>${cart.product.mainProduct.productName}</h6>
                          <h6>
                            price: ${cart.product.mainProduct.productPrice}$
                          </h6>
                          <h6>
                            Size:
                            <select id="sizeSelect" onchange="getSize()">
                              <option value="${product.size}" selected>
                                ${cart.product.size}
                              </option>
                              <c:choose>
                                <c:when test="${not empty cart.product.size}">
                                  <c:forEach
                                    var="size"
                                    items="${['S', 'M', 'L', 'XL']}"
                                  >
                                    <c:if
                                      test="${!size.equals(cart.product.size)}"
                                    >
                                      <option value="${size}">${size}</option>
                                    </c:if>
                                  </c:forEach>
                                </c:when>
                                <c:otherwise>
                                  <option disabled>Select Size</option>
                                  <c:forEach
                                    var="size"
                                    items="${['S', 'M', 'L', 'XL']}"
                                  >
                                    <option value="${size}">${size}</option>
                                  </c:forEach>
                                </c:otherwise>
                              </c:choose>
                            </select>
                          </h6>
                        </div>
                      </div>
                    </td>
                    <td class="quantity__item">
                      <div class="quantity">
                        <div class="pro-qty-2">
                          <input
                            type="number"
                            name="quantity"
                            value="${cart.quantity}"
                            min="1"
                            max="10"
                          />
                        </div>
                      </div>
                    </td>
                    <td class="cart__price">${cart.subTotal}$</td>
                    <input
                      type="hidden"
                      id="selectedSize"
                      name="selectedSize"
                      value="${cart.product.size}"
                    />
                    <td>
                      <button type="submit" class="small-rounded-button">
                        Update
                      </button>
                    </td>
                  </form>
                  <td class="cart__close">
                    <a
                      href="/order/deleteCartItem/${cart.id}"
                      class="fa fa-close"
                    ></a>
                  </td>
                </tr>
              </c:forEach>
            </tbody>
          </table>
        </div>

        <div class="col-lg-6 col-md-6 col-sm-6">
          <div class="continue__btn">
            <a href="/product/productlist" class="primary-btn"
              >Continue Shopping</a
            >
          </div>
        </div>
      </div>
      <div class="col-lg-4">
        <div class="cart__total">
          <h6>Cart total</h6>
          <ul>
            <li>Subtotal <span>$ ${subtotal}</span></li>
            <li>Tax <span>$ ${tax}</span></li>
            <li>Total <span>$ ${total}</span></li>
          </ul>
          <a href="/order/proceedCheckout" class="primary-btn"
            >Proceed to checkout</a
          >
        </div>
      </div>
    </div>
  </div>
</section>
<script src="../../../pub/js/product.js"></script>
<jsp:include page="../include/footer.jsp" />
