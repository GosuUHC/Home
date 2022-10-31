
var innerHTMLReg =
    `
    <main id="regMain">
        <div id="goBackBtnDiv">
            <input type="button" id="goBackBtn" value="Go back">
        </div>
        <h1 id="regHeader">Register</h1>

        <div id="regErrorMsgDiv">
            <p id="regErrorMsgP"> Invalid username <span id="regErrMsgSecondLine"> and/or password</span></p>
        </div>

        <div id="regForm">
            <input type="text" name="regUsername" id="regUsernameField" placeholder="Login">
            <input type="text" name="regPassword" id="regPasswordField" placeholder="Password">
            <input type="text" name="regPasswordCheck" id="regPasswordCheckField" placeholder="Password check">
            <input type="button" id="regBtn" value="Register">
        </div>

    </main>
`

function initReg() {
    root.innerHTML = innerHTMLReg;
    var regErrorMsgP = document.getElementById("regErrorMsgP");
    regErrorMsgP.style.opacity = 0;

    var regBtn = document.getElementById("regBtn");

    regBtn.addEventListener("click", register)

}

function register() {
    var regLoginField = document.getElementById("regUsernameField");
    var regPasswordField = document.getElementById("regPasswordField");
    var regPasswordCheckField = document.getElementById("regPasswordCheckField");

    var login = regLoginField.value;
    var password = regPasswordField.value;
    var passwordCheck = regPasswordCheckField.value;

    if (password !== passwordCheck) {
        var regErrorMsgP = document.getElementById("regErrorMsgP");
        regErrorMsgP.firstChild.nodeValue = "Passwords dont match";
        regErrorMsgP.childNodes[1].textContent = "";
        regErrorMsgP.style.opacity = 1;

        return;
    }

    var regData = {
        "login": login,
        "password": password
    };

    myXmlRequest("POST", "api/registration", regData, initLogin);
}