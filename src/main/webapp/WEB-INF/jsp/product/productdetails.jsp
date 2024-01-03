<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../include/header.jsp" />
<section id="prodetails" class="section-p1">
  <div class="single-pro-image">
    <img
      src="../../..${selectedProduct.productImage}"
      width="100%"
      id="MainImg"
      alt=""
    />
  </div>
  <div class="single-pro-details">
    <h4>${selectedProduct.productName}</h4>
    <h2>${selectedProduct.productPrice}$</h2>
    <form
      action="/order/addToCart/${selectedProduct.id}"
      method="post"
      id="cartForm"
    >
      <span id="sizeError" style="color: red"></span>
      <select id="sizeSelect" onchange="getSize()">
        <option>Select Size</option>
        <option>S</option>
        <option>M</option>
        <option>L</option>
        <option>XL</option>
      </select>
      <input type="hidden" id="selectedSize" name="selectedSize" />
      <input type="number" name="quantity" value="1" min="1" max="10" />
      <button type="submit" class="normal" onclick="validateForm(event)">
        Add To Cart
      </button>
    </form>
    <h4>Product Details</h4>
    <span>${selectedProduct.productDescription}</span>
  </div>
</section>
<section id="product1" class="section-p1">
  <h2>Featured Products</h2>
  <p>Summer Collection New Modern Design</p>
  <div class="pro-container">
    <div class="pro">
      <img src="../../../pub/images/product/f1.jpg" />
      <div class="des">
        <span>adidas</span>
        <h5>Cartoon Astronaut T-Shirts</h5>
        <div class="star">
          <i class="fas fa-star"></i>
          <i class="fas fa-star"></i>
          <i class="fas fa-star"></i>
          <i class="fas fa-star"></i>
          <i class="fas fa-star"></i>
        </div>
        <h4>$78</h4>
      </div>
      <a href="#"><i class="fas fa-shopping-cart cart"></i></a>
    </div>
    <div class="pro">
      <img src="../../../pub/images/product/f2.jpg" />
      <div class="des">
        <span>adidas</span>
        <h5>Cartoon Astronaut T-Shirts</h5>
        <div class="star">
          <i class="fas fa-star"></i>
          <i class="fas fa-star"></i>
          <i class="fas fa-star"></i>
          <i class="fas fa-star"></i>
          <i class="fas fa-star"></i>
        </div>
        <h4>$78</h4>
      </div>
      <a href="#"><i class="fas fa-shopping-cart cart"></i></a>
    </div>
    <div class="pro">
      <img src="../../../pub/images/product/f3.jpg" />
      <div class="des">
        <span>adidas</span>
        <h5>Cartoon Astronaut T-Shirts</h5>
        <div class="star">
          <i class="fas fa-star"></i>
          <i class="fas fa-star"></i>
          <i class="fas fa-star"></i>
          <i class="fas fa-star"></i>
          <i class="fas fa-star"></i>
        </div>
        <h4>$78</h4>
      </div>
      <a href="#"><i class="fas fa-shopping-cart cart"></i></a>
    </div>
    <div class="pro">
      <img src="../../../pub/images/product/f4.jpg" />
      <div class="des">
        <span>adidas</span>
        <h5>Cartoon Astronaut T-Shirts</h5>
        <div class="star">
          <i class="fas fa-star"></i>
          <i class="fas fa-star"></i>
          <i class="fas fa-star"></i>
          <i class="fas fa-star"></i>
          <i class="fas fa-star"></i>
        </div>
        <h4>$78</h4>
      </div>
      <a href="#"><i class="fas fa-shopping-cart cart"></i></a>
    </div>
  </div>
</section>
<script src="../../../pub/js/product.js"></script>
<jsp:include page="../include/footer.jsp" />
