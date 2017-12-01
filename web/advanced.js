'use strict';

const buttonElement3 = document.getElementById("test-button-level-3");
const compList3 = document.getElementById('image-wrapper-3');

buttonElement3.addEventListener('click', () => {
    addNewComposition (compList3);
});

const addNewComposition = (list) => {
list.innerHTML += ' <div class="composition"> <div class="play-comp-title-level-3">Anh. 115 Minuet <br> J.S.Bach </div> <div class="play-comp-image"><img src="play-button.png"></div> </div> </div> ';
};
