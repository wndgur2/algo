function solution(alp, cop, problems) {
    var answer = 15000;
    
    let max_alp=alp, max_cop=cop;
    problems.forEach(p=>{
        if(p[0]>max_alp) max_alp = p[0]
        if(p[1]>max_cop) max_cop = p[1]
    })
    
    const dp = Array(max_alp+1).fill(0).map(v=>Array(max_cop+1).fill(15000))
    // dp[i][j]=k i알고력, j코딩력을 얻는 데에 드는 최소 시간 k
    dp[alp][cop] = 0
    
    let i=alp, j=cop;
    while(i<=max_alp && j<=max_cop){
        if(i<max_alp){
            // 알고리즘 공부
            dp[i+1][j] = Math.min(dp[i+1][j], dp[i][j]+1)
            updateAnswer(i+1, j, Math.min(dp[i+1][j], dp[i][j]+1))
        }
        
        if(j<max_cop){
            // 코딩 공부
            dp[i][j+1] = Math.min(dp[i][j+1], dp[i][j]+1)
            updateAnswer(i, j+1, Math.min(dp[i][j+1], dp[i][j]+1))
        }
        
        // 문제풀이
        problems.forEach(([alp_req, cop_req, alp_rwd, cop_rwd, cost])=>{
            if(i<alp_req || j<cop_req) return
            const nextI = Math.min(i+alp_rwd, max_alp)
            const nextJ = Math.min(j+cop_rwd, max_cop)
            dp[nextI][nextJ] = Math.min(dp[nextI][nextJ], dp[i][j]+cost)
            updateAnswer(nextI, nextJ, Math.min(dp[nextI][nextJ], dp[i][j]+cost))
        })
        j++
        if(j>max_cop){
            j=cop
            i++
        }
    }
    // console.log(dp)
    return answer;
    
    function updateAnswer(alPower, coPower, val){
        if(
            alPower >= max_alp
            && coPower>=max_cop
            && answer>val
        ) {
            answer = val
        }
    }
}