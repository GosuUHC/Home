
export default function (vm) {
    return ` 
    <main id="loginMain">
        <h1 id="loginHeader">Login</h1>

        <div id="loginErrorMsgDiv">
        </div>

        <div id="loginForm">
            <input type="text" name="username" id="loginField" placeholder="Login">
            <input type="password" name="password" id="passwordField" placeholder="Password">
            <input type="button" id="loginBtn" value="Login">
        </div>
        <div id="registration">
            <input type="button" id="regBtn" value="Registration">
        </div>

    </main>
    `;
}