let mainContainer = document.getElementById('mainContainer')
mainContainer.id='mainBox'
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
            startSession()
            generateMenu(data)
        } catch {
        window.alert(text)
        }
    })
    .catch(() => {window.alert('Oops.. Something happened')})

    return false
}

function startSession() {
    let urlg='http://localhost:7777/gen'
    fetch(urlg, {
        method: 'GET' 
    })
    .then(response => response.text())
    .then(text => {
        try {
            const d = JSON.parse(text)  
            console.log(d)               
        } catch {
            console.log(text)           
        }
    })
    
    .catch(() => {window.alert('Oops.. Something happened')})
}

/**
 * Employee menu handling
 */
function generateMenu(data) {  
    mainContainer.innerHTML = ''
    let employeeMenu = document.createElement('ul')
    employeeMenu.id='menu'
    
    let createNewRequest = document.createElement('li')
    createNewRequest.innerText = 'Create new reimbursement request'
    createNewRequest.onclick = function() {createRequest(data)}
    
    let viewRequests = document.createElement('li')
    viewRequests.innerText = 'View submitted requests'
    viewRequests.onclick = function() {viewRequestHandler(data)}
    
    let logOut = document.createElement('li')
    logOut.innerText = 'Log out'
    logOut.addEventListener('click', () => {
        let url='http://localhost:7777/logout'
        fetch(url), {
            method: "GET"
        }
        location.reload(true)
    })
    
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

    let menuDiv = document.createElement('div')
    menuDiv.id = 'menuBox'
    menuDiv.append(employeeMenu)

    mainContainer.append(menuDiv)
}

/**
 *  Request creation handling
 */
function createRequest(data) {
    let requestForm = document.createElement('form')
    
    let empName = document.createElement('input')
    empName.name='empName'
    empName.type='text'
    empName.value = data.name
    empName.setAttribute('readonly', 'readonly')
    let empNameLbl = document.createElement('label')
    empNameLbl.className='reqBoxLbl'
    empNameLbl.htmlFor='empName'
    empNameLbl.innerText='Employee name '

    let reqAmount = document.createElement('input')
    reqAmount.name='reqAmount'
    reqAmount.required = true
    reqAmount.type='text'
    reqAmount.maxLength='8'
    reqAmount.placeholder=('Enter amount')
    let reqAmountLbl = document.createElement('label')
    reqAmountLbl.className='reqBoxLbl'
    reqAmountLbl.htmlFor='reqAmount'
    reqAmountLbl.innerText='Amount requested '

    let reqReason = document.createElement('select')
    reqReason.required = true
    reqReason.name='reqReason'
    reqReason.id='reqReasonSel'
    let reqReasonLbl = document.createElement('label')
    reqReasonLbl.className='reqBoxLbl'
    reqReasonLbl.htmlFor='reqReason'
    reqReasonLbl.innerText='Reason for request '
    let option1 = document.createElement('option')
    option1.text = 'Travel'
    option1.className='reqOption'
    let option2 = document.createElement('option')
    option2.text = 'Equipment'
    option2.className='reqOption'
    let option3 = document.createElement('option')
    option3.text = 'Work-related expense'
    option3.className='reqOption'
    let option4 = document.createElement('option')
    option4.text = 'Illness'
    option4.className='reqOption'
    let option5 = document.createElement('option')
    option5.text = 'Work-related injury'
    option5.className='reqOption'
    let option6 = document.createElement('option')
    option6.text = 'Other'
    option6.className='reqOption'

    reqReason.options.add(option1)
    reqReason.options.add(option2)
    reqReason.options.add(option3)
    reqReason.options.add(option4)
    reqReason.options.add(option5)
    reqReason.options.add(option6)

    let reqButton = document.createElement('button')
    reqButton.type='submit'
    reqButton.innerText='Submit'

    let b = document.createElement('br')
    let bb = document.createElement('br')
    let bbb = document.createElement('br')
    let bbbb = document.createElement('br')
    let bbbbb = document.createElement('br')
    let bbbbbb = document.createElement('br')

    requestForm.appendChild(empNameLbl)  
    requestForm.appendChild(empName)
    requestForm.appendChild(b)
    requestForm.appendChild(bbbbb)
    requestForm.appendChild(reqAmountLbl)
    requestForm.appendChild(reqAmount)
    requestForm.appendChild(bb)
    requestForm.appendChild(bbbbbb)
    requestForm.appendChild(reqReasonLbl)
    requestForm.appendChild(reqReason)
    requestForm.appendChild(bbb)
    requestForm.appendChild(bbbb)
    requestForm.appendChild(reqButton)

    requestForm.onsubmit= function(event){createRequestHandler(requestForm, data), event.preventDefault()}

    let backButton = document.createElement('button')
    backButton.innerText='Go Back'
    backButton.onclick = function() {generateMenu(data)}

    mainContainer.innerHTML=''
    let reqDiv = document.createElement('div')
    reqDiv.id='reqBox'

    let reqH = document.createElement('h2')
    reqH.id='reqH'
    reqH.innerText='Reimbursement Request Form'

    let br = document.createElement('br')
    let bbr = document.createElement('br')
    let reqDivHolder = document.createElement('div')
    reqDivHolder.id='rDivHolder'

    reqDiv.append(requestForm)
    reqDivHolder.append(reqH)
    mainContainer.append(reqDivHolder)
    mainContainer.append(reqDiv)
    mainContainer.append(br)
 
    mainContainer.append(backButton)
}

/**
 *  Employee request view handling
 */
async function viewRequestHandler(data) {
    let url = 'http://localhost:7777/devdev/empRequests'
    
    fetch(url, {
        method: "POST", 
        body: JSON.stringify(data),
    })
        .then(response => response.text())
        .then(text => {
        try {
            let empReqData = JSON.parse(text)
            buildTable(empReqData, data, false)
        } catch {
            window.alert(text)
        }})
        .catch(() => {window.alert('Oops.. Something happened')})
}

/**
 *  Manager request view handling
 */
async function getAllRequestData(data) {
    let url = 'http://localhost:7777/devdev/all-requests'
    let response_body = await fetch(url)
    let allRequestData = await response_body.json()

    buildTable(allRequestData, data, true)
}

/**
 *  Statistics view handling
 */
function viewStats(data) {
    let mainStatDiv = document.createElement('div')
    mainStatDiv.id='statDiv'
    let highestPayoutDiv = document.createElement('button')
    highestPayoutDiv.innerText='Highest amount paid'
    highestPayoutDiv.className='buttonSpace'
    highestPayoutDiv.id='highest'
    let numberOfReqDiv = document.createElement('button')
    numberOfReqDiv.innerText='Number of requests submitted'
    numberOfReqDiv.className='buttonSpace'
    let avgAmtReqDiv = document.createElement('button') 
    avgAmtReqDiv.innerText='Average amount requested'
    avgAmtReqDiv.className='buttonSpace'
    
    highestPayoutDiv.addEventListener('click', function(){
        let url  = 'http://localhost:7777/devdev/highest-payout'
        fetch(url)
        .then(response => response.text())
        .then(text => {
        try {
            let statRequestData = JSON.parse(text)
            statDisplay.innerText=("Employee: " + statRequestData.employeeName + "\nAmount: $" + statRequestData.amount.toFixed(2))
        } catch {
            window.alert(text)
        }})
        .catch(() => {window.alert('Oops.. Something happened')})
    })

    numberOfReqDiv.addEventListener('click', function(){
        let url = 'http://localhost:7777/devdev/number-requests'
        fetch(url)
        .then(response => response.text())
        .then(text => {
        try {
            let statRequestData = JSON.parse(text)
            statDisplay.innerText=("Total number of requests submitted:\n" + statRequestData)
        } catch {
            window.alert(text)
        }})
        .catch(() => {window.alert('Oops.. Something happened')})
    })

    avgAmtReqDiv.addEventListener('click', function(){
        let url = 'http://localhost:7777/devdev/average-requested'
        fetch(url)
        .then(response => response.text())
        .then(text => {
        try {
            let statRequestData = JSON.parse(text)
            statDisplay.innerText=("Average amount requested:\n$" + statRequestData.toFixed(2))
        } catch {
            window.alert(text)
        }})
        .catch(() => {window.alert('Oops.. Something happened')})
    })
    
    let backB = document.createElement('button')
    backB.innerText='Go Back'
    backB.onclick = function() {generateMenu(data)}
    backB.id='backButton'
    let bbr = document.createElement('br')
    let bbbr = document.createElement('br')
    let statDisplay = document.createElement('div')
    statDisplay.id='statDivDisplay'
    let statBtnDiv = document.createElement('div')
    statBtnDiv.id='statBtnDiv'
    let holderDiv= document.createElement('div')

    mainContainer.innerHTML=''
    statBtnDiv.append(highestPayoutDiv)
    statBtnDiv.append(bbr)
    statBtnDiv.append(numberOfReqDiv)
    statBtnDiv.append(bbbr)
    statBtnDiv.append(avgAmtReqDiv)
    mainStatDiv.append(statBtnDiv)
    mainStatDiv.append(statDisplay)
    
    holderDiv.appendChild(mainStatDiv)
    holderDiv.append(backB)

    mainContainer.append(holderDiv)
}

/**
 *  Handle send/recieve portion of request creation
 */
function createRequestHandler(requestForm, data)  {
    let url = 'http://localhost:7777/devdev/requestSubmit'
    let createReqData = new FormData(requestForm)

    fetch(url, {
        method: 'PUT', 
        body: createReqData
    })

    .then(response => response.text())
    .then(text => {
        window.alert(text)
    })

    .catch(() => {window.alert('Oops.. Something happened')})
    generateMenu(data)
}

/**
 *  Table construction for employees and managers
 */
function buildTable(buildData, data, viewAll) {

    let table = document.createElement('table');
    let thead = document.createElement('thead');
    let tbody = document.createElement('tbody');

    table.id = 'requestTable'
    table.appendChild(thead);
    table.appendChild(tbody);

    let row_1 = document.createElement('tr');
    let heading_1 = document.createElement('th');
    heading_1.innerHTML = 'Request ID';
    heading_1.className='headerGroup'
    heading_1.onclick=function(){sortTableN(0)}
    let heading_2 = document.createElement('th');
    heading_2.innerHTML = 'Employee Name';
    heading_2.className='headerGroup'
    heading_2.onclick=function(){sortTable(1)}
    let heading_3 = document.createElement('th');
    heading_3.innerHTML = 'Amount';
    heading_3.className='headerGroup'
    heading_3.onclick=function(){sortTableN(2)}
    let heading_4 = document.createElement('th');
    heading_4.innerHTML = 'Reason';
    heading_4.className='headerGroup'
    heading_4.onclick=function(){sortTable(3)}
    let heading_5 = document.createElement('th');
    heading_5.innerHTML = 'Status';
    heading_5.className='headerGroup'
    heading_5.onclick=function(){sortTable(4)}
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
    if(data.manager === true && viewAll){
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
            approveButton.id='approveButton'
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
    tableDiv.id='tableBox'
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
 *  Table sorting
 */
 function sortTable(n) {
    var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
    table = document.getElementById("requestTable");
    switching = true;
    // Set the sorting direction to ascending:
    dir = "asc";
    /* Make a loop that will continue until
    no switching has been done: */
    while (switching) {
      // Start by saying: no switching is done:
      switching = false;
      rows = table.rows;
      /* Loop through all table rows (except the
      first, which contains table headers): */
      for (i = 1; i < (rows.length - 1); i++) {
        // Start by saying there should be no switching:
        shouldSwitch = false;
        /* Get the two elements you want to compare,
        one from current row and one from the next: */
        x = rows[i].getElementsByTagName("TD")[n];
        y = rows[i + 1].getElementsByTagName("TD")[n];
        /* Check if the two rows should switch place,
        based on the direction, asc or desc: */
        if (dir == "asc") {
          if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
            // If so, mark as a switch and break the loop:
            shouldSwitch = true;
            break;
          }
        } else if (dir == "desc") {
          if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
            // If so, mark as a switch and break the loop:
            shouldSwitch = true;
            break;
          }
        }
      }
      if (shouldSwitch) {
        /* If a switch has been marked, make the switch
        and mark that a switch has been done: */
        rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
        switching = true;
        // Each time a switch is done, increase this count by 1:
        switchcount ++;
      } else {
        /* If no switching has been done AND the direction is "asc",
        set the direction to "desc" and run the while loop again. */
        if (switchcount == 0 && dir == "asc") {
          dir = "desc";
          switching = true;
        }
      }
    }
  }

/**
 *  Numerical table sort
 */
 function sortTableN(n) {
    var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
    table = document.getElementById("requestTable");
    switching = true;
    // Set the sorting direction to ascending:
    dir = "asc";
    /* Make a loop that will continue until
    no switching has been done: */
    while (switching) {
      // Start by saying: no switching is done:
      switching = false;
      rows = table.rows;
      /* Loop through all table rows (except the
      first, which contains table headers): */
      for (i = 1; i < (rows.length - 1); i++) {
        // Start by saying there should be no switching:
        shouldSwitch = false;
        /* Get the two elements you want to compare,
        one from current row and one from the next: */
        x = rows[i].getElementsByTagName("TD")[n];
        y = rows[i + 1].getElementsByTagName("TD")[n];
        /* Check if the two rows should switch place,
        based on the direction, asc or desc: */
        if (dir == "asc") {
            if (Number(x.innerHTML) > Number(y.innerHTML)) {
                shouldSwitch = true;
                break;
              }
        } else if (dir == "desc") {
            if (Number(x.innerHTML) < Number(y.innerHTML)) {
                shouldSwitch = true;
                break;
              }
        }
      }
      if (shouldSwitch) {
        /* If a switch has been marked, make the switch
        and mark that a switch has been done: */
        rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
        switching = true;
        // Each time a switch is done, increase this count by 1:
        switchcount ++;
      } else {
        /* If no switching has been done AND the direction is "asc",
        set the direction to "desc" and run the while loop again. */
        if (switchcount == 0 && dir == "asc") {
          dir = "desc";
          switching = true;
        }
      }
    }
  }

/**
 *  Handle approve/deny request functionality
 */
function handleRequest(requestData, reqAction, data) { 
    let url = 'http://localhost:7777/devdev/request-update';
    let noteWindow = prompt('Reason for action. (Optional)')
    
    if(noteWindow != null) {
        requestData.note = noteWindow
        requestData.status = reqAction
    
        fetch(url, {
            method: 'PUT', 
            body: JSON.stringify(requestData)
        })
        
        .then(response => response.text())
        .then(text => {
            window.alert(text)
            getAllRequestData(data)
        })
        
        .catch(() => {window.alert('Oops.. Something happened')})
    } 
}
