let masterLandingPage = document.getElementById("loginPage");
let userHomePage = document.getElementById("userHome");
let userBanner = document.createElement("div");
userBanner.id = "userbanner";
let titleBanner = document.createElement("div");
titleBanner.id = "titlebanner";
let inputUser;
let inputPass;
let inputButton;
let loginObject;
let loginID; 
let storedLogin;
let info;
let data_body;
let allRequests;
let response_body;
let createButton;
let reasonValue;
let amountValue;
let requestReason;
let request_body;
let requestDetails;
let updateRequest;
let updateString;
let update_body;
let returnedRequestBody;
let statBody;
function breakGenerator(breakNum, object){
    let br;
    for(let i = 0; i < breakNum ; i++){
     br = document.createElement("br");
    object.appendChild(br.cloneNode());
    }
}
 function createTagOnTitle(hTag, tagID, tagText ) {
    let tag = document.createElement(hTag);
    tag.id = tagID;
    tag.innerHTML = tagText;
    titleBanner.appendChild(tag);
    masterLandingPage.appendChild(titleBanner);
    
}
function createTagOnHome(hTag, tagID, tagText ) {
    let tag = document.createElement(hTag);
    tag.id = tagID;
    tag.innerHTML = tagText;
    userBanner.appendChild(tag);
    userHomePage.appendChild(userBanner);
}
function initHomePage(){
    userHomePage.appendChild(userBanner);

}
function initLoginPage(){
    masterLandingPage.appendChild(titleBanner);
}
function formFactory(firstFormName, firstFormType, secondFormName, secondFormType, buttonName){

 const formGenerator = document.createElement("form");
 inputUser = document.createElement("input");
const userLabel = document.createElement("label");
const passLabel = document.createElement("label");
 
userLabel.innerHTML = firstFormName;
formGenerator.appendChild(userLabel);
breakGenerator(1,formGenerator);
passLabel.innerHTML = secondFormName;

 inputUser.type = firstFormType;
 inputUser.name = firstFormName;
 inputUser.id = firstFormName + "1";
 inputPass = document.createElement("input");
 inputPass.type = secondFormType;
 inputPass.name = secondFormName;
 inputPass.id = secondFormName + "1";
  inputButton = document.createElement("input");
 inputButton.type = "button";
 inputButton.value = "Submit";
 inputButton.name = buttonName;
 inputButton.id = buttonName + "1";
 formGenerator.appendChild(inputUser);
 breakGenerator(1,formGenerator);
 formGenerator.appendChild(passLabel);
 breakGenerator(1,formGenerator)
 formGenerator.appendChild(inputPass);
 breakGenerator(2,formGenerator)
 formGenerator.appendChild(inputButton); 
titleBanner.appendChild(formGenerator);
}
function formFactoryHome(firstFormName, firstFormType, secondFormName, secondFormType, buttonName){

    const formGenerator = document.createElement("form");
    formGenerator.id ="formID"
    inputUser = document.createElement("input");
   const userLabel = document.createElement("label");
   const passLabel = document.createElement("label");
    
   userLabel.innerHTML = firstFormName;
   formGenerator.appendChild(userLabel);
   breakGenerator(1,formGenerator);
   passLabel.innerHTML = secondFormName;
   
    inputUser.type = firstFormType;
    inputUser.name = firstFormName;
    inputUser.id = firstFormName + "2";
    inputPass = document.createElement("input");
    inputPass.type = secondFormType;
    inputPass.name = secondFormName;
    inputPass.id = secondFormName + "2";
     createButton = document.createElement("input");
    createButton.type = "button";
    createButton.value = "Submit";
    createButton.name = buttonName;
    createButton.id = buttonName + "2";
    formGenerator.appendChild(inputUser);
    breakGenerator(1,formGenerator);
    formGenerator.appendChild(passLabel);
    breakGenerator(1,formGenerator)
    formGenerator.appendChild(inputPass);
    breakGenerator(2,formGenerator)
    formGenerator.appendChild(createButton); 
   

   userBanner.appendChild(formGenerator);
   
   

 
}
function tagFactory(){
createTagOnTitle("h1", "h1Tag", "Super Weenie Hut Srs");
createTagOnTitle("h1", "h3Tag", "Reimbursements");

}
function getDate(){
    while(data_body){
        data_body.submittedDate = data_body.submittedDate.toString();
        
    }
}
function tableGenerator(){
    let headers = ['Request ID', 'Submitted By', 'Submitted Date', 'Request Amount','Reason', 'Status']
    let tableDiv = document.createElement("div");
    tableDiv.id = "tablediv";
    let masterTable = document.createElement("table");
    masterTable.id = "mastertable"
    let tableRows = document.createElement("tr");
    tableRows.id = "rows";
    headers.forEach(headerText => {
        let header = document.createElement("th");
        let textNode = document.createTextNode(headerText);
        header.appendChild(textNode);
        tableRows.appendChild(header);

    });
    masterTable.appendChild(tableRows);

        data_body.forEach(reqs => {
            let row = document.createElement('tr');
            Object.values(reqs).forEach(vals => {
                let cell = document.createElement('td');
                let node = document.createTextNode(vals);
                cell.appendChild(node);
                row.appendChild(cell);
            })
            masterTable.appendChild(row);

        });
        tableDiv.appendChild(masterTable);
        
        userBanner.appendChild(tableDiv);
      }

 function tableGeneratorManager(){
        let headers = ['Request ID', 'Submitted By', 'Submitted Date', 'Request Amount','Reason', 'Status']
        let tableDiv = document.createElement("div");
        tableDiv.id = "tablediv";
        let masterTable = document.createElement("table");
        masterTable.id = "mastertable"
        let tableRows = document.createElement("tr");
        tableRows.id = "rows";
        headers.forEach(headerText => {
            let header = document.createElement("th");
            let textNode = document.createTextNode(headerText);
            header.appendChild(textNode);
            tableRows.appendChild(header);
    
        });
        masterTable.appendChild(tableRows);
    
            returnedRequestBody.forEach(reqs => {
                let row = document.createElement('tr');
                Object.values(reqs).forEach(vals => {
                    let cell = document.createElement('td');
                    let node = document.createTextNode(vals);
                    cell.appendChild(node);
                    row.appendChild(cell);
                })
                masterTable.appendChild(row);
    
            });
            tableDiv.appendChild(masterTable);
            
            userBanner.appendChild(tableDiv);
          }

// function postFetcher(url,bodyData) 
//     {
   
// }
async function verifyLogin(){
    console.log("Go back to Java please?");
    // console.log(loginObject);
    let objectString = JSON.stringify(loginObject);
    let url = "http://localhost:8800/reimbursements/validate";
    try{

        let iHateJS = await fetch(url,{method: 'POST',body: objectString,  
    });
         response_body = await iHateJS.json();
      

            loginID = {
             submittedBy:  response_body[0].userLogin
           }
            // console.log(loginID)
            titleBanner.remove();
            if (response_body[0].manager){
                createTagOnHome("button","modify","Modify Requests");
                createTagOnHome("button","all","View All");
                createTagOnHome("button","stats","Statistics");
                console.log("IS  a manager");

                modifyRequest();
            }else{

            }
        //    initHomePage();
           
        
    }catch(e){
        console.log(e)
    }
    requestsByUser();
    
}
function modifyRequest(){
    let modify = document.getElementById("modify");  
    modify.addEventListener("click",() =>{
   formFactoryHome("Request ID", "text", "Status", "text","Submit");
   createButton.addEventListener("click", () =>{
                   updateRequestButtons();
   })
   });
}
function viewAllRequests(){
    let view = document.getElementById("all");
    let tableKiller = document.getElementById("mastertable");

    view.addEventListener("click", () => {
    tableKiller.remove();
    returnAllRequests();
    });
}
async function returnAllRequests(){
let url = "http://localhost:8800/reimbursements/manager/all";
try{
let connection = await fetch (url, {method: "GET"});
returnedRequestBody = await connection.json();
formatDate(returnedRequestBody);
tableGeneratorManager();
console.log(returnedRequestBody);
}catch(e){
console.log(e);
}
}
function formatDate(body){
    for (const dateChange of body){
        for (let i = 0; i < body.length; i++){
            body[i].submittedDate = new Date(body[i].submittedDate).toLocaleDateString();
        }
    }
}
async function requestsByUser(){
    console.log(loginID); 
    console.log("Made it to the other thing")

    let url = "http://localhost:8800/reimbursements/show";
    storedLogin = JSON.stringify(loginID)
    try{
        let connection = await fetch(url, {method: 'POST', body: storedLogin});
         data_body = await connection.json();
        console.log(data_body);
        formatDate(data_body);
         info = JSON.stringify(data_body)
         data_body.forEach(infoData =>{
             console.log(Object.values(infoData));
         })

        // tablediv.innerHTML = info;
        createTagOnHome("h1", "h1Tag", `Welcome Back, ${response_body[0].firstName}`)

        initHomePage();
        tableGenerator();
        createTagOnHome("button", "logout", "Log Out")
        createTagOnHome("button","createnew","Create New Request")
         homeOptions();

    }catch(e){
        console.log(e);
        window.alert("Incorrect Username or Password");

    }
    viewAllRequests();
    showStatistics();

   

}
function showStatistics(){
    let stats = document.getElementById("stats");
    stats.addEventListener("click", () =>{
        let tableKiller = document.getElementById("mastertable");
        showStats();
    })
}
async function showStats(){
    let url = "http://localhost:8800/reimbursements/manager/stats";

    
    try{
        let connection = await fetch(url,{method: "GET" });

        statBody = await connection.text();
        window.alert(statBody);
    }catch(e){
        console.log(e);
    }
}
 function homeOptions(){
    let logout = document.getElementById("logout");
    let newReq= document.getElementById("createnew");
       logout.addEventListener('click', () =>{
        console.log("worked")
        userBanner.remove();
        location.reload();
        initLoginPage();
       

    })
    
    newReq.addEventListener("click", ()=>{
     formFactoryHome("Reason for Request","text","Requested Amount", "text","Submit", "newReq");
     userHomePage.appendChild(userBanner);
     createButton.addEventListener("click",()=>{
         reasonValue = document.getElementById("Reason for Request2")
         amountValue = document.getElementById("Requested Amount2")
         requestDetails = {
             submittedBy: response_body[0].userLogin,
            reason: reasonValue.value,
            requestAmount: amountValue.value

        };
        console.log(requestDetails);
        newRequest();
     })
    
    });
}
async function newRequest(){
    let url ="http://localhost:8800/reimbursements/new";
    requestReason = JSON.stringify(requestDetails)
    try{
       let connection = await fetch(url, {method:"POST", body: requestReason});
    //    request_body = await connection.json();
       window.alert("Refund submitted. Relog to see updated list")
    }catch(e){
       console.log(e);
    }
}
 function updateRequestButtons(){
    let requestID = document.getElementById("Request ID2");
    let status = document.getElementById("Status2");
  
        updateRequest = {
            requestID: requestID.value, 
            status: status.value

    };
    console.log(updateRequest);
  
   
            if(data_body[0].submittedBy == response_body[0].userLogin)
            {
                
                window.alert("Ever heard of separations of powers, checks and balances? Nice try bud")
            }
            else{
                  fetchUpdates();

            }
        
    




}
async function fetchUpdates(){
let url = "http://localhost:8800/reimbursements/manager/update";
updateString = JSON.stringify(updateRequest);
try{
    let connection = await fetch (url,{method: "PUT", body: updateString});
    // update_body = await connection.json();
    
}catch(e){
    console.log(e);
}
}
function initBoi (){
    tagFactory();
    formFactory("Username", "text", "Password", "password", "Submit", "login");
    inputButton.addEventListener('click', ()=>{
        
        loginObject = {
            userLogin: inputUser.value,
            userPass: inputPass.value
        
        }
        // console.log(loginObject)
        verifyLogin();
    
        });
    }
    
    initBoi();
