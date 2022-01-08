let mainContainer = document.getElementById('mainContainer')
let loginButton = document.getElementById('loginButton')
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

    buildTable(empReqData, false) 
}

/**
 *  Manager request view handling
 */
async function getAllRequestData(data) {
    let url = 'http://localhost:7777/all-requests'
    let response_body = await fetch(url)
    let allRequestData = await response_body.json()

    buildTable(allRequestData, true)
}

/**
 *  Statistics view handling
 */
function viewStats(data) {

}

/**
 *  Table construction for employees and managers
 */
function buildTable(buildData, managerFlag) {
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
    if(managerFlag === true){
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
        r3.innerHTML = request.amount
        let r4 = document.createElement('td')
        r4.innerHTML = request.reason
        let r5 = document.createElement('td')
        r5.innerHTML = request.status
        let r6 = document.createElement('td')
        r6.innerHTML = request.note
        let r7 = document.createElement('td')
        r7.innerHTML = 'Approve/Deny'
        row.appendChild(r1)
        row.appendChild(r2)
        row.appendChild(r3)
        row.appendChild(r4)
        row.appendChild(r5)
        row.appendChild(r6)
        if(managerFlag === true){
            row.appendChild(r7)
        }
        tbody.appendChild(row)
    }
    table.appendChild(tbody)
    mainContainer.innerHTML=''
    mainContainer.append(table)
}
