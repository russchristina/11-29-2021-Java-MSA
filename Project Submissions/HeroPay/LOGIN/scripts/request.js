
let createRequestButton = document.getElementsByName("create-button");
let containerOptions;
let createRequestContainer;
let formContainer;
let closeContainerButton;

this.addEventListener('load', clearPendingRequests);
this.addEventListener('load', getCompletedRequests);

createRequestButton[0].addEventListener('click', createRequestDisplay);
let createRequestCheck = true;

//Get this from login
let userObj;

let createRequestObj;

function createRequestDisplay(){
    if(createRequestCheck){

        containerOptions = document.getElementById('option-div');
        createRequestContainer = document.createElement('div');
        createRequestContainer.id = 'createRequestContainer';
        formContainer = document.createElement('form');
        closeContainerButton = document.createElement('input');

        let textBox = document.createElement('input');
        let textBoxLabel = document.createElement('label');
        
        let requestType = document.createElement('select');
        let requestTypeLabel = document.createElement('label');
        
        let amountInput = document.createElement('input');
        let amountInputLabel = document.createElement('label');
        
        let submitButton = document.createElement('input');
        let resetButton = document.createElement('input');

        closeContainerButton.type = 'button'
        closeContainerButton.value = 'Close';
        containerOptions.append(closeContainerButton);
        closeContainerButton.addEventListener('click', removeCreateRequest);

        
        let selectOptions = ['Travel', 'Equipment', 'Consumable', 'Book'];
        
        textBox.type = 'text';
        textBox.name = 'request-message';
        textBox.required = 'required';
        textBoxLabel.htmlFor = textBox.name;
        textBoxLabel.innerText = "Request Message";
        
        
        requestType.name = 'request-type';
        requestTypeLabel.htmlFor = requestType.name;
        requestTypeLabel.innerText = "Request Type";
        
        amountInput.type = 'number';
        amountInput.name = 'amount';
        amountInput.required = 'required';
        amountInputLabel.htmlFor = amountInput.name;
        amountInputLabel.innerText = 'Amount'; 
        
        submitButton.type = 'button';
        submitButton.value = 'submit';
        resetButton.type = 'reset';
        resetButton.value = 'reset';
        
        submitButton.addEventListener('click', getCreateRequest);
        submitButton.addEventListener('click', clearPendingRequests);
        
        formContainer.action = '/test.php';
        formContainer.target = '_blank';
        formContainer.method = 'GET';
        formContainer.id = 'create-request-form';
        
        
        generateNewLine(1, createRequestContainer);
        createRequestContainer.appendChild(textBoxLabel);
        generateNewLine(1, createRequestContainer);
        createRequestContainer.appendChild(textBox);
        generateNewLine(2, createRequestContainer);
        
        createRequestContainer.appendChild(requestTypeLabel);
        generateOption(selectOptions, requestType);
        generateNewLine(1, createRequestContainer);
        createRequestContainer.appendChild(requestType);
        
        
        generateNewLine(2, createRequestContainer);
        createRequestContainer.appendChild(amountInputLabel);
        generateNewLine(1, createRequestContainer);
        createRequestContainer.appendChild(amountInput);
        generateNewLine(2, createRequestContainer);
        
        createRequestContainer.appendChild(submitButton);
        createRequestContainer.appendChild(resetButton);
        
        formContainer.appendChild(createRequestContainer);
        
        containerOptions.appendChild(formContainer);
        createRequestCheck = !createRequestCheck;
    }
    
}

function removeCreateRequest(){
    createRequestCheck = true;
    containerOptions.removeChild(closeContainerButton);
    containerOptions.removeChild(formContainer);
}

function generateNewLine(lines, container){
    let emptySpace;
    for(let i = 0; i < lines;i++){
        emptySpace = document.createElement('br');
        container.appendChild(emptySpace);
    }
}

function generateOption(selectOptions, container){
    let option;
    for(element in selectOptions){
        option = document.createElement('option');
        option.value = selectOptions[element];
        option.innerText = selectOptions[element];
        container.appendChild(option);
    }

}

function getCreateRequest(){
    let userForm = document.getElementById('create-request-form');
    let message = userForm.elements[0].value;
    let type = userForm.elements[1].value;
    let amount = userForm.elements[2].value;
    
    createRequestObj = JSON.stringify({ requestMessage: message, requestType:type, requestAmount:amount});
    if(message && type && amount) window.alert(createRequestObj);
    else window.alert("EMPTY INPUT")
}

function clearPendingRequests(){
    let pendingRequestDiv = document.getElementById("pending-request-div");
    let rows = pendingRequestDiv.getElementsByClassName('pendingRows');

    for(var i = 0; i < rows.length; i++){
        pendingRequestDiv.removeChild(rows[i]);
    }

    getPendingRequests(pendingRequestDiv);
}

function getPendingRequests(pendingRequestDiv){

    for(let i = 0; i < 5; i++){
        generatePendingRow(pendingRequestDiv, i);
    }
}

function getCompletedRequests(){
    let completedRequestDiv = document.getElementById("completed-request-div");
    console.log(completedRequestDiv);
    for(let i = 0; i < 5; i++){
        generateCompletedRow(completedRequestDiv, i);
    }
}

function generatePendingRow(pendingRequestDiv, number){
    pendingRequestDiv.appendChild(createPendingRow(number));
}

function generateCompletedRow(completedRequestDiv, number){
    completedRequestDiv.appendChild(createCompletedRow(number));
}

function createPendingRow(number){
    let rowDiv = document.createElement('div');
    
    let rowId = document.createElement('h4');
    rowId.innerText = number;

    let rowMessage = document.createElement('p');
    rowMessage.innerText = 'Generic Message';

    let rowType = document.createElement('p');
    rowType.innerText = 'Food';

    let rowDate = document.createElement('p');
    rowDate.innerText = 'Date Submitted';

    let rowAmount = document.createElement('p');
    rowAmount.innerText = '$9000';

    rowDiv.className = 'pendingRows';

    rowDiv.appendChild(rowId);
    rowDiv.appendChild(rowMessage);
    rowDiv.appendChild(rowType);
    rowDiv.appendChild(rowAmount);
    rowDiv.appendChild(rowDate);
    return rowDiv;
}

function createCompletedRow(number){
    let rowDiv = document.createElement('div');
    
    let rowId = document.createElement('h4');
    rowId.innerText = number;

    let rowManager = document.createElement('p');
    rowManager.innerText = 'Brian';

    let rowMessage = document.createElement('p');
    rowMessage.innerText = 'Response Message';

    let rowDate = document.createElement('p');
    rowDate.innerText = 'Date Recieved';

    let rowStatus = document.createElement('p');
    rowStatus.innerText = 'Rejected';

    let rowAmount = document.createElement('p');
    rowAmount.innerText = '$9000';

    rowDiv.className = 'completedRows';

    rowDiv.appendChild(rowId);
    rowDiv.appendChild(rowManager)
    rowDiv.appendChild(rowMessage);
    rowDiv.appendChild(rowStatus);
    rowDiv.appendChild(rowAmount);
    rowDiv.appendChild(rowDate);
    return rowDiv;
}
