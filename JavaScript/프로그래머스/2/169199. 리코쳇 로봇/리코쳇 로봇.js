const visit = Array(100).fill(0).map(v=>Array(100).fill(false))
const DIRS = [[-1,0], [1,0], [0,-1], [0,1]]
let board_g

function solution(board) {
    board_g = board.map(str=>str.split(''))
    var answer = -1;
    let start, end;
    board_g.forEach((arr, i)=>{
        arr.forEach((v, j)=>{
            if(v=='G') end = [i, j]
            else if(v=='R') start = [i, j]
        })
    })
    
    // bfs
    let nodes = [[...start, 0]]
    while(nodes.length>0){
        let cur = nodes.shift()
        const depth = cur[2]
        
        if(cur[0]==end[0] && cur[1]==end[1]){
            answer = depth
            break
        }
        
        DIRS.forEach(d=>{
            let next = getNext(cur, d)
            if(visit[next[0]][next[1]]) return
            visit[next[0]][next[1]] = true
            nodes.push([next[0], next[1], depth+1])
        })
    }
    
    return answer;
}

function getNext(from, d){
    let curY = from[0]
    let curX = from[1]
    let nextY = curY+d[0]
    let nextX = curX+d[1]
    while(true){
        if( nextY<0 || nextY>=board_g.length
           || nextX<0 || nextX>=board_g[0].length
           || board_g[nextY][nextX]=='D'
          )
            break
        curY = nextY
        curX = nextX
        nextY += d[0]
        nextX += d[1]
    }
    return [curY, curX]
}