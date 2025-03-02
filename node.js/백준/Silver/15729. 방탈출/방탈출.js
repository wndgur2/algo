let fs = require('fs')
let inputs = fs.readFileSync('dev/stdin').toString().trim().split('\n');
let N = parseInt(inputs[0])
let target_lights = inputs[1].split(' ').map(v=>parseInt(v)?true:false)
let lights = Array(N).fill(false)

function toggle(index){
  for(let i=index; i<index+3; i++){
    if(i>=N) break
    lights[i]=!lights[i]
  }
}

let answer = 0
for(let i=0; i<N; i++){
  if(lights[i]!=target_lights[i]){
    toggle(i)
    answer ++
  }
}

console.log(answer)