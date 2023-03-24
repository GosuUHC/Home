async function fetchWrapper(
  method,
  uri,
  object = {},
  filter = {},
  paramsToHeader = false
) {
  let token = localStorage.getItem("token");
  token = !token ? "" : token;

  let headers = {
    "Content-Type": "application/json;charset=UTF-8",
    Name: localStorage.getItem("login"),
    Token: token,
  };

  let options = { method, headers };

  if (method === "GET" || method === "DELETE") {
    for (let key in filter) {
      headers[key] = filter[key];
    }
  } else if (paramsToHeader) {
    for (let key in object) {
      headers[key] = object[key];
    }
  } else {
    options["body"] = JSON.stringify(object);
  }

  let response = await fetch(uri, options);

  try {
    switch (response.headers.get("Content-Type")) {
      case "application/json":
        return await response.json();
      case "text/plain":
        return await response.text();
      default:
        throw new Error("Request.js content-type support not implemented");
    }
  } catch (err) {
    console.log(err);
  }
}

export { fetchWrapper };
