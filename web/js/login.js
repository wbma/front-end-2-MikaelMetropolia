

const loginForm = document.querySelector('#loginForm');
const signUpForm = document.querySelector('#signUpForm');
const loginAliasInput = document.querySelector('#loginName');
const loginPwInput = document.querySelector('#loginPw');

const signupAliasInput = signUpForm.elements[0];
const signupEmailInput = signUpForm.elements[1];
const signupPwInput = signUpForm.elements[2];
const signupPw2Input = signUpForm.elements[3];

const SPECIALS = "\\!\\$\\&\\%\\+\\#\\\\\\{\\}\\@\\/\\[\\]\\*\\;\\^\\'\\~\\<\\>\\|\\\"\\=\\`\\(\\)";
/*---------------SERVER SIDE VALIDATION---------------- */

const buttonSubmit = document.getElementById('submit-values');

buttonSubmit.addEventListener('click', () => {
    const signUpForm = document.querySelector('#signUpForm');
    const signupAliasInput = signUpForm.elements[0].value;
    const signupEmailInput = signUpForm.elements[1].value;
    const signupPwInput = signUpForm.elements[2].value;
    const signupPw2Input = signUpForm.elements[3].value;

    var patternUsername = new RegExp("^[a-zA-Z0-9]+$");
    var patternEmail = new RegExp("^[^"+SPECIALS+"\\d\\s+][a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]{2,3}$");
    var patternPassword = new RegExp("^(?=.*["+SPECIALS+"]{2,})(?!.*\\s+)(?=.*[a-z]{2,})(?=.*[A-Z]{2,})(?=.*\\d{2,}).*$");
    var res = patternUsername.test(signupAliasInput);
    var rest = patternEmail.test(signupEmailInput);
    var rester = patternPassword.test(signupPwInput);
    var rester1 = patternPassword.test(signupPw2Input);
    const match = 0;


    if (signupPwInput == signupPw2Input) {
        const match = 1;
    }

    if (res && rest && rester && match === 1) {
        signup();
        console.log("Working");
    }
    console.log(res);
    console.log(rest);
    console.log(rester);
    console.log(match);
});

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

    const request = { 
        headers: { 'Content-Type': 'application/x-www-form-urlencoded'},
        method: 'POST',
        credentials: 'same-origin',
        body: `loginUsername=${loginAliasInput.value}&loginPassword=${loginPwInput.value}`
    };
    
    fetch('App/LoginService/LogIn', request).then((response) => {   
        if(response.ok) {
            return response.json();
        }
        throw new Error('Network response was not ok.');
        
    }).then((myJson) => {

        if (myJson.status === 'loggedIn') {
            
            // NOTE: might need to convert the id from int to String !
            document.cookie = "id=" + myJson.id + "; path=/"; // store the user's id in a global cookie for the duration of the session
            // TODO: display a msg about successfully logging in
            // TODO: make the login/register buttons invisible and replace with a 'logout' button
            // TODO: possibly store the other values in the cookie as well (alias etc)... more potential for erros that way.
            // Yet without that, we'll have to do a database operation each time the user enters the profile page, etc.
        }
        else if (myJson.status === 'wrongUsername') {
            
            // TODO: display a msg about wrong username
        } 
        else if (myJson.status === 'wrongPw') {
            
            // TODO: display a msg about wrong password
        }
    }).catch(function(error) {
        console.log('There has been a problem with your fetch operation: ' + error.message);
}); // end fetch()
} // end login()

function signup() {
    
    const request = { 
        headers: { 'Content-Type': 'application/x-www-form-urlencoded'},
        method: 'POST',
        credentials: 'same-origin',
        body: `username=${signupAliasInput.value}&email=${signupEmailInput.value}&password=${signupPwInput.value}&password2=${signupPw2Input.value}`
    };
    
    fetch('App/LoginService/SignUp', request).then((response) => {
        if(response.ok) {
            return response.json();
        }
        throw new Error('Network response was not ok.');
        
    }).then((myJson) => {

        if (myJson.status === 'loggedIn') {
            
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
    }).catch(function(error) {
        console.log('There has been a problem with your fetch operation: ' + error.message);
    }); // end fetch()
} // end signup()
