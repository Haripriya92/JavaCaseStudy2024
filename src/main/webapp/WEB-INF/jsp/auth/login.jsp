<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Login Page</title>
    <link rel="stylesheet" href="../../../pub/css/login.css" />
  </head>
  <body>
    <div class="container">
      <form method="post" action="/auth/loginSubmit" id="login">
        <h1 class="form_title">Login</h1>
        <div class="form__input-group">
          <input
            type="text"
            id="email"
            class="form__input"
            autofocus
            placeholder="Username or email"
            name="username"
          />
        </div>
        <div class="form__input-group">
          <input
            id="password"
            type="password"
            class="form__input"
            autofocus
            placeholder="Password"
            name="password"
          />
        </div>
        <div id="credentialError" style="color: red;"></div>
                        <br>
        <button id="loginBtn" class="form__button" type="submit">
          Continue
        </button>
        <p class="form__text">
          <a href="#" class="form__link">Forgot your password?</a>
        </p>
        <p class="form__text">
          <a class="form__link" href="/auth/register" id="linkCreateAccount"
            >Dont have an account?Create account</a
          >
        </p>
      </form>
    </div>
    <script src="../../../pub/js/login.js"></script>
  </body>
</html>
