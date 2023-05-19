import { messagesManager } from "transport/websocket";

function manageMessages() {
  const { subscribe, unsubscribe } = messagesManager;
  return {
    subscribe,
    unsubscribe,
  };
}
export { manageMessages };
