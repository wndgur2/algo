let fs = require('fs')
let inputs = fs.readFileSync('dev/stdin').toString().trim().split('\n');

let [H, W, treshCount] = inputs[0].split(' ').map((ch)=>Number(ch))
let floorMap = [...Array(H)].map((ar)=>Array(W).fill(0))

for(let i=1; i<inputs.length; i++){
    const pos = inputs[i].split(' ').map((loc)=>Number(loc)-1)
    floorMap[pos[0]][pos[1]] = 1
}

let result = 0
let temp

for(let i=0; i<H; i++){
    for(let j=0; j<W; j++){
        if(floorMap[i][j] == 1){
            temp = getSize(i, j)
            result = temp>result?temp:result
        }
    }
}

console.log(result)

function getSize(y, x){
    // 내 주변 getSize를 리턴
    if(floorMap[y][x]==0) return 0
    let res = 1
    floorMap[y][x] = 0

    if(y>0) res+=getSize(y-1, x)
    if(x>0) res+=getSize(y, x-1)
    if(y<H-1) res+=getSize(y+1, x)
    if(x<W-1) res+=getSize(y, x+1)
    return res
}