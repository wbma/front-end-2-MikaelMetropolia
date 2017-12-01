'use strict';


const mainMenu = document.querySelector("#main-menu");
const submenu1 = document.getElementById("start-sub-menu-1");
const submenu2 = document.getElementById("start-sub-menu-2");
const arrowElement = document.getElementById("right");
const arrowElement2 = document.getElementById("right2");
const headerButton = document.getElementById("menu-button");
const submenu1Button = document.getElementById("sub-menu-button-1");
const submenu2Button = document.getElementById("sub-menu-button-2");

mainMenu.style.display = 'none';
submenu1.style.display = 'none';
submenu2.style.display = 'none';

headerButton.addEventListener('click', () => {
 if (mainMenu.style.display == 'none') {
        mainMenu.style.display = 'inline-block';
  } else {
     mainMenu.style.display = 'none';
     submenu1.style.display = 'none';
     submenu2.style.display = 'none';
     arrowElement.style.transform = 'rotate(-45deg)';
     arrowElement2.style.transform = 'rotate(-45deg)';
    }
});

submenu1Button.addEventListener('click', () => {
 if (submenu1.style.display == 'none') {
        submenu1.style.display = 'inline-block';
        arrowElement.style.transform = 'rotate(45deg)';
  } else {
     submenu1.style.display = 'none';
     arrowElement.style.transform = 'rotate(-45deg)';
    }
});

submenu2Button.addEventListener('click', () => {
 if (submenu2.style.display == 'none') {
        submenu2.style.display = 'inline-block';
        arrowElement2.style.transform = 'rotate(45deg)';
  } else {
     submenu2.style.display = 'none';
     arrowElement2.style.transform = 'rotate(-45deg)';
    }
});

