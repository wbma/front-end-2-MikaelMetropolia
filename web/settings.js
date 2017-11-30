'use strict';
const myElement = document.querySelector("#pepe-menu-id");
const pepeButton = document.getElementById("pepe");
const pepeProfileImg = document.getElementById("profile-img");
const pepeSquareImg = document.getElementById("pepe");
const removeButton = document.getElementById("remove-photo");
const addButton = document.getElementById("add-photo");

myElement.style.display = 'none';

pepeButton.onclick = function () {
    if (myElement.style.display == 'none') {
        myElement.style.display = 'inline';
  } else {
     myElement.style.display = 'none';
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







