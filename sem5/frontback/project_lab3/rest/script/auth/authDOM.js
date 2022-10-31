var pageAuth = (function () {

    var innerHTMLAuth = `
<main id="loginMain">
    <h1 id="loginHeader">Login</h1>

    <div id="loginErrorMsgDiv">
        <p id="loginErrorMsgP">Inavlid username <span id="errMsgSecondLine">and/or password</span></p>
    </div>

    <div id="loginForm">
        <input type="text" name="username" id="usernameField" placeholder="Login">
        <input type="password" name="password" id="passwordField" placeholder="Password">
        <input type="button" id="loginBtn" value="Login">
    </div>
    <div id="registration">
        <input type="button" id="regBtn" value="Registration">
    </div>

</main>
`;

    function initLogin() {
        root.innerHTML = innerHTMLAuth;

        var regBtn = document.getElementById("regBtn");
        regBtn.addEventListener("click", initReg);

        var loginBtn = document.getElementById("loginBtn");
        loginBtn.addEventListener("click", function () {
            setLoginData();
            modelAuth.proceedAuth();
            //myXmlRequest("POST", "api/auth", getLoginData(), setToken);

        });

        localStorage.clear();
    }

    function setLoginData() {
        var loginField = document.getElementById("usernameField");
        var login = loginField.value;
    
        localStorage.setItem("login", login);
        localStorage.setItem("token", "");
    
    }

    return {
        initLogin: initLogin
    }

})();
