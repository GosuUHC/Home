export default function (vm) {
    return `
    <style>
        .mainTableDivItem {
            display: grid;
            grid-template-columns: 1fr;
            grid-template-rows: 20px;
        }
        .itemContent {
            display: grid;
            grid-template-columns: 600px 75px 30fr;
        
        }
        .itemCountValue {
            width: 50px;
        }
    
    </style>
    <div class="mainTableDivItem">
        <div class="itemCheckBoxDiv">
            <input class="itemCheckBox" type="checkbox">
        </div>

        <div class="itemImage">

        </div>

        <div class="itemContent">
            <div class="itemDescription">

                <div class="itemDescriptionText">
                    ${vm.getItemText()}
                </div>
                <div class="itemDescriptionData">
                    Item id: ${vm.getItemData()}
                </div>

            </div>

            <div class="itemPrice">
                <span> ${vm.getFinalPrice()} P</span>
            </div>

            <article class="itemActions">
                <div class="itemCountAction">
                    <input class="itemCountValue" type="number" min="1" max="10" value="1">
                </div>
            </article>

        </div>

    </div>
    `
}
