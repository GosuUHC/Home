var root = document.getElementById("root");

function whatToLoad() {
    if (getLoginData().token) {
        initMain();

    }
    else {
        initLogin();
    }
}

function initLogin() {
    root.innerHTML = innerHTMLAuth;

    var regBtn = document.getElementById("regBtn");
    regBtn.addEventListener("click", initReg);

    var loginBtn = document.getElementById("loginBtn");
    loginBtn.addEventListener("click", function () {
        setLoginData(); 
        myXmlRequest("POST", "api/auth", getLoginData(), setToken);
    });

    localStorage.clear();
}

whatToLoad();

function setLoginData() {
    var loginField = document.getElementById("usernameField");
    var login = loginField.value;

    localStorage.setItem("login", login);
    localStorage.setItem("token", "");

}

function getLoginData() {
    var loginData = {
        "login": localStorage.getItem("login"),
        "token": localStorage.getItem("token")
    };

    return loginData;
}

function setToken(response) {

    localStorage.setItem("token", response);
    console.log(response);
    initMain();

}
