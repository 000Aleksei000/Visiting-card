let interaction = document.getElementById("interactive");

let pg_1 = document.getElementById("pg_1");

let info_2 = document.getElementById("info_2")




info_2.addEventListener('click' , goToPg_2)

function goToPg_2(evn) {
    loadPage("/save" , ".mainBodyPg_2")
}

pg_1.addEventListener("click" , goToPg_1)

function goToPg_1(evn) {
    loadPage("/" , ".description")
}








function loadPage(url, selector) {
    request(new XMLHttpRequest());

    function request(xhr) {
        xhr.open('GET', 'http://localhost:8880/' + url, true);
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