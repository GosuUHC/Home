import { fetchWrapper } from "transport/request";

const protocol = "http";
const host = "localhost";
const port = "8080";
const name = "rest-1";
const domain = `${protocol}://${host}:${port}/${name}`;

async function sendRegData(login, password) {
  const regData = {
    login,
    password,
  };
  localStorage.setItem("login", login);
  await fetchWrapper("POST", `${domain}/api/v1/registration`, regData, {});
}

export { sendRegData };
