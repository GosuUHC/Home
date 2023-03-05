import { sendRegData } from "../../transport/registration/transportRegistration.js";

function proceedRegistration(login, password) {
    return sendRegData(login, password);
}

function checkData(password, checkPassword) {
    if (password !== checkPassword) {
        return false;
    }
    return true;
    /// some other validation
}

export { proceedRegistration, checkData };
