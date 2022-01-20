// ==============================================
// ==============================================
// GET ALL Reimbursements for employee
// ==============================================
// ==============================================
let reimburseCards = document.querySelector(".reimburseCards");
let displayCard;
let inited = false;

let findAllReimburse =
  "http://localhost:8000/employee/findAll/" + document.title;

let getAllReimbursements = document.getElementById("getAllReimbursements");
let showFormButton = document.getElementById("showForm");

let showForm = document.getElementById("hide");

// ========================
//GO BACK
// ========================
goBack.addEventListener("click", () => {
  location.href = "index.html";
});

// ========================
// ========================

const getAll = async (employeeURL) => {
  const getAllReim = async (employeeURL) => {
    let response_body = await fetch(employeeURL);
    let data = await response_body.json();
    return data;
  };

  let allReimburse = await getAllReim(employeeURL);

  function displayAll(allReimburse) {
    displayCard = allReimburse.map(function (item) {
      return `<div class="card">
        <p class="cardText reimbursed_invoice_num"><b>Invoice: </b>${
          item.reimbursed_invoice_num
        }</p>
        <p class="cardText reimbursed_employee"><b>Employee:</b> ${
          item.reimbursed_employee
        }</p>
        <p class="cardText reimbursed_past_pending"><b>Status:</b> ${
          item.reimbursed_past_pending
        }</p>
        <p class="cardText reimbursed_approval"><b>Approval:</b> ${
          item.reimbursed_approval
        }</p>
        <p class="cardText reimbursed_amount"><b>Amount:</b> $${item.reimbursed_amount.toFixed(
          2
        )}</p>  
        <p class="cardText reimbursed_reason"><b>Reason:</b> ${
          item.reimbursed_reason
        }</p>
        <p class="cardText reimbursed_manager_reason"><b>Manager Reason:</b> ${
          item.reimbursed_manager_reason
        }</p>
        </div>`;
    });

    displayCard = displayCard.join("");
    return displayCard;
  }

  displayAll(allReimburse);
  inited = true;
};

function display() {
  if (inited) {
    reimburseCards.innerHTML = displayCard;
  } else {
    setTimeout(display, 250);
  }
}

getAllReimbursements.addEventListener("click", () => {
  getAll(findAllReimburse);
  display();
});

// ==================================================
// Submit Reimbursement
// ==================================================
let submissionButton = document.getElementById("reimbursement-submission");
let submitReimbursementForm = document.getElementsByName("submissionForm")[0];
let errorMessage = document.getElementById("errorMessage");

let invalidInput = "";

function showForms() {
  showForm.className = "show";
}

showFormButton.addEventListener("click", () => {
  showForms();
});

let submissionURL =
  "http://localhost:8000/employee/" + document.title + "_submit";

function handleErrors(response) {
  if (!response.ok) throw new Error(response.status);
  return response;
}

async function postData(submissionURL, employee_Submission) {
  const response = await fetch(submissionURL, {
    method: "POST",
    mode: "no-cors",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
    },
    body: JSON.stringify(employee_Submission),
  })
    .then(handleErrors)
    .then((response) => {
      return response.json();
    })
    .catch((err) => {
      return console.log(err);
    });
}

submissionButton.addEventListener("click", () => {
  let amountInput = document.getElementById("amount").value;
  let reasonInput = document.getElementById("reason").value;
  let employee_Submission = {
    reimbursed_invoice_num: 1,
    reimbursed_employee: document.title + " Employee",
    reimbursed_past_pending: "pending",
    reimbursed_approval: "not approved",
    reimbursed_amount: parseFloat(amountInput),
    reimbursed_reason: reasonInput,
    reimbursed_manager_reason: "awaiting reason",
  };

  postData(submissionURL, employee_Submission);

  showForm.className = "hide";
  submitReimbursementForm.reset();
});
