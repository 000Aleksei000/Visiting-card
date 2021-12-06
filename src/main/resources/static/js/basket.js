let basketList = document.getElementById('basketList')


fillingGrid()


setTimeout(addListener, 1000);






function addListener() {
    let divs = basketList.querySelectorAll('div');
    divs.forEach(function (elem) {
        let button = elem.querySelector('button');
        button.addEventListener('click' , addProductInBasket)

    })
}

function addProductInBasket(evt) {
    let name = this.parentNode.querySelector('nav').innerText;
    let node = this.parentNode;
    let req = createRequest(name)
    //
    let ajax = new XMLHttpRequest();
    ajax.open('POST', 'http://localhost:8880/basketServ/deleteFromBasket', true);
    ajax.setRequestHeader('Content-type', 'application/json')
    ajax.send(JSON.stringify(req))
    ajax.onreadystatechange = function() {
        if(ajax.readyState === 4 && ajax.status === 200) {
            node.remove();
            // fillingGrid()
            // setTimeout(addListener, 1000);
        }
    }
}














function fillingGrid() {
    let ajax = new XMLHttpRequest();
    ajax.open('GET', 'http://localhost:8880/basketServ/getProductsByCookie', true);
    ajax.send();
    ajax.onreadystatechange = function() {
        if(ajax.readyState === 4 && ajax.status === 200) {
            let resp = JSON.parse(ajax.responseText);
            resp.products.forEach(function (item) {
                let div = createDiv(item.name, item.price, item.quantity);
                basketList.insertAdjacentElement('afterbegin', div);
            })
        }
    }
}


/*
{"products":[{"name":"kiwi","price":180.0,"quantity":1},
    {"name":"pineapple","price":500.0,"quantity":1},
    {"name":"mango","price":200.0,"quantity":1},
    {"name":"cherry","price":300.0,"quantity":3},
    {"name":"cucumber","price":80.0,"quantity":8}]}
*/

function createDiv(name, price, quantity) {
    let navName = document.createElement('nav');
    let navQuantity = document.createElement('nav');
    let navPrice = document.createElement('nav');
    let input = document.createElement('input');
    let button = document.createElement('button');
    let img = document.createElement('img');
    img.src = `img/fruit/${name}.png`

    button.innerHTML = "Delete";
    input.type = 'number';
    input.placeholder = 'quantity';
    input.value = quantity
    input.min = '1';
    input.innerHTML = "1";
    navPrice.innerHTML = price;
    navQuantity.innerHTML = quantity;
    navName.innerHTML = name;

    let elem = document.createElement('div');
    elem.className = 'box';
    elem.insertAdjacentElement('afterbegin', button)
    elem.insertAdjacentElement('afterbegin', document.createElement('br'))
    elem.insertAdjacentElement('afterbegin', input)
    elem.insertAdjacentElement('afterbegin', navPrice)
    // elem.insertAdjacentElement('afterbegin', navQuantity)
    elem.insertAdjacentElement('afterbegin', navName)
    elem.insertAdjacentElement('afterbegin', img);

    return elem;
}


function createRequest(name) {
    return{
        name,
    }
}