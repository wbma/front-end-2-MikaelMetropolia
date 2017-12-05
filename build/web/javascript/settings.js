'use strict';
const pepeProfileImg = document.getElementById("profile-img");
const pepeSquareImg = document.getElementById("pepe");
const usernameInput = document.getElementById("username-input");
const usernameDisplay = document.getElementById("username-display");

const removeButton = document.getElementById("remove-photo");
const addButton = document.getElementById("add-photo");
const saveButton = document.getElementById("save-settings");

const settingsForm = document.querySelector("#settingsForm");

console.log(usernameInput.value);


removeButton.addEventListener('click', () => {
    pepeProfileImg.src = "resources/default.jpg";
    console.log(pepeProfileImg.src);
    pepeSquareImg.src = "resources/default.jpg";
});

addButton.addEventListener('click', () => {
    pepeProfileImg.src = "resources/pepe.png";
    pepeSquareImg.src = "resources/pepe.png";
});

saveButton.addEventListener('click', () => {
    if (usernameInput.value.length < 20 && usernameInput.value != "") {
    const name = usernameInput.value;
    usernameDisplay.innerText = name;
    usernameInput.value = "";
    } else {
    const popup = document.getElementById("myPopup");
    popup.classList.toggle("show");
    }
});

settingsForm.addEventListener("submit", function(evt) {
        evt.preventDefault();
        alterUserStats();
});

function alterUserStats() {
    
    const email = settingsForm.elements[0].value;
    const alias = settingsForm.elements[1].value;
    const pw = settingsForm.elements[2].value;
    const pw2 = settingsForm.elements[3].value;

    const request = { 
    headers: { 'Content-Type': 'application/x-www-form-urlencoded',  
    'Cookie': document.cookie}, // not sure if this is how this works...
    method: 'POST',
    credentials: 'same-origin',
    body: `newEmail=${email}&newAlias=${alias}&newPw=${pw}&newPw2=${pw2}`
    // NOTE: could be done with a FormData object instead in a much easier way..?
    };
    
    fetch('App/ProfileService/AlterUserStats', request).then((response) => {
    if(response.ok) {
        return response.json();
    }
    throw new Error('Network response was not ok.');  
        
    }).then((myJson) => {

        if (myJson.status === 'addedComp') {
            
            // TODO: display a msg about successfully adding a composition
            // TODO: I'm not sure which screen should open afterwards... If we want to 'go to' the composition (detailed view),
            // then we need to return more info from the method (addComp() in CompService.java)
        } else {
            
            // TODO: display a msg about failing to add the composition
        }
    }).catch(function(error) {
        console.log('There has been a problem with your fetch operation: ' + error.message);
}); // end fetch()
    
    
    
    
} // end alterUserStats()









