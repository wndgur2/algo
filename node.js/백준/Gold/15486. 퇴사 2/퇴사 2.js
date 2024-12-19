let fs = require('fs')
let inputs = fs.readFileSync('dev/stdin').toString().trim().split('\n');

let N = Number(inputs[0])
let times = []
let prices = []

for(let i=1; i<=N; i++){
  let temp = inputs[i].split(' ').map(_=>Number(_))
  times.push(temp[0])
  prices.push(temp[1])
}

// console.log(times, prices)

let dp = Array(N+1).fill(0)

for(let i=0; i<N; i++){
  let finishTime = i+times[i]
  if(dp[i]<dp[i-1]) dp[i] = dp[i-1]
  if(finishTime > N) continue
  // console.log(i)
  // console.log(times[i], prices[i], finishTime, dp[finishTime])
  // console.log(dp[finishTime], dp[i] + prices[i])
  if(dp[finishTime] < dp[i] + prices[i]) dp[finishTime] = dp[i] + prices[i]
}

// console.log(dp)
console.log(Math.max(...dp))