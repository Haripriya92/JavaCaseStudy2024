
<jsp:include page="../include/header.jsp" />
<link
  href="https://fonts.googleapis.com/css?family=Nunito+Sans:400,400i,700,900&display=swap"
  rel="stylesheet"
/>
<body class="order">
  <div class="card">
    <div
      style="
        border-radius: 200px;
        height: 200px;
        width: 200px;
        background: #f8faf5;
        margin: 0 auto;
      "
    >
      <div class="fe-box">
        <img src="../../../pub/images/feature/f2.png" />
      </div>
    </div>
    <h1 class="main_title">Order Complete</h1>
    <p class="sub_para">
      Thanks ${order.customer.firstName} !! We received your purchase request
      with tracking id ${order.orderNumber}<br />
      we'll be in touch shortly!
    </p>
  </div>
  <jsp:include page="../include/footer.jsp" />
</body>
