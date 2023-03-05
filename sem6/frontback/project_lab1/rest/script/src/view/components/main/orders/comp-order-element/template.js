export default function (vm) {
    return `
    <div class="mainTableDivItem">
        <div class="itemCheckBoxDiv">
            <input class="itemCheckBox" type="checkbox">
        </div>

        <div class="itemImage">

        </div>

        <div class="itemContent">
            <div class="itemDescription">

                <div class="itemDescriptionText">
                    ${vm.getOrderData()}
                    <br>
                        Item: ${vm.getItemData()}
                </div>
                <div class="itemDescriptionData">
                    Item id: ${vm.getItemId()}
                    Order id: ${vm.getOrderId()}
                </div>

            </div>

            <div class="itemPrice">
                <span> ${vm.getPrice()} P</span>
            </div>

        </div>

    </div>
    `;
}