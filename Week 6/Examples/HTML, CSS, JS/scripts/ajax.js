/**AJAX stands for Asynchronous JavaScript and XML. We use AJAX in order to make requests for web resources. These requests can be synchronous (blocking) or asynchronous (nonblocking). 
 * 
 * When we perform a task asynchronously, our script can continue execution and then later return to see the results of the request.
 * 
 * In order to use AJAX, we can use a type called "XMLHttpRequest" that is built into JavaScript.
*/

//We can easily can create an XMLHttpRequest object like so. Note that an XMLHttpRequest has 5 "ready states" (0-4):

//Ready State 0: An XMLHttpRequest has been created
//Ready State 1: We have opened our XMLHttpRequest
//Ready State 2: We have sent our XMLHttpRequest
//Ready State 3: Our request has made it to its destination (another node on the network, etc.)
//Read State 4: A response has been populated

let xhr = new XMLHttpRequest() //ready state 0
let url = 'https://pokeapi.co/api/v2/pokemon/charmander'

/**XMLHttpRequest objects have event listeners that listen for ready state changes. We can take advantage of this by creating a function that checks ready state ourselves and act accordingly. */
xhr.onreadystatechange = function checkForReceivedData(){
    if(xhr.readyState === 4 && xhr.status === 200){
        //Though we have the actual data (which is just JSON that we fetched using the URL), this data is just a large JSON string that we can't easily work with here. As such...
        console.log(xhr.response)
        //...we will parse the JSON string as a JavaScript object using a built-in function that allows us to easily do so.
        let myData = JSON.parse(xhr.response)
        console.log(myData)

        // Now that we have a JS object, we can access its properties.
        console.log(myData.abilities)
        
        //We can just iterate over every ability and print it.
        for(let ability of myData.abilities){
            console.log(ability)
        }
    }
}

xhr.open('GET', url) //ready state 1
xhr.send() //ready state 2