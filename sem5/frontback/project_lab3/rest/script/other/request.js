function myXmlRequest(method, uri, object = {}, func, filter = {}) {
    var xhr = new XMLHttpRequest();

    var flagAsync = true;

    var token = localStorage.getItem("token");
    if (!token && !object.password) {
        object.password = document.getElementById("passwordField").value;
    }

    xhr.open(method, uri, flagAsync);
    xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    xhr.setRequestHeader("Name", localStorage.getItem("login"));
    xhr.setRequestHeader("Token", token);

    


    xhr.onreadystatechange = function () {
        if (this.readyState !== 4) { return; }
        if (this.status !== 200) {
            alert(this.status + ": " + this.statusText);
            return;
        }

        var response = xhr.responseText;

        if (response) {
            try {
                response = JSON.parse(response);
                console.log(response);
            } catch (e) {
                console.log(e);
            }
        }

        func(response);
    }

    if (method == "GET" || method == "DELETE") {
        for (var key in filter) {
            xhr.setRequestHeader(key, filter[key]);
        }
        xhr.send();
    }
    else {
        var body = JSON.stringify(object);
        xhr.send(body);
    }
}