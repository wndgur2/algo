const MAX_COST = 500001
var edges
var costs

function solution(n, roads, sources, destination) {
    costs = Array(n+1).fill(MAX_COST)
    costs[destination]=0
    
    edges = Array(n+1).fill(0).map(v=>[])
    roads.forEach(([a,b])=>{
        edges[a].push(b)
        edges[b].push(a)
    })
    bfs(destination)
    
    let answer = sources.map(v=>costs[v]==MAX_COST?-1:costs[v])
    return answer;
}

function bfs(index){
    const nodes = [index]
    
    while(nodes.length>0){
        let node = nodes.shift()
        const cost = costs[node]
        edges[node].forEach(next=>{
            if(costs[next]<=cost+1) return
            costs[next] = cost+1
            nodes.push(next)
        })
    }
}