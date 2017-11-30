const buttonElement = document.getElementById("test-button");
const compList = document.getElementById('image-wrapper');

buttonElement.addEventListener('click', () => {
    addNewComposition ();
});

const addNewComposition = () => {
compList.innerHTML += ' <div class="composition"> <div class="play-comp-title">Anh. 115 Minuet <br> J.S.Bach </div> <div class="play-comp-image"><img src="play-button.png"></div> </div> </div> ';

}