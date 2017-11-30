var element = document.getElementById('arrow1'),
style = window.getComputedStyle(element),
right1 = style.getPropertyValue('transform');

var element2 = document.getElementById('arrow2'),
style2 = window.getComputedStyle(element2),
right2 = style.getPropertyValue('transform');

var element3 = document.getElementById('arrow3'),
style3 = window.getComputedStyle(element3),
right3 = style.getPropertyValue('transform');

var element4 = document.getElementById('arrow4'),
style4 = window.getComputedStyle(element4),
right4 = style.getPropertyValue('transform');

var dropElement = document.getElementById('dropdown');
var dropElement2 = document.getElementById('dropdown2');
var dropElement3 = document.getElementById('dropdown3');
var dropElement4 = document.getElementById('dropdown4');
var myElement = document.querySelector("#pepe-menu-id");

var button3 = document.getElementById("uploads");
const button4 = document.getElementById("uploads2");
var button5 = document.getElementById("uploads3");
var button6 = document.getElementById("uploads4");
var pepeButton = document.getElementById("pepe");

myElement.style.display = 'none';

button3.onclick = function () {
    if (right1 == 'rotate(-45deg)' || right1 == 'matrix(0.707107, -0.707107, 0.707107, 0.707107, 0, 0)' ) {
        element.style.transform = "rotate(45deg)";
        dropElement.style.display = "inline";
        right1 = 'rotate(45deg)';
        console.log('Arrow down');
    } else {
        element.style.transform = "rotate(-45deg)";
        dropElement.style.display = "none";
        right1 = 'rotate(-45deg)';
        console.log('Arrow up');
    }
}

button4.addEventListener('click', () => {
    if (right2 == 'rotate(-45deg)' || right2 == 'matrix(0.707107, -0.707107, 0.707107, 0.707107, 0, 0)' ) {
        element2.style.transform = "rotate(45deg)";
        dropElement2.style.display = "inline";
        right2 = 'rotate(45deg)';
        console.log('Arrow down');
    } else {
        element2.style.transform = "rotate(-45deg)";
        dropElement2.style.display = "none";
        right2 = 'rotate(-45deg)';
        console.log('Arrow up');
    }
});

const piilotaValikko =  (nuoli, element, dropElement) => {
    if (nuoli == 'rotate(-45deg)' || nuoli == 'matrix(0.707107, -0.707107, 0.707107, 0.707107, 0, 0)' ) {
        element.style.transform = "rotate(45deg)";
        dropElement.style.display = "inline";
        nuoli = 'rotate(45deg)';
        console.log('Arrow down');
    } else {
        element.style.transform = "rotate(-45deg)";
        dropElement.style.display = "none";
        nuoli = 'rotate(-45deg)';
        console.log('Arrow up');
    }
}

button5.addEventListener('click', () => {
    piilotaValikko(right3, element3, dropElement3);
});

button6.onclick = function () {
    if (right4 == 'rotate(-45deg)' || right3 == 'matrix(0.707107, -0.707107, 0.707107, 0.707107, 0, 0)' ) {
        element4.style.transform = "rotate(45deg)";
        dropElement4.style.display = "inline";
        right4 = 'rotate(45deg)';
        console.log('Arrow down');
    } else {
        element4.style.transform = "rotate(-45deg)";
        dropElement4.style.display = "none";
        right4 = 'rotate(-45deg)';
        console.log('Arrow up');
    }
}

pepeButton.onclick = function () {
    if (myElement.style.display == 'none') {
        myElement.style.display = 'inline';
  } else {
     myElement.style.display = 'none';
    }
}
