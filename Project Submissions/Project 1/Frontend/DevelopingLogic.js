let mainContainer = document.getElementById('mainContainer')
let loginForm = document.getElementById('loginForm')

/**
 * Log in form handling
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
            generateMenu(data)
        } catch {
        window.alert(text)
        console.log(text)
        }
    })

    .catch(() => {window.alert('Oops.. Something happened')})

    return false
}

/**
 * Employee menu handling
 */
function generateMenu(data) {
    mainContainer.innerHTML = ''
    let employeeMenu = document.createElement('ul')
    
    let createNewRequest = document.createElement('li')
    createNewRequest.innerText = 'Create new reimbursement request'
    createNewRequest.onclick = function() {createRequest(data)}
    
    let viewRequests = document.createElement('li')
    viewRequests.innerText = 'View submitted requests'
    viewRequests.onclick = function() {viewRequestHandler(data)}
    
    let logOut = document.createElement('li')
    logOut.innerText = 'Log out'
    logOut.addEventListener('click', () => {location.reload(true)})
    
    employeeMenu.appendChild(createNewRequest)
    employeeMenu.appendChild(viewRequests)
    if(data.manager === true) {
        let viewAllRequests = document.createElement('li')
        viewAllRequests.innerText = 'View all requests'
        viewAllRequests.onclick = function() {getAllRequestData(data)}
        
        let stats = document.createElement('li')
        stats.innerText = 'View Statistics'
        stats.onclick = function() {viewStats(data)}
        
        employeeMenu.appendChild(viewAllRequests)
        employeeMenu.appendChild(stats)
    }
    employeeMenu.appendChild(logOut)

    mainContainer.append(employeeMenu)
    console.log(data)
}

/**
 *  Request creation handling
 */
function createRequest(data) {
    let requestForm = document.createElement('form')
    
    let empName = document.createElement('input')
    empName.name='empName'
    empName.setAttribute('type', 'text')
    empName.value = data.name
    empName.setAttribute('readonly', 'readonly')

    let reqAmount = document.createElement('input')
    reqAmount.name='reqAmount'
    reqAmount.required = true
    reqAmount.type=('text')
    reqAmount.placeholder=('Enter amount')

    let reqReason = document.createElement('select')
    reqReason.required = true
    reqReason.name='reqReason'
    let option1 = document.createElement('option')
    option1.text = 'Travel'
    let option2 = document.createElement('option')
    option2.text = 'Equipment'
    let option3 = document.createElement('option')
    option3.text = 'Work-related expense'
    let option4 = document.createElement('option')
    option4.text = 'Illness'
    let option5 = document.createElement('option')
    option5.text = 'Work-related injury'
    let option6 = document.createElement('option')
    option6.text = 'Other'

    reqReason.options.add(option1)
    reqReason.options.add(option2)
    reqReason.options.add(option3)
    reqReason.options.add(option4)
    reqReason.options.add(option5)
    reqReason.options.add(option6)
    
    let reqButton = document.createElement('button')
    reqButton.type='submit'
    reqButton.innerText='Submit'

    requestForm.appendChild(empName)
    requestForm.appendChild(reqAmount)
    requestForm.appendChild(reqReason)
    requestForm.appendChild(reqButton)

    requestForm.onsubmit= function(){createRequestHandler(requestForm, data)}

    mainContainer.innerHTML=''
    let reqDiv = document.createElement('div')
    
    reqDiv.append(requestForm)
    mainContainer.append(reqDiv)
}

/**
 *  Employee request view handling
 */
async function viewRequestHandler(data) {
    let url = 'http://localhost:7777/empRequests'
    console.log(data)
    let response_body = await fetch(url, {
        method: "POST", 
        body: JSON.stringify(data)
    })
    let empReqData = await response_body.json()

    buildTable(empReqData, data) 
}

/**
 *  Manager request view handling
 */
async function getAllRequestData(data) {
    let url = 'http://localhost:7777/all-requests'
    let response_body = await fetch(url)
    let allRequestData = await response_body.json()

    buildTable(allRequestData, data)
}

/**
 *  Statistics view handling
 */
function viewStats(data) {
    let mainStatDiv = document.createElement('div')
    let highestPayoutDiv = document.createElement('button')
    highestPayoutDiv.innerText='Highest amount paid'
    let numberOfReqDiv = document.createElement('button')
    numberOfReqDiv.innerText='Number of requests submitted'
    let avgAmtReqDiv = document.createElement('button') 
    avgAmtReqDiv.innerText='Average amount requested'
    
    highestPayoutDiv.addEventListener('click', function(){
        let url  = 'http://localhost:7777/highest-payout'
        fetch(url)
        .then(response => response.text())
        .then(text => {
        try {
            let statRequestData = JSON.parse(text)
            window.alert("Employee: " + statRequestData.employeeName + "\nAmount: " + statRequestData.amount.toFixed(2))
        } catch {
            window.alert(text)
        }})
        .catch(() => {window.alert('Oops.. Something happened')})
    })

    numberOfReqDiv.addEventListener('click', function(){
        let url = 'http://localhost:7777/number-requests'
        fetch(url)
        .then(response => response.text())
        .then(text => {
        try {
            let statRequestData = JSON.parse(text)
            window.alert("Total number of requests submitted: " + statRequestData)
        } catch {
            window.alert(text)
        }})
        .catch(() => {window.alert('Oops.. Something happened')})
    })

    avgAmtReqDiv.addEventListener('click', function(){
        let url = 'http://localhost:7777/average-requested'
        fetch(url)
        .then(response => response.text())
        .then(text => {
        try {
            let statRequestData = JSON.parse(text)
            window.alert("Average amount requested: " + statRequestData.toFixed(2))
        } catch {
            window.alert(text)
        }})
        .catch(() => {window.alert('Oops.. Something happened')})
    })
    
    let backButton = document.createElement('button')
    backButton.innerText='Go Back'
    backButton.onclick = function() {generateMenu(data)}

    mainStatDiv.append(highestPayoutDiv)
    mainStatDiv.append(numberOfReqDiv)
    mainStatDiv.append(avgAmtReqDiv)
    mainStatDiv.append(backButton)
    mainContainer.innerHTML=''
    mainContainer.append(mainStatDiv)

}

/**
 *  Handle send/recieve portion of request creation
 */
function createRequestHandler(requestForm, data)  {
    let url = 'http://localhost:7777/requestSubmit'
    let createReqData = new FormData(requestForm)

    fetch(url, {
        method: 'PUT', 
        body: createReqData
    })

    .then(response => response.text())
    .then(text => {
        window.alert(text)
        console.log(text)
    })

    .catch(() => {window.alert('Oops.. Something happened')})
    generateMenu(data)
}

/**
 *  Table construction for employees and managers
 */
function buildTable(buildData, data) {

    let table = document.createElement('table');
    let thead = document.createElement('thead');
    let tbody = document.createElement('tbody');

    table.id = 'requestTable'
    table.appendChild(thead);
    table.appendChild(tbody);

    let row_1 = document.createElement('tr');
    let heading_1 = document.createElement('th');
    heading_1.innerHTML = 'Request ID';
    let heading_2 = document.createElement('th');
    heading_2.innerHTML = 'Employee Name';
    let heading_3 = document.createElement('th');
    heading_3.innerHTML = 'Amount';
    let heading_4 = document.createElement('th');
    heading_4.innerHTML = 'Reason';
    let heading_5 = document.createElement('th');
    heading_5.innerHTML = 'Status';
    let heading_6 = document.createElement('th');
    heading_6.innerHTML = 'Manager Note';
    let heading_7 = document.createElement('th');
    heading_7.innerHTML = 'Action';

    row_1.appendChild(heading_1);
    row_1.appendChild(heading_2);
    row_1.appendChild(heading_3);
    row_1.appendChild(heading_4);
    row_1.appendChild(heading_5);
    row_1.appendChild(heading_6);
    if(data.manager === true){
        row_1.appendChild(heading_7);
    }
    thead.appendChild(row_1);

    for(let request of buildData){
        let row = document.createElement('tr')
        let r1 = document.createElement('td')
        r1.innerHTML = request.requestId
        let r2 = document.createElement('td')
        r2.innerHTML = request.employeeName
        let r3 = document.createElement('td')
        r3.innerHTML = request.amount.toFixed(2)
        let r4 = document.createElement('td')
        r4.innerHTML = request.reason
        let r5 = document.createElement('td')
        r5.innerHTML = request.status
        let r6 = document.createElement('td')
        r6.innerHTML = request.note
        let r7 = document.createElement('td')
        row.appendChild(r1)
        row.appendChild(r2)
        row.appendChild(r3)
        row.appendChild(r4)
        row.appendChild(r5)
        row.appendChild(r6)
        if(data.manager === true && data.name !== request.employeeName && request.status === 'Pending'){
            let approveButton = document.createElement('button')
            approveButton.innerText='Approve'
            approveButton.onclick = function() {handleRequest(request, 'Approved', data)}
            let denyButton = document.createElement('button')
            denyButton.innerText='Deny'  
            denyButton.onclick = function() {handleRequest(request, 'Denied', data)}
            r7.appendChild(approveButton)
            r7.appendChild(denyButton)
            row.appendChild(r7)
        }
        tbody.appendChild(row)
    }
    
    let tableDiv = document.createElement('div')
    let b = document.createElement('br')
    let backButton = document.createElement('button')
    backButton.innerText='Go Back'
    backButton.onclick = function() {generateMenu(data)}
    
    tableDiv.append(table)
    tableDiv.append(b)
    tableDiv.append(backButton)
    
    mainContainer.innerHTML=''
    mainContainer.append(tableDiv)
}

/**
 *  Handle approve/deny request functionality
 */
function handleRequest(requestData, reqAction, data) { 
    let url = 'http://localhost:7777/request-update';
    let noteWindow = prompt('Reason for action. (Optional)')
    requestData.status = reqAction
    
    if(noteWindow != null) {
        requestData.note = noteWindow
    } 
    
    fetch(url, {
        method: 'PUT', 
        body: JSON.stringify(requestData)
    })
    
    .then(response => response.text())
    .then(text => {
        window.alert(text)
        console.log(text)
        getAllRequestData(data)
    })
    
    .catch(() => {window.alert('Oops.. Something happened')})
}
