'use strict';

const loginForm = document.querySelector('#loginForm');
const signUpForm = document.querySelector('#signUpForm');
const loginAliasInput = document.querySelector('#loginName');
const loginPwInput = document.querySelector('#loginPw');

const signupAliasInput = signUpForm.elements[0];
const signupEmailInput = signUpForm.elements[1];
const signupPwInput = signUpForm.elements[2];
const signupPw2Input = signUpForm.elements[3];



loginForm.addEventListener("submit", function(evt) {
        evt.preventDefault();
        login();
});


signUpForm.addEventListener("submit", function(evt) {
        evt.preventDefault();
        signup();
});

function login() {

    const request = { 
        headers: { 'Content-Type': 'application/x-www-form-urlencoded'},
        method: 'POST',
        credentials: 'same-origin',
        body: `loginUsername=${loginAliasInput.value}&loginPassword=${loginPwInput.value}`
    };
    
    fetch('App/UserService/LogIn', request).then((response) => {
        return response.json();
        // TODO: need to catch a possible network error here...
    }).then((myJson) => {

        if (myJson.status === 'loggedIn') {
            
            console.log("huu! got to loggedin!");
            
            // NOTE: might need to convert the id from int to String !
            document.cookie = "id=" + myJson.id + "; path=/"; // store the user's id in a global cookie for the duration of the session
            // TODO: display a msg about successfully logging in
            // TODO: make the login/register buttons invisible and replace with a 'logout' button
            // TODO: possibly store the other values in the cookie as well (alias etc)... more potential for erros that way.
            // Yet without that, we'll have to do a database operation each time the user enters the profile page, etc.
        }
        else if (myJson.status === 'wrongUsername') {
            console.log("wrong username, bozo! gratz on your working app, tho!");
            // TODO: display a msg about wrong username
        } else if (myJson.status === 'wrongPw') {
            
            console.log("huu! got to wrongpw!");
            // TODO: display a msg about wrong password
        }
        // NOTE: if we get this far, other stuff should never happen, but it's good to have 
        // the last else-if just to see the underlying status that makes it occur

        console.log("Status: " + myJson.status);
    }); // end fetch()
} // end login()

function signup() {
    
    const request = { 
        headers: { 'Content-Type': 'application/x-www-form-urlencoded'},
        method: 'POST',
        credentials: 'same-origin',
        body: `username=${signupAliasInput.value}&email=${signupEmailInput.value}&password=${signupPwInput.value}&password2=${signupPw2Input.value}`
    };
    
    fetch('App/UserService/SignUp', request).then((response) => {
        return response.json();
        // TODO: need to catch a possible network error here...
    }).then((myJson) => {

        if (myJson.status === 'loggedIn') {
            
            console.log("made a new user! woohoo!");
            // NOTE: might need to convert the id from int to String !
            document.cookie = "id=" + myJson.id + "; path=/"; // store the user's id in a global cookie for the duration of the session
            // TODO: display a msg about successfully logging in
            // TODO: make the login/register buttons invisible and replace with a 'logout' button
            // TODO: possibly store the other values in the cookie as well (alias etc)... more potential for erros that way.
            // Yet without that, we'll have to do a database operation each time the user enters the profile page, etc.
        }
        else if (myJson.status === 'usernameTaken') {
            
            // TODO: display a msg about taken username
        } else if (myJson.status === 'emailTaken') {
            
            // TODO: display a msg about taken email
        }
    }); // end fetch()
} // end signup()
