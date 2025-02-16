let fs = require('fs')
let inputs = fs.readFileSync('dev/stdin').toString().trim().split('\n');
const [H, W] = inputs[0].split(' ').map(v=>Number(v))
const board = inputs.filter((v,i)=>i>0).map(str=>Array.from(str))
const ds = [[-1, 1], [0, 1], [1, 1]]

// dfs backtracking

let answer = 0
board.forEach((v,i)=>dfs(i, 0))
console.log(answer)

function dfs(y, x){
  board[y][x] = 'x'
  if(x==W-1) {
    answer++;
    return true;
  }
  for(let di=0; di<3; di++){
    const ny = y + ds[di][0]
    const nx = x + ds[di][1]
    if(ny<0 || ny>=H || board[ny][nx]=='x') continue
    if(dfs(ny, nx)) return true
  }
  return false
}