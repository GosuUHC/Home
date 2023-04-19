import { sendAuthData, logout } from "../../transport/auth/transportAuth";
import { openWS } from "../../transport/websocket";

async function proceedAuth(login, password) {
  await sendAuthData(login, password);
  const name = localStorage.getItem("login");
  openWS(name);
}

function proceedLogout() {
  return logout();
}

export { proceedAuth, proceedLogout };
