'use strict';
const submitButton = document.getElementById("test1234");
const submitButton1 = document.getElementById("test123");
/*  ENABLE THIS WHEN DONE TESTING
if (document.cookie.length === 0) {
    window.location.href = "LogInPage.html";
}
*/
/* ---------------GETTING SHEET NAME ---------------*/

document.getElementById('sheet-upload').onchange = uploadOnChange;

function uploadOnChange() {
    var filename = this.value;
    var lastIndex = filename.lastIndexOf("\\");
    if (lastIndex >= 0) {
        filename = filename.substring(lastIndex + 1);
    }
    document.getElementById('sheet-form').value = filename;
}



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
number1.onkeypress = number.onpaste = checkInput;

function checkInput (e) {
        var e = e || event;
        var char = e.type == 'keypress'
            ? String.fromCharCode(e.keyCode || e.which)
            : (e.clipboardData || window.clipboardData).getData('Text');
        if (/[^\d]/gi.test(char)) {
            return false;
        }
}



submitButton.addEventListener('click', () => {
    validateFields();
});

submitButton1.addEventListener('click', () => {
    event.preventDefault();
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

    const patternUsername = new RegExp("^[a-zA-Z0-9]+$");
    const patternYoutubeURL = new RegExp("^https\\:\\/\\/www\\.youtube\\.com\\/\\S+$");
    const testTitle = patternUsername.test(title);
    const testAuthor = patternUsername.test(author);
    const testYoutubeURL = patternYoutubeURL.test(video);

if (testTitle && testAuthor && testYoutubeURL && length <= 600 && year <= 2020 && year >= 1500 && pages !== 0 && diff !== 'null') {
    console.log("Working");
    addComp();
}
console.log("something is wrong");
};










