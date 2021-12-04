// /*Отложенный старт джава скрипта, так как элементов изначально нет на странице*/
// let elementById = document.getElementById("info_2");
//
// elementById.addEventListener("click", startJs);
//
// function startJs(evt) {
//     let timer = setTimeout(startJsWithTimer, 700, evt);
// }
// /*Основная логика скрипта*/


    let name, lastName, age, town, country;

    let form_inp_1, form_inp_2, form_inp_3, form_inp_4, form_inp_5, form_btn , countTowns, townsList, countriesList,
    countCountries;

    form_inp_1 = document.getElementById("form_inp_1")
    form_inp_2 = document.getElementById("form_inp_2")
    form_inp_3 = document.getElementById("form_inp_3")
    form_inp_4 = document.getElementById("form_inp_4")
    form_inp_5 = document.getElementById("form_inp_5");
    form_btn = document.getElementById("pg_2_form_btn");
    countTowns = document.getElementById("countTowns");
    townsList = document.getElementById("townsListId");
    countriesList = document.getElementById('countryListId');
    countCountries = document.getElementById('countCountries');
    form_btn.addEventListener("click", sendForm);

    function sendForm(evt) {
        getInputValue();
        let people = createPeople(name, lastName, age, town, country);
        let ajax = new XMLHttpRequest();
        ajax.open("PUT", "http://localhost:8880/service/savePeople", true);
        ajax.setRequestHeader('Content-type', 'application/json');
        ajax.send(JSON.stringify(people));
        ajax.addEventListener('readystatechange', function () {
            if (this.readyState === 4 && this.status === 200) {
                let townsAndCountries = JSON.parse(this.responseText);
                console.log(townsAndCountries);
                countTowns.innerHTML = townsAndCountries.towns.length;
                countCountries.innerHTML = townsAndCountries.countries.length;
                let townsUl = createUlTown(townsAndCountries.towns);
                let coutriesUl = createUlCountries(townsAndCountries.countries);
                console.log(coutriesUl)
                townsList.innerHTML = '';
                townsList.insertAdjacentElement('afterbegin', townsUl);
                countriesList.innerHTML = '';
                countriesList.insertAdjacentElement('afterbegin', coutriesUl);
            }
        });
    }

    function createUlTown(towns) {
        let ul = document.createElement('ul');
        for (let i = 0; i < towns.length; i++) {
            li = document.createElement("li");
            li.innerHTML = towns[i];
            ul.insertAdjacentElement('beforeend', li);
        }
        return ul;
    }

    function createUlCountries(countries) {
        let ul = document.createElement('ul');
        for (let i = 0; i < countries.length; i++) {
            li = document.createElement("li");
            li.innerHTML = countries[i];
            ul.insertAdjacentElement('beforeend', li);
        }
        return ul;
    }


    function getInputValue() {
        name = form_inp_1.value;
        lastName = form_inp_2.value;
        age = form_inp_3.value;
        town = form_inp_4.value;
        country = form_inp_5.value;
        form_inp_1.value = '';
        form_inp_2.value = '';
        form_inp_3.value = '';
        form_inp_4.value = '';
        form_inp_5.value = '';

}

function createPeople(name, lastName, age, town, country) {
    return {
        name,
        lastName,
        age,
        town,
        country,
    }
}

