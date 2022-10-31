function whatToLoad() {
    if (localStorage.getItem("token")) {
        pageMain.initMain();

    }
    else {
        pageAuth.initLogin();
    }
}

whatToLoad();