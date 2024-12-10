let fs = require('fs')
let inputs = fs.readFileSync('dev/stdin').toString().trim().split(' ').map(_=>Number(_));

// 3턴 동안의 경우의 수 고려해서 최솟값 유지하기 8*100000 = 80만

// 현재 각 발의 위치
let dp = [...Array(inputs.length)].map(_=>[...Array(5)].map(__=>Array(5).fill(1000000)))

// dp[i][l][r]: i번째까지 move를 마친 왼발이 l, 오른발이 r에 위치할 때 든 힘의 최솟값
dp[0][0][0] = 0

let next, temp
let i = 1
while(i<inputs.length){
    next = inputs[i-1]
    for(let l=0; l<5; l++){ // 기존 왼발 위치
        for(let r=0; r<5; r++){ // 기존 오른발 위치
            // 왼발을 next로
            if(next!=r)
                dp[i][next][r] = Math.min(dp[i-1][l][r]+getCost(l, next), dp[i][next][r])
            // 오른발을 next로
            if(next!=l)
                dp[i][l][next] = Math.min(dp[i-1][l][r]+getCost(r, next), dp[i][l][next])
        }
    }
    i++
}

let result = 1000000
dp[dp.length-1].forEach((l)=>{
    temp = Math.min(...l)
    if(result>temp) result = temp
})

// console.log(dp)
console.log(result)

function getCost(from, to){
    if(from==0) return 2
    
    let dif = Math.abs((from-to)%4)
    if(dif==0) return 1
    if(dif==3 || dif==1) return 3
    if(dif==2) return 4
}