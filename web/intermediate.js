const buttonElement2 = document.getElementById("test-button-level-2");
const compList2 = document.getElementById('image-wrapper-2');

buttonElement2.addEventListener('click', () => {
    addNewComposition (compList2);
});

const addNewComposition = (list) => {
list.innerHTML += ' <div class="composition"> <div class="play-comp-title-level-2">Anh. 115 Minuet <br> J.S.Bach </div> <div class="play-comp-image"><img src="play-button.png"></div> </div> </div> ';
};
