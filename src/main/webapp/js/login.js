var noti = document.getElementById('noti')


window.addEventListener("DOMContentLoaded", function () {
    if(!noti) return;
    const value = noti.innerText;
    showPopup(value)

});


document.getElementById("close-popup").addEventListener("click", function () {
    hidePopup();
});


function showPopup(message) {
    var popup = document.getElementById("popup");
    var popupMessage = document.getElementById("popup-message");

    popupMessage.innerText = message;
    popup.style.display = "block";
}

function hidePopup() {
    var popup = document.getElementById("popup");
    popup.style.display = "none";
}