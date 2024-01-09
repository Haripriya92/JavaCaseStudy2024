<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../include/header.jsp" />

<!-- Css Styles -->

<link
  rel="stylesheet"
  href="../../../pub/css/products/productsstyle.css"
  type="text/css"
/>
<link rel="stylesheet" href="../../../pub/css/card.css" type="text/css" />

<!-- Checkout Section Begin -->
<section class="checkout spad">
  <div class="container">
    <div class="col-md-7 col-sm-12 p-0 box">
      <div class="card rounded-0 border-0 card2" id="paypage">
        <div class="form-card">
          <h2 id="heading2" class="text-danger">Payment Method</h2>
          <div class="radio-group">
            <div class="radio" data-value="credit">
              <img
                src="https://i.imgur.com/28akQFX.jpg"
                width="200px"
                height="60px"
              />
            </div>
            <div class="radio" data-value="paypal">
              <img
                src="https://i.imgur.com/5QFsx7K.jpg"
                width="200px"
                height="60px"
              />
            </div>
            <br />
          </div>
          <div class="credit_card">
            <label class="pay">Name on Card</label>
            <input type="text" name="holdername" placeholder="Name" />
            <div class="row">
              <div class="col-8 col-md-6">
                <label class="pay">Card Number</label>
                <input
                  type="text"
                  name="cardno"
                  id="cr_no"
                  placeholder="0000-0000-0000-0000"
                  minlength="19"
                  maxlength="19"
                />
              </div>
              <div class="col-4 col-md-6">
                <label class="pay">CVV</label>
                <input
                  type="password"
                  name="cvvno"
                  placeholder="&#9679;&#9679;&#9679;"
                  class="placeicon"
                  minlength="3"
                  maxlength="3"
                />
              </div>
            </div>
            <div class="row">
              <div class="col-md-12">
                <label class="pay">Expiration Date</label>
              </div>
              <div class="col-md-12">
                <input
                  type="text"
                  name="expdate"
                  id="exp"
                  placeholder="MM/YY"
                  minlength="5"
                  maxlength="5"
                />
              </div>
            </div>
            <div class="checkout__input__checkbox">
              <label for="payment">
                Add Card for Payment
                <input
                  type="checkbox"
                  id="payment"
                  name="addCardForPayment"
                  value="true"
                />
                <span class="checkmark"></span>
              </label>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="checkout__form">
      <form action="/order/placeOrder">
        <div class="row">
          <div class="col-lg-8 col-md-6">
            <h6 class="checkout__title" style="margin-top: 45px">
              Billing Details
            </h6>
            <div class="row">
              <c:if test="${errors.hasFieldErrors('firstName')}">
                        <div style="color:red">
                            <c:forEach items="${errors.getFieldErrors('firstName')}" var="error">
                                ${error.defaultMessage}<br>
                            </c:forEach>
                        </div>
                    </c:if>
              <div class="col-lg-6">
                <div class="checkout__input">
                  <p>First Name<span>*</span></p>
                  <input
                    type="text"
                    name="firstName"
                    value="${user.firstName}"
                  />
                </div>
              </div>
              <c:if test="${errors.hasFieldErrors('lastName')}">
                        <div style="color:red">
                            <c:forEach items="${errors.getFieldErrors('lastName')}" var="error">
                                ${error.defaultMessage}<br>
                            </c:forEach>
                        </div>
                    </c:if>
              <div class="col-lg-6">
                <div class="checkout__input">
                  <p>Last Name<span>*</span></p>
                  <input type="text" name="lastName" value="${user.lastName}" />
                </div>
              </div>
            </div>
            <c:if test="${errors.hasFieldErrors('country')}">
                        <div style="color:red">
                            <c:forEach items="${errors.getFieldErrors('country')}" var="error">
                                ${error.defaultMessage}<br>
                            </c:forEach>
                        </div>
                    </c:if>
            <div class="checkout__input">
              <p>Country<span>*</span></p>
              <input type="text" name="country" value="${user.country}" />
            </div>
            <c:if test="${errors.hasFieldErrors('address1')}">
                        <div style="color:red">
                            <c:forEach items="${errors.getFieldErrors('address1')}" var="error">
                                ${error.defaultMessage}<br>
                            </c:forEach>
                        </div>
                    </c:if>
            <div class="checkout__input">
              <p>Address<span>*</span></p>
              <input
                type="text"
                placeholder="Street Address"
                name="address1"
                value="${user.address1}"
                class="checkout__input__add"
              />
              <c:if test="${errors.hasFieldErrors('address2')}">
                        <div style="color:red">
                            <c:forEach items="${errors.getFieldErrors('address2')}" var="error">
                                ${error.defaultMessage}<br>
                            </c:forEach>
                        </div>
                    </c:if>
              <input
                type="text"
                name="address2"
                value="${user.address2}"
                placeholder="Apartment, suite, unite ect (optinal)"
              />
            </div>
            
            <c:if test="${errors.hasFieldErrors('city')}">
              <div style="color:red">
                  <c:forEach items="${errors.getFieldErrors('city')}" var="error">
                      ${error.defaultMessage}<br>
                  </c:forEach>
              </div>
          </c:if>
            <div class="checkout__input">
              <p>Town/City<span>*</span></p>
              <input type="text" name="city" value="${user.city}" />
            </div>
            <c:if test="${errors.hasFieldErrors('state')}">
              <div style="color:red">
                  <c:forEach items="${errors.getFieldErrors('state')}" var="error">
                      ${error.defaultMessage}<br>
                  </c:forEach>
              </div>
          </c:if>
            <div class="checkout__input">
              <p>State<span>*</span></p>
              <input type="text" name="state" value="${user.state}" />
            </div>
            <c:if test="${errors.hasFieldErrors('zip')}">
              <div style="color:red">
                  <c:forEach items="${errors.getFieldErrors('zip')}" var="error">
                      ${error.defaultMessage}<br>
                  </c:forEach>
              </div>
          </c:if>
            <div class="checkout__input">
              <p>Postcode / ZIP<span>*</span></p>
              <input type="text" name="zip" value="${user.zip}" />
            </div>
            <div class="row">
              <c:if test="${errors.hasFieldErrors('phone')}">
                        <div style="color:red">
                            <c:forEach items="${errors.getFieldErrors('phone')}" var="error">
                                ${error.defaultMessage}<br>
                            </c:forEach>
                        </div>
                    </c:if>
              <div class="col-lg-6">
                <div class="checkout__input">
                  <p>Phone<span>*</span></p>
                  <input type="text" name="phone" value="${user.phone}" />
                </div>
              </div>
              <c:if test="${errors.hasFieldErrors('email')}">
                <div style="color:red">
                    <c:forEach items="${errors.getFieldErrors('email')}" var="error">
                        ${error.defaultMessage}<br>
                    </c:forEach>
                </div>
            </c:if>
              <div class="col-lg-6">
                <div class="checkout__input">
                  <p>Email<span>*</span></p>
                  <input type="text" name="email" value="${user.email}" />
                </div>
              </div>
            </div>
            <div class="checkout__input__checkbox">
              <label for="diff-acc">
                  Add as default address
                  <input
                      type="checkbox"
                      id="diff-acc"
                      name="defaultAddress"
                      value="true"
                  />
                  <span class="checkmark"></span>
              </label>
          </div>
            <div class="checkout__input">
              <p>Order notes</p>
              <input
                type="text"
                placeholder="Notes about your order, e.g. special notes for delivery."
                name="ordernotes"
              />
            </div>
          </div>
          <div class="col-lg-4 col-md-6">
            <div class="checkout__order">
              <h4 class="order__title">Your order</h4>
              <div class="checkout__order__products">
                Product <span>Total</span>
              </div>
              <ol class="checkout__total__products">
                <c:forEach items="${cartproducts}" var="cart">
                  <li>
                    <span>
                      ${cart.product.mainProduct.productName}
                      (${cart.product.size})</span
                    ><span>$ ${cart.subTotal}</span>
                  </li>
                </c:forEach>
              </ol>
              <ul class="checkout__total__all">
                <li>Subtotal <span>$ ${subtotal}</span></li>
                <li>Tax <span>$ ${tax}</span></li>
                <li>Total <span>$ ${total}</span></li>
              </ul>
              <a href="">
                <button type="submit" class="site-btn">PLACE ORDER</button>
              </a>
            </div>
          </div>
        </div>
      </form>
    </div>
  </div>
</section>
<!-- Checkout Section End -->
<jsp:include page="../include/footer.jsp" />
