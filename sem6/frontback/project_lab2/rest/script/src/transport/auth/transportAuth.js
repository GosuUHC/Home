import { fetchWrapper } from "../request.js";

let protocol = "http";
let host = "localhost";
let port = "8080";
let name = "rest-1";
let domain = `${protocol}://${host}:${port}/${name}`;

async function sendAuthData(login, password) {
  const loginData = {
    login,
    password,
  };
  localStorage.setItem("login", login);
  let response = await fetchWrapper(
    "POST",
    `${domain}/api/v1/auth`,
    loginData,
    {}
  );
  setTokenAndRoleFromServ(response);
}

function setTokenAndRoleFromServ(response) {
  localStorage.setItem("token", response.token);
  localStorage.setItem("role", response.role);
}

async function logout() {
  localStorage.removeItem("token");
  localStorage.removeItem("role");
  localStorage.removeItem("login");
}

export { sendAuthData, logout };
