let LAND = []
const areaSizeByType = new Map()

function solution(land){
    LAND = land
    searchOilArea()
    
    var answer = 0
    for(let col=0; col<land[0].length; col++){
        // collect col's oil
        const visited_area = new Map()
        let oils = 0
        for(let row=0; row<LAND.length; row++){
            const areaType = LAND[row][col]
            if(areaType==0) continue
            if(visited_area.get(areaType)) continue
            visited_area.set(areaType, true)
            oils += areaSizeByType.get(areaType)
        }
        
        if(oils>answer) answer = oils
    }
    return answer
}

const ds = [[1,0], [-1,0], [0,1], [0,-1]]

function searchOilArea(){
    let currentType = 2
    for(let y=0; y<LAND.length; y++){
        for(let x=0; x<LAND[0].length; x++){
            if(LAND[y][x]!=1) continue
            
            // bfs
            let currentSize = 0
            const q = [[y,x]]
            LAND[y][x] = currentType
            
            while(q.length>0){
                const [curY, curX] = q.pop()
                currentSize++
                
                ds.forEach(([dy, dx])=>{
                    const ny = curY+dy
                    const nx = curX+dx
                    
                    if(ny<0 || ny>=LAND.length || nx<0 || nx>=LAND[0].length) return
                    if(LAND[ny][nx]!=1) return
                    
                    LAND[ny][nx] = currentType
                    q.push([ny, nx])
                })
            }
            
            areaSizeByType.set(currentType++, currentSize)
        }
    }
}