'use strict';

// TODO: catch the form fields and prevent regular form action
 
function addComp() {

    const request = { 
        headers: { 'Content-Type': 'application/x-www-form-urlencoded',  
        'Cookie': document.cookie}, // not sure if this is how this works...
        method: 'POST',
        credentials: 'same-origin',
        body: `` // TODO: insert values from the form fields (title, author, etc)
    };
    
    fetch('App/CompService/AddComp', request).then((response) => {
        return response.json();
        // TODO: need to catch a possible network error here...
    }).then((myJson) => {

        if (myJson.status === 'addedComp') {
            
            // TODO: display a msg about successfully adding a composition
        } else {
            
            // TODO: display a msg about failing to add the composition
        }
    }); // end fetch()
} // end addComp()
