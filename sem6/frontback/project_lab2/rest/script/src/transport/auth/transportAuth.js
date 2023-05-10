import { fetchWrapper } from "transport/request";

const protocol = "http";
const host = "localhost";
const port = "8080";
const name = "rest-1";
const domain = `${protocol}://${host}:${port}/${name}`;

/** **Also returns user role!** */
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
  return response.role;
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
