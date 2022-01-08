
let loginView = document.getElementById('login');
let homepageView = document.getElementById('homepage');
let statisticsView = document.getElementById('statistics');

let bannerDiv;
let homepageTopContainer;

let personalPRContainer;
let personalCRContainer;
let personalARContainer;

let allPRContainer;
let allARContainer;
let allCRContainer;

//============================================================================
// JUST FOR DEVELOPMENT, COMMENT OUT WHEN TESTING WITH SERVER, BUGGY
let toggleViewButton = document.createElement('input');
toggleViewButton.type = 'button';
toggleViewButton.value = 'Switch'
toggleViewButton.addEventListener('click', toggleView);

let loginPageCheck = true;
let homePageCheck = false;

function toggleView(){
    loginPageCheck = !loginPageCheck;
    homePageCheck = !homePageCheck;
    toggleLogin(loginPageCheck);
    toggleHomepage(homePageCheck, true);
}

document.body.appendChild(toggleViewButton);

//============================================================================

let containerOptions;
let requestFormContainer;

let userLoginObj;
let userData;
let employeeData;
let allRequestData;


let createRequestObj;
let newRequestData;
let createRequestCheck = true;

/** Function Objects */
/* Handle Page Utility*/

const pageUtility = {
    logoutHomepage: function (){
        toggleHomepage(false);
        toggleLogin(true);
        createRequestCheck = true;
    
    },
    generateNewLine: function (lines, container){
        let emptySpace;
        for(let i = 0; i < lines;i++){
            emptySpace = document.createElement('br');
            container.appendChild(emptySpace);
        }
    },
    clearView: function (divContainer){
        divContainer.innerHTML = "";
    },
    attachInputElement: function (labelName, name, container){
        let input = document.createElement('input');
    
        input.type = 'text';
        input.name = labelName;
        input.placeholder = name;
        input.required = 'required';
    
        container.appendChild(input);
    },
    attachInputNumberElement: function (labelName, name, container){
        let input = document.createElement('input');
    
        input.type = 'number';
        input.name = labelName;
        input.placeholder = name;
        input.required = 'required';
    
        container.appendChild(input);
    },
    attachButtonElement: function (labelName, name, container,funkyType, funky){
        let input = document.createElement('input');
    
        input.type = 'button';
        input.name = labelName;
        input.value = name;
    
        input.addEventListener(funkyType, funky);
    
        container.appendChild(input);
    },
    attachSelectOptions: function (optionName, insideText, options, container){
        
        let selectBox = document.createElement('select');
    
        let selectLabel = document.createElement('label');
        
        selectBox.name = optionName;
        selectLabel.htmlFor = selectBox.name;
        selectLabel.innerText = insideText;
    
        container.appendChild(selectLabel);
        pageUtility.generateOption(options, selectBox);
        pageUtility.generateNewLine(1, container);
        container.appendChild(selectBox);
    },
    attachTitleElement: function (type, text, container){
        let title = document.createElement(type);
        title.innerHTML = text;
        container.appendChild(title);
    },
    generateOption: function (selectOptions, container){
        let option;
        for(element in selectOptions){
            option = document.createElement('option');
            option.value = selectOptions[element];
            option.innerText = selectOptions[element];
            container.appendChild(option);
        }
    
    },
    generateTableElement: function(headerNames, tableId, container){
        let table = document.createElement('table');
        table.id = tableId;
        pageUtility.generateTableHeader(headerNames, table);
        container.appendChild(table);
    },
    generateTableHeader: function(headerNames, container){
        let tableRow = document.createElement('tr');
        let headerElement;
        for(let element of headerNames){
            headerElement = document.createElement('th')
            headerElement.innerText = element;
            tableRow.appendChild(headerElement);
        }
        container.appendChild(tableRow);
    },
    generateTableRows: function(tableId, container, values){
        let table = document.getElementById(tableId);
        let tableRow;
        let tableElement;
        for(let i = 0; i < values.length; i++){
            tableRow = document.createElement('tr');
            for(let key in values[i]){
                tableElement = document.createElement('td')
                tableElement.innerText = values[i][key];
                tableRow.appendChild(tableElement);
            }
            table.appendChild(tableRow);
        }
        container.appendChild(table);
    },
    generateManagerTableRow: function(tableId, container, values, funky){
        let table = document.getElementById(tableId);
        let tableRow;
        let tableElement;
        for(let i = 0; i < values.length; i++){
            tableRow = document.createElement('tr');
            for(let key in values[i]){
                tableElement = document.createElement('td')
                tableElement.innerText = values[i][key];
                tableRow.appendChild(tableElement);
            }
            pageUtility.attachButtonElement('respond', 'respond-button', tableRow, 'click', e => {
                funky(e);
            });
            table.appendChild(tableRow);
        }
        container.appendChild(table);
    },
    generateSingleTableRow: function(value, container){
        let tableRow = document.createElement('tr');
        let tableElement;
        for(let key in value){
            tableElement = document.createElement('td')
            tableElement.innerText = value[key];
            tableRow.appendChild(tableElement);
        }
        container.appendChild(tableRow);
    }
}

const requestViewUtility = {
    openDisplay: function (){
        if(createRequestCheck){
            createRequestCheck = !createRequestCheck;

            requestFormContainer = document.createElement('form');
            requestFormContainer.id = 'request-form-container';
    
            pageUtility.generateNewLine(1, requestFormContainer);
            pageUtility.attachInputElement('request-messagee', 'Request Message', requestFormContainer);
            pageUtility.generateNewLine(2, requestFormContainer);
            
            pageUtility.attachSelectOptions('request-type', 
                                "Type", 
                                ['Travel', 'Equipment', 'Consumable', 'Book'], 
                                requestFormContainer);
    
            pageUtility.generateNewLine(2, requestFormContainer);
            pageUtility.attachInputNumberElement('amount', '$0.00', requestFormContainer);
            pageUtility.generateNewLine(2, requestFormContainer);
            
            pageUtility.attachButtonElement('close-create-button', 'close', requestFormContainer, 'click', requestViewUtility.closeDisplay);
            pageUtility.attachButtonElement('submit-request', 'submit', requestFormContainer, 'click', getCreateRequest);
    
            containerOptions.appendChild(requestFormContainer);
            homepageTopContainer.appendChild(containerOptions);
        }
        
    },
    closeDisplay: function (){
        createRequestCheck = !createRequestCheck;
        let requestForm = document.getElementById('request-form-container')
        containerOptions.removeChild(requestForm);
    },
    createPersonalRequestTables: function(){
        pageUtility.attachTitleElement('h3', 'Pending Requests', personalPRContainer);
        pageUtility.generateTableElement(['id', 'employeeId', 'type', 'requestMessage', 'amount', 'dateSubmission'], 'pending-request-table', personalPRContainer);
        homepageView.appendChild(personalPRContainer);

        pageUtility.attachTitleElement('h3', 'Past Requests', personalARContainer);
        pageUtility.generateTableElement(['id', 'employeeId', 'type', 'requestMessage', 'amount', 'dateSubmission'], 'answered-request-table', personalARContainer);
        homepageView.appendChild(personalARContainer);

        pageUtility.attachTitleElement('h3', 'Completed Requests', personalCRContainer);
        pageUtility.generateTableElement(['id', 'employeeId', 'managerId', 'status', 'response', 'dateResolved'], 'completed-request-table', personalCRContainer);
        homepageView.appendChild(personalCRContainer);
    },
    createAllRequestTable: function(){
        pageUtility.attachTitleElement('h3', 'All Pending Requests', allPRContainer);
        pageUtility.generateTableElement(['id', 'employeeId', 'type', 'requestMessage', 'amount', 'dateSubmission'], 'all-pending-request-table', allPRContainer);
        homepageView.appendChild(allPRContainer);

        pageUtility.attachTitleElement('h3', 'All Past Requests', allARContainer);
        pageUtility.generateTableElement(['id', 'employeeId', 'type', 'requestMessage', 'amount', 'dateSubmission'], 'all-answered-request-table', allARContainer);
        homepageView.appendChild(allARContainer);

        pageUtility.attachTitleElement('h3', 'All Completed Requests', allCRContainer);
        pageUtility.generateTableElement(['id', 'employeeId', 'managerId', 'status', 'response', 'dateResolved'], 'all-completed-request-table', allCRContainer);
        homepageView.appendChild(allCRContainer);
    },
    displayEmployeeRequests: function (allRequestData){

        requestViewUtility.displayPendingRequests(allRequestData.pendingRequests);
        requestViewUtility.displayAnsweredRequests(allRequestData.answeredRequests);
        requestViewUtility.displayCompletedRequests(allRequestData.completedRequests);
        console.log(allRequestData);
    },
    displayManagerRequests: function (sortedRequests){
        requestViewUtility.displayPendingRequests(sortedRequests.personalRequests.personalPendingRequests);
        requestViewUtility.displayAnsweredRequests(sortedRequests.personalRequests.personalAnsweredRequests);
        requestViewUtility.displayCompletedRequests(sortedRequests.personalRequests.personalCompletedRequests);

        requestViewUtility.displayAllPendingRequests(sortedRequests.employeeRequests.employeePendingRequests);
        requestViewUtility.displayAllAnsweredRequests(sortedRequests.employeeRequests.employeeAnsweredRequests);
        requestViewUtility.displayAllCompletedRequests(sortedRequests.employeeRequests.employeeCompletedRequests);
        console.log(sortedRequests);
    },
    displayPendingRequests : function (pendingRequests){
        pageUtility.generateTableRows('pending-request-table', personalPRContainer, pendingRequests);
        homepageView.appendChild(personalPRContainer);
    },
    displayAnsweredRequests : function (answeredRequests){
        pageUtility.generateTableRows('answered-request-table', personalARContainer, answeredRequests)
        homepageView.appendChild(personalARContainer);
    },
    displayCompletedRequests : function (completedRequests){
        pageUtility.generateTableRows('completed-request-table', personalCRContainer, completedRequests);
        homepageView.appendChild(personalCRContainer);
    },
    displayNewPendingRequests : function (newPendingRequestData){
        let table = document.getElementById('pending-request-table');
        pageUtility.generateSingleTableRow(newPendingRequestData, table);
    },
    displayAllPendingRequests : function (pendingRequests){
        pageUtility.generateManagerTableRow('all-pending-request-table', allPRContainer, pendingRequests, requestInteractionUtility.respondToRequest);
        homepageView.appendChild(allPRContainer);
    },
    displayAllAnsweredRequests : function (answeredRequests){
        pageUtility.generateTableRows('all-answered-request-table', allARContainer, answeredRequests)
        homepageView.appendChild(allARContainer);
    },
    displayAllCompletedRequests : function (completedRequests){
        pageUtility.generateTableRows('all-completed-request-table', allCRContainer, completedRequests);
        homepageView.appendChild(allCRContainer);
    },
    displayNewPendingRequests : function (newPendingRequestData){
        let table = document.getElementById('pending-request-table');
        pageUtility.generateSingleTableRow(newPendingRequestData, table);
    },
    failedToGetRequests : function (){
        window.alert('Failed to get requests from server');
    }
}

const requestInteractionUtility = {
    respondToRequest: function (e){
        console.log(e.target.parentNode);
    }
}

const userViewUtility = {
    welcomeBanner : function (employeeData){
        pageUtility.attachTitleElement('h2', employeeData.firstName + " " + employeeData.lastName, bannerDiv);
    }
}


function toggleLogin(toggle){
    if(toggle)loadLoginInput(loginView);
    else pageUtility.clearView(loginView)
};

function toggleHomepage(toggle, isManager, userData){
    if(toggle){
        if(isManager){
            loadManagerHomepage(homepageView);
            getMTotalRequests(userData);  
        }else{
            loadEmployeeHomepage(homepageView);
            getEmployeeTotalRequests(userData);
        }
    }
        else pageUtility.clearView(homepageView)
}

window.onload = toggleLogin(true);

/* Page Views*/

function loadLoginInput(loginView){

    let formContainer = document.createElement('form');

    let loginInputContainer = document.createElement('div');

    let loginTitle = document.createElement('div');

    let title = document.createElement('h1');
    let subTitle = document.createElement('h4');
    title.innerText = 'Hero Pay';
    subTitle.innerText = 'Login';
    subTitle.id = 'subTitle';
    loginTitle.id = 'title';
    loginTitle.appendChild(title);
    loginTitle.appendChild(subTitle);
    loginInputContainer.id = 'loginContainer';
    let loginInputDiv = document.createElement('div');
    loginInputDiv.id = 'loginDiv';

    loginView.appendChild(loginTitle);

    let submitButton = document.createElement('input');
    let resetButton = document.createElement('input');

    submitButton.type = 'button';
    submitButton.value = 'submit';


    submitButton.addEventListener('click', getUserLogin);

    resetButton.type = 'reset';
    resetButton.value = 'reset';

    formContainer.id = 'formContainer';

    pageUtility.attachInputElement('username', 'Username', formContainer);
    pageUtility.generateNewLine(2, formContainer);
    pageUtility.attachInputElement('password', 'Password', formContainer);
    pageUtility.generateNewLine(2, formContainer);
    formContainer.appendChild(submitButton);
    formContainer.appendChild(resetButton);

    loginInputDiv.appendChild(formContainer);

    loginView.appendChild(loginInputDiv);

}

function loadEmployeeHomepage(homepageView){
    homepageTopContainer = document.createElement('div');
    homepageTopContainer.id = 'homepage-top';

    bannerDiv = document.createElement('div');
    bannerDiv.id = 'banner-div';
    pageUtility.attachTitleElement('h1', 'Employee Homepage', bannerDiv);
    homepageTopContainer.appendChild(bannerDiv);

    homepageView.appendChild(homepageTopContainer);
    pageUtility.attachTitleElement('h3', 'Options', homepageTopContainer);
    pageUtility.attachButtonElement('create-button', 'Create Request', homepageTopContainer,'click', requestViewUtility.openDisplay);
    pageUtility.attachButtonElement('logout-button', 'Logout', homepageTopContainer, 'click', pageUtility.logoutHomepage);
    containerOptions = document.createElement('div');
    containerOptions.id = 'options-div';
    
    personalPRContainer = document.createElement('div');
    personalPRContainer.id = 'pending-request-div';

    personalARContainer = document.createElement('div');
    personalARContainer.id = 'answered-request-div';

    personalCRContainer = document.createElement('div');
    personalCRContainer.id = 'completed-request-div';
    requestViewUtility.createPersonalRequestTables();

}

function loadManagerHomepage(homepageView){
    homepageTopContainer = document.createElement('div');
    homepageTopContainer.id = 'homepage-top';

    bannerDiv = document.createElement('div');
    bannerDiv.id = 'banner-div';
    pageUtility.attachTitleElement('h1', 'Manager Homepage', bannerDiv);
    homepageTopContainer.appendChild(bannerDiv);

    homepageView.appendChild(homepageTopContainer);
    pageUtility.attachTitleElement('h3', 'Options', homepageTopContainer);
    pageUtility.attachButtonElement('create-button', 'Create Request', homepageTopContainer,'click', requestViewUtility.openDisplay);
    pageUtility.attachButtonElement('logout-button', 'Logout', homepageTopContainer, 'click', pageUtility.logoutHomepage);
    containerOptions = document.createElement('div');
    containerOptions.id = 'options-div';

    personalPRContainer = document.createElement('div');
    personalPRContainer.id = 'pending-request-div';

    personalARContainer = document.createElement('div');
    personalARContainer.id = 'answered-request-div';

    personalCRContainer = document.createElement('div');
    personalCRContainer.id = 'completed-request-div';

    allPRContainer = document.createElement('div');
    allPRContainer.id = 'all-pending-request-div';

    allARContainer = document.createElement('div');
    allARContainer.id = 'all-answered-request-div';

    allCRContainer = document.createElement('div');
    allCRContainer.id = 'all-completed-request-div';

    requestViewUtility.createPersonalRequestTables();
    requestViewUtility.createAllRequestTable();
}

/*================================================================================================*/

/** Sorting utility */

const sortingUtility = {
    sortManagerTotalRequests: function(totalRequestData, managerId){
        let personalPendingRequests = [];
        let personalAnsweredRequests = [];
        let personalCompletedRequests = [];

        let employeePendingRequests = [];
        let employeeAnsweredRequests = [];
        let employeeCompletedRequests = [];

        totalRequestData.pendingRequests.forEach(sortPending);
        totalRequestData.answeredRequests.forEach(sortAnswered);
        totalRequestData.completedRequests.forEach(sortCompleted);


        function sortPending(var1){
            if(var1.employeeId == managerId){
                personalPendingRequests.push(var1);
            }else{
                employeePendingRequests.push(var1);
            }
        }

        function sortAnswered(var1){
            if(var1.employeeId == managerId){
                personalAnsweredRequests.push(var1);
            }else{
                employeeAnsweredRequests.push(var1);
            }
        }

        function sortCompleted(var1){
            if(var1.employeeId == managerId){
                personalCompletedRequests.push(var1);
            }else{
                employeeCompletedRequests.push(var1);
            }
        }

        let personalRequests = {personalPendingRequests, personalAnsweredRequests, personalCompletedRequests};
        let employeeRequests = {employeePendingRequests, employeeAnsweredRequests, employeeCompletedRequests};
        
        let sortedRequests = {personalRequests, employeeRequests};
        return sortedRequests;
    }
}


/*================================================================================================*/

/** Handle User Login */

function getUserLogin(){
    let userForm = document.getElementById('formContainer');
    let usernameInput = userForm.elements[0].value;
    let passwordInput = userForm.elements[1].value;
    if(usernameInput && passwordInput){
        userLoginObj = JSON.stringify({ username: usernameInput, password:passwordInput});
        verifyUser();
    } else{
        window.alert("INPUT ALL FIELDS");
    }

}

function failedLogin(){
    window.alert("FAIL");
}

function successfulLogin(userData, isManager){
    toggleLogin(false);
    if(isManager){
        toggleHomepage(true, isManager, userData);
 
    }else{
        toggleHomepage(true, isManager, userData);

    }

}

function welcomeEmployee(employeeData){
    userViewUtility.welcomeBanner(employeeData);
    console.log(employeeData);
}
/*================================================================================================*/

/**Handle user request */

function getCreateRequest(){
    let requestForm = document.getElementById('request-form-container');
    let message = requestForm.elements[0].value;
    let type = requestForm.elements[1].value;
    let amount = requestForm.elements[2].value;

     
    if(message && type && amount) {
        createRequestObj = JSON.stringify({ employeeId: userData.employeeId, type:type, requestMessage: message, amount:amount});
        postNewRequest(createRequestObj);
    } else window.alert("EMPTY INPUT")
}

/*================================================================================================*/


/* Asynchronous functions: Fetch and server logic*/

async function verifyUser(){
    let loginUrl = 'http://localhost:9002/login/validate';
    try{
        let userLoginBody = await fetch(loginUrl, {method: "POST", body: userLoginObj});
        userData = await userLoginBody.json();
        if(userData) {
            console.log(userData);
            getEmployeeInfo(userData);
        }else failedLogin();
    }catch(e){
        console.log(e);
        failedLogin();
    }
}

async function getEmployeeInfo(userData){
    let employeeValidationUrl = 'http://localhost:9002/login/employee-info?employeeId=' + userData.employeeId;
    try{
        let employeeResponseBody = await fetch(employeeValidationUrl, {method: "GET"});
        employeeData = await employeeResponseBody.json();
        if(userData) {
            successfulLogin(userData, employeeData.manager);
            welcomeEmployee(employeeData);
        }else failedLogin();
    }catch(e){
        console.log(e);
        failedLogin();
    }
}

async function getEmployeeTotalRequests(userData){
    let employeeTotalRequestUrl = 'http://localhost:9002/employee/request/all/?employeeId=' + userData.employeeId;
    try{
        let allRequestResponseBody = await fetch(employeeTotalRequestUrl, {method: "GET"});
        allRequestData = await allRequestResponseBody.json();
        if(allRequestData) {
            requestViewUtility.displayEmployeeRequests(allRequestData);
        }else failed();
    }catch(e){
        console.log(e);
        requestViewUtility.failedToGetRequests();
    }
}

async function postNewRequest(createRequestObj){
    let createNewRequestUrl = 'http://localhost:9002/employee/request/new';
    try{
        let newRequestResponseBody = await fetch(createNewRequestUrl, {method: "POST", body: createRequestObj});
        newRequestData = await newRequestResponseBody.json();
        if(newRequestData) {
            requestViewUtility.closeDisplay();
            console.log(newRequestData);
            requestViewUtility.displayNewPendingRequests(newRequestData);
        }else failedLogin();
    }catch(e){
        console.log(e);
    }
}

async function getMTotalRequests(userData){
    let totalRequestUrl = 'http://localhost:9002/manager/total';
    try{
        let totalRequestResponseBody = await fetch(totalRequestUrl, {method: 'GET'});
        totalRequestData = await totalRequestResponseBody.json();
        if(totalRequestData){
            let sortedRequests = sortingUtility.sortManagerTotalRequests(totalRequestData, userData.employeeId);
            requestViewUtility.displayManagerRequests(sortedRequests);
        }else failed();
    }catch(e){
        console.log(e);
        requestViewUtility.failedToGetRequests();
    }
}