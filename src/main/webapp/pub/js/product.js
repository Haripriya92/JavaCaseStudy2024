function redirectToController(element) {
  var selectedValue = element.getAttribute("data-value");
  var url =
    "/product/selectedCategory?selectedValue=" +
    encodeURIComponent(selectedValue);
  window.location.href = url;
}

function redirectToDetailsPage(productId) {
  var url =
    "/product/productdetails?selectedId=" + encodeURIComponent(productId);
  window.location.href = url;
}
function getSize() {
  var selectElement = document.getElementById("sizeSelect");
  var selectedValue = selectElement.options[selectElement.selectedIndex].value;
  document.getElementById("selectedSize").value = selectedValue;
}

function validateForm(event) {
  var selectedSize = document.getElementById("sizeSelect").value;
  var sizeError = document.getElementById("sizeError");

  if (selectedSize === "Select Size") {
    sizeError.innerText = "Please select a size";
    event.preventDefault();
  } else {
    sizeError.innerText = "";
  }
}
