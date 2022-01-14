/** Fetch is a more modern alternative to AJAX. It still allows us to make asynchronous requests, but the syntax is cleaner and more concise. The readability is arguably superior to the syntax we used with AJAX as fetch calls appear to happen synchronously. */

// We still need the location of the resource of course
let url = 'https://pokeapi.co/api/v2/pokemon/charmander'

/**When working with fetch, you do not get the response back directly. Instead you get a "promise". A promise is the representation of an eventual value. Because a promise does not represent the value itself, you cannot directly access the response using the promise; you have to use a callback function (defined using the "then" function to access the value). Defining so many callback functions has the potential to go poorly. Imagine, for instance, having to chain "then" 5 times. I'll also note, though, that the "then" and "catch" functions provide a decently clean syntax for handling the resolution or rejection of a promise.*/
fetch(url).then(response => {
    response.json().then(
        json_body => {
            console.log(json_body)
        }
    )
})

/** You can get around chaining the "then" function by using the "async" and "await" keywords. We use these keywords when we are performing asynchronous tasks but want the code to look and feel synchronous.
 * 
 * The "async" should be used with a function declaration. You can only use "await" the context of an "async" function.
*/

async function getPokeData(){
    let response_body = await fetch(url)
    let data = await response_body.json()
   
    console.log(data)
}


/**This has nothing to do with fetch or even async/await. I just want you to know that the window object has an "onload" that responds to the window being loaded/reloaded. Anything that is defined within this callback function will happen when the window is loaded. */
window.onload = function doThingsImmediately(){
    this.getPokeData()
}