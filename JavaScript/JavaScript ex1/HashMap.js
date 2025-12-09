let key = [

];
let value = [

];
//新增KEY / VALUE
document.getElementById("PUT").addEventListener("click", function () {
    const KEY = document.getElementById("KEY").value.trim();
    const VALUE = document.getElementById("VALUE").value.trim();
    HashMap(KEY, VALUE);
    Render();
    console.log(key, value);

});
document.getElementById("CLEAR").addEventListener("click", function () {
    Result.innerHTML = '';

});

function HashMap(KEY, VALUE) {
    if ( KEY==='' || key.includes(KEY)) {
        alert('KEY值不能為空或已有重複KEY值');
        return;
    }
    key.push(KEY);
    value.push(VALUE);
}

function Render() {
    //清空舊畫面
    Result.innerHTML = '';
    //CreateTitle
    const resultText =
        document.createTextNode('[RESULT]');
    //Create an "ul" node:
    const newDiv = document.createElement('ul');
    newDiv.id = 'RESULT';
    newDiv.className = 'box';
    //for in 遍歷陣列
    for (let i = 0; i < key.length; i++) {
        const ResultKey = document.createElement('li');
        ResultKey.textContent = `KEY:  ${key[i]} 
           VALUE:  ${value[i]}`;
        newDiv.appendChild(ResultKey);
    }
    document.getElementById("Result").appendChild(resultText);
    document.getElementById("Result").appendChild(newDiv);
}





