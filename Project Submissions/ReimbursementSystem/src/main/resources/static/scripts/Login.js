export class Login{

    loginForm = {
        user:null,
        pass:null
    }

    account = {
        emp_id:0,
        username:"",
        password:"",
        fName:"",
        lName:"",
        address:"",
        city:"",
        state:"",
        zip:0,
        social_number:"",
        balance:0.00,
        manager:false
    }

    postForm(){
        this.loginForm.user = document.getElementsByName("username")[0].value
        this.loginForm.pass = document.getElementsByName("password")[0].value
        this.login()
    }

    async login(){
        await fetch("http://localhost:700/Account/login", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                },
            body: JSON.stringify(this.loginForm)
        }).then(response => response.json())
        .then(data => {
            this.account = data

            if(this.account.emp_id > 0){
                location.href = 'http://localhost:700/views/landing.html'
            }
            
        })
    }
}