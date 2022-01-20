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

let reimbursementForm = {
    pKey:1,
    fKey:0,
    amount:0,
    memo:"",
    approval:false
}

async function getUser() {
   await fetch("http://localhost:700/Account/user")
    .then(response => response.json())
    .then(data => account = data)
}

async function postForm() {
    await fetch("http://localhost:700/Forms/reimbursement", {
        method: 'POST',
        body: JSON.stringify(reimbursementForm)
    }).then(response => response.json())
    .then(data => {
        reimbursementForm = data
        location.href = 'http://localhost:700/views/landing.html'
    })
}

function setupForm(){
    getUser()

    let container = document.body
    container.style.display = "grid"
    container.style.gridTemplateColumns = "subgrid auto auto auto"

    let formElement = document.getElementById("reimbursement_form")
    formElement.style.display = "grid"
    formElement.style.gridTemplateRows = "auto auto auto"
    formElement.style.gridColumnStart = "1"
    formElement.style.gridColumnEnd = "2"
    formElement.style.gridRowStart = "2"
    formElement.style.marginLeft = "auto"
    formElement.style.marginRight = "auto"

    let amountInput = document.getElementById("amount")
    amountInput.style.marginLeft = "auto"
    amountInput.style.marginLeft = "auto"
    amountInput.style.marginTop = "2em"
    amountInput.style.marginBottom = "2em"
    amountInput.style.paddingTop = "0%"

    let memo = document.getElementById("memo")
    memo.style.width = "30em"
    memo.style.height = "15em"
    memo.style.value = "Reason for refund: "

    let subBut = document.getElementById("reimbursement-button")
    subBut.style.marginTop = "1em"

    subBut.addEventListener('click', e => {
        reimbursementForm.fKey = account.emp_id
        reimbursementForm.amount = parseFloat(document.getElementById("amount").value)
        reimbursementForm.memo = document.getElementById("memo").value
        
        if(reimbursementForm.amount > 0){
        postForm()
        }

        console.log(reimbursementForm)
    })
}

setupForm()