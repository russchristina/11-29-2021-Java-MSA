console.log('script running')


function viewAll(){
    let xhr = new XMLHttpRequest();
    let url = "http://localhost:8000/empPortal/request/all"
     
    xhr.addEventListener("readystatechange", function() {
      if(this.readyState === 4) {
        console.log(this.responseText);
        let retrievedRequests = JSON.parse(this.responseText)
        console.log(retrievedRequests)

        let request1 = retrievedRequests[1]
        let id1 = request1.id
        let emp1= request1.employee
        let amt1 = request1.amount
        let reas1 = request1.reason
        let date1 = request1.date
        let stat1 = request1.status
        let approved1 = request1.approvedBy

        let record_1_id = document.getElementById('record_1_id')
        let record_1_employee = document.getElementById('record_1_employee')
    let record_1_amount = document.getElementById('record_1_amount')
    let record_1_reason = document.getElementById('record_1_reason')
    let record_1_date = document.getElementById('record_1_date')
    let record_1_status = document.getElementById('record_1_status')
    let record_1_approved = document.getElementById('record_1_approved')

    record_1_id.innerText = id1
    record_1_employee.innerText = emp1
    record_1_amount.innerText = "$ "+amt1
    record_1_reason.innerText = reas1
    record_1_date.innerText = new Date(date1)
    record_1_status.innerText = stat1
    record_1_approved.innerText = approved1

    let request2 = retrievedRequests[2]
        let id2 = request2.id
        let emp2= request2.employee
        let amt2 = request2.amount
        let reas2 = request2.reason
        let date2 = request2.date
        let stat2 = request2.status
        let approved2 = request2.approvedBy

        let record_2_id = document.getElementById('record_2_id')
        let record_2_employee = document.getElementById('record_2_employee')
    let record_2_amount = document.getElementById('record_2_amount')
    let record_2_reason = document.getElementById('record_2_reason')
    let record_2_date = document.getElementById('record_2_date')
    let record_2_status = document.getElementById('record_2_status')
    let record_2_approved = document.getElementById('record_2_approved')

    record_2_id.innerText = id2
    record_2_employee.innerText = emp2
    record_2_amount.innerText = "$ "+amt2
    record_2_reason.innerText = reas2
    record_2_date.innerText = new Date(date2)
    record_2_status.innerText = stat2
    record_2_approved.innerText = approved2
      }
 
    })
    xhr.open("GET", url);   
    xhr.send();
}



  

let requestTable = document.getElementById('request_table')
    







// function findById(){
//   let xhr = new XMLHttpRequest();
//   let url = "http://localhost:8000/empPortal/request/id/1"

//   xhr.addEventListener("readystatechange", function(){
//     if(this.readyState === 4){
//       console.log(this.responseText);
//       let retrievedRequest = JSON.parse(this.responseText)
//       console.log(retrievedRequest)
//     }
//   });
//   xhr.open("GET", url);
//   xhr.send();
// }

let button = document.getElementById('request-submit')

function submitNew(){
    let xhr = new XMLHttpRequest()
    let url = 'http://localhost:8000/empPortal/request/new'
    xhr.open('POST', url)

    
    let name = document.getElementById('employee-submit').value
    let amount = document.getElementById('request-amount').value
    let reason = document.getElementById('request-reason').value
    let date = document.getElementById('request-date').value
    let status = 'Pending'
    let approvedBy = 'none'

    let request = {employee:name, amount:amount, reason:reason, date:date, status:status, approvedBy:approvedBy}
    console.log(request)

    let json_string = JSON.stringify(request)
    xhr.send(json_string)



}

function clearForm(){
    document.getElementById('submit-request').reset()
}

viewAll()
//findById()
button.addEventListener('click', submitNew)
button.addEventListener('click', clearForm)