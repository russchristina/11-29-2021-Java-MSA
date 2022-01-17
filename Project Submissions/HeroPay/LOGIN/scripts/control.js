
/** Base HTML Elements */

let loginView = document.getElementById('login');
let homepageView = document.getElementById('homepage');
let statisticsPageView = document.getElementById('statistics');

/** Display Div for each Employee */
let bannerDiv;

/** Homepage Elements */
let homepageTopContainer;

let personalPRContainer;
let personalCRContainer;
let personalARContainer;

let allPRContainer;
let allARContainer;
let allCRContainer;

let containerOptions;
let requestFormContainer;
let requestFileUpload;

let createRequestCheck = true;
let completedRequestCheck = true;

let respondFormContainer;
let respondRequestCheck = true;

/** Statistics Elements */

let statisticsTopContainer;

let generalStatisticsContainer;
let employeeStatisticsContainer;
let indivdiualStatisticsContainer;

let generalStatisticsRoleContainer;
let generalStatisticsTypeContainer;
let generalStatisticsTotalContainer;

let individualEmployeeFormContainer;


let statisticsPageCheck = true;


/** JSON objects with needed information for global access */

let createRequestObj;
let newResponseData;

let generalStatData;
let generalEmployeeStatData;
let sortedRequests;

let individualEmployeeData;

/** Function Objects */
/* Handle Page Utility*/

const pageUtility = {
    logoutHomepage: function () {
        toggleHomepage(false);
        toggleLogin(true);
        createRequestCheck = true;
        respondRequestCheck = true;
        localStorage.clear();

    },
    generateNewLine: function (lines, container) {
        let emptySpace;
        for (let i = 0; i < lines; i++) {
            emptySpace = document.createElement('br');
            container.appendChild(emptySpace);
        }
    },
    clearView: function (divContainer) {
        divContainer.innerHTML = "";
    },
    attachInputElement: function (labelName, name, container) {
        let input = document.createElement('input');

        input.type = 'text';
        input.name = labelName;
        input.placeholder = name;
        input.required = 'required';

        container.appendChild(input);
    },
    attachInputNumberElement: function (labelName, name, container) {
        let input = document.createElement('input');

        input.type = 'number';
        input.name = labelName;
        input.placeholder = name;
        input.required = 'required';
        input.step = '.01';

        container.appendChild(input);
    },
    attachButtonElement: function (labelName, name, container, funkyType, funky) {
        let input = document.createElement('input');

        input.type = 'button';
        input.name = labelName;
        input.value = name;

        input.addEventListener(funkyType, funky);

        container.appendChild(input);
    },
    attachSelectOptions: function (optionName, insideText, options, container) {

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
    attachTitleElement: function (type, text, container) {
        let title = document.createElement(type);
        title.innerHTML = text;
        container.appendChild(title);
    },
    generateOption: function (selectOptions, container) {
        let option;
        for (element in selectOptions) {
            option = document.createElement('option');
            option.value = selectOptions[element];
            option.innerText = selectOptions[element];
            container.appendChild(option);
        }
    },
    generateTableElement: function (headerNames, tableId, container) {
        let table = document.createElement('table');
        table.id = tableId;
        pageUtility.generateTableHeader(headerNames, table);
        container.appendChild(table);
    },
    generateTableHeader: function (headerNames, container) {
        let tableRow = document.createElement('tr');
        let headerElement;
        for (let element of headerNames) {
            headerElement = document.createElement('th')
            headerElement.innerText = element;
            tableRow.appendChild(headerElement);
        }
        container.appendChild(tableRow);
    },
    generateTableRows: function (tableId, container, values) {
        let table = document.getElementById(tableId);
        let tableRow;
        let tableElement;
        if (values.length == 0) {

            return;
        }
        for (let i = 0; i < values.length; i++) {
            tableRow = document.createElement('tr');
            for (let key in values[i]) {
                tableElement = document.createElement('td')
                tableElement.innerText = values[i][key];
                tableRow.appendChild(tableElement);
            }
            table.appendChild(tableRow);
        }
        container.appendChild(table);
    },
    generateTableRowsStats: function (tableId, container, values) {
        let table = document.getElementById(tableId);
        let tableRow;
        let tableElement;
        if (values.length == 0) {

            return;
        }
        for (let i = 0; i < values.length; i++) {
            tableRow = document.createElement('tr');
            for (let key in values[i]) {
                tableElement = document.createElement('td')
                if (key == 'meanAverage' || key == 'sum') {
                    tableElement.innerText = `$ ${values[i][key]}`
                } else {
                    tableElement.innerText = values[i][key];
                }
                tableRow.appendChild(tableElement);
            }
            table.appendChild(tableRow);
        }
        container.appendChild(table);
    },
    generateTableRowsEmployeeStats: function (tableId, container, values) {
        let table = document.getElementById(tableId);
        let tableRow;
        let tableElement;
        if (values.length == 0) {

            return;
        }
        for (let i = 0; i < values.orderedList.length; i++) {
            tableRow = document.createElement('tr');
            for (let key in values.orderedList[i]) {
                tableElement = document.createElement('td')
                if (key == 'sum') {
                    tableElement.innerText = `$ ${values.orderedList[i][key]}`
                } else {
                    tableElement.innerText = values.orderedList[i][key];
                }

                tableRow.appendChild(tableElement);
            }
            table.appendChild(tableRow);
        }
        container.appendChild(table);
    },
    generateTableRowsClean: function (tableId, container, values, hiddenColumns) {
        let table = document.getElementById(tableId);
        let tableRow;
        let tableElement;
        if (values.length == 0) {

            return;
        }
        for (let i = 0; i < values.length; i++) {
            let counter = 0;
            tableRow = document.createElement('tr');
            for (let key in values[i]) {
                tableElement = document.createElement('td')
                if (Array.isArray(values[i][key])) {
                    tableElement.innerText = `${values[i][key][2]} / ${values[i][key][1]} / ${values[i][key][0]}`;
                } else if (values[i][key] === false) {
                    tableElement.innerText = 'Denied';

                } else if (values[i][key] === true) {
                    tableElement.innerText = 'Approved';
                } else if (key == 'amount') {
                    tableElement.innerText = `$ ${values[i][key]}`
                } else {
                    tableElement.innerText = values[i][key];
                }

                tableRow.appendChild(tableElement);

                for (let hidden of hiddenColumns) {
                    if (hidden == counter) {
                        tableElement.hidden = true;
                    }
                }
                counter++;
            }
            tableRow.id = "completed-id-" + String(tableRow.firstChild.innerText);
            table.appendChild(tableRow);
        }
        container.appendChild(table);
    },
    generateTableRowsCleanPender: function (tableId, container, values, hiddenColumns) {
        let table = document.getElementById(tableId);
        let tableRow;
        let tableElement;
        if (values.length == 0) {

            return;
        }
        for (let i = 0; i < values.length; i++) {
            let counter = 0;
            tableRow = document.createElement('tr');
            for (let key in values[i]) {
                tableElement = document.createElement('td')
                if (Array.isArray(values[i][key])) {
                    tableElement.innerText = `${values[i][key][2]} / ${values[i][key][1]} / ${values[i][key][0]}`;
                } else if (values[i][key] === false) {
                    tableElement.innerText = 'Pending';
                } else if (values[i][key] === true) {
                    tableElement.innerText = 'Answered';
                } else if (key == 'amount') {
                    tableElement.innerText = `$ ${values[i][key]}`
                } else {
                    tableElement.innerText = values[i][key];
                }


                tableRow.appendChild(tableElement);

                for (let hidden of hiddenColumns) {
                    if (hidden == counter) {
                        tableElement.hidden = true;
                    }
                }
                counter++;
            }
            tableRow.id = "request-id-" + String(tableRow.firstChild.innerText);
            table.appendChild(tableRow);
        }
        container.appendChild(table);
    },
    generateManagerTableRow: function (tableId, container, values, funky) {
        let table = document.getElementById(tableId);
        let tableRow;
        let tableElement;
        if (values.length == 0) {

            return;
        }
        for (let i = 0; i < values.length; i++) {
            tableRow = document.createElement('tr');
            for (let key in values[i]) {
                tableElement = document.createElement('td')
                tableElement.innerText = values[i][key];
                tableRow.appendChild(tableElement);
            }
            pageUtility.attachButtonElement('Respond', 'respond-button', tableRow, 'click', e => {
                funky(e);
            });
            table.appendChild(tableRow);
        }
        container.appendChild(table);
    },
    generateManagerTableRowClean: function (tableId, container, values, funky, hiddenColumns) {
        let table = document.getElementById(tableId);
        let tableRow;
        let tableElement;
        if (values.length == 0) {

            return;
        }
        for (let i = 0; i < values.length; i++) {
            let counter2 = 0;
            tableRow = document.createElement('tr');
            for (let key in values[i]) {
                tableElement = document.createElement('td');
                if (Array.isArray(values[i][key])) {
                    tableElement.innerText = `${values[i][key][2]} / ${values[i][key][1]} / ${values[i][key][0]}`;
                } else if (values[i][key] === false) {
                    tableElement.innerText = 'Pending';
                } else if (values[i][key] === true) {
                    tableElement.innerText = 'Answered';
                } else if (key == 'amount') {
                    tableElement.innerText = `$ ${values[i][key]}`
                } else {
                    tableElement.innerText = values[i][key];
                }
                for (let hidden of hiddenColumns) {
                    if (hidden == counter2) {
                        tableElement.hidden = true;
                    }
                }
                counter2++;
                tableRow.appendChild(tableElement);
            }
            pageUtility.attachButtonElement('respond-button', 'Respond', tableRow, 'click', e => {
                funky(e);
            });
            table.appendChild(tableRow);
        }
        container.appendChild(table);
    },
    generateCompletedRequestRowClean: function (tableId, container, values, funky, hiddenColumns) {
        let table = document.getElementById(tableId);
        let tableRow;
        let tableElement;
        if (values.length == 0) {
            return;
        }
        for (let i = 0; i < values.length; i++) {
            let counter2 = 0;
            tableRow = document.createElement('tr');
            for (let key in values[i]) {
                tableElement = document.createElement('td');
                if (Array.isArray(values[i][key])) {
                    tableElement.innerText = `${values[i][key][2]} / ${values[i][key][1]} / ${values[i][key][0]}`;
                } else if (values[i][key] === false) {
                    tableElement.innerText = 'Pending';
                } else if (values[i][key] === true) {
                    tableElement.innerText = 'Answered';
                } else if (key == 'amount') {
                    tableElement.innerText = `$ ${values[i][key]}`
                } else {
                    tableElement.innerText = values[i][key];
                }
                for (let hidden of hiddenColumns) {
                    if (hidden == counter2) {
                        tableElement.hidden = true;
                    }
                }
                counter2++;
                tableRow.appendChild(tableElement);
            }
            pageUtility.attachButtonElement('open-cr-button', 'View Response', tableRow, 'click', e => {
                funky(e);
            });
            table.appendChild(tableRow);
        }
        container.appendChild(table);
    },
    generateSingleTableRow: function (value, container) {
        let tableRow = document.createElement('tr');
        let tableElement;
        if (values.length == 0) {

            return;
        }
        for (let key in value) {
            tableElement = document.createElement('td')
            if (key == 'amount') {
                tableElement.innerText = `$ ${value[key]}`
            } else {
                tableElement.innerText = value[key];
            }

            tableRow.appendChild(tableElement);
        }
        container.appendChild(tableRow);
    },
    generateSingleTableRowStats: function (value, container) {
        let tableRow = document.createElement('tr');
        let tableElement;
        if (!value) {
            return;
        }
        for (let key in value) {
            tableElement = document.createElement('td')
            if (key == 'meanAverage' || key == 'sum') {
                tableElement.innerText = `$ ${value[key]}`
            } else {
                tableElement.innerText = value[key];
            }
            tableRow.appendChild(tableElement);
        }
        container.appendChild(tableRow);
    },
    generateSingleTableRowIndividualStat: function (value, container) {
        let tableRow = document.createElement('tr');
        let tableElement;
        if (!value) return;

        for (let key in value) {
            tableElement = document.createElement('td')
            if (key == 'average' || key == 'sum') {
                tableElement.innerText = `$ ${value[key]}`
            } else {
                tableElement.innerText = value[key];
            }
            tableRow.appendChild(tableElement);
        }
        container.appendChild(tableRow);
    },
    generateSingleTableRowClean: function (value, container, hiddenColumns) {
        let tableRow = document.createElement('tr');
        let tableElement;
        let counter = 0;
        if (!value) return;

        for (let key in value) {
            tableElement = document.createElement('td')
            if (Array.isArray(value[key])) {
                tableElement.innerText = `${value[key][2]} / ${value[key][1]} / ${value[key][0]}`;
            } else if (value[key] === false) {
                tableElement.innerText = 'Denied';
            } else if (value[key] === true) {
                tableElement.innerText = 'Approved';
            } else if (key == 'amount') {
                tableElement.innerText = `$ ${values[i][key]}`
            } else {
                tableElement.innerText = value[key];
            }
            tableRow.appendChild(tableElement);

            for (let hidden of hiddenColumns) {
                if (hidden == counter) {
                    tableElement.hidden = true;
                }
            }
            counter++;
        }
        container.appendChild(tableRow);
    },
    generateSingleTableRowCleanPender: function (value, container, hiddenColumns) {
        let tableRow = document.createElement('tr');
        let tableElement;
        let counter = 0;
        if (!value) return;

        for (let key in value) {
            tableElement = document.createElement('td')
            if (Array.isArray(value[key])) {
                tableElement.innerText = `${value[key][2]} / ${value[key][1]} / ${value[key][0]}`;
            } else if (value[key] === false) {
                tableElement.innerText = 'Pending';

            } else if (value[key] === true) {
                tableElement.innerText = 'Answered';
            } else if (key == 'amount') {
                tableElement.innerText = `$ ${value[key]}`
            } else {
                tableElement.innerText = value[key];
            }
            tableRow.appendChild(tableElement);

            for (let hidden of hiddenColumns) {
                if (hidden == counter) {
                    tableElement.hidden = true;
                }
            }
            counter++;
        }
        container.appendChild(tableRow);
    }
}

const requestViewUtility = {
    openCreateRequestDisplay: function () {
        if (createRequestCheck) {
            createRequestCheck = !createRequestCheck;

            requestFormContainer = document.createElement('form');
            requestFormContainer.id = 'request-form-container';

            // pageUtility.generateNewLine(3, requestFormContainer);
            // pageUtility.generateNewLine(2, requestFormContainer);

            pageUtility.attachSelectOptions('request-type',
                "Create Request",
                ['Travel', 'Equipment', 'Consumable', 'Book'],
                requestFormContainer);
            
            pageUtility.generateNewLine(2, requestFormContainer);

            pageUtility.attachInputElement('request-message', 'Request Message', requestFormContainer);

            pageUtility.generateNewLine(2, requestFormContainer);
            pageUtility.attachInputNumberElement('amount', '$0.00', requestFormContainer);
            pageUtility.generateNewLine(2, requestFormContainer);

            requestViewUtility.fileUpload('file-upload', requestFormContainer);
            pageUtility.generateNewLine(2, requestFormContainer);
            pageUtility.attachButtonElement('close-create-button', 'Close', requestFormContainer, 'click', requestViewUtility.closeCreateRequestDisplay);
            pageUtility.attachButtonElement('submit-request', 'Submit', requestFormContainer, 'click', userRequestUtility.getCreateRequest);

            containerOptions.appendChild(requestFormContainer);
            homepageTopContainer.appendChild(containerOptions);
        }

    },
    closeCreateRequestDisplay: function () {
        createRequestCheck = !createRequestCheck;
        let requestForm = document.getElementById('request-form-container')
        containerOptions.removeChild(requestForm);
    },
    openRespondRequestDisplay: function (tableRow) {
        if (respondRequestCheck) {
            respondRequestCheck = !respondRequestCheck;

            respondFormContainer = document.createElement('form');
            respondFormContainer.id = 'respond-form-container';
            pageUtility.generateNewLine(1, respondFormContainer);
            pageUtility.attachSelectOptions('status', "Respond", ['Approve', 'Deny'], respondFormContainer);
            pageUtility.generateNewLine(2, respondFormContainer);
            pageUtility.attachInputElement('response-message', 'Response', respondFormContainer);
            pageUtility.generateNewLine(1, respondFormContainer);

            if (tableRow.children[7].innerText == 'Answered') {
                pageUtility.attachButtonElement('file-download', 'Download File', respondFormContainer, 'click', () => {
                    getFile(tableRow.children[0].innerText);
                });
            }

            pageUtility.attachButtonElement('close-respond-button', 'Close', respondFormContainer, 'click', requestViewUtility.closeRespondRequestDisplay);
            pageUtility.attachButtonElement('submit-response', 'Submit', respondFormContainer, 'click', () => {
                requestInteractionUtility.getResponse(tableRow);
            });

            allPRContainer.appendChild(respondFormContainer);

        }
    },
    closeRespondRequestDisplay: function () {
        respondRequestCheck = !respondRequestCheck;
        let respondForm = document.getElementById('respond-form-container');
        allPRContainer.removeChild(respondForm);

    },
    fileUpload: function (fileId, container) {
        let inputFile = document.createElement('input');
        inputFile.type = 'file';
        inputFile.id = fileId;
        container.appendChild(inputFile);
    },
    createPersonalRequestTables: function () {
        pageUtility.attachButtonElement('personalRequest', 'Pending Requests', personalPRContainer, 'click', () =>{
            if (document.getElementById('pending-request-table').style.display === "block") {
                document.getElementById('pending-request-table').style.display = "none";
            } else {
                document.getElementById('pending-request-table').style.display = "block";
            }
        });
        // pageUtility.generateTableElement(['id', 'employeeId', 'type', 'requestMessage', 'amount', 'dateSubmission'], 'pending-request-table', personalPRContainer);
        
        pageUtility.generateTableElement(['Request Type', 'Message', 'Cost', 'Submitted'], 'pending-request-table', personalPRContainer);
        homepageView.appendChild(personalPRContainer);

        pageUtility.attachButtonElement('pastRequests', 'Past Requests', personalARContainer, 'click', () =>{
            if (document.getElementById('answered-request-table').style.display === "block") {
                document.getElementById('answered-request-table').style.display = "none";
            } else {
                document.getElementById('answered-request-table').style.display = "block";
            }
        });


        pageUtility.generateTableElement(['Request Type', 'Message', 'Cost', 'Submitted', 'View Response'], 'answered-request-table', personalARContainer);
        homepageView.appendChild(personalARContainer);

    },
    createAllRequestTable: function () {
        pageUtility.attachButtonElement('allPendingRequests', 'All Pending Requests', allPRContainer, 'click', () =>{
            if (document.getElementById('all-pending-request-table').style.display === "block") {
                document.getElementById('all-pending-request-table').style.display = "none";
            } else {
                document.getElementById('all-pending-request-table').style.display = "block";
            }
        });

        pageUtility.generateTableElement(['Employee ID', 'Request Type', 'Message', 'Cost', 'Submitted'], 'all-pending-request-table', allPRContainer);
        homepageView.appendChild(allPRContainer);

        pageUtility.attachButtonElement('allPastRequests', 'All Past Requests', allARContainer, 'click', () =>{
            if (document.getElementById('all-answered-request-table').style.display === "block") {
                document.getElementById('all-answered-request-table').style.display = "none";
            } else {
                document.getElementById('all-answered-request-table').style.display = "block";
            }
        });

        pageUtility.generateTableElement(['Employee ID', 'Request Type', 'Message', 'Cost', 'Submitted'], 'all-answered-request-table', allARContainer);
        homepageView.appendChild(allARContainer);
    },
    displayEmployeeRequests: function () {
        let allRequestData = JSON.parse(localStorage.getItem('allRequestData'));
        requestViewUtility.displayPendingRequests(allRequestData.unansweredRequests);
        requestViewUtility.displayAnsweredRequests(allRequestData.answeredRequests);
    },
    displayManagerRequests: function (sortedRequests) {
        requestViewUtility.displayPendingRequests(sortedRequests.personalRequests.personalPendingRequests);
        requestViewUtility.displayAnsweredRequests(sortedRequests.personalRequests.personalAnsweredRequests);

        requestViewUtility.displayAllPendingRequests(sortedRequests.employeeRequests.employeePendingRequests);
        requestViewUtility.displayAllAnsweredRequests(sortedRequests.employeeRequests.employeeAnsweredRequests);
    },
    displayPendingRequests: function (pendingRequests) {
        pageUtility.generateTableRowsCleanPender('pending-request-table', personalPRContainer, pendingRequests, [0, 1, 6, 7]);
        homepageView.appendChild(personalPRContainer);
    },
    displayAnsweredRequests: function (answeredRequests) {
        pageUtility.generateCompletedRequestRowClean('answered-request-table', personalARContainer, answeredRequests, requestInteractionUtility.openResponse, [0, 1, 6, 7]);
        homepageView.appendChild(personalARContainer);
    },
    displayCompletedRequests: function (completedRequests) {
        pageUtility.generateTableRowsClean('completed-request-table', personalCRContainer, completedRequests, [0, 1, 2]);
        homepageView.appendChild(personalCRContainer);
    },
    displayAllPendingRequests: function (pendingRequests) {
        pageUtility.generateManagerTableRowClean('all-pending-request-table', allPRContainer, pendingRequests, requestInteractionUtility.respondToRequest, [0, 6, 7]);
        homepageView.appendChild(allPRContainer);
    },
    displayAllAnsweredRequests: function (answeredRequests) {
        pageUtility.generateCompletedRequestRowClean('all-answered-request-table', allARContainer, answeredRequests, requestInteractionUtility.openResponseManager, [0, 6, 7])
        homepageView.appendChild(allARContainer);
    },
    displayAllCompletedRequests: function (completedRequests) {
        pageUtility.generateTableRowsClean('all-completed-request-table', allCRContainer, completedRequests, [0]);
        homepageView.appendChild(allCRContainer);
    },
    displayNewPendingRequests: function () {
        let newPendingRequestData = JSON.parse(localStorage.getItem('newRequestData'));
        let table = document.getElementById('pending-request-table');
        pageUtility.generateSingleTableRowCleanPender(newPendingRequestData, table, [0, 1]);
    },
    failedToGetRequests: function () {
        window.alert('Failed to get requests from server');
    },
    viewCompletedResponseDisplay: function (tableRow) {
        let allRequestData = JSON.parse(localStorage.getItem('allRequestData'));
        let answeredRequestId = tableRow.children[0].innerText;
        for (const iterator of allRequestData.completedRequests) {
            if (answeredRequestId == iterator.id) {
                requestViewUtility.openCompletedRequestDisplay(iterator);
            }
        }
    },
    viewCompletedResponseDisplayManager: function (tableRow) {
        let answeredRequestId = tableRow.children[0].innerText;
        for (const iterator of sortedRequests.employeeRequests.employeeCompletedRequests) {
            if (answeredRequestId == iterator.id) {
                requestViewUtility.openCompletedRequestDisplayManager(iterator);
            }
        }
    },
    openCompletedRequestDisplay: function (completedRequest) {
        if (completedRequestCheck) {

            pageUtility.clearView(personalCRContainer);
            
            pageUtility.attachButtonElement('completedRequests', 'Completed Request', personalCRContainer, 'click', () =>{
                if (document.getElementById('completed-request-table').style.display === "block") {
                    document.getElementById('completed-request-table').style.display = "none";
                } else {
                    document.getElementById('completed-request-table').style.display = "block";
                }
            });
            // pageUtility.generateTableElement(['id', 'employeeId', 'managerId', 'status', 'response', 'dateResolved'], 'completed-request-table', personalCRContainer);
            pageUtility.generateTableElement(['Status', 'Response', 'Resolved'], 'completed-request-table', personalCRContainer);
            homepageView.appendChild(personalCRContainer);
            pageUtility.generateSingleTableRowClean(completedRequest, document.getElementById('completed-request-table'), [0, 1, 2]);
            homepageView.appendChild(personalCRContainer);
        }
    },
    openCompletedRequestDisplayManager: function (completedRequest) {
        if (completedRequestCheck) {


            pageUtility.clearView(allCRContainer);
            pageUtility.attachButtonElement('allCompletedRequests', 'Completed Request', allCRContainer, 'click', () =>{
                if (document.getElementById('all-completed-request-table').style.display === "block") {
                    document.getElementById('all-completed-request-table').style.display = "none";
                } else {
                    document.getElementById('all-completed-request-table').style.display = "block";
                }
            });
            // pageUtility.generateTableElement(['id', 'employeeId', 'managerId', 'status', 'response', 'dateResolved'], 'completed-request-table', personalCRContainer);
            pageUtility.generateTableElement(['Employee ID', 'Manager ID', 'Response', 'Responding Message', 'Resolved'], 'all-completed-request-table', allCRContainer);
            homepageView.appendChild(allCRContainer);
            pageUtility.generateSingleTableRowClean(completedRequest, document.getElementById('all-completed-request-table'), [0]);
            homepageView.appendChild(allCRContainer);
        }
    }
}

const requestInteractionUtility = {
    respondToRequest: function (e) {
        let tableRow = e.target.parentNode;
        requestViewUtility.openRespondRequestDisplay(tableRow);
    },
    getResponse: function (tableRow) {
        let userData = JSON.parse(localStorage.getItem('userData'));
        let responseObj = userRequestUtility.getResponse();
        let managerResponseJson = JSON.stringify({ requestId: tableRow.children[0].innerText, employeeId: tableRow.children[1].innerText, managerId: userData.employeeId, status: responseObj.bool, response: responseObj.text });
        tableRow.innerHTML = "";
        postRequestResponse(managerResponseJson);
    },
    openResponse: function (e) {
        let tableRow = e.target.parentNode;

        requestViewUtility.viewCompletedResponseDisplay(tableRow);
    },
    openResponseManager: function (e) {
        let tableRow = e.target.parentNode;

        requestViewUtility.viewCompletedResponseDisplayManager(tableRow);
    }
}

const userViewUtility = {
    welcomeBanner: function () {
        let employeeData = JSON.parse(localStorage.getItem('employeeData'));
        pageUtility.attachTitleElement('h2', employeeData.firstName + " " + employeeData.lastName, bannerDiv);
    }
}

const statisticsUtility = {
    viewGeneralData: function () {

        document.getElementsByName('view-general-button')[0].hidden = true;
        document.getElementsByName('view-ranking-button')[0].hidden = false;
        document.getElementsByName('view-individual-button')[0].hidden = false;

        generalStatisticsContainer.hidden = false;
        employeeStatisticsContainer.hidden = true;
        indivdiualStatisticsContainer.hidden = true;

    },
    viewEmployeeData: function () {


        document.getElementsByName('view-ranking-button')[0].hidden = true;
        document.getElementsByName('view-general-button')[0].hidden = false;
        document.getElementsByName('view-individual-button')[0].hidden = false;
        generalStatisticsContainer.hidden = true;
        employeeStatisticsContainer.hidden = false;
        indivdiualStatisticsContainer.hidden = true;

    },
    viewIndividualData: function () {


        document.getElementsByName('view-individual-button')[0].hidden = true;
        document.getElementsByName('view-general-button')[0].hidden = false;
        document.getElementsByName('view-ranking-button')[0].hidden = false;

        generalStatisticsContainer.hidden = true;
        employeeStatisticsContainer.hidden = true;
        indivdiualStatisticsContainer.hidden = false;

    },
    createGeneralStatisticsTable: function () {

        pageUtility.attachTitleElement('h3', 'General Employee Role Statistics', generalStatisticsRoleContainer);
        pageUtility.generateTableElement(["Employee Roles", "Mean Average", "Sum"], 'general-employee-role-table', generalStatisticsRoleContainer);
        generalStatisticsContainer.appendChild(generalStatisticsRoleContainer);
        pageUtility.generateTableRowsStats('general-employee-role-table', generalStatisticsRoleContainer, generalStatData.sortedRoles);

        pageUtility.attachTitleElement('h3', 'General Request Type Statistics', generalStatisticsTypeContainer);
        pageUtility.generateTableElement(["Request Types", "Mean Average", "Sum"], 'general-request-type-table', generalStatisticsTypeContainer);
        generalStatisticsContainer.appendChild(generalStatisticsTypeContainer);
        pageUtility.generateTableRowsStats('general-request-type-table', generalStatisticsTypeContainer, generalStatData.sortedTypes);

        pageUtility.attachTitleElement('h3', 'General Total Statistics', generalStatisticsTotalContainer);
        pageUtility.generateTableElement(["Total", "Mean Average", "Sum"], 'general-total-table', generalStatisticsTotalContainer);
        generalStatisticsContainer.appendChild(generalStatisticsTotalContainer);
        pageUtility.generateSingleTableRowStats(generalStatData.total, document.getElementById('general-total-table'));
    },
    createGeneralEmployeeStatisticsTable: function () {
        pageUtility.attachTitleElement('h3', 'Employee Detail View', employeeStatisticsContainer);
        pageUtility.generateTableElement(['Employee ID', "Sum"], 'general-employee-statistics-table', employeeStatisticsContainer);
        pageUtility.generateTableRowsEmployeeStats('general-employee-statistics-table', employeeStatisticsContainer, generalEmployeeStatData);
    },
    createIndividualEmployeeStatisticsTable: function () {
        let tableContainer = document.getElementById('individual-employee-table');
        if (tableContainer.children[1]) tableContainer.removeChild(tableContainer.children[1]);
        pageUtility.generateSingleTableRowIndividualStat(individualEmployeeData, document.getElementById('individual-employee-table'));

    },
    individualEmployeeSearchDisplay: function () {
        individualEmployeeFormContainer = document.createElement('form');
        individualEmployeeFormContainer.id = 'individual-employee-form-container';

        pageUtility.generateNewLine(1, individualEmployeeFormContainer);
        pageUtility.attachInputNumberElement('employee-id-input', 'Employee ID', individualEmployeeFormContainer);
        pageUtility.generateNewLine(2, individualEmployeeFormContainer);

        pageUtility.attachButtonElement('find-button', 'Find', individualEmployeeFormContainer, 'click', userRequestUtility.getIndividualEmployeeRequest);
        let resetButton = document.createElement('input');
        resetButton.type = 'Reset';
        resetButton.value = 'Reset';
        individualEmployeeFormContainer.appendChild(resetButton);
        indivdiualStatisticsContainer.appendChild(individualEmployeeFormContainer);
        pageUtility.attachTitleElement('h3', 'Employee Details', indivdiualStatisticsContainer);
        pageUtility.generateTableElement(['Employee ID', 'First Name', 'Last Name', 'Role', 'Average Expenditure', 'Total'], 'individual-employee-table', indivdiualStatisticsContainer);
    },

}


function toggleLogin(toggle) {
    if (toggle) loadLoginInput(loginView);
    else pageUtility.clearView(loginView)
};

function toggleHomepage(toggle, isManager) {
    if (toggle) {
        if (isManager) {
            getGeneralStats();
            loadManagerHomepage(homepageView);
            getMTotalRequests();
        } else {
            loadEmployeeHomepage(homepageView);
            getEmployeeTotalRequests();
        }
    }
    else {
        pageUtility.clearView(homepageView);
    }
}

function toggleStatistics() {
    if (statisticsPageCheck) {
        statisticsPageCheck = !statisticsPageCheck;

        pageUtility.clearView(homepageView);
        loadStatistics(statisticsPageView);
    }
    else {
        pageUtility.clearView(statisticsPageView);
        statisticsPageCheck = !statisticsPageCheck;

        toggleHomepage(true, true);
    }
}

window.onload = toggleLogin(true);

/* Page Views*/

function loadLoginInput(loginView) {

    let formContainer = document.createElement('form');

    let loginInputContainer = document.createElement('div');

    let loginTitle = document.createElement('div');

    let title = document.createElement('h1');
    // let subTitle = document.createElement('h4');
    title.innerText = 'Fantasy Reimbursement';
    // subTitle.innerText = 'Login';
    // subTitle.id = 'subTitle';
    loginTitle.id = 'title';
    loginTitle.appendChild(title);
    // loginTitle.appendChild(subTitle);
    loginInputContainer.id = 'loginContainer';
    let loginInputDiv = document.createElement('div');
    loginInputDiv.id = 'loginDiv';

    loginView.appendChild(loginTitle);

    let submitButton = document.createElement('input');
    let resetButton = document.createElement('input');

    submitButton.type = 'button';
    submitButton.value = 'Submit';


    submitButton.addEventListener('click', getUserLogin);

    resetButton.type = 'Reset';
    resetButton.value = 'Reset';

    formContainer.id = 'formContainer';

    pageUtility.attachInputElement('username', 'Username', formContainer);
    pageUtility.generateNewLine(2, formContainer);
    pageUtility.attachInputElement('password', 'Password', formContainer);

    pageUtility.generateNewLine(2, formContainer);
    formContainer.appendChild(submitButton);
    formContainer.appendChild(resetButton);

    loginInputDiv.appendChild(formContainer);

    loginView.appendChild(loginInputDiv);
    let passwordInputBox = document.getElementsByName('password');
    passwordInputBox[0].type = 'password';

}

function loadEmployeeHomepage(homepageView) {
    homepageTopContainer = document.createElement('div');
    homepageTopContainer.id = 'homepage-top';

    bannerDiv = document.createElement('div');
    bannerDiv.id = 'banner-div';
    pageUtility.attachTitleElement('h1', 'Employee Homepage', bannerDiv);
    homepageTopContainer.appendChild(bannerDiv);

    homepageView.appendChild(homepageTopContainer);
    // pageUtility.attachTitleElement('h3', 'Options', homepageTopContainer);
    pageUtility.attachButtonElement('create-button', 'Create Request', homepageTopContainer, 'click', requestViewUtility.openCreateRequestDisplay);
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

function loadManagerHomepage(homepageView) {
    homepageTopContainer = document.createElement('div');
    homepageTopContainer.id = 'homepage-top';

    bannerDiv = document.createElement('div');
    bannerDiv.id = 'banner-div';
    pageUtility.attachTitleElement('h1', 'Manager Homepage', bannerDiv);
    homepageTopContainer.appendChild(bannerDiv);

    homepageView.appendChild(homepageTopContainer);
    // pageUtility.attachTitleElement('h3', 'Options', homepageTopContainer);
    pageUtility.attachButtonElement('create-button', 'Create Request', homepageTopContainer, 'click', requestViewUtility.openCreateRequestDisplay);
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

function loadStatistics(statisticsPageView) {
    statisticsTopContainer = document.createElement('div');
    statisticsTopContainer.id = 'statistics-top';

    bannerDiv = document.createElement('div');
    bannerDiv.id = 'banner-div';
    pageUtility.attachTitleElement('h1', 'Statistics', bannerDiv);
    statisticsTopContainer.appendChild(bannerDiv);
    statisticsPageView.appendChild(statisticsTopContainer);
    pageUtility.attachButtonElement('homepage-button', 'Return', statisticsTopContainer, 'click', toggleStatistics);


    generalStatisticsContainer = document.createElement('div');
    generalStatisticsContainer.id = 'general-statistics-container';
    pageUtility.attachButtonElement('view-general-button', 'View General Data', statisticsTopContainer, 'click', statisticsUtility.viewGeneralData);
    statisticsPageView.appendChild(generalStatisticsContainer);

    employeeStatisticsContainer = document.createElement('div');
    employeeStatisticsContainer.id = 'employee-statistics-container';
    pageUtility.attachButtonElement('view-ranking-button', 'View Employee Rankings', statisticsTopContainer, 'click', statisticsUtility.viewEmployeeData);
    statisticsPageView.appendChild(employeeStatisticsContainer);

    indivdiualStatisticsContainer = document.createElement('div');
    indivdiualStatisticsContainer.id = 'individual-statistics-container';
    pageUtility.attachButtonElement('view-individual-button', 'View Individual Data', statisticsTopContainer, 'click', statisticsUtility.viewIndividualData);
    statisticsPageView.appendChild(indivdiualStatisticsContainer);


    generalStatisticsRoleContainer = document.createElement('div');
    generalStatisticsRoleContainer.id = 'general-statistics-role-container';
    generalStatisticsTypeContainer = document.createElement('div');
    generalStatisticsTypeContainer.id = 'general-statistics-Type-container';
    generalStatisticsTotalContainer = document.createElement('div');
    generalStatisticsTotalContainer.id = 'general-statistics-total-container';


    statisticsUtility.createGeneralStatisticsTable();
    statisticsUtility.createGeneralEmployeeStatisticsTable();
    statisticsUtility.individualEmployeeSearchDisplay();
    generalStatisticsContainer.hidden = false;
    document.getElementsByName('view-general-button')[0].hidden = true;
    employeeStatisticsContainer.hidden = true;
    document.getElementsByName('view-ranking-button')[0].hidden = false;
    indivdiualStatisticsContainer.hidden = true;
    document.getElementsByName('view-individual-button')[0].hidden = false;


}

/*================================================================================================*/

/** Sorting utility */

const sortingUtility = {
    sortManagerTotalRequests: function (totalRequestData, managerId) {
        let personalPendingRequests = [];
        let personalAnsweredRequests = [];
        let personalCompletedRequests = [];

        let employeePendingRequests = [];
        let employeeAnsweredRequests = [];
        let employeeCompletedRequests = [];

        totalRequestData.unansweredRequests.forEach(sortPending);
        totalRequestData.answeredRequests.forEach(sortAnswered);
        totalRequestData.completedRequests.forEach(sortCompleted);


        function sortPending(var1) {
            if (var1.employeeId == managerId) {
                personalPendingRequests.push(var1);
            } else {
                employeePendingRequests.push(var1);
            }
        }

        function sortAnswered(var1) {
            if (var1.employeeId == managerId) {
                personalAnsweredRequests.push(var1);
            } else {
                employeeAnsweredRequests.push(var1);
            }
        }

        function sortCompleted(var1) {
            if (var1.employeeId == managerId) {
                personalCompletedRequests.push(var1);
            } else {
                employeeCompletedRequests.push(var1);
            }
        }

        let personalRequests = { personalPendingRequests, personalAnsweredRequests, personalCompletedRequests };
        let employeeRequests = { employeePendingRequests, employeeAnsweredRequests, employeeCompletedRequests };

        let sortedRequests = { personalRequests, employeeRequests };
        return sortedRequests;
    }
}


/*================================================================================================*/

/** Handle User Login */

function getUserLogin() {
    let userForm = document.getElementById('formContainer');
    let usernameInput = userForm.elements[0].value;
    let passwordInput = userForm.elements[1].value;
    if (usernameInput && passwordInput) {
        verifyUser(JSON.stringify({ username: usernameInput, password: passwordInput }));
    } else {
        window.alert("INPUT ALL FIELDS");
    }
}

function failedLogin() {
    window.alert("FAIL");
}

function successfulLogin(isManager) {
    toggleLogin(false);
    if (isManager) {
        getGeneralEmployeeStats();
        toggleHomepage(true, isManager);
    } else {
        toggleHomepage(true, isManager);
    }
}

function welcomeEmployee() {
    userViewUtility.welcomeBanner();
}
/*================================================================================================*/

/**Handle user request */

const userRequestUtility = {
    getCreateRequest: function () {
        let userData = JSON.parse(localStorage.getItem('userData'));
        let requestForm = document.getElementById('request-form-container');
        let message = requestForm.elements[0].value;
        let type = requestForm.elements[1].value;
        let amount = requestForm.elements[2].value;
        let file = document.getElementById('file-upload').files[0];

        if (message && type && amount && file) {
            createRequestObj = JSON.stringify({ employeeId: userData.employeeId, type: type, requestMessage: message, amount: amount });
            postNewRequestWithFile(createRequestObj, file);
        } else if (message && type && amount) {
            createRequestObj = JSON.stringify({ employeeId: userData.employeeId, type: type, requestMessage: message, amount: amount });
            postNewRequest(createRequestObj);
        }
        else window.alert("EMPTY INPUT")
    },
    getResponse: function () {
        let responseForm = document.getElementById('respond-form-container');
        let statusText = responseForm.elements[0].value;
        let message = responseForm.elements[1].value;
        let boolio;
        statusText == 'Approve' ? boolio = true : boolio = false;
        let responseObj = { bool: boolio, text: message };
        return responseObj;
    },
    getIndividualEmployeeRequest: function () {
        let id = individualEmployeeFormContainer.elements[0].value;

        if (id) {
            getIndividualEmployeeRequest(id);
        } else {
            window.alert('EMPTY INPUT');
        }

    }
}

/*================================================================================================*/


/* Asynchronous functions: Fetch and server logic*/

async function verifyUser(userLoginJson) {
    let loginUrl = 'http://localhost:9002/login/validate';
    try {
        let userLoginBody = await fetch(loginUrl, { method: "POST", body: userLoginJson });
        let userData = await userLoginBody.json();
        if (userData) {
            if (userData.status) {
                localStorage.setItem('userData', JSON.stringify(userData));
                getEmployeeInfo();
            } else failedLogin();
        } else failedLogin();
    } catch (e) {
        console.log(e);
        failedLogin();
    }
}

async function getEmployeeInfo() {
    let userData = JSON.parse(localStorage.getItem('userData'));
    let employeeValidationUrl = 'http://localhost:9002/login/employee-info?employeeId=' + userData.employeeId;
    try {
        let employeeResponseBody = await fetch(employeeValidationUrl, { method: "GET" });
        let employeeData = await employeeResponseBody.json();
        if (employeeData) {
            localStorage.setItem('employeeData', JSON.stringify(employeeData));
            successfulLogin(employeeData.manager);
            welcomeEmployee();
        } else failedLogin();
    } catch (e) {
        console.log(e);
        failedLogin();
    }
}

async function getEmployeeTotalRequests() {
    let employeeData = JSON.parse(localStorage.getItem('employeeData'));
    const userToken = employeeData.jwt;
    let userData = JSON.parse(localStorage.getItem('userData'));
    let employeeTotalRequestUrl = 'http://localhost:9002/employee/request/all/?employeeId=' + userData.employeeId;
    try {
        let allRequestResponseBody = await fetch(employeeTotalRequestUrl,
            {
                method: "GET",
                headers:
                {
                    'Authorization': `${userToken}`
                }
            });
        let allRequestData = await allRequestResponseBody.json();
        if (allRequestData) {
            localStorage.setItem('allRequestData', JSON.stringify(allRequestData));
            requestViewUtility.displayEmployeeRequests();
        } else failed();
    } catch (e) {
        console.log(e);
        requestViewUtility.failedToGetRequests();
    }
}

async function postNewRequest(createRequestObj) {
    let employeeData = JSON.parse(localStorage.getItem('employeeData'));
    const userToken = employeeData.jwt;
    let createNewRequestUrl = 'http://localhost:9002/employee/request/new?fileUpload=no';
    try {
        let newRequestResponseBody = await fetch(createNewRequestUrl,
            {
                method: "POST",
                headers:
                {
                    'Authorization': `${userToken}`
                },
                body: createRequestObj
            });
        let newRequestData = await newRequestResponseBody.json();
        if (newRequestData) {
            if (localStorage.getItem('newRequestData')) localStorage.removeItem('newRequestData');

            localStorage.setItem('newRequestData', JSON.stringify(newRequestData));
            requestViewUtility.closeCreateRequestDisplay();
            requestViewUtility.displayNewPendingRequests();
        } else failedLogin();
    } catch (e) {
        console.log(e);
    }
}

async function postNewRequestWithFile(createRequestObj, file) {
    let employeeData = JSON.parse(localStorage.getItem('employeeData'));
    const userToken = employeeData.jwt;
    let createNewRequestUrl = 'http://localhost:9002/employee/request/new?fileUpload=check';
    try {
        let newRequestResponseBody = await fetch(createNewRequestUrl,
            {
                method: "POST",
                headers:
                {
                    'Authorization': `${userToken}`
                },
                body: createRequestObj
            });
        let newRequestData = await newRequestResponseBody.json();
        if (newRequestData) {
            if (localStorage.getItem('newRequestData')) localStorage.removeItem('newRequestData');

            localStorage.setItem('newRequestData', JSON.stringify(newRequestData));
            requestViewUtility.closeCreateRequestDisplay();
            saveFile(file);
            requestViewUtility.displayNewPendingRequests();
        } else failedLogin();
    } catch (e) {
        console.log(e);
    }
}

async function getMTotalRequests() {
    let employeeData = JSON.parse(localStorage.getItem('employeeData'));
    const userToken = employeeData.jwt;
    let userData = JSON.parse(localStorage.getItem('userData'));
    let totalRequestUrl = 'http://localhost:9002/manager/total';
    try {
        let totalRequestResponseBody = await fetch(totalRequestUrl,
            {
                method: 'GET',
                headers:
                {
                    'Authorization': `${userToken}`
                }
            });
        let totalRequestData = await totalRequestResponseBody.json();
        if (totalRequestData) {
            sortedRequests = sortingUtility.sortManagerTotalRequests(totalRequestData, userData.employeeId);
            requestViewUtility.displayManagerRequests(sortedRequests);
        } else failed();
    } catch (e) {
        console.log(e);
        requestViewUtility.failedToGetRequests();
    }
}

async function postRequestResponse(responseObj) {
    let employeeData = JSON.parse(localStorage.getItem('employeeData'));
    const userToken = employeeData.jwt;
    let userData = JSON.parse(localStorage.getItem('userData'));
    let respondUrl = 'http://localhost:9002/manager/respond';
    try {
        let responseBody = await fetch(respondUrl,
            {
                method: "POST",
                headers:
                {
                    'Authorization': `${userToken}`
                },
                body: responseObj
            });
        newResponseData = await responseBody.json();
        pageUtility.clearView(homepageView);
        toggleHomepage(true, true, userData);
    } catch (e) {
        console.log(e);
    }
}

async function getGeneralStats() {
    let employeeData = JSON.parse(localStorage.getItem('employeeData'));
    const userToken = employeeData.jwt;
    let generalStatUrl = 'http://localhost:9002/manager/statistic/general';
    try {
        let generalStatBody = await fetch(generalStatUrl,
            {
                method: "GET",
                headers:
                {
                    "Authorization": `${userToken}`
                }
            });
        generalStatData = await generalStatBody.json();
        if (generalStatData) {
            pageUtility.attachButtonElement('statistic-page-button', 'Statistics', homepageTopContainer, 'click', toggleStatistics);
        }
    } catch (e) {
        console.log(e);
    }
}

async function getGeneralEmployeeStats() {
    let employeeData = JSON.parse(localStorage.getItem('employeeData'));
    const userToken = employeeData.jwt;
    let generalEmployeeStatUrl = 'http://localhost:9002/manager/statistic/employee-list';
    try {
        let generalEmployeeBody = await fetch(generalEmployeeStatUrl,
            {
                method: "GET",
                headers:
                {
                    "Authorization": `${userToken}`
                }
            });
        generalEmployeeStatData = await generalEmployeeBody.json();

    } catch (e) {
        console.log(e);
    }
}

async function getIndividualEmployeeRequest(id) {
    let employeeData = JSON.parse(localStorage.getItem('employeeData'));
    const userToken = employeeData.jwt;
    let individualEmployeeUrl = 'http://localhost:9002/manager/statistic/individual?employeeId=' + id;
    try {
        let individualEmployeeBody = await fetch(individualEmployeeUrl,
            {
                method: "GET",
                headers:
                {
                    "Authorization": `${userToken}`
                }
            });
        individualEmployeeData = await individualEmployeeBody.json();
        if (individualEmployeeData) {
            statisticsUtility.createIndividualEmployeeStatisticsTable();
        }
    } catch (e) {
        console.log(e);
    }
}

async function saveFile(file) {
    let employeeData = JSON.parse(localStorage.getItem('employeeData'));
    const userToken = employeeData.jwt;
    let newRequest = JSON.parse(localStorage.getItem('newRequestData'));
    let fileUploadUrl = 'http://localhost:9002/employee/request/file?fileKey=' + newRequest.id + '_file'
    try {
        let r = await fetch(fileUploadUrl,
            {
                method: "POST",
                headers:
                {
                    "Authorization": `${userToken}`
                },
                body: file
            });
    } catch (e) {
        console.log(e);
    }
}

async function getFile(requestId) {
    let employeeData = JSON.parse(localStorage.getItem('employeeData'));
    const userToken = employeeData.jwt;
    let fileDownloadUrl = 'http://localhost:9002/employee/request/treasure/?fileKey=' + requestId + '_file';
    try {
        let r = await fetch(fileDownloadUrl,
            {
                method: "GET",
                headers:
                {
                    "Authorization": `${userToken}`
                }
            });
        let file = await r.blob(['hello'], { type: 'image/jpeg' });
        let fileURL = URL.createObjectURL(file);
        const anchor = document.createElement('a');
        anchor.target = '_blank';
        anchor.href = fileURL;
        document.body.appendChild(anchor);
        anchor.click();
        document.body.removeChild(anchor);

        URL.revokeObjectURL(fileURL);
    } catch (e) {
        console.log(e);
    }
}
