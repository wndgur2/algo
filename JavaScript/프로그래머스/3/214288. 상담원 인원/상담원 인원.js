var answer = 30000000;
let typeN, N;
let requests;

function solution(k, n, reqs) {
    typeN = k
    N = n
    requests = reqs
    allocate(0, n, [])
    return answer;
}

function allocate(depth, leftN, nPerTypes){
    if(depth==typeN){ 
        if(leftN>0) return;
        // 모든 유형에 할당할 인원수를 정했다.
        // console.log(nPerTypes);
        const res = simulate(nPerTypes);
        if(res<answer) answer = res;
        return;
    }
    for(let i=1; i<=leftN; i++){ // depth에 i명 할당
        allocate(depth+1, leftN-i, [...nPerTypes, i])
    }
    return;
}

function simulate(nPerTypes){
    let waitSum = 0
    let q = []
    nPerTypes.forEach(n=>{
        const newQ = []
        for(let i=0; i<n; i++) newQ.push(0)
        q.push(newQ)
    })
    
    requests.forEach(req=>{
        const reqTime = req[0]
        const duration = req[1]
        const type = req[2]-1 // 0부터
        
        // req 처리
        if(q[type][0] > reqTime){
            waitSum += q[type][0] - reqTime
            q[type][0] += duration
        }else{
            q[type][0] = reqTime + duration
        }
        
        // q[type]를 오름차순 정렬
        q[type].sort((a,b)=>a-b)
    })
    return waitSum
}