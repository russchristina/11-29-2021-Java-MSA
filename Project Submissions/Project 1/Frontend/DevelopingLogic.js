let mainContainer = document.getElementById('mainContainer')
let loginButton = document.getElementById('loginButton')
let loginForm = document.getElementById('loginForm')

/* 
 * Log in form handling
 */

/*
let newButton = document.createElement('button')
newButton.innerHTML = "Press Me!"
mainContainer.append(newButton)
newButton.addEventListener('click', () => {
    location.reload(true)
})
*/


/*
xhr.onreadystatechange = function checkForReceivedData(){
    if(xhr.readyState === 4 && xhr.status === 200){ 
        console.log(xhr.response)
        
        let myData = JSON.parse(xhr.response)
        console.log(myData)

        JSON.stringify()

        console.log(myData.username)

        for(let request of myData.requests){
            console.log(request)
        }
    }
}
*/
function fetchData() {
    let url = 'http://localhost:7777/verify'
    let loginData = new FormData(loginForm)

    fetch(url, {
        method: "POST", 
        body: loginData
    })

    .then(response => response.text())
    .then(text => {
        try {
            const data = JSON.parse(text)
            console.log(data)
            window.alert('Log in successful')
        } catch {
        window.alert(text)
        console.log(text)
        }
    })

    .catch(() => {window.alert('Oops.. Something happened')})

    return false
}

async function getAllRequestData(){
    let url = 'http://localhost:7777/requests'
    let response_body = await fetch(url)
    let data = await response_body.json()
    console.log(data)
}