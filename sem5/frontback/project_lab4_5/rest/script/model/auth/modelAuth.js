import { sendAuthData, logout } from "../../transport/auth/transportAuth.js";

function proceedAuth(login, password) {
    // здесь можно например проверять login password по длине и тп
    return sendAuthData(login, password);
}

function checkData(login, password) {
    // some validation
    return true; // or false
}

function proceedLogout() {
    return logout();
}

export {
    proceedAuth,
    proceedLogout,
    checkData,
};
