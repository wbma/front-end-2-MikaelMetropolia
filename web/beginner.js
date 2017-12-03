'use strict';

const favoriteElement = document.getElementById("favorite-count");
const thumbElement = document.getElementById("like-count");

const favoriteButton = document.getElementById("favorite-button");
const thumbButton = document.getElementById("thumb-button");
var likeCount;
var favoriteCount;


thumbButton.addEventListener('click', () => {
    likeCount = thumbElement.innerHTML.toString();
    if (thumbButton.getAttribute("src") == 'thumb-white.png') {
        likeCount++;
        likeCount.toString;
        thumbElement.innerHTML = likeCount;
        thumbButton.src = 'thumb-green.png';
    } else {
       likeCount--;
       likeCount.toString;
       thumbElement.innerHTML = likeCount;
       thumbButton.src = 'thumb-white.png';
    }
   });

favoriteButton.addEventListener('click', () => {
    favoriteCount = favoriteElement.innerHTML.toString();
    if (favoriteButton.getAttribute("src") == 'favorite-white.png') {
        favoriteCount++;
        favoriteCount.toString;
        favoriteElement.innerHTML = favoriteCount;
        favoriteButton.src = 'favorite-red.png';
    } else {
       favoriteCount--;
       favoriteCount.toString;
       favoriteElement.innerHTML = favoriteCount;
       favoriteButton.src = 'favorite-white.png';
    }
   });



/*
const compList1 = document.getElementById('image-wrapper-1');
const buttonElement1 = document.getElementById("test-button-level-1");

const addNewComposition = (list) => {
list.innerHTML += '<div class="composition"> \n' +
                       '<div class="composition-title-level-1">Anh. 115 Minuet <br> J.S.Bach </div> \n' +
                       '<div class ="composition-image"><img src="play-button.png"></div> \n' +
                       '<div class ="composition-stats"> \n' +
                       '<div class="stat-img"><img src="thumb-white.png"></div> \n' +
                       '<div class="stat-text"><p id="like-count"> 1</p> </div> \n' +
                       '<div class="stat-img"><img src="like-white.png"> </div> \n' +
                       '<div class="stat-text"><p id="favorite-count"> 1</p> </div> \n' +
                       '<div class="stat-img"><img src="test123.png"></div> \n' +
                       '<div class="stat-text"><p> 1 </p></div> \n' +
                       '</div> \n' +
                 '</div>';
};
*/