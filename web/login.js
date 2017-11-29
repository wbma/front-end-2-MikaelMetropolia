'use strict';

const loginForm = document.querySelector('#loginForm');
const signUpForm = document.querySelector('#signUpForm');

loginForm.addEventListener("submit", function(evt) {
        evt.preventDefault();
        login();
});

/*
signUpForm.addEventListener("submit", function(evt) {
        evt.preventDefault();
        signup();
});
*/

function login() {
    
    const settings = { 
        method: 'POST',
        credentials: 'same-origin'
    };
    
    fetch('DB/RestfulDbService/LogIn', settings).then((response) => {
        return response.json();
        // TODO: need to catch a possible network error here...
    }).then((myJson) => {

        if (myJson.status === 'loggedIn') {
            
            // NOTE: might need to convert the id from int to String !
            document.cookie = "id=" + myJson.id + "; path=/"; // store the user's id in a global cookie for the duration of the session
            // TODO: display a msg about successfully logging in
            // TODO: make the login/register buttons invisible and replace with a 'logout' button
            // TODO: possibly store the other values in the cookie as well (alias etc)... more potential for erros that way.
            // Yet without that, we'll have to do a database operation each time the user enters the profile page, etc.
        }
        else if (myJson.status === 'wrongUserName') {
            console.log("wrong username, bozo! gratz on your working app, tho!");
            // TODO: display a msg about wrong username
        } else if (myJson.status === 'wrongPw') {
            // TODO: display a msg about wrong password
        }
        // NOTE: if we get this far, other stuff should never happen, but it's good to have 
        // the last else-if just to see the underlying status that makes it occur

    }); // end fetch()
} // end login()

function signUp() {
    
    
    
    
    
}
