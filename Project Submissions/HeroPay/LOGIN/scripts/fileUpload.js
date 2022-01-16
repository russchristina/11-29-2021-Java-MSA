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
let container = document.getElementById('formContainer');
let fileUploadButton = document.getElementById('file-upload-button');

let fileViewButton = document.getElementById('seeThing');

fileUploadButton.addEventListener('click', getFile);
fileViewButton.addEventListener('click', download);

function getFile(){
    let file = document.getElementById('files').files[0];
    if(file){
        console.log(file);
        savePhoto(file);
    }
}

async function download(){
    let downloadURL = "http://localhost:9002/employee/request/treasure/?fileKey=imageTest";
    try{
        let r = await fetch(downloadURL, {method: "GET"});
        let file = await r.blob(["hello"], {type : 'image/png'});
        let fileURL = URL.createObjectURL(file);
        const anchor = document.createElement('a');
        anchor.target = '_blank';
        anchor.href = fileURL;
        document.body.appendChild(anchor);
        anchor.click();
        document.body.removeChild(anchor);

        URL.revokeObjectURL(fileURL);

    }catch(e){
        console.log(e);
    }

}

async function savePhoto(file){
    try{
        let r = await fetch('http://localhost:9002/employee/request/file', {method: "POST", body : file});
    }catch(e){
        console.log("damn");
    }
}

