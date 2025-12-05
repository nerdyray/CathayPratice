/**一般JavaScript邏輯 */
function add(x, y) {
    return x + y;
}
console.log(add(3, 5));
/**
 * 柯理化處理
 * @param x 
 * @returns 
 */
function addCurry(x) {
    return function (y) {
        return x + y;
    };
}
console.log(addCurry(3)(5));

let multiply = (x, y) => x * y;
let multiplyThree = y => multiply(3, y);
let multiplyFive = y => multiply(5, y);

console.log(multiply(3,5));
console.log(multiply(5));
console.log(multiplyFive(3));