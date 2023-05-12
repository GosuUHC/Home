import { sendAuthData, logout } from "transport/auth/transportAuth";
import { closeAllConnections, messagesManager } from "transport/websocket";

async function proceedAuth(login, password) {
  let res = await sendAuthData(login, password);
  const { initialize } = messagesManager;
  initialize(login);
  return { res };
}

async function proceedLogout() {
  let res = await logout();
  closeAllConnections();
  return res;
}

export { proceedAuth, proceedLogout };
