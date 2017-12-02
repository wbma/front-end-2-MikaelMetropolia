'use strict';
const myElement = document.querySelector("#pepe-menu-id");
const pepeButton = document.getElementById("pepe");
const pepeProfileImg = document.getElementById("profile-img");
const pepeSquareImg = document.getElementById("pepe");
const usernameInput = document.getElementById("username-input");
const usernameDisplay = document.getElementById("username-display");

const removeButton = document.getElementById("remove-photo");
const addButton = document.getElementById("add-photo");
const saveButton = document.getElementById("save-settings");

myElement.style.display = 'none';
console.log(usernameInput.value);
pepeButton.onclick = function () {
    if (myElement.style.display == 'none') {
        myElement.style.display = 'inline';
  } else {
     myElement.classList.toggle = 'pepe-menu-closed';
    }
};

removeButton.addEventListener('click', () => {
    pepeProfileImg.src = "default.jpg";
    pepeSquareImg.src = "default.jpg";
});

addButton.addEventListener('click', () => {
    pepeProfileImg.src = "pepe.png";
    pepeSquareImg.src = "pepe.png";
});

saveButton.addEventListener('click', () => {
    if (usernameInput.value.length < 20 && usernameInput.value != "") {
    const name = usernameInput.value;
    usernameDisplay.innerText = name;
    usernameInput.value = "";
    } else {
    const popup = document.getElementById("myPopup");
    popup.classList.toggle("show");
    }
});











