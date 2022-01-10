let userObject;


    let username = document.getElementById("username")
    let password = document.getElementById("password")
    let form = document.getElementById("finish")
    let bodyData = document.getElementById("data")

form.addEventListener('click', ()=>{
     userObject = {
        userLogin: username.value,
        userPass: password.value
        
    };
    verifyLogin();

});
   


async function verifyLogin(){
    let url = "http://localhost:8800/reimbursements/validate";
    console.log("Go back to Java please?");
    console.log(userObject);
    let objectString = JSON.stringify(userObject);
    try{

        let iHateJS = await fetch(url,{method: 'POST',body: objectString,  
    });
        let response_body = await iHateJS.json();
      
        if (response_body.manager){
            console.log("IS a manager");

        }else{
            console.log("IS NOT a manager");
           console.log(response_body);
           let loginID = response_body.userLogin;

        }
    }catch(e){
        window.alert("Incorrect Username or Password");
    }
    employeePage();


}
    function employeePage(){
        

        console.log("made it here");
        let id = document.getElementById("form");
        id.remove();
        let link = document.getElementById("link");
        link.remove();
       let requests = document.getElementById("h2");
       requests.innerHTML = "Reimbursement List";

       requestsByUser();
    }
    async function requestsByUser(){
        console.log("Made it to the other thing")
        let url = "http://localhost:8800/reimbursements/show";
        let connection = await fetch(url);
        let data = await connection.json();
        console.log(data);

    }