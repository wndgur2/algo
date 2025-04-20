var answer = 121
var visited

function solution(info, n, m) {
    visited = Array(info.length).fill(0).map(
        v=>Array(120).fill(0).map(
            v=>Array(120).fill(false)))
    dfs(info, n, m, 0, 0, 0)
    return answer==121? -1 : answer;
}

function dfs(info, n, m, cur_n, cur_m, index){
    if(cur_n>=answer) return
    if(cur_n>=n) return
    if(cur_m>=m) return
    if(index==info.length){
        answer = cur_n
        return
    }
    if(visited[index][cur_n][cur_m]) return
    visited[index][cur_n][cur_m] = true
    dfs(info,n,m, cur_n+info[index][0], cur_m, index+1)
    dfs(info,n,m, cur_n, cur_m+info[index][1], index+1)
    
}