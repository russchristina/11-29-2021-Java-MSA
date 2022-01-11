import {Login} from './Login.js'

let formPoster = new Login()

document.getElementById('login_button').addEventListener('click', e => {
    formPoster.postForm()
})



