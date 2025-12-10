
let Cars = [
    //{id:1 , mau:Honda,type:Sporty,min_price:20,price:40},
];
let nextNum = 1;
let selectedCarId;
document.getElementsByClassName("add")[0].addEventListener("click", function () {
    // TODO 資料異動應以組處理
    const Mau = document.getElementsByClassName("detail")[0].value.trim();
    const Type = document.getElementsByClassName("detail")[1].value.trim();
    const Min_price = document.getElementsByClassName("detail")[2].value.trim();
    const Price = document.getElementsByClassName("detail")[3].value.trim();
    addCars(Mau, Type, Min_price, Price);
    render();
});

document.getElementsByClassName("delete")[0].addEventListener("click", function () {
    document.getElementsByClassName("detail")[0].value = "";
    document.getElementsByClassName("detail")[1].value = "";
    document.getElementsByClassName("detail")[2].value = "";
    document.getElementsByClassName("detail")[3].value = "";
    document.getElementsByClassName("detail")[4].value = "";

});
document.getElementsByClassName("update")[0].addEventListener("click", function () {
    const Mau = document.getElementsByClassName("detail")[0].value.trim();
    const Type = document.getElementsByClassName("detail")[1].value.trim();
    const Min_price = document.getElementsByClassName("detail")[2].value.trim();
    const Price = document.getElementsByClassName("detail")[3].value.trim();
    updateStatus(Mau, Type, Min_price, Price);
    render();
});
function addCars(Mau, Type, Min_price, Price) {
    if (Mau === '' || Type == '') {
        alert('製造商或類別不可為空');
        return;
    }
    let newCar = {
        id: nextNum++,
        mau: Mau,
        type: Type,
        min_price: Min_price,
        price: Price
    }
    Cars.push(newCar);
}

// TODO 方法名稱小駝峰
function render() {
    //清空舊畫面
    const tableList = document.getElementsByClassName('tableList')[0];
    const tableBody = document.getElementsByClassName('tableBody')[0];
    tableList.appendChild(tableBody);
    tableBody.innerHTML = '';
    //for  遍歷陣列
    for (let i = 0; i < Cars.length; i++) {
        const addCar = document.createElement('tr');
        const carTd = document.createElement('td');
        const carSelect = document.createElement('input');
        const carId = document.createElement('td');
        const carMau = document.createElement('td');
        const carType = document.createElement('td');
        const carMin_price = document.createElement('td');
        const carPrice = document.createElement('td');
        const deleteCell = document.createElement('td');
        const deleteButton = document.createElement('button');

        carId.textContent = `${Cars[i].id}`;
        carMau.textContent = `${Cars[i].mau}`;
        carType.textContent = `${Cars[i].type}`;
        carMin_price.textContent = `${Cars[i].min_price}`;
        carPrice.textContent = `${Cars[i].price}`;

        deleteButton.setAttribute('onclick', `deleteCar(${Cars[i].id})`);
        deleteButton.textContent = '刪除';

        carSelect.className = `${Cars[i]}`;
        carSelect.type = 'radio';
        carSelect.name = 'carSelection';
        carSelect.setAttribute('onclick', `selectedCar(${Cars[i].id})`);
        carTd.appendChild(carSelect);
        addCar.appendChild(carTd);
        addCar.appendChild(carId);
        addCar.appendChild(carMau);
        addCar.appendChild(carType)
        addCar.appendChild(carMin_price);
        addCar.appendChild(carPrice);
        deleteCell.appendChild(deleteButton);
        addCar.appendChild(deleteCell);
        tableBody.appendChild(addCar);
    }


}

function deleteCar(selectedCarId) {
    console.log(Cars);
    Cars = Cars.filter(car => car.id !== selectedCarId);
    reIndex();
    render();
}

function reIndex() {
    for (let i = 0; i < Cars.length; i++) {
        Cars[i].id = i + 1;
    }
    nextNum = Cars.length + 1;
}

function selectedCar(id,) {
    const selectedCar = Cars.find(car => car.id == id);
    if (selectedCar.id) {
        selectedCarId = selectedCar.id;
        document.getElementsByClassName("detail")[0].value = selectedCar.mau;
        document.getElementsByClassName("detail")[1].value = selectedCar.type;
        document.getElementsByClassName("detail")[2].value = selectedCar.min_price;
        document.getElementsByClassName("detail")[3].value = selectedCar.price;
    }
}

function updateStatus(Mau, Type, Min_price, Price) {
    let carIndex = Cars.findIndex(car => car.id === selectedCarId);
    if (carIndex !== -1) {

        if (Mau === '' || Type == '') {
            alert('製造商或類別不可為空');
            return;
        } else {
            Cars[carIndex].mau = Mau;
            Cars[carIndex].type = Type;
            Cars[carIndex].min_price = Min_price;
            Cars[carIndex].price = Price;
        }
    } else {
        alert('請至少選擇一樣');
        return;
    }
    render();
}
