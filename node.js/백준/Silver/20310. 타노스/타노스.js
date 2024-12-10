let fs = require('fs')
let inputs = fs.readFileSync('/dev/stdin').toString().trim().split(' ');
let input = inputs[0]

// 0의 개수와 1의 개수를 세어준다.
let zeroCount = 0
let oneCount = 0

for(let i=0; i<input.length; i++){
    if(input.charAt(i)==='0') zeroCount++
    else oneCount++
}

let removeZeroCount = zeroCount/2
let removeOneCount = oneCount/2

while(removeOneCount--) input = input.replace('1', '')
input = input.split('').reverse().join('')

while(removeZeroCount--) input = input.replace('0', '')
input = input.split('').reverse().join('')

console.log(input)