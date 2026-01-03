function sumThreeNumbers(a: number, b: number, c: number): number {
  return a + b + c;
}

function multiplyTwoNumbers(a: number, b: number): number {
  return a * b;
}

//tinh tong cac so chan trong mang
function sumEvenNumbers(numbers: number[]): number {
  let sum: number = 0;
  for (let num of numbers) {
    if (num % 2 === 0) {
      sum += num;
    }
  }
  return sum;
}

export { sumThreeNumbers, multiplyTwoNumbers };
