let account = {
    emp_id:0,
    username:"",
    password:"",
    fName:"",
    lName:"",
    address:"",
    city:"",
    state:"",
    zip:0,
    social_number:"",
    balance:0.00,
    manager:false
}

getUser()

async function getUser() {
    await fetch("http://localhost:700/Account/user")
     .then(response => response.json())
     .then(data => {
        account = data
        if(account.manager === true){
            navDiv.appendChild(admin)
            navDiv.appendChild(statistics)
        
            admin.addEventListener('click', e => {
                location.href = "http://localhost:700/views/adminPage.html"
            })
        }
        
        })
}

document.getElementsByTagName('header')[0].style.marginTop = '2%'

let navDiv = document.createElement('div')
let requestReimbursement = document.createElement('h2')
requestReimbursement.className = 'options'
let viewAccount = document.createElement('h2')
viewAccount.className = 'options'
let admin = document.createElement('h2')
viewAccount.className = 'options'
let statistics = document.createElement('h2')

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
navDiv.style.cursor = 'default'
navDiv.id = 'navDiv'

requestReimbursement.innerText = 'Reimbursement Request'
viewAccount.innerText = 'View Account'
admin.innerText = "Admin"
statistics.innerText = 'Statistics'

document.body.appendChild(navDiv)
navDiv.appendChild(requestReimbursement)
navDiv.appendChild(viewAccount)

requestReimbursement.addEventListener('click', e => {
    location.href = 'http://localhost:700/views/form.html'
})

viewAccount.addEventListener('click', e => {
    location.href = 'http://localhost:700/views/account.html'
})
    
let menu = document.createElement('div')
let hamburger = document.createElement('img')

hamburger.src = '../images/hamburgericon.jpg'
hamburger.style.width = '4%'
hamburger.style.position = 'fixed'
hamburger.style.top = '0%'

document.body.appendChild(menu)
menu.appendChild(hamburger)

let hamburgerTracker = false
menu.addEventListener('click', e => {
    if (hamburgerTracker === false) {
        navDiv.style.visibility = 'visible'
    }
    else {
        navDiv.style.visibility = 'hidden'
    }
    hamburgerTracker = !hamburgerTracker
})


