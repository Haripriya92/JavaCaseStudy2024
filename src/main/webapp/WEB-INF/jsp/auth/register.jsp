<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Register</title>
    <link rel="stylesheet" href="../../../pub/css/login.css" />
  </head>
  <body>
    <div class="container">
      <form
        class="form"
        id="createAccount"
        method="get"
        action="/auth/userRegister"
      >
        <h1 class="form_title">Create Account</h1>

        <div class="form__input-group">
          <input
            type="text"
            class="form__input"
            id="firstName"
            name="firstName"
            autofocus
            placeholder="Firstname"
           />
        </div> 
        <c:if test="${errors.hasFieldErrors('firstName')}">
          <div style="color:red">
              <c:forEach items="${errors.getFieldErrors('firstName')}" var="error">
                  ${error.defaultMessage}<br>
              </c:forEach>
          </div>
      </c:if>
      <div class="form__input-group">
        <input
          type="text"
          class="form__input"
          id="lastName"
          name="lastName"
          autofocus
          placeholder="Lastname"
         />
      </div> 
      <c:if test="${errors.hasFieldErrors('lastName')}">
        <div style="color:red">
            <c:forEach items="${errors.getFieldErrors('lastName')}" var="error">
                ${error.defaultMessage}<br>
            </c:forEach>
        </div>
    </c:if>

        <div class="form__input-group">
          <input
            type="text"
            class="form__input"
            id="email"
            name="email"
            autofocus
            placeholder="Email Address as User name"
           />
        </div> 
        <c:if test="${errors.hasFieldErrors('email')}">
          <div style="color:red">
              <c:forEach items="${errors.getFieldErrors('email')}" var="error">
                  ${error.defaultMessage}<br>
              </c:forEach>
          </div>
      </c:if>

        <div class="form__input-group">
          <input
            type="number"
            class="form__input"
            id="phone"
            name="phone"
            autofocus
            placeholder="Phone Number"
          />
        </div>
          <c:if test="${errors.hasFieldErrors('phone')}">
            <div style="color:red">
                <c:forEach items="${errors.getFieldErrors('phone')}" var="error">
                    ${error.defaultMessage}<br>
                </c:forEach>
            </div>
        </c:if>  
        <div class="form__input-group">
          <select id="country" name="country" class="form__input">
            <option value="" disabled selected>Select your country</option>
            <option value="us">United States</option>
            <option value="ca">Canada</option>
          </select>
        </div>
        <c:if test="${errors.hasFieldErrors('country')}">
            <div style="color:red">
                <c:forEach items="${errors.getFieldErrors('country')}" var="error">
                    ${error.defaultMessage}<br>
                </c:forEach>
            </div>
        </c:if>  
        <div class="form__input-group">
          <input
            type="password"
            id="password"
            name="password"
            class="form__input"
            autofocus
            placeholder="Password"
          />
        </div>
        <c:if test="${errors.hasFieldErrors('password')}">
            <div style="color:red">
                <c:forEach items="${errors.getFieldErrors('password')}" var="error">
                    ${error.defaultMessage}<br>
                </c:forEach>
            </div>
        </c:if>
        <div class="form__input-group">
          <input
            id="confirmPassword"
            name="confirmPassword"
            type="password"
            class="form__input"
            autofocus
            placeholder="Confirm Password"
          />
        </div>
         <br>
                <div id="passwordError" style="color: red;"></div>
                <br>
        <c:if test="${errors.hasFieldErrors('confirmPassword')}">
            <div style="color:red">
                <c:forEach items="${errors.getFieldErrors('confirmPassword')}" var="error">
                    ${error.defaultMessage}<br>
                </c:forEach>
            </div>
        </c:if>
        <button id="submitBtn" class="form__button" type="submit">
          Continue
        </button>
        <p class="form__text">
          <a class="form__link" href="/auth/login" id="linkLogin"
            >Already have an account? Sign in</a
          >
        </p>
      </form>
    </div>
    <script src="../../../pub/js/register.js"></script>
  </body>
</html>
