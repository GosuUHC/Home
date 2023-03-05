import template from "./template";
import "../../components/main/comp-main-controls/component.js";
import { proceedLogout } from "../../../model/auth/modelAuth.js";


class CompPageMain extends HTMLElement {

    constructor() {
        super();
        this._dataDiv = undefined;
        this._root = this.attachShadow({ mode: "closed" });
    }

    connectedCallback() {
        this._render();
        this._dataDiv = this._root.getElementById("dataDiv");
        this.setListeners();
    }

    async setListeners() {
        let pagesRouterImport = await import("../../router/pagesRouter.js");
        let pagesRouter = pagesRouterImport.PagesRouterFactory.createInstance();

        let compMainControls = this._root.querySelectorAll("comp-main-controls")[0];
        
        compMainControls.setLogoutListener(async () => {
            await proceedLogout();
            pagesRouter.go("auth");
        });

        compMainControls.setCatalogueSelectListener(async () => {
            await import("./itemCatalogue/component.js");
            let itemCataloguePage = document.createElement("comp-page-item-catalogue");
            itemCataloguePage.setItemType(compMainControls.getSelectValue());


            this._dataDiv.removeChild(this._dataDiv.childNodes[0]);
            this._dataDiv.appendChild(itemCataloguePage);
        });

        compMainControls.setItemCartListener(async () => {
            await import("./itemCart/component.js");
            let itemCartPage = document.createElement("comp-page-item-cart");
            this._dataDiv.removeChild(this._dataDiv.childNodes[0]);
            this._dataDiv.appendChild(itemCartPage);
        });

        compMainControls.setOrdersListener(async () => {
            await import("./orders/component.js");
            let ordersPage = document.createElement("comp-page-orders");
            this._dataDiv.removeChild(this._dataDiv.childNodes[0]);
            this._dataDiv.appendChild(ordersPage);
        });
    }

    _render() {
        if (!this.ownerDocument.defaultView) return;
        this._root.innerHTML = template(this);
    }
}

customElements.define("comp-page-main", CompPageMain);