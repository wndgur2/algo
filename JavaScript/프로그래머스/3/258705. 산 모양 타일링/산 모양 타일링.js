function solution(n, tops) {
    var answer = 0;
    const length = 2*n+1
    const dp = [1, 1, 2]
    for(let i=3; i<=length; i++){
        dp.push(0)
    }
    
    for(let i=2; i<=length; i++){
        let alpha = 0
        if(i%2==0 && tops[i/2 - 1]) alpha = dp[i-1]
        
        // normal case
        dp[i] = (dp[i-1] + dp[i-2] + alpha) % 10007
    }
    
    return dp[length]
}

function calc_cases(length){
}