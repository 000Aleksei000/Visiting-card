let basketList = document.getElementById('basketList')






function fillingGrid() {
    let ajax = new XMLHttpRequest();
    ajax.open('GET', 'http://localhost:8880/basketServ/getProductsByCookie', true);
    ajax.send();
    ajax.onreadystatechange = function() {
        if(ajax.readyState === 4 && ajax.status === 200) {
            let resp = JSON.parse(ajax.responseText);
            resp.products.forEach(function (item) {
                let div = createDiv(item.name, item.price);
                basketList.insertAdjacentElement('afterbegin', div);
            })
        }
    }
}

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