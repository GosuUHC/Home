const _webSockets = [];

export const messagesManager = (() => {
  const app = "rest-1";
  let _wsMessages = null;
  let _subs = [];
  let isInitialized = false;

  const messagesHandler = ({ data }) => {
    const message = data;
    console.log(message);
    _subs.forEach((sub) => sub(message));
  };

  const subscribe = (callback) => {
    _subs.push(callback);
  };

  const unsubscribe = (callback) => {
    _subs = _subs.filter((sub) => sub !== callback);
  };

  const initialize = (login) => {
    if (!isInitialized) {
      _wsMessages = new WebSocket(
        `ws://localhost:8080/${app}/messages/${login}`
      );
      _webSockets.push(_wsMessages);
      _wsMessages.onmessage = messagesHandler;
      isInitialized = true;
    }
  };

  return {
    initialize,
    subscribe,
    unsubscribe,
  };
})();

export const ordersNotificationsManager = (() => {
  const app = "rest-2";
  let _wsOrders = null;
  let _subs = [];
  let isInitialized = false;

  const notificationsHandler = ({ data }) => {
    const notification = JSON.parse(data);
    _subs.forEach((sub) => sub(notification));
  };

  const subscribe = (callback) => {
    _subs.push(callback);
  };

  const unsubscribe = (callback) => {
    _subs = _subs.filter((sub) => sub !== callback);
  };

  const initialize = (login) => {
    if (!isInitialized) {
      _wsOrders = new WebSocket(
        `ws://localhost:8080/${app}/notifications/orders/${login}`
      );
      _webSockets.push(_wsOrders);
      _wsOrders.onmessage = notificationsHandler;
      isInitialized = true;
    }
  };

  return {
    initialize,
    subscribe,
    unsubscribe,
  };
})();

export const closeAllConnections = () => {
  _webSockets.forEach((ws) => {
    if (ws.readyState === WebSocket.OPEN) {
      ws.close();
    }
  });
};
