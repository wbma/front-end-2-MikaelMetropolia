'use strict';
const myElement = document.getElementById("pepe-menu-id");
const pepeButton = document.getElementById("pepe");
const submitButton = document.getElementById("test1234");
myElement.style.display = 'none';

pepeButton.addEventListener('click', () => {
    if (myElement.style.display == 'none') {
        myElement.style.display = 'inline';
  } else {
     myElement.style.display = 'none';
    }
});
/* ----- Page options -------*/
  for (var i = 1; i <= 10; i++) {
        if (i === 1){
        const name = i + " page";
        const sel = document.getElementById("list-20");
        sel.options[sel.options.length] = new Option(name,i);
        } else {
       const name = i + " pages";
        const sel = document.getElementById("list-20");
        sel.options[sel.options.length] = new Option(name,i);
        }
}
/* ------- VALIDATION ---------*/


const number = document.getElementById('number');
const number1 = document.getElementById('number-1');
const newComposition = document.getElementsByClassName("inputfield");

number.onkeypress = number.onpaste = checkInput;

function checkInput (e) {
        var e = e || event;
        var char = e.type == 'keypress'
            ? String.fromCharCode(e.keyCode || e.which)
            : (e.clipboardData || window.clipboardData).getData('Text');
        if (/[^\d]/gi.test(char)) {
            return false;
        }
}





test1234.addEventListener('click', () => {
    validateFields();
});


const validateFields = () => {
    const title = newComposition[0].value;
    const author = newComposition[1].value;
    const length = newComposition[2].value;
    const year = newComposition[3].value;
    const pages = newComposition[4].options[newComposition[4].selectedIndex].value;
    const diff = newComposition[5].options[newComposition[5].selectedIndex].value;
    const video = newComposition[6].value;
    const sheet = newComposition[7].value;



    console.log(title);
    console.log(author);
    console.log(length);
    console.log(year);
    console.log(pages);
    console.log(diff);
    console.log(video);
    console.log(sheet);

};










