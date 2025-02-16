let fs = require('fs')
let inputs = fs.readFileSync('dev/stdin').toString().trim().split('\n');

const [height, width] = inputs[0].split(' ')
const board = inputs.filter((_,i)=>i!=0).map(row=>Array.from(row))

let answer=64
for(let y=0; y<=height-8; y++){
  for(let x=0; x<=width-8; x++){
    // BW로 칠할 경우 드는 횟수
    let cost_bw=getCost('B', y, x)
    
    // WB로 칠할 경우 드는 횟수
    let cost_wb=getCost('W', y, x)

    answer = Math.min(answer, Math.min(cost_bw, cost_wb))
  }
}

// 최솟값
console.log(answer)

function getCost(startCharacter, y, x){
  const chessBoard = board.slice(y, y+8).map(row=>row.slice(x, x+8))
  let odd;
  let even;
  if(startCharacter=='B'){
    even= makeRow('B')
    odd= makeRow('W')
  } else{
    even= makeRow('W')
    odd= makeRow('B')
  }
  let res = 0
  chessBoard.forEach((row,i)=>{
    if(i%2==0)
      res += row.reduce((acc, cur, j)=>{
        // console.log(acc, cur, j)
        return (cur!=even[j]? acc+1:acc)
      }, 0)
    else
      res += row.reduce((acc, cur, j)=>(cur!=odd[j]? acc+1:acc), 0)
  })
  // console.log(res)
  return res
}

function makeRow(startCharacter){
  const letters = [startCharacter, startCharacter=='W'?'B':'W']
  const row = []
  let index = 0
  while(index<width)
    row.push(letters[index++%2])
  return row
}