import { sendAuthData, logout } from "transport/auth/transportAuth";
import { closeWS, openWS } from "transport/websocket";

async function proceedAuth(login, password) {
  let res = await sendAuthData(login, password);
  openWS(login);
  return res;
}

async function proceedLogout() {
  let res = await logout();
  closeWS();
  return res;
}

export { proceedAuth, proceedLogout };
