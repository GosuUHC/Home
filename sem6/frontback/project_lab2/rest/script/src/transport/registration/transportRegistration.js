import { fetchWrapper } from "../request.js";

let protocol = "http";
let host = "localhost";
let port = "8080";
let name = "rest-1";
let domain = `${protocol}://${host}:${port}/${name}`;

async function sendRegData(login, password) {
  const regData = {
    login,
    password,
  };
  localStorage.setItem("login", login);
  await fetchWrapper("POST", `${domain}/api/v1/registration`, regData, {});
}

export { sendRegData };
