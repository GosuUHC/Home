import { sendAuthData, logout } from "../../transport/auth/transportAuth.js";

function proceedAuth(login, password) {
    return sendAuthData(login, password);
}

function proceedLogout() {
    return logout();
}

export {
    proceedAuth,
    proceedLogout,
};
