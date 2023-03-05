export default function (vm) {
    return `
    <style>
        .delBtnDiv {
            color: rgb(30, 126, 223);
            width: auto;
            height: auto;
        }
        
        .delBtnDiv:hover {
            color: rgb(198, 146, 50);
            cursor: pointer;
        }
    </style> 
    <div class="delBtnDiv" id="delBtnID">Delete selected</div>
        
    `;
}