import template from "./template.js";
import { proceedAuth } from "../../../../model/auth/modelAuth.js";


class CompAuth extends HTMLElement {

    constructor() {
        super();
        this._login = undefined;
        this._password = undefined;
        this._root = this.attachShadow({ mode: "closed" });
    }

    connectedCallback() {
        this._render();
        let loginField = this._root.getElementById("loginField");
        let passwField = this._root.getElementById("passwordField");
        let loginBtn = this._root.getElementById("loginBtn");
        let regBtn = this._root.getElementById("regBtn"); /// //

        loginField.addEventListener("change", (event) => {
            this._login = event.target.value;
        });

        passwField.addEventListener("change", (event) => {
            this._password = event.target.value;
        });

        loginBtn.addEventListener("click", async () => {
            await proceedAuth(this._login, this._password)
            let pagesRouterImport = await import("../../../router/pagesRouter.js");
            let pagesRouter = pagesRouterImport.PagesRouterFactory.createInstance();
            pagesRouter.go("main");
        });

        regBtn.addEventListener("click", async () => {
            let pagesRouterImport = await import("../../../router/pagesRouter.js");
            let pagesRouter = pagesRouterImport.PagesRouterFactory.createInstance();
            pagesRouter.go("registration");
        });


    }

    _render() {
        if (!this.ownerDocument.defaultView) return;

        this._root.innerHTML = template(this);
    }
}

customElements.define("comp-auth", CompAuth);
