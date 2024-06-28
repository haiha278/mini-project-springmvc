const currentURL = new URL(window.location.href);
const createStatus = currentURL.searchParams.get('create');
console.log("createStatus :", createStatus);


function showPopup() {
    console.log("called")
    var popup = document.getElementById("popup");
    var popupMessage = document.getElementById("popup-message");
    popupMessage.innerText = createStatus == "true" ? "Create employee successfully!" : "Error! Can not create the employee";
    popup.style.display = "block";
}

function hidePopup() {
    var popup = document.getElementById("popup");
    popup.style.display = "none";
}

window.addEventListener("DOMContentLoaded", function () {
    if (!createStatus) return;
    showPopup(message.innerText)

});

document.getElementById("close-popup").addEventListener("click", function () {
    hidePopup();
    window.location.href = "/employee-list"
});