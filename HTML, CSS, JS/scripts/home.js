let button = document.getElementById('submit-button')

function sendCredentials(){
    //create xmlhttprequest object
    let xhr = newXMLHttpRequest()
    //create reference to string which represents URL for my resource
    let url = 'http://localhost:8000/user/auth'
    //need to open xmlhttprequest
    xhr.open('POST', url )
    
    let username = document.getElementById('username').value
    let password = document.getElementById('password').value

    let user = {name:username, pass:password}
    console.log(user) //check in that everything is working properly

    let json_string = JSON.stringify(user)

    xhr.send(json_string)
}

//sendCredentials()

button.addEventListener('click', sendCredentials)

