import {
    proceedLogout,
    checkData,
    proceedAuth,
} from "../../model/auth/modelAuth.js";
import { init as initReg } from "../registration/pageRegistration.js";
import { init as initMain } from "../main/pageMain.js";


let root;
let innerHTMLAuth = `
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

/** init auth */
function init() {
    root = document.getElementById("root");
    root.innerHTML = innerHTMLAuth;

    let regBtn = document.getElementById("regBtn");
    regBtn.addEventListener("click", initReg);

    let loginBtn = document.getElementById("loginBtn");
    loginBtn.addEventListener("click", () => {
        let login = document.getElementById("usernameField").value;
        let password = document.getElementById("passwordField").value;
        if (checkData(login, password)) {
            proceedAuth(login, password)
                .then(() => initMain());
        }
        else {
            /// вывести какое-нибудь сообщение, 
            /// тип что логин не может быть "" или тп
        }
    });

}

function logout() {
    proceedLogout().then(() => init());
}

export { init, logout };
