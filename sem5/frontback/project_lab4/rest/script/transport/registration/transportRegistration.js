async function sendRegData(login, password) {
    const regData = {
        login,
        password,
    };
    await myXmlRequest("POST", "api/registration", regData, {});
}

export { sendRegData };
