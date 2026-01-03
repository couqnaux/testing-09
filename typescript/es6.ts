// 1: let, const
// const: hằng số, giá trị của nó không thay đổi
const PI = 3.14;
const MAX_USER = 100;

// 2: arrow function
// ES5 - cũ
// function addTwoNumber(a: number, b: number): number {
//     return a + b
// }

// ES6
// () => {}
const addTwoNumber = (a: number, b: number): number => {
  return a + b;
};

// 3: template string
let name1 = "John";
let age1 = 30;

let greeting = `Hello, my name is ${name1} and I am ${age1} years old.`;

console.log(greeting);

// 4: destructuring
const person = {
  name: "John",
  age: 30,
  gender: "Male",
  occupation: "Developer",
};
const { name: namePerson, age } = person;
console.log(namePerson, age);

const colors = ["red", "green", "blue"];
const [redColor, greenColor] = colors;
console.log(redColor, greenColor);

// 5: default parameter

// 6: rest parameter

// 7: spread operator

// 8: async/await: bất đồng bộ- gặp nhiều trong playwright

// 9: module: import/export
