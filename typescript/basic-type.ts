import { sumThreeNumbers, multiplyTwoNumbers } from "./module.ts";

console.log(sumThreeNumbers(1, 2, 3));

console.log(multiplyTwoNumbers(4, 5));

// Khai bao bien
let username: string = "Mai Xuan Quoc";
let age: number = 30;
let price: number = 99.99;
let isActive: boolean = true;
let email: any = "maixuanquoc@example.com";
let quantity: any = 10;

let fruits: string[] = ["Apple", "Banana", "Orange"];

let numbers: number[] = [1, 2, 3, 4, 5];

function addTwoNumbers(a: number, b: number): number {
  return a + b;
}

let sum: number = addTwoNumbers(5, 10);
console.log("Sum:", sum);

//sum array
function sumArray(arr: number[]): number {
  let total: number = 0;
  for (let num of arr) {
    total += num;
  }
  return total;
}
let totalSum: number = sumArray(numbers);
console.log("Total Sum:", totalSum);
