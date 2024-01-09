<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../include/header.jsp" />
<style>
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
</style>

<div id="task-bar">
  <button onclick="showTask('addProduct')">Add Product</button>
  <button onclick="showTask('updateOrder')">Update Order Status</button>
  <button onclick="showTask('viewUsers')">View Users</button>
  <button onclick="showTask('viewProducts')">View Products</button>
</div>

<div id="content">
  <c:if test="${not empty message}">
    <div class="row justify-content-center">
      <div class="col-6 text-center">
        <div class="alert alert-success" role="alert">${message}</div>
      </div>
    </div>
  </c:if>
  <div id="addProduct" class="hidden">
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

          <div class="row justify-content-center pt-3">
            <div class="col-7">
              <input type="submit" value="Submit" />
            </div>
          </div>
        </div>
      </div>
    </form>
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
</script>

<jsp:include page="../include/footer.jsp" />
