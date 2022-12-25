import { proceedRegistration, checkData } from "../../../../model/registration/modelRegistration.js";
import template from "./template.js";

class CompRegistration extends HTMLElement {

    constructor() {
        super();
        this._login = undefined;
        this._password = undefined;
        this._passwordCheck = undefined;
        this._root = this.attachShadow({ mode: "closed" });
    }

    connectedCallback() {
        this._render();
        let goBackBtn = this._root.getElementById("goBackBtn");
        let loginField = this._root.getElementById("regLoginField");
        let passwField = this._root.getElementById("regPasswordField");
        let passwCheckField = this._root.getElementById("regPasswordCheckField");
        let regBtn = this._root.getElementById("regBtn"); /// //

        goBackBtn.addEventListener("click", async () => {
            let pagesRouterImport = await import("../../../router/pagesRouter.js");
            let pagesRouter = pagesRouterImport.PagesRouterFactory.createInstance();
            pagesRouter.go("auth");
        });

        loginField.addEventListener("change", (event) => {
            this._login = event.target.value;
        });

        passwField.addEventListener("change", (event) => {
            this._password = event.target.value;
        });

        passwCheckField.addEventListener("change", (event) => {
            this._passwordCheck = event.target.value;
        });

        regBtn.addEventListener("click", async () => {
            if (!checkData(this._password, this._passwordCheck)) {
                return;
            }
            await proceedRegistration(this._login, this._password);
            let pagesRouterImport = await import("../../../router/pagesRouter.js");
            let pagesRouter = pagesRouterImport.PagesRouterFactory.createInstance();
            pagesRouter.go("auth");
        });

    }

    _render() {
        if (!this.ownerDocument.defaultView) return;
        this._root.innerHTML = template(this);
    }
}

customElements.define("comp-registration", CompRegistration);
