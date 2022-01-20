let home = document.getElementById('home');
let login = document.getElementById('login');


    const pageUtility = {
        logoutHomepage: function (){
            toggleHomepage(false);
            toggleLogin(true);
            createRequestCheck = true;
            respondRequestCheck = true;  

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

generateOption: function (selectOptions, container){
    let option;
    for(element in selectOptions){
        option = document.createElement('option');
        option.value = selectOptions[element];
        option.innerText = selectOptions[element];
        container.appendChild(option);
    }
},

    }