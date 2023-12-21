<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../include/header.jsp" />

<!-- Css Styles -->

<link
  rel="stylesheet"
  href="../../../pub/css/products/productsstyle.css"
  type="text/css"
/>

<link
  rel="stylesheet"
  href="../../../pub/css/products/elegant-icons.css"
  type="text/css"
/>
<section class="shop spad">
  <div class="container">
    <div class="row">
      <div class="col-lg-3">
        <div class="shop__sidebar">
          <div class="shop__sidebar__search">
            <form action="/product/search">
              <input type="text" name="itemName" placeholder="Search..." /
              value="${itemName}" style="color: black;">
              <button type="submit"><span class="icon_search"></span></button>
            </form>
          </div>
          <div class="btn-group">
            <button
              type="button"
              class="btn btn-secondary dropdown-toggle"
              data-toggle="dropdown"
              aria-haspopup="true"
              aria-expanded="false"
              style="background-color: #f9f9f9; color: black; font-weight: bold"
            >
              Shopping Categories
            </button>
            <div class="dropdown-menu dropdown-menu-right">
              <button
                class="dropdown-item"
                type="button"
                data-value="men"
                onclick="redirectToController(this)"
              >
                Men Wear
              </button>
              <button
                class="dropdown-item"
                type="button"
                data-value="women"
                onclick="redirectToController(this)"
              >
                Women wear
              </button>
              <button
                class="dropdown-item"
                type="button"
                data-value="boys"
                onclick="redirectToController(this)"
              >
                Boys Wear
              </button>
              <button
                class="dropdown-item"
                type="button"
                data-value="girls"
                onclick="redirectToController(this)"
              >
                Girls Wear
              </button>
              <button
                class="dropdown-item"
                type="button"
                data-value="babies"
                onclick="redirectToController(this)"
              >
                Babies and Toddlers
              </button>
            </div>
          </div>
        </div>
      </div>
      <div class="col-lg-9">
        <div class="shop__product__option">
          <div class="row">
            <div class="col-lg-6 col-md-6 col-sm-6">
              <div class="shop__product__option__right">
                <div class="btn-group">
                  <button
                    type="button"
                    class="btn btn-secondary dropdown-toggle"
                    data-toggle="dropdown"
                    aria-haspopup="true"
                    aria-expanded="false"
                    style="
                      background-color: #f9f9f9;
                      color: black;
                      font-weight: bold;
                    "
                  >
                    Sort by Price
                  </button>
                  <div class="dropdown-menu dropdown-menu-right">
                    <button
                      class="dropdown-item"
                      type="button"
                      data-value="asc"
                      onclick="redirectToController(this)"
                    >
                      Low to High
                    </button>
                    <button
                      class="dropdown-item"
                      type="button"
                      data-value="desc"
                      onclick="redirectToController(this)"
                    >
                      High to Low
                    </button>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <section id="product1" class="section-p1">
          <div class="pro-container">
            <c:forEach items="${productlist}" var="product">
              <div class="pro" onclick="redirectToDetailsPage('${product.id}')">
                <img src="../../..${product.productImage}" />
                <div class="des">
                  <h5><b>${product.productName}</b></h5>
                  <div class="star">
                    <c:forEach begin="1" end="${product.rating}">
                      <i class="fas fa-star"></i>
                    </c:forEach>
                  </div>
                  <h4>${product.productPrice}$</h4>
                </div>
                <a href="#"><i class="fas fa-shopping-cart cart"></i></a>
              </div>
            </c:forEach>
          </div>
        </section>
        <div class="row">
          <div class="col-lg-12">
            <div class="product__pagination">
              <a class="active" href="#">1</a>
              <a href="#">2</a>
              <a href="#">3</a>
              <span>...</span>
              <a href="#">21</a>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</section>
<script
  src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
  integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
  crossorigin="anonymous"
></script>
<script
  src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"
  integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx"
  crossorigin="anonymous"
></script>
<script src="../../../pub/js/product.js"></script>
<jsp:include page="../include/footer.jsp" />
