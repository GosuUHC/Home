import template from "./template.js";
import "../../components/auth/comp-auth/component.js";

class CompPageAuth extends HTMLElement {

    constructor() {
        super();
        this._root = this.attachShadow({ mode: "closed" });
    }

    connectedCallback() {
        this._render();
    }

    _render() {
        if (!this.ownerDocument.defaultView) return;
        this._root.innerHTML = template(this);
    }
}
customElements.define("comp-page-auth", CompPageAuth);


