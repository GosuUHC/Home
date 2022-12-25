
async function start() {
    let pagesRouterImport = await import("./view/router/pagesRouter.js");

    let pagesRouter = pagesRouterImport.PagesRouterFactory.createInstance();

    ///////////////  url     view
    pagesRouter.add("auth", "auth");
    pagesRouter.add("main", "main");
    pagesRouter.add("registration", "registration");


    if (localStorage.getItem("token")) {
        pagesRouter.default("main");        
    }
    else {
        pagesRouter.default("auth");
    }


    pagesRouter.go();
}

start();
