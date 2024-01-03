<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Ecommerce Website</title>

    <!-- Font Awesome CSS -->
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.1/css/all.min.css"
    />

    <!-- Bootstrap CSS -->
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
      crossorigin="anonymous"
    />

    <!-- Custom CSS -->
    <link rel="stylesheet" href="../../../pub/css/style.css" />

    <!-- Custom CSS to hide the dropdown arrow -->
    <style>
      .dropdown-toggle::after {
        content: none; /* Hide the arrow */
      }
    </style>
  </head>

  <body>
    <!-- Header Section -->
    <section id="header">
      <a href="#">
        <img src="../../../pub/images/banner/mainlogo.jpg" class="logo" />
      </a>
      <div>
        <ul id="navbar">
          <!-- Navigation Links -->
          <li><a href="/auth/home">Home</a></li>
          <li><a href="/product/productlist">Shop</a></li>
          <li><a href="about.html">About</a></li>
          <li><a href="contact.html">Contact</a></li>

          <!-- Authentication Section - Show when not authenticated -->
          <sec:authorize access="!isAuthenticated()">
            <li>
              <div class="dropdown">
                <a
                  href="#"
                  class="dropdown-toggle"
                  id="userDropdownToggle"
                  role="button"
                  data-bs-toggle="dropdown"
                  aria-haspopup="true"
                  aria-expanded="false"
                >
                  <i class="fas fa-user"></i>
                </a>
                <!-- Dropdown Menu -->
                <ul
                  class="dropdown-menu dropdown-menu-center"
                  aria-labelledby="userDropdown"
                >
                  <li><a class="dropdown-item" href="/auth/login">Login</a></li>
                  <li>
                    <a class="dropdown-item" href="/auth/register">Register</a>
                  </li>
                </ul>
              </div>
            </li>
          </sec:authorize>

          <!-- Authentication Section - Show when authenticated -->
          <sec:authorize access="isAuthenticated()">
            <li class="nav-item">
              <a class="nav-link" href=""
                ><sec:authentication property="principal.firstName"
              /></a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="/auth/logout">Logout</a>
            </li>
          </sec:authorize>

          <!-- Shopping Cart Icon -->
          <li>
            <a href="/order/displayCart" class="fas fa-shopping-cart"></a>
          </li>
        </ul>
      </div>
    </section>
    <!-- Bootstrap JS and dependencies -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>
  </body>
</html>
