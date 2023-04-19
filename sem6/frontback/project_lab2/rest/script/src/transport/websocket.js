export function openWS(name) {
  console.log(name);
  const app = "rest-1";

  const ws = new WebSocket(`ws://localhost:8080/${app}/notifications/${name}`);
  ws.onopen = function (event) {};

  ws.onmessage = function (event) {
    console.log(event.data);
  };
}
