
var noti = document.getElementById('noti')
var message = document.getElementById('message')

console.log(message)

window.addEventListener("DOMContentLoaded", function () {
    if (!message && !message) return;
    // const value = noti.innerText;
    showPopup(message.innerText, noti.innerText)

});


document.getElementById("close-popup").addEventListener("click", function () {
    hidePopup();
    window.location.href = "/login"
});