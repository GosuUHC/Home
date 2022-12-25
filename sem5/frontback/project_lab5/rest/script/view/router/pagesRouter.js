
class PagesRouter {

    constructor() {
        this._default = "";
        this._routes = [];
    }

    add(url, view) {
        this._routes.push({ url, view });
    }

    default(url) {
        this._default = url;
    }

    async go(url = "") {

        let view = null;
        if (url === "") {
            url = this._default;
        }

        this._routes.forEach(route => {
            if (route.url === url) {
                view = route.view;
            }
        });

        if (view !== null) {
            await import(`../pages/${view}/component.js`);
            let nodeView = document.createElement(`comp-page-${view}`);

            let nodeApp = document.getElementById("root");
            nodeApp.removeChild(nodeApp.childNodes[0]);
            nodeApp.appendChild(nodeView);
        }

    }
}

class PagesRouterFactory {

    static _router = null;

    static _createInstance() {
        return new PagesRouter();
    }

    static createInstance() {
        if (PagesRouterFactory._router === null) {
            PagesRouterFactory._router = PagesRouterFactory._createInstance();
        }
        return PagesRouterFactory._router;
    }
}

export { PagesRouterFactory };