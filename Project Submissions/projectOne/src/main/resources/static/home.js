console.log("script running")

let button = document.getElementById('submit_button')

function sendCredentials(){
    let xhr = new XMLHttpRequest()
    let url = 'http://localhost:8000/greendale/user/login'
    xhr.open('POST', url)

    let username = document.getElementById('username').value
    let password = document.getElementById('password').value

    let credentials = {username:username, password:password}
    console.log(credentials)

    
    let json_string = JSON.stringify(credentials)
    xhr.send(json_string)
    console.log(json_string)

    if(credentials = true){
        button.addEventListener('click', open("http://localhost:8000/empPortal.html", 'GET'))
    }
    else{
        let errorDiv = document.getElementById('error')
        let error = document.createElement('p')
        error.innerText = "Incorrect credentials."
        errorDiv.appendChild(error)
    } 
}



function findAllUsers(){
    let xhr = new XMLHttpRequest
    let url = 'http://localhost:8000/greendale/user/all'
    xhr.open('GET', url)

    xhr.addEventListener("readystatechange", function() {
        if(this.readyState === 4) {
          console.log(this.responseText);
          let retrievedUsers = JSON.parse(this.responseText)
          console.log(retrievedUsers)
        }
    })
    xhr.send();
}   


findAllUsers()


button.addEventListener('click', sendCredentials)