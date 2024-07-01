const currentURL = new URL(window.location.href);
const createStatus = currentURL.searchParams.get('create');
const updateStatus = currentURL.searchParams.get('update')

function showPopup() {
    var popup = document.getElementById("popup");
    var popupMessage = document.getElementById("popup-message");

    if (createStatus) {
        popupMessage.innerText = createStatus == "true" ? "Create employee successfully!" : "Error! Can not create the employee";
    }
    if (updateStatus) {
        popupMessage.innerText = updateStatus == "true" ? "The employee was updated!" : "Error! Can not update the employee";
    }
    popup.style.display = "block";
}

function hidePopup() {
    var popup = document.getElementById("popup");
    popup.style.display = "none";
}

window.addEventListener("DOMContentLoaded", function () {
    if (!createStatus && !updateStatus) return;
    // showPopup(message.innerText)
    showPopup()

});

document.getElementById("close-popup").addEventListener("click", function () {
    hidePopup();
    window.location.href = "/employee-list"
});


function toggleRowBackground(checkbox) {
    const row = checkbox.closest('tr');
    if (checkbox.checked) {
        row.classList.add('selected');
    } else {
        row.classList.remove('selected');
    }
}