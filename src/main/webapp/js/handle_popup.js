// var noti = document.getElementById('noti')
// var message = document.getElementById('message')
//
// window.addEventListener("DOMContentLoaded", function () {
//     if (!message && !message) return;
//     // const value = noti.innerText;
//     showPopup(message.innerText, noti.innerText)
//
// });
//
//
// document.getElementById("close-popup").addEventListener("click", function () {
//     hidePopup();
//     window.location.href = "/login"
// });


function showPopup(message, noti) {
    var popup = document.getElementById("popup");
    var popupMessage = document.getElementById("popup-message");
    var popupNoti = document.getElementById("popup-noti");

    popupNoti.innerText = noti
    popupMessage.innerText = message;
    popup.style.display = "block";
}

function hidePopup() {
    var popup = document.getElementById("popup");
    popup.style.display = "none";
}