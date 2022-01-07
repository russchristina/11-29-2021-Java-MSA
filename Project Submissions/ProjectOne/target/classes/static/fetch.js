
let poke_url = 'https://pokeapi.co/api/v2/pokemon/hitmonchan'
let dog_url = 'https://api.thedogapi.com/v1/breeds/search?q=german%20shepherd'
let iss_url = 'https://api.wheretheiss.at/v1/satellites/25544'

// fetch(url).then(response =>{
//     response.json().then(
//         json_body =>{
//             console.log(json_body)
//         }
//     )
// })

// async function getPokeData(){
// let response_body = await fetch(url)
// let data = await response_body.json()
// console.log(data)
// }
// window.onload = function doThings(){
//     this.getPokeData()
// }
// getLogin();

async function getDoggo(){
    let breed_info = await fetch(dog_url)
    let data = await breed_info.json()
    console.log('donde esta?')
    // console.log(data[0].name)
    const {dogName,[0]:name} = data;
    document.getElementById('doggo').textContent = data[0].name
    
// }
// getISS();
// async function getISS(){
// let iss = await fetch(iss_url)
// let data = await iss.json()
// // console.log(data.latitude)
// // console.log(data.longitude)
// const {latitude, longitude} = data;

// document.getElementById('lat').textContent = latitude;
// document.getElementById('lon').textContent = longitude;
}
getDoggo();
