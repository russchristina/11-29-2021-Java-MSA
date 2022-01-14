// let fileUploadButton = document.getElementById('upload-button');

// fileUploadButton.addEventListener('click', async function(){
//     let upload = await uploadFile();
//     console.log(upload);
// })

// async function uploadFile(){
//     //function return values
//     let return_data = { error: 0, message: ''};
//     try{

//         if(document.querySelector('#file-to-upload').files.length == 0){
//             throw new Error('No File Selected');
//         }else{
//             let data = new FormData();
//             data.append('file', document.querySelector('#file-to-upload').files[0]);
//             return data;
//         }
//     }catch(e){
//         console.log(e);
//     }
// }
let fileUploadButton = document.getElementById('file-upload-button');

fileUploadButton.addEventListener('click', getFile);

function getFile(){
    let file = document.getElementById('files').files[0];
    if(file){
        console.log(file);
        savePhoto(file);
    }
}

async function savePhoto(file){
    let key = {key: 'photo'};
    const ctrl = new AbortController()
    setTimeout(() => ctrl.abort(), 5000);

    try{
        let r = await fetch('http://localhost:9002/employee/request/file', {method: "POST", body : file});
    }catch(e){
        console.log("damn");
    }
}

