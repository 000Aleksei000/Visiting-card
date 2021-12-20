const captchaBtn = document.getElementById('captchaBtn');
let captchaInput = document.getElementById('captchaInput');
let captchaImg = document.getElementById('captchaImg');
let interactive = document.getElementById('interactive');


captchaBtn.addEventListener('click', validateCaptcha);

function validateCaptcha(evt) {
    let captchaInpStr = captchaInput.value;
    captchaInput.value = "";
    let xhr = new XMLHttpRequest();
    xhr.open("GET" , '/captcha/getCaptcha')
    xhr.send();
    xhr.onreadystatechange = function() {
        if(xhr.readyState === 4 && xhr.status === 200) {
            let resp = JSON.parse(xhr.responseText);
            console.log(resp.code)
            console.log(captchaInpStr);
            if (resp.code === captchaInpStr) {
                interactive.innerHTML = "Мои поздравления! Вы ввели правильный код."
            } else {
                captchaImg.src = "/captcha/getImg?" + new Date().getTime();
                document.getElementById('wrongCaptchaMsg').innerText = "Код введен не верно, повторите попытку";
            }
        }
    }
}