let key = [

];
let value = [

];
export function HashMap(KEY, VALUE) {
    if (KEY === '' || key.includes(KEY)) {
        alert('KEY值不能為空或已有重複KEY值');
        return;
    }
    key.push(KEY);
    value.push(VALUE);
}
export { key, value }
