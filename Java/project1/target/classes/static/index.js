
 let button = document.getElementById('submit-button')
 
 /**
  * We also know that when we add an event listener, we must specify 1) the event on the
  * frontend that we're responding to and 2) the callback function which should be invoked
  * when that event occurs. In our case, we know that we want to send the user credentials
  * via an HTTP request when the button is clicked. This ultimately means that we want to
  * use AJAX to create an asynchronous HTTP request when that button is clicked.
 */
 
 function sendCredentials(){
 	//Create your XMLHttpRequest object
 	let xhr = new XMLHttpRequest()
 	//Creating a reference to a string which represents the URL for my resource
 	let url = 'http://localhost:9000/user/auth'
 	
 	//I need to open the XMLHttpRequest
 	xhr.open('POST', url)
 	
 	//I also know that I need to send data in the request body. That data is the user
 	//input that they type into the input boxes. This means that I need to be able to
 	//grab the actual input elements so that I can access their values.
 	
 	let username = document.getElementById('username').value
 	let password = document.getElementById('password').value
 	
 	//Now that we have the user information, we can actually store it as a JS object.
 	let user = {username:username, password:password}
 	console.log(user) //just debugging
 	//Now that we have our JS object, we want to turn it into JSON so that we can ship
 	//it off to the server
 	let json_string = JSON.stringify(user)
 	
 	
 	//Ship it off to the server by sending it:
 	xhr.send(json_string)
 	
 }
 
 button.addEventListener('click', sendCredentials)