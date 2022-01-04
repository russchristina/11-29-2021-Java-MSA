// JavaScript has several basic data types. These include:

//Function
//Undefined
//Null
//Boolean
//Object
//Number (used for ALL numeric values)
//String

// You can declare them with the following syntax. I've also left in the calls to "typeof" in order to show what the function returns for each reference.

var aNum = 89.87;
typeof(aNum);
'number'

var aBoolean = true
typeof(aBoolean)
'boolean'

//Note that you can use single or double quotes for strings
var aString = "a string"
typeof(aString)
'string'

var aNull = null
typeof(aNull)
'object'

var anUndefined;
typeof(anUndefined)
'undefined'

var anObject = {}
typeof(anObject)
'object'

function aFunc(){}
typeof(aFunc)
'function'

/*Note that you do not include types with reference variables in JS. This is a syntax error.*/
String aString = 'what are you doing?'
VM850:1 Uncaught SyntaxError: Unexpected identifier

/*JavaScript objects look like comma separated lists of key-value pairs.*/
var anObject = {prop1:'a string', prop2:45.6, prop3:false}

/*You access a value in the object by using its key like so:*/
anObject.prop1
'a string'

/*You can also add properties to an object on the fly:*/
anObject.prop4 = 89
anObject.anotherProp = 'yet another property'
anObject

/*DECLARING VARIABLES IN JAVASCRIPT

There are 3 keywords we can use to declare variables in JS:

1) var: bad practice; it allows for redeclaration of variables and hoisting. Hoisting entails moving a variable declaration to the top of its scope.

2) let: good practice; it prevents redeclaration of variables and prevents hoisting.

3) const: good practice; it prevents redeclaration of varabies, prevents hoisting, and prevents reassignment.*/

var badVariable = 'nope'
let goodVariable = 'yaaaaas'
const alsoGoodVariable = 'also yaaaaas'

/*OBJECT EQUALITY IN JAVASCRIPT

In JS, you can use either the == or === operator to compare values. The == operator only compares values and NOT data types. In order to do this, type coercion occurs in which a pseudo conversion of one of the values being compared occurs. The === operator compares values AND data types. As a result, using the === operator is considered best practice. */

let string1 = 'content'
let string2 = 'content'

//true as expected
string1 == string2
'true'


let number1 = 1
let stringOne = '1'

//true because of type coercion
number1 == stringOne
'true'

//false because it checks the data type as well
number1 === stringOne
'false'

/*HOISTING: 
Recall that hoisting moves a variable's declaration to the top of its scope, which makes the following code valid.*/

function weWantTheFunc(){
    console.log(aVariable)
    var aVariable = 7;
}

//This function is equivalent to the following:
function weWantTheFunc(){
    var avariable;
    console.log(aVariable)
    aVariable = 7;
}

// We can prevent hoisting by using the "let" keyword like so. This will throw an error if we reference a variable before it is declared:
function weWantTheFunc(){
    console.log(aVariable)
    let aVariable = 7;
}

/**Please note that there is shorter way of creating functions using ARROW FUNCTION notation. Note that this is an anonymous function that changes the binding of the "this" keyword. */

() => {console.log('i am an anonymous function')}

/** The "this" keyword in JavaScript*/

console.log(this)
Window https://acrenwelge.github.io/Portfolio-Reference-Documents/javams-guidelines.html
debugger eval code:1:9
undefined
var myJsObject = {prop1:'this is property2', prop2:function aFunc(){console.log(this)}}
undefined
myJsObject.prop2()
Object { prop1: "this is property2", prop2: aFunc() }
​
prop1: "this is property2"
​
prop2: function aFunc()
​
<prototype>: Object { … }
debugger eval code:1:77
undefined
var myJsObject = {prop1:'this is property2', prop2:() => {console.log(this)}}
undefined
myJsObject.prop2()
Window https://acrenwelge.github.io/Portfolio-Reference-Documents/javams-guidelines.html