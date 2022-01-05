function addNewRecipeCard(){
    /* Let's select the recipe_container so that we can add a new div to the inside of it.*/

    let recipeContainer = document.getElementById('recipe_container')

    /* We know that we want to add a new div to the inside of the recipe container, so we have to create the new element before appending it to the DOM. Note that we also have to create any elements that should be present inside of this div we would like to append.*/

    let newRecipeDiv = document.createElement('div')
    let h3 = document.createElement('h3')
    let p = document.createElement('p')
    let img = document.createElement('img')
    let ol = document.createElement('ol')
    let li = document.createElement('li')
    let li2 = document.createElement('li')

    /* All we've done is create elements. We need to add text and attributes to them.*/

    newRecipeDiv.className = 'recipe_div'
    h3.innerText = 'Peanut Brittle'
    p.innerText = 'This a southern style peanut brittle made by a Grandma'
    img.className = 'recipe_img'
    img.src = 'nonexistent link'
    li.innerText = 'Put a lot of sugar in the pan'
    li2.innerText = 'Eat a lot sugar'

    /* Append the li to the ol because they are children of the list*/
    ol.appendChild(li)
    ol.appendChild(li2)

    /* Append all of these child elements to the created div*/

    newRecipeDiv.appendChild(h3)
    newRecipeDiv.appendChild(p)
    newRecipeDiv.appendChild(img)
    newRecipeDiv.appendChild(ol)

    /* Now we should our newly constructed recipe card to the recipe container. */

    recipeContainer.appendChild(newRecipeDiv)
}

/* Of course we have to call our own functions. */
addNewRecipeCard()

/* While we have performed DOM manipulation, we are still not doing so in response to user interactions on the client side. In many cases, we will want to respond to user interactions in order to perform DOM manipulation. So let's do it! Let's start with a basic click or hover event.*/

/* We'll make a function that changes the color of a recipe card when a user clicks on it. */

// Let's target just the cheescakeDiv for this first example:

let cheescakeDiv = document.getElementById('cheesecakeDiv')

// In order to change the color in response to a user event, I need to add an "event listener" to the element. The listener "listens" for a specific event on the element. When said event occurs, a callback function is invoked; we define what this callback function is.

//NOTE: A callback function is simply a function that is passed to another function and later invoked within the context of the outer function.

// A helper flag to keep track of whether or not the color has been changed
let colorIsNotChanged = true

cheescakeDiv.addEventListener('mouseenter', () => {
    if(colorIsNotChanged) cheescakeDiv.style.backgroundColor = 'white'

    else cheescakeDiv.style.backgroundColor = 'orange'

    colorIsNotChanged = !colorIsNotChanged

})

/* When we add event listeners to elements, there's a default order in which events are dispatched (as you can have multiple event listeners to a single element). In order to showcase this, we will make some alert boxes pop up indicating which event is being triggered. */

// Let's add an event listener to our header. It will be a simple "click" event.

// Note that the querySelector function allows you to use valid CSS selectors to grab elements.
let header = document.querySelector('header')

header.addEventListener('click', () => {
    window.alert('Congratulations, you have won a free yPhone!')
}, true)

// Let's also add an event listener to the h1 inside of our header. Note that it is not possible to click the h1 without also clicking on the header in this case.

let h1 = document.querySelector('h1')

h1.addEventListener('click', () => {
    window.alert('Congratulations, you have won a free h1Phone!')
})

/** What you'll notice about the above 2 events is that innermost event (the child element's event) is dispatched first and then the outermost event second. This is the default order in which events are dispatched. It is known as "bubbling".
 * 
 * You can reverse this order if you want to by using "capturing" instead. In order to do so, you can pass in a third (optional) boolean argument to the addEventListener function specifying whether or not you want to use capturing. Note that you should add this to the outermost element's addEventListener call.
 */
