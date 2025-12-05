const array = [1, 2, 3, 4, 5, 6, 7, 8];
/**
宣告式函示
 */
// let doFilter = arr => {
//     let outputArr = [];
//     for (let i = 0; i < arr.length; i++) {
//         if (arr[i] >= 5) {
//             outputArr.push(arr[i]);
//         }
//     }
//     return outputArr;
// };
//     let answer = doFilter(array);
// console.log(answer)
/**
命令式函示
 */
let answer = array.filter(ele => ele >= 5);
console.log(answer);