let interaction = document.getElementById("interactive");

let pg_1 = document.getElementById("pg_1");

let info_2 = document.getElementById("info_2");

let basket_pg = document.getElementById('basket_pg');

info_2.addEventListener('click' , goToPg_2)
pg_1.addEventListener("click" , goToPg_1)
basket_pg.addEventListener('click' , goToBasketPg)

function goToPg_2(evn) {
    loadPage("/save" , ".mainBodyPg_2")
}

function goToBasketPg(evn) {
    loadPage("/basket" , ".body_basket_pg")
}

function goToPg_1(evn) {
    loadPage("/" , ".description")
}








function loadPage(url, selector) {
    request(new XMLHttpRequest());

    function request(xhr) {
        xhr.open('GET', '/' + url, true);
        xhr.send();
        xhr.onreadystatechange = function() {
            if(xhr.readyState === 4 && xhr.status === 200) {
                html = document.createElement('div');
                html.innerHTML = xhr.responseText;
                let a = html.querySelector(selector)
                interaction.innerHTML = "";
                interaction.append(a);
            }
        }
    }
}