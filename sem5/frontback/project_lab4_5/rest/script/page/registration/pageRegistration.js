import { proceedRegistration, checkData } from "../../model/registration/modelRegistration.js";
import { init as initAuth } from "../auth/pageAuth.js";


let root;
let innerHTMLReg = `
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
        `;

/** init registration */
function init() {
    root = document.getElementById("root");
    root.innerHTML = innerHTMLReg;

    let regErrorMsgP = document.getElementById("regErrorMsgP");
    regErrorMsgP.style.opacity = 0;

    let regBtn = document.getElementById("regBtn");

    regBtn.addEventListener("click", register);

    // Go back button
    let goBackBtn = document.getElementById("goBackBtn");
    goBackBtn.addEventListener("click", initAuth);
}

function register() {
    let login = document.getElementById("regUsernameField").value;
    let password = document.getElementById("regPasswordField").value;
    let checkPassword = document.getElementById("regPasswordCheckField").value;

    if (!checkData(login, password, checkPassword)) {
        let regErrorMsgP = document.getElementById("regErrorMsgP");
        regErrorMsgP.firstChild.nodeValue = "Passwords dont match";
        regErrorMsgP.childNodes[1].textContent = "";
        regErrorMsgP.style.opacity = 1;

        return;
    }

    proceedRegistration(login, password).then(() => initAuth());
}

export { init };
