'use strict';
const myElement = document.querySelector("#pepe-menu-id");
const pepeButton = document.getElementById("pepe");
myElement.style.display = 'none';

pepeButton.addEventListener('click', () => {
    if (myElement.style.display == 'none') {
        myElement.style.display = 'inline';
  } else {
     myElement.style.display = 'none';
    }
});













