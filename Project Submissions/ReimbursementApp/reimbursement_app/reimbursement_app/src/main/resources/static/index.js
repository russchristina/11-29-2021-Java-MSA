// Employee
let reimbursementsAlliURL = "http://localhost:8000/employee/findAll/Alli";
let reimbursementsBenURL = "http://localhost:8000/employee/findAll/Ben";
let reimbursementsSamURL = "http://localhost:8000/employee/findAll/Sam";

// Manager
let getPendingOrPastAlli_byID = "http://localhost:8000/manager/Alli/";
let getPendingOrPastBen_byID = "http://localhost:8000/manager/Ben/";
let getPendingOrPastSam_byID = "http://localhost:8000/manager/Sam/335";

let updateAlliApprovalURL = "http://localhost:8000/manager/AlliApproval";
let updateBenApprovalURL = "http://localhost:8000/manager/BenApproval";
let updateSamApprovalURL = "http://localhost:8000/manager/SamApproval";

// ==============================================
// ==============================================
// LOGIN Employees/Manager
// ==============================================
// ==============================================

let getEmployeesLogin = "http://localhost:8000/employee/login";
let getManagerLogin = "http://localhost:8000/manager/login";

let welcome = document.querySelector(".welcome");

let loginForm = document.getElementsByName("loginForm")[0];

let loginButton = document.getElementById("login-submission");

const grabLogin = async (getEmployeesTableURL) => {
  const getEmployees = async (getEmployeesTableURL) => {
    let response_body = await fetch(getEmployeesTableURL);
    let loginData = await response_body.json();

    return loginData;
  };

  let employee_name = [];
  let employee_password = [];

  const loginData = await getEmployees(getEmployeesTableURL);
  console.log(loginData);

  let username = document.getElementById("username").value;
  let password = document.getElementById("password").value;

  employee_name;
  employee_password;

  for (let i = 0; i < loginData.length; i++) {
    name = loginData[i].employee_login_name;
    employee_name.push(name);
    pass = loginData[i].employee_login_password;
    employee_password.push(pass);
  }

  switch ((username, password)) {
    case (employee_name[0], employee_password[0]):
      setTimeout(() => {
        location.href = "employeeBen.html";
        sessionStorage.setItem("user", "Ben Employee");
      }, 2000);

      return (welcome.innerHTML = "Welcome " + employee_name[0]);

    case (employee_name[1], employee_password[1]):
      setTimeout(() => {
        location.href = "employeeAlli.html";
      }, 2000);

      return (welcome.innerHTML = "Welcome " + employee_name[1]);

    case (employee_name[2], employee_password[2]):
      setTimeout(() => {
        location.href = "employeeSam.html";
      }, 2000);

      return (welcome.innerHTML = "Welcome " + employee_name[2]);

    case (employee_name[3], employee_password[3]):
      setTimeout(() => {
        location.href = "manager.html";
      }, 2000);

      return (welcome.innerHTML = "Welcome " + employee_name[3]);

    default:
      return (welcome.innerHTML =
        "Sorry, username or password don't match. Please try again.");
      break;
  }
  return employeeName;
};

function getLogin() {
  grabLogin(getEmployeesLogin);
}

loginButton.addEventListener("click", () => {
  getLogin();

  setTimeout(() => {
    loginForm.reset();
    welcome.innerHTML = "";
  }, 4000);
});
