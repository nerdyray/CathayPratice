
let Cars = [
    //{id:1 , mau:Honda,type:Sporty,min_price:20,price:40},
];
let nextNum = 1;
let selectedCarId;
document.getElementsByClassName("add")[0].addEventListener("click", function () {
    const Mau = document.getElementsByClassName("detail")[0].value.trim();
    const Type = document.getElementsByClassName("detail")[1].value.trim();
    const Min_price = document.getElementsByClassName("detail")[2].value.trim();
    const Price = document.getElementsByClassName("detail")[3].value.trim();
    addCars(Mau, Type, Min_price, Price);
    Render();
});

document.getElementsByClassName("delete")[0].addEventListener("click", function () {
    deleteCar(selectedCarId);
});
document.getElementsByClassName("update")[0].addEventListener("click", function () {
    const Mau = document.getElementsByClassName("detail")[0].value.trim();
    const Type = document.getElementsByClassName("detail")[1].value.trim();
    const Min_price = document.getElementsByClassName("detail")[2].value.trim();
    const Price = document.getElementsByClassName("detail")[3].value.trim();
    updateStatus(Mau, Type, Min_price, Price);
    Render();
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


function Render() {
    //清空舊畫面
    const Table = document.getElementsByClassName('table')[0];
    Table.innerHTML = '';
    //Create an "tr" node:
    const newDiv = document.createElement('table');
    newDiv.className = 'car styled-table';    //表頭
    const headerRow = document.createElement('tr');
    const headerIndex = document.createElement('th');
    headerIndex.textContent = '  ';
    const headerId = document.createElement('th');
    headerId.textContent = '   序號';
    const headerMau = document.createElement('th');
    headerMau.textContent = '   製造商';
    const headerType = document.createElement('th');
    headerType.textContent = '   類別';
    const headerMin_price = document.createElement('th');
    headerMin_price.textContent = '   底價';
    const headerPrice = document.createElement('th');
    headerPrice.textContent = '   售價';
    headerRow.appendChild(headerIndex);
    headerRow.appendChild(headerId);
    headerRow.appendChild(headerMau);
    headerRow.appendChild(headerType);
    headerRow.appendChild(headerMin_price);
    headerRow.appendChild(headerPrice);
    newDiv.appendChild(headerRow);
    //for in 遍歷陣列
    for (let i = 0; i < Cars.length; i++) {
        const addCar = document.createElement('tr');
        const carTd = document.createElement('td');
        const carSelect = document.createElement('input');
        const carId = document.createElement('td');
        const carMau = document.createElement('td');
        const carType = document.createElement('td');
        const carMin_price = document.createElement('td');
        const carPrice = document.createElement('td');
        carId.textContent = `${Cars[i].id}`;
        carMau.textContent = `${Cars[i].mau}`;
        carType.textContent = `${Cars[i].type}`;
        carMin_price.textContent = `${Cars[i].min_price}`;
        carPrice.textContent = `${Cars[i].price}`;
        carSelect.className = `${Cars[i]}`;
        carSelect.type = 'radio';
        carSelect.setAttribute('onclick', `selectedCar(${Cars[i].id})`);
        carTd.appendChild(carSelect);
        addCar.appendChild(carTd);
        addCar.appendChild(carId);
        addCar.appendChild(carMau);
        addCar.appendChild(carType)
        addCar.appendChild(carMin_price);
        addCar.appendChild(carPrice);
        newDiv.appendChild(addCar);
    }
    Table.appendChild(newDiv);

}
function deleteCar(selectedCarId) {
    console.log(Cars);
    Cars = Cars.filter(car => car.id !== selectedCarId);
    reIndex();
    Render();
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
    } else {
        alert("請一次修改一項");
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
    }
    Render();
}
