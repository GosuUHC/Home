var modelAuth = (function () {

    function proceedAuth() {
        var loginData = getLoginData();
        myXmlRequest("POST", "api/auth", loginData, function (response) {
            setTokenFromServ(response);
        });
    }

    function getLoginData() {
        var loginData = {
            "login": localStorage.getItem("login"),
            "token": localStorage.getItem("token")
        };

        return loginData;
    }

    function setTokenFromServ(response) {
        localStorage.setItem("token", response);
        pageMain.initMain();

    }

    return {
        proceedAuth: proceedAuth
    }

}
)();
