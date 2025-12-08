//淺拷貝
var peter = { name: 'Peter' };//物件Peter的字串變數是'Peter'
var john = peter;//物件john=peter
john.name = 'John';//修改john
console.log(peter);/**物件john和peter都被修改掉了 */
console.log(john);

///DEEP COPY深拷貝(1)
var peter = { name: 'Peter' };//物件Peter的字串變數是'Peter'
var john = { name: peter.name }
john.name = 'John';//修改john
console.log(peter);/**物件john被建立另一個獨立物件放置 */
console.log(john);

//DEEP COPY深拷貝(超過一層會失效)
var peter = { name: 'Peter', data: { gender: 'male' } };
var mary = { name: peter.name, data: peter.data };
mary.name = 'Mary';
mary.data.gender = 'female';
console.log(peter);
console.log(mary);

//深拷貝2 Object.assign
var peter = { name: 'Peter' };
var john = Object.assign({}, peter);
john.name = 'john';
console.log(peter);
console.log(john);

//深拷貝 JSON(JSON方法會受限於JSON規定無法處理function)
var peter = { name: 'Peter', data: { gender: 'male' } };
var mary = JSON.parse(JSON.stringify(peter));
mary.name = 'Mary';
mary.data.gender = 'female';
console.log(peter);
console.log(mary);

//深拷貝 Lodash
var _ = require('lodash');

var peter = { name: 'Peter', greet: function () { return 'Hi'; } };
var mary = _.cloneDeep(peter);
mary.name = 'Mary';
console.log(peter);
console.log(mary);