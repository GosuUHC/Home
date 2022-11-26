async function sendAuthData(login, password) {
    const loginData = {
        login,
        password,
    };
    localStorage.setItem("login", login);
    let response = await myXmlRequest("POST", "api/v1/auth", loginData, {});
    setTokenFromServ(response);
}

function setTokenFromServ(response) {
    localStorage.setItem("token", response);
}

async function logout() {
    localStorage.removeItem("token");
}


export { sendAuthData, logout };
