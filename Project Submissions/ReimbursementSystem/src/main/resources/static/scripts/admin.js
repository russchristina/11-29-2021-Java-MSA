let past_requests = {
    pKey: 0,
    fKey: 0,
    approval: false
}

account = {
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
    pKey: 0,
    fKey: 0,
    amount: 0,
    memo: 0,
    approval: false
}

let allNewForms = []

getNewForms()

async function getNewForms(){
    await fetch("http://localhost:700/Forms/allnew")
    .then(response => response.json())
    .then(data => {
        allNewForms = data
        showForms()
    })
}

function showForms(){
    let formLabel = document.createElement('h2')
    formLabel.style.textAlign = 'center'
    document.body.appendChild(formLabel)

    if(allNewForms.length > 0){
        formLabel.innerText = "Administrative action required:"
        formLabel.style.color = 'red'
        
    }
    else {
        formLabel.innerText = "No action needed"

    }

    for(let x = 0; x < allNewForms.length; x++) {
        createFormCard(x)
    }
}

async function decision(){
    await fetch("http://localhost:700/Approval/decision", {
        method: 'POST',
        body: JSON.stringify(past_requests)
    })
    .then(response => response.json())
    .then(data => console.log(data))
}

async function updatePendingTable(accountName){
    await fetch("http://localhost:700/Forms/handled", {
        method: 'POST',
        body: JSON.stringify(reimbursementForm)
    })
    .then(response => response.json())
    .then(data => location.reload())
}

function createFormCard(x) {
    let formBody = document.createElement('div')
    formBody.className = 'form'
    let formId = document.createElement('p')
    let accountName = document.createElement('p')
    let requestedAmount = document.createElement('p')
    let formMemo = document.createElement('p')
    let accept = document.createElement('button')
    let deny = document.createElement('button')
    
    formBody.appendChild(accept)
    formBody.appendChild(deny)
    formBody.appendChild(formId)
    formBody.appendChild(accountName)
    formBody.appendChild(requestedAmount)
    formBody.appendChild(formMemo)
    document.body.appendChild(formBody)

    formBody.style.backgroundColor = 'rgb(248, 248, 245)'
    formBody.style.borderStyle = 'solid'
    formBody.style.borderWidth = '2px'
    formBody.style.width = '30em'
    formBody.style.marginLeft = 'auto'
    formBody.style.marginRight = 'auto'
    formBody.style.marginTop = '2em'
    formBody.style.padding = '1em'
    formBody.style.textAlign = 'center'
    formBody.style.paddingTop = '1em'
    formId.style.paddingLeft = '0'
    formId.style.paddingRight = '0'
    formId.style.borderBottomStyle = 'solid'
    formId.style.paddingBottom = '.5em'
    formId.style.margin47Top = '.5em'
    formId.style.fontSize = '30px'

    accept.style.backgroundColor = 'green'
    accept.style.width = '6em'
    accept.style.height = '3em'
    accept.style.marginRight = '1em'
    deny.style.backgroundColor = 'red'
    deny.style.width = '6em'
    deny.style.height = '3em'

    formId.innerText = "Form No." + allNewForms[x].pKey
    formBody.id = allNewForms[x].pKey
    accountName.innerText = "Account Id: " + allNewForms[x].fKey
    requestedAmount.innerText = "Amount: $" + allNewForms[x].amount
    formMemo.innerText = "Memo: \n\t" + allNewForms[x].memo

    accept.textContent = "Accept"
    deny.textContent = "Deny"

    accept.addEventListener('click', e => {
        reimbursementForm.pKey = parseInt(allNewForms[x].pKey)
        reimbursementForm.fKey = parseInt(allNewForms[x].fKey)
        reimbursementForm.amount = parseFloat(allNewForms[x].amount)
        reimbursementForm.memo = allNewForms[x].memo
        reimbursementForm.approval = true

        past_requests.pKey = 1
        past_requests.fKey = reimbursementForm.pKey
        past_requests.approval = true
        
        decision()
        updatePendingTable(accountName)
    })

    deny.addEventListener('click', e => {
        reimbursementForm.pKey = parseInt(allNewForms[x].pKey)
        reimbursementForm.fKey = parseInt(allNewForms[x].fKey)
        reimbursementForm.amount = parseFloat(allNewForms[x].amount)
        reimbursementForm.memo = allNewForms[x].memo
        reimbursementForm.approval = true

        past_requests.pKey = 1
        past_requests.fKey = reimbursementForm.pKey
        past_requests.approval = false

        decision()
        updatePendingTable(accountName)
    })
}

