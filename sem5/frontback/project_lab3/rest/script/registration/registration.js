
function initReg() {
    root.innerHTML = innerHTMLReg;
    var regErrorMsgP = document.getElementById("regErrorMsgP");
    regErrorMsgP.style.opacity = 0;

    var regBtn = document.getElementById("regBtn");

    regBtn.addEventListener("click", register)

    // Go back button
    var goBackBtn = document.getElementById("goBackBtn");
    goBackBtn.addEventListener("click", initLogin);

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