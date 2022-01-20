let accs = []
let forms = []
let highestAvg = {
    name: "",
    average: 0
}

printHighestAvg()

async function getAccounts(){
    await fetch("http://localhost:700/Account/selectall")
    .then(response => response.json())
    .then(data => {
        accs = data
        getForms()
    })
}

async function getForms(){
    await fetch("http://localhost:700/Forms/actually-all")
    .then(response => response.json())
    .then(data => {
        forms = data
        averageAll(accs, forms)
    })
}

// I didn't have time to grasp Hibernate aggregate functions so... I did this... lol
function averageAll(accs, forms){
    let currentAvg = 0
    let currentUser = ""

    for(let i = 0; i < accs.length; i++){
        let storage = 0
        let count = 0
       
        for(let j = 0; j < forms.length; j++){
            if(forms[j].fKey === accs[i].emp_id){
                storage += forms[j].amount
                count += 1
            }
            if(j === forms.length - 1){
                storage = storage/count
            }
        }

        if(storage > currentAvg){
            currentAvg = storage
            currentUser = accs[i].fName + " " + accs[i].lName
        }
    }

    highestAvg.name = currentUser
    highestAvg.average = currentAvg
}

function printHighestAvg(){
    getAccounts()
}