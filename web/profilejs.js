'use strict';

const element = document.getElementById('arrow1'),
style = window.getComputedStyle(element),
right1 = style.getPropertyValue('transform');

const element2 = document.getElementById('arrow2'),
style2 = window.getComputedStyle(element2),
right2 = style.getPropertyValue('transform');

const element3 = document.getElementById('arrow3'),
style3 = window.getComputedStyle(element3),
right3 = style.getPropertyValue('transform');

const element4 = document.getElementById('arrow4'),
style4 = window.getComputedStyle(element4),
right4 = style.getPropertyValue('transform');

const dropElement = document.getElementById('dropdown');
const dropElement2 = document.getElementById('dropdown2');
const dropElement3 = document.getElementById('dropdown3');
const dropElement4 = document.getElementById('dropdown4');
const myElement = document.querySelector("#pepe-menu-id");

const button3 = document.getElementById("uploads");
const button4 = document.getElementById("uploads2");
const button5 = document.getElementById("uploads3");
const button6 = document.getElementById("uploads4");
const pepeButton = document.getElementById("pepe");

myElement.style.display = 'none';

button3.addEventListener('click', () => {
    hideMenu(right1, element, dropElement);
});
button4.addEventListener('click', () => {
    hideMenu(right2, element2, dropElement2);
});
button5.addEventListener('click', () => {
    hideMenu(right3, element3, dropElement3);
});
button6.addEventListener('click', () => {
    hideMenu(right4, element4, dropElement4);
});

/*
hideMenu(right4, element4, dropElement4);

var arrowState1 = 0;
var arrowState2 = 0;
var arrowState3 = 0;
var arrowState4 = 0;

const hideMenu =  (arrow, element, dropElement, arrowState) => {
    console.log(arrowState);
    if (arrowState === 0) {
        element.style.transform = "rotate(45deg)";
        dropElement.style.display = "inline";
        arrowState++;
        console.log('Arrow down');
        console.log(arrowState);
    } else {
        element.style.transform = "rotate(-45deg)";
        dropElement.style.display = "none";
        arrowState--;
        arrow = 'rotate(-45deg)';
        console.log('Arrow up');
        console.log(arrowState);
    }
    console.log(arrowState);
};
*/
pepeButton.onclick = function () {
    if (myElement.style.display == 'none') {
        myElement.style.display = 'inline';
  } else {
     myElement.style.display = 'none';
    }
};



const hideMenu =  (arrow, element, dropElement) => {
console.log(arrow);
    if (arrow == 'rotate(-45deg)' || arrow == 'matrix(0.707107, -0.707107, 0.707107, 0.707107, 0, 0)') {
        element.style.transform = "rotate(45deg)";
        arrow = 'rotate (45deg)';
        dropElement.style.display = "inline";
        console.log('Arrow down');
        console.log(arrow);
    } else {
        element.style.transform = "rotate(-45deg)";
        dropElement.style.display = "none";
        arrow = 'rotate(-45deg)';
        console.log('Arrow up');
        console.log(arrow);
    }
    console.log(arrow);
};









