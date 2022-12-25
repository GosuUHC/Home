export default function (vm) {
    return ` 
    <main id="regMain">
        <div id="goBackBtnDiv">
            <input type="button" id="goBackBtn" value="Go back">
        </div>
        <h1 id="regHeader">Register</h1>

        <div id="regErrorMsgDiv">
           
        </div>

        <div id="regForm">
            <input type="text" id="regLoginField" placeholder="Login">
            <input type="text" id="regPasswordField" placeholder="Password">
            <input type="text" id="regPasswordCheckField" placeholder="Password check">
            <input type="button" id="regBtn" value="Register">
        </div>
    </main>
    `;
}