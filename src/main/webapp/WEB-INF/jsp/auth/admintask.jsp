<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../include/header.jsp" />
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
<style>
  .custom-search-input {
        height: 30px;
        width: 100px;
        padding: 5px;
        /* Add any other styles you need */
    }
  #task-bar {
    background-color: #333;
    color: white;
    padding: 10px;
    text-align: center;
    box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.1);
    display: flex;
    justify-content: space-around;
    align-items: center;
  }

  #task-bar button {
    background-color: #4caf50;
    color: white;
    border: none;
    padding: 10px 20px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    cursor: pointer;
    border-radius: 5px;
    font-weight: bold; /* Make the font bold */
    transition: background-color 0.3s ease;
  }

  #task-bar button:hover {
    background-color: #45a049;
  }

  #content {
    padding: 20px;
    text-align: center;
  }

  .hidden {
    display: none;
  }

  .checkout__input {
    margin-bottom: 15px;
  }

  .form-field {
    display: flex;
    align-items: center;
  }

  .form-field label {
    margin: 0;
    flex: 1;
    font-weight: bold; /* Make the font bold */
  }

  .form-field input,
  .form-field textarea {
    flex: 2;
    margin-left: 10px;
  }
  .visible {
    display: block;
}

.hidden {
    display: none;
}
</style>

<div id="task-bar">
  <button onclick="showTask('addProduct')">Add Product</button>
  <button onclick="showTask('updateOrder')">Update Order Status</button>
  <button onclick="showTask('viewUsers'); window.location.href='/admin/viewUsers'">View Users</button>
</div>

<div id="content">
  <c:if test="${not empty message}">
    <div class="row justify-content-center">
      <div class="col-6 text-center">
        <div class="alert alert-success" role="alert">${message}</div>
      </div>
    </div>
  </c:if>
  <div id="addProduct" class="${hasErrors ? 'visible' : 'hidden'}">
    <form
      method="POST"
      action="/admin/addProduct"
      enctype="multipart/form-data"
    >
      <div class="row">
        <div class="col-lg-8 col-md-6">
          <h3 class="checkout__title" style="margin-top: 45px">
            Add New Product
          </h3>

          <c:if test="${errors.hasFieldErrors('productName')}">
                        <div style="color:red">
                            <c:forEach items="${errors.getFieldErrors('productName')}" var="error">
                                ${error.defaultMessage}<br>
                            </c:forEach>
                        </div>
                    </c:if>
          <div class="col-lg-6">
            <div class="checkout__input form-field">
              <label>Product Name<span>*</span></label>
              <input type="text" name="productName" />
            </div>
          </div>

          <c:if test="${errors.hasFieldErrors('productDescription')}">
                        <div style="color:red">
                            <c:forEach items="${errors.getFieldErrors('productDescription')}" var="error">
                                ${error.defaultMessage}<br>
                            </c:forEach>
                        </div>
                    </c:if>
          <div class="col-lg-6">
            <div class="checkout__input form-field">
              <label>Product Description<span>*</span></label>
              <textarea
                id="myTextArea"
                name="productDescription"
                rows="4"
                cols="50"
              ></textarea>
            </div>
          </div>

          <c:if test="${errors.hasFieldErrors('file')}">
                        <div style="color:red">
                            <c:forEach items="${errors.getFieldErrors('file')}" var="error">
                                ${error.defaultMessage}<br>
                            </c:forEach>
                        </div>
                    </c:if>
          <div class="checkout__input form-field">
            <div class="row justify-content-center pt-5">
              <label>Product Image<span>*</span></label>
              <div class="col-7">
                <input type="file" name="file" />
              </div>
            </div>
          </div>

          <c:if test="${errors.hasFieldErrors('quantity')}">
            <div style="color:red">
                <c:forEach items="${errors.getFieldErrors('quantity')}" var="error">
                    ${error.defaultMessage}<br>
                </c:forEach>
            </div>
        </c:if>
          <div class="col-lg-6">
            <div class="checkout__input form-field">
              <label>Quantity<span>*</span></label>
              <input type="number" name="quantity" />
            </div>
          </div>

          <c:if test="${errors.hasFieldErrors('price')}">
            <div style="color:red">
                <c:forEach items="${errors.getFieldErrors('price')}" var="error">
                    ${error.defaultMessage}<br>
                </c:forEach>
            </div>
        </c:if>
          <div class="col-lg-6">
            <div class="checkout__input form-field">
              <label>price<span>*</span></label>
              <input type="number" name="price" />
            </div>
          </div>

          <c:if test="${errors.hasFieldErrors('rating')}">
            <div style="color:red">
                <c:forEach items="${errors.getFieldErrors('rating')}" var="error">
                    ${error.defaultMessage}<br>
                </c:forEach>
            </div>
        </c:if>
          <div class="col-lg-6">
            <div class="checkout__input form-field">
              <label>Rating<span>*</span></label>
              <input type="number" name="rating" />
            </div>
          </div>
          <select id="categorySelect" onchange="getCategory()">
            <option>Select Category</option>
            <option>MEN</option>
            <option>WOMEN</option>
            <option>BOYS</option>
            <option>GIRLS</option>
            <option>BABIES AND TODDLERS</option>
          </select>
          <input type="hidden" id="selectedCategory" name="selectedCategory" />
          <div class="row justify-content-center pt-3">
            <div class="col-7">
              <input type="submit" value="Submit" />
            </div>
          </div>
        </div>
      </div>
    </form>
  </div>

  <div id="updateOrder" class="${detailsFetched ? 'visible' : 'hidden'}">
      <form action="/admin/selectOrderDetails?ordernumber=${orderNumber}">
        <label for="orderNumber">Order Number:</label>
        <input type="text" id="orderNumber" name="orderNumber" value="${order.orderNumber}"  placeholder="Enter order number" required>
        <button type="submit">Search</button>
      </form>
        <c:if test="${hasOrder}">
          <section class="bg-light1 pb-5">
            <div class="container">
              <div class="row justify-content-center">
                <div class="col-12">
                  <table class="table table-hover">
                    <tr>
                        <td>Id</td>
                        <td>First Name</td>
                        <td>Last Name</td>
                        <td>Order Date</td>
                        <td>Shipped Date</td>
                        <td>Delivered Date</td>
                        <td>Status</td>
                        <td>Total</td>
                        <td>Order Number</td>
                    </tr>
                    <tr>
                        <td>${order.id}</td>
                        <td>${order.customer.firstName}</td>
                        <td>${order.customer.lastName}</td>
                        <td>${order.orderDate}</td>
                        <td>${order.shippedDate}</td>
                        <td>${order.deliveredDate}</td>
                        <td>${order.total}</td>
                        <td>${order.orderNumber}</td>
                        <td>
                            <form action="/admin/updateOrderStatus" style="display: flex; align-items: center;">
                                <input type="hidden" name="orderId" value="${order.id}">
                                <select name="newOrderStatus" style="margin-right: 10px;">
                                    <option value="${order.orderStatus}" selected>${order.orderStatus}</option>
                                    <option value="Shipped">Shipped</option>
                                    <option value="Delivered">Delivered</option>
                                </select>
                                <button type="submit" class="small-rounded-button">Update</button>
                            </form>
                        </td>
                    </tr>
                </table>
                   </div>
                 </div>
               </div>
              </section>
             </c:if>
         </div>

         <div id="viewUsers"  class="${usersFetched ? 'visible' : 'hidden'}">
          <c:if test="${not empty userlist}">
    <section class="bg-light1 pb-5">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-12">
                    <table class="table table-hover">
                                         <tr>
                                             <td>Email</td>
                                             <td>Phone</td>
                                             <td>Country</td>
                                             <td>First Name</td>
                                             <td>Last Name</td>
                                             <td>Default Address</td>
                                             <td>Role</td>
                                         </tr>
                                         <c:forEach items="${userlist}" var="user">
                                             <tr>
                                                 <td>${user.email}</td>
                                                 <td>${user.phone}</td>
                                                 <td>${user.country}</td>
                                                 <td>${user.firstName}</td>
                                                 <td>${user.lastName}</td>
                                                 <td>${user.address1}&nbsp;${user.address2}&nbsp;${user.city}&nbsp;${user.zip}&nbsp;${user.state}</td>
                                                 <td>${user.userRole.userRole}</td>
                                                </tr>
                                         </c:forEach>
                                     </table>
                </div>
            </div>
        </div>
    </section>
</c:if>
         </div>
       </div>
<script>
  function showTask(taskId) {
    // Hide all task divs
    var taskDivs = document.querySelectorAll("#content > div");
    taskDivs.forEach(function (div) {
      div.classList.add("hidden");
    });

    // Show the selected task div
    var selectedTaskDiv = document.getElementById(taskId);
    if (selectedTaskDiv) {
      selectedTaskDiv.classList.remove("hidden");
    }
  }
  function getCategory() {
  var selectElement = document.getElementById("categorySelect");
  var selectedValue = selectElement.options[selectElement.selectedIndex].value;
  document.getElementById("selectedCategory").value = selectedValue;
}


</script>

<jsp:include page="../include/footer.jsp" />
