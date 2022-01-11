
let navDiv = document.createElement('div')
let requestReimbursement = document.createElement('h2')
let viewAccount = document.createElement('h2')

navDiv.style.visibility = 'hidden'
navDiv.style.backgroundColor = 'white'
navDiv.style.borderStyle = 'solid'
navDiv.style.position = 'fixed'
navDiv.style.width = '300px'
navDiv.style.height = '600px'
navDiv.style.left = 'auto'
navDiv.style.top = 'auto'
navDiv.style.right = '0%'
navDiv.style.bottom = 'auto'
navDiv.style.textAlign = 'center'
navDiv.id = 'navDiv'

requestReimbursement.innerText = 'Reimbursement Request'
viewAccount.innerText = 'View Account'

document.body.appendChild(navDiv)
navDiv.appendChild(requestReimbursement)
navDiv.appendChild(viewAccount)

requestReimbursement.addEventListener('click', e => {
    location.href = 'http://localhost:700/views/form.html'
})

let menu = document.createElement('div')
let hamburger = document.createElement('img')

hamburger.src = '../images/hamburgericon.jpg'
hamburger.style.width = '2em'
hamburger.style.position = 'fixed'
hamburger.style.top = '10%'

document.body.appendChild(menu)
menu.appendChild(hamburger)

let hamburgerTracker = false
menu.addEventListener('click', e => {
    if(hamburgerTracker === false) {
        navDiv.style.visibility = 'visible'
    }
    else{
        navDiv.style.visibility = 'hidden'
    }
    hamburgerTracker = !hamburgerTracker
})