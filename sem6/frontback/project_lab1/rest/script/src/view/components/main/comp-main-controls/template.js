export default function (vm) {
    return `
    <main id="main">
        <div id="logout">
            <input type="button" id="logoutBtn" value="logout">
        </div>
        <div id="catalogueDiv" class="catalogue">
            <select id="itemSelect">
                <option value="">Catalogue</option>
                <option value="cpu">CPU</option>
                <option value="gpu">GPU</option>
                <option value="ram">RAM</option>
                <option value="motherboard">Motherboard</option>
            </select>
            <div class="catalogueActions">
                <input type="button" class="itemCartButton" id="itemCartButtonID" value="Item cart">
                <input type="button" class="ordersButton" id="ordersButtonID" value="Orders">
            </div>
        </div>
    </main>
    `;
}