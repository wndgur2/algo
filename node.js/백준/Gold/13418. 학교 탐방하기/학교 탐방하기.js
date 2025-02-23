let fs = require('fs')
let inputs = fs.readFileSync('dev/stdin').toString().trim().split('\n');
let [n, e] = inputs[0].split(' ').map(v=>Number(v))
let edges = inputs.slice(1,).map(v=>v.split(' ').map(v=>Number(v)))
edges.forEach((v,i)=>edges[i][2] = 1-edges[i][2])

function getRoot(index){
  if(parents[index]==index) return index
  return (parents[index] = getRoot(parents[index]))
}

function union(a, b){
  const rootA = getRoot(a)
  const rootB = getRoot(b)
  if(rootA==rootB)
    return false
  parents[a] = rootB
  parents[rootA] = rootB
  return true
}

// cruskal 1: 최선의 경우

let cost = 0
let parents = new Array(n+1).fill(0).map((v,i)=>i)
edges.sort((a, b)=> a[2]-b[2]) // 오름차순
let connected = 1
for(let i=0; i<e+1; i++){
  const edge = edges[i]
  if(union(edge[0], edge[1])) {
    // console.log(edge)
    connected++
    cost+=edge[2]
    if(connected==n+1) break
  }
}
const minCost = Math.pow(cost, 2)

// cruskal 1: 최악의 경우

cost = 0
parents = new Array(n+1).fill(0).map((v,i)=>i)
edges.sort((a, b)=> b[2]-a[2]) // 내림차순
connected = 1
for(let i=0; i<e+1; i++){
  const edge = edges[i]
  if(union(edge[0], edge[1])) {
    connected++
    cost+=edge[2]
    if(connected==n+1) break
  }
}
const maxCost = Math.pow(cost, 2)

// console.log(minCost, maxCost)
console.log(maxCost-minCost)