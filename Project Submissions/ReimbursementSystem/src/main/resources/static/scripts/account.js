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

formList = []

getUser()

async function getUser() {
    await fetch("http://localhost:700/Account/user")
     .then(response => response.json())
     .then(data => {
        account = data
        accountName.innerText = account.fName + " " + account.lName
        address.innerText = account.address + "\n" + account.city + ", " 
                            + account.state + "\n" + account.zip;
        balance.innerText = Intl.NumberFormat('en-US', {
            style: "currency",
            currency: "USD"
        }).format(account.balance)
        document.body.appendChild(accountCard)
        document.body.appendChild(options)
    })
}

async function getAllForms() {
    await fetch("http://localhost:700/Forms/all", {
        method: 'POST',
        body: JSON.stringify(account)
    })
    .then(response => response.json())
    .then(data => {
        formList = data
        document.body.appendChild(formContainer)

        for(x = 0; x < formList.length; x++){
            createFormCard(x)
        }
    })
}


let accountCard = document.createElement('div')
let accountName = document.createElement('h2')
let balance = document.createElement('h2')
let address = document.createElement('h2')

accountCard.appendChild(accountName)
accountCard.appendChild(balance)
accountCard.appendChild(address)

accountCard.style.borderStyle = 'solid'
accountCard.style.borderWidth = '1px'
accountCard.style.width = '75%'
accountCard.style.marginLeft = 'auto'
accountCard.style.marginRight = 'auto'
accountCard.style.marginTop = '1em'
accountCard.style.textAlign = 'center'
accountCard.style.backgroundColor = 'rgb(248, 248, 245)'

let options = document.createElement('div')
let pending = document.createElement('h2')
let all = document.createElement('h2')

options.appendChild(all)

let clickerTracker = false
all.addEventListener('click', e => {
    if(clickerTracker == true){
        document.body.appendChild(formContainer)
    } else {
        getAllForms()
        document.body.appendChild(formContainer)
        clickerTracker = true
    }
})


all.innerText = 'View Your Requests'
all.style.textAlign = "center"
function createFormCard(x) {
    let formBody = document.createElement('div')
    formBody.className = 'form'
    let formId = document.createElement('p')
    let accountName = document.createElement('p')
    let requestedAmount = document.createElement('p')
    let formMemo = document.createElement('p')
    
    formBody.appendChild(formId)
    formBody.appendChild(accountName)
    formBody.appendChild(requestedAmount)
    formBody.appendChild(formMemo)
    formContainer.appendChild(formBody)

    if(formList[x].approval == true){
        formBody.style.backgroundColor = 'rgb(248, 248, 245)'
    } else formBody.style.backgroundColor = '#C4C3C3'

    
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

    formId.innerText = "Form No." + formList[x].pKey
    formBody.id = formList[x].pKey
    accountName.innerText = "Account Id: " + formList[x].fKey
    requestedAmount.innerText = "Amount: $" + formList[x].amount
    formMemo.innerText = "Memo: \n\t" + formList[x].memo
}

let formContainer = document.createElement('div')
formContainer.id = 'container'
let exitButton = document.createElement('button')
exitButton.innerText = 'X'
formContainer.appendChild(exitButton)
formContainer.style.padding = '2em'
formContainer.style.height = '50em'
formContainer.style.position = 'fixed'
formContainer.style.top = '2em'
formContainer.style.right = '1em'
formContainer.style.borderStyle = 'solid'
formContainer.style.backgroundColor = 'white'
formContainer.style.overflow = 'auto'
document.body.style.cursor = 'default'

exitButton.addEventListener('click', e => {
    document.body.removeChild(formContainer)
})