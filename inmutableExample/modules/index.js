const { fromJS } = require('immutable');

const obj = { 1: "one" };
console.log(Object.keys(obj));
console.log(obj["1"], obj[1]); 

const map = fromJS(obj);
console.log(map.get("1"), map.get(1));

const { Map } = require('immutable');
const map1 = Map({ a: 1, b: 2, c: 3 });
const map2 = map1.set('b', 50);
console.log(map1.get('b') + " vs. " + map2.get('b'));

