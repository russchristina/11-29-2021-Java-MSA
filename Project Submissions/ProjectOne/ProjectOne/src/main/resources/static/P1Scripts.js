let userObject;
let loginID;

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
            loginID = response_body.userLogin;
            employeePage();

        }
    }catch(e){
        window.alert("Incorrect Username or Password");
        console.log(e)
    }


}
    function employeePage(){
        

        console.log("made it here!");
        console.log(loginID);
        let id = document.getElementById("form");
        id.remove();
        let link = document.getElementById("link");
        link.remove();
       let requests = document.getElementById("h2");
       requests.innerHTML = "Reimbursement List";

       requestsByUser();
    }
  