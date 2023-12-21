document.getElementById("login").addEventListener("submit", function (event) {
    var usernameOrEmail = document.getElementById("email").value;
    var password = document.getElementById("password").value;
    var credentialError = document.getElementById("credentialError"); // Added this line

    if (!usernameOrEmail.trim() || !password.trim()) {
        event.preventDefault();
        credentialError.innerHTML = "Please enter both username/email and password.";
    } else {
        credentialError.innerHTML = "";
    }
});
