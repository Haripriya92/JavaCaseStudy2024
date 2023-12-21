    function validatePassword() {
            var password = document.getElementById("password").value;
            var confirmPassword = document.getElementById("confirmPassword").value;
            var passwordError = document.getElementById("passwordError");

            if (password === confirmPassword) {
                passwordError.innerHTML = "";
            } else {
                passwordError.innerHTML = "Passwords do not match";
            }
        }

        document.getElementById("createAccount").addEventListener("submit", function(event) {
            var password = document.getElementById("password").value;
            var confirmPassword = document.getElementById("confirmPassword").value;
            var passwordError = document.getElementById("passwordError");

            if (password !== confirmPassword) {
                event.preventDefault(); // Prevent form submission if passwords don't match
                passwordError.innerHTML = "Passwords do not match";
            } else {
                passwordError.innerHTML = ""; // Clear the error message if passwords match
            }
        });
