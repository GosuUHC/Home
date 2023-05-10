let ws_ = null;

export function openWS(name) {
  const app = "rest-1";

  ws_ = new WebSocket(`ws://localhost:8080/${app}/notifications/${name}`);
  ws_.onopen = function (event) {};

  ws_.onmessage = function (event) {
    console.log(event.data);
  };
}

export function closeWS() {
  if (ws_ && ws_.readyState === WebSocket.OPEN) {
    ws_.close();
  }
}
