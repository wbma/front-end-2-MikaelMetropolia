'use strict';
const buttonElement1 = document.getElementById("test-button-level-1");
const compList1 = document.getElementById('image-wrapper-1');

buttonElement1.addEventListener('click', () => {
    addNewComposition (compList1);
});

const addNewComposition = (list) => {
list.innerHTML += ' <div class="composition"> <div class="play-comp-title-level-1">Anh. 115 Minuet <br> J.S.Bach </div> <div class="play-comp-image"><img src="play-button.png"></div> </div> </div> ';
};
