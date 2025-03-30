function solution(points, routes) {
    var answer = 0;
    const MAX_POINT_I = routes[0].length-2
    
    routes = routes.map(v=>v.map(a=>a-1))
    
    const robots = Array(routes.length).fill(0).map(
        (v,i)=> [ 0, points[routes[i][0]][0], points[routes[i][0]][1], true]
    )
    
    let n = robots.length
    
    while(n>0){
        // check
        const positions = new Map() // r*100+c : count
        robots.forEach(([point_i, y, x, alive], robot_i)=>{
            if(!alive) return
            const key = y*100+x
            const status = positions.get(key)
            if(status==1){
                answer++
                positions.set(key, 2)
            } else if(!status){
                positions.set(key, 1)
            }
            if(point_i>MAX_POINT_I) {
                robots[robot_i][3]=false 
                n--
            }
        })
        
        // move
        robots.forEach(([point_i, y, x], robot_i)=>{
            if(point_i>MAX_POINT_I) return
            const nextPoint = points[routes[robot_i][point_i+1]]
            const [dy, dx] = nextMove([y,x], nextPoint)
            
            robots[robot_i][1] += dy
            robots[robot_i][2] += dx
            if(robots[robot_i][1]==nextPoint[0] && robots[robot_i][2]==nextPoint[1]){
                ++robots[robot_i][0]
            }
        })
    }
    
    return answer;
}

// 시뮬레이션

function nextMove(from, to){
    if(from[0]<to[0]) return [1,0]
    else if(from[0]>to[0]) return [-1,0]
    else if(from[1]<to[1]) return [0, 1]
    else if(from[1]>to[1]) return [0, -1]
    
    return [0,0]
}