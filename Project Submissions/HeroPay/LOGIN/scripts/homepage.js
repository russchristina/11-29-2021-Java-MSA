let cleanPage = false;
if(!cleanPage) clearPage();
else populate();

let createRequestObj;

function clearPage(){
    document.body.innerHTML = "";
    cleanPage = !cleanPage;
}

function populate(){
    let scriptReq = document.createElement('script');
    scriptReq.src = '../scripts/homepage.js';
    document.body.appendChild(scriptReq)
    createRequestDisplay();
    let createRequestButton = document.createElement("create-button");
    document.body.appendChild(createRequestButton);
}

let createRequestCheck = true;

function createRequestDisplay(){
    if(createRequestCheck){
        let containerOptions = document.createElement('option-div');
        let createRequestContainer = document.createElement('div');
        createRequestContainer.id = 'createRequestContainer';

        let formContainer = document.createElement('form');
        let closeContainerButton = document.createElement('input');

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