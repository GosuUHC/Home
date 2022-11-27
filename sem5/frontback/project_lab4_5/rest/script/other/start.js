import { init as initMain } from "../page/main/pageMain.js";
import { init as initAuth } from "../page/auth/pageAuth.js";


if (localStorage.getItem("token")) {
    initMain();
}
else {
    initAuth();
}

