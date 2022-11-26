async function sendRegData(login, password) {
    const regData = {
        login,
        password,
    };
    await myXmlRequest("POST", "api//v1registration", regData, {});
}

export { sendRegData };
