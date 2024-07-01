
var noti = document.getElementById('noti')
var message = document.getElementById('message')

window.addEventListener("DOMContentLoaded", function () {
    if (!noti && !message) return;
    showPopup(message.innerText, noti.innerText)

});


document.getElementById("close-popup").addEventListener("click", function () {
    console.log("called")
    hidePopup();
    window.location.href = "/employee-list"
});