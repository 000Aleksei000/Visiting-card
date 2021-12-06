let bodyBasket = document.getElementById('body_basket');

/*Заполняем страничку тем что есть в бд!!!*/
fillingGrid();

/*Добавляем листенеры на добаленные элементы для добавления товара в корзину*/
setTimeout(addListener, 500);


let basketImg = document.getElementById('goToBasket');

basketImg.addEventListener('click', goToBasket)


function goToBasket(evt) {
    window.location = 'http://localhost:8880/basket';
}




























/*Заполняем страничку тем что есть в бд!!!*/
function fillingGrid() {
    let ajax = new XMLHttpRequest();
    ajax.open('GET', 'http://localhost:8880/basketServ/getProductsList', true);
    ajax.send();
    ajax.onreadystatechange = function() {
        if(ajax.readyState === 4 && ajax.status === 200) {
            let resp = JSON.parse(ajax.responseText);
            resp.products.forEach(function (item) {
                let div = createDiv(item.name, item.price);
                bodyBasket.insertAdjacentElement('afterbegin', div);
            })
        }
    }
}

/*{"products":
[{"name":"cucumber","price":80.0},
{"name":"apple","price":100.0},
{"name":"orange","price":120.0},
{"name":"potato","price":30.0},
{"name":"cherry","price":300.0},
{"name":"strawberry","price":250.0},
{"name":"pineapple","price":500.0},
{"name":"kiwi","price":180.0},
{"name":"mango","price":200.0}]}*/

function createDiv(name, price) {
    let navName = document.createElement('nav');
    let navPrice = document.createElement('nav');
    let input = document.createElement('input');
    let button = document.createElement('button');
    let img = document.createElement('img');
    img.src = `img/fruit/${name}.png`

    button.innerHTML = "Add to bucket";
    input.type = 'number';
    input.placeholder = 'quantity';
    input.value = '1'
    input.min = '1';
    input.innerHTML = "1";
    navPrice.innerHTML = price;
    navName.innerHTML = name;

    let elem = document.createElement('div');
    elem.className = 'box';
    elem.insertAdjacentElement('afterbegin', button)
    elem.insertAdjacentElement('afterbegin', document.createElement('br'))
    elem.insertAdjacentElement('afterbegin', input)
    elem.insertAdjacentElement('afterbegin', navPrice)
    elem.insertAdjacentElement('afterbegin', navName)
    elem.insertAdjacentElement('afterbegin', img);

    return elem;
}
/*---------------------------------------------------------------------------------------------*/


/*Добавляем листенеры на добаленные элементы для добавления товара в корзину*/
function addListener() {
    let divs = bodyBasket.querySelectorAll('div');
    divs.forEach(function (elem) {
        let button = elem.querySelector('button');
        button.addEventListener('click' , addProductInBasket)

    })
}

function addProductInBasket(evt) {
    let name = this.parentNode.querySelector('nav').innerText;
    let quantity = this.parentNode.querySelector("input").value;
    let req = createRequest(name, quantity)

    let ajax = new XMLHttpRequest();
    ajax.open('POST', 'http://localhost:8880/basketServ/addProductToCostumer', true);
    ajax.setRequestHeader('Content-type', 'application/json')
    ajax.send(JSON.stringify(req))
    ajax.onreadystatechange = function() {
        if(ajax.readyState === 4 && ajax.status === 200) {

        }
    }
}


function createRequest(name, quantity) {
    return {
        name,
        quantity,
    }
}
/*----------------------------------------------------------------------------------------------*/