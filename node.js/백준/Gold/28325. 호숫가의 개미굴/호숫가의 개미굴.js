let fs = require('fs')
let inputs = fs.readFileSync('dev/stdin').toString().split('\n')
let N = Number(inputs[0])
let rooms = inputs[1].split(' ').map(v=>Number(v))

let answer = 0

answer += rooms.reduce((pre,cur)=>pre+cur, 0)

let visits = new Array(N).fill(false)
let isEmpty = new Array(N).fill(false).map((v, i)=>rooms[i]==0?false:true)

// find first empty room
if(answer==0){
  answer = Math.ceil(N/2)
} else{
  let firstEmptyIndex;
  for(let i=0; i<N; i++){
    if(rooms[i]>0) {
      firstEmptyIndex=i
      break
    }
  }
  let length=0
  for(let i=firstEmptyIndex; i<firstEmptyIndex+N; i++){
    const index = i%N
    // console.log('current room: ', index+1)
    if(rooms[index]==0) length++
    else{
      answer += Math.ceil(length/2)
      // console.log('answer:', answer)
      length=0
    }
  }
  answer += Math.ceil(length/2)
}

console.log(answer)