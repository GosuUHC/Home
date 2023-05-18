export const messagesManager = (() => {
  const app = "rest-1";
  let _wsMessages = null;
  let _subs = [];
  let isInitialized = false;

  const messagesHandler = ({ data }) => {
    const message = data;
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
      _wsMessages.onmessage = messagesHandler;
      isInitialized = true;
    }
  };

  const resetConnection = () => {
    if (isInitialized) {
      if (_wsMessages.readyState === WebSocket.OPEN) {
        _wsMessages.close();
      }
      isInitialized = false;
    }
  };

  return {
    initialize,
    subscribe,
    unsubscribe,
    resetConnection,
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
      _wsOrders.onmessage = notificationsHandler;
      isInitialized = true;
    }
  };

  const resetConnection = () => {
    if (isInitialized) {
      if (_wsOrders.readyState === WebSocket.OPEN) {
        _wsOrders.close();
      }
      isInitialized = false;
    }
  };

  return {
    initialize,
    subscribe,
    unsubscribe,
    resetConnection,
  };
})();

export const closeAllConnections = () => {
  [messagesManager, ordersNotificationsManager].forEach((manager) => {
    manager.resetConnection();
  });
};
