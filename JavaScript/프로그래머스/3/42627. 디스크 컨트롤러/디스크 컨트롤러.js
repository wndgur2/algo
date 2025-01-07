// [[0, 3], [1, 9], [3, 5]]	8
function solution(jobs) {
  jobs.sort((a, b)=>{
    if(a[0]==b[0]) return a[1]-b[1]
    else return a[0]-b[0]
  })
  // console.log(jobs)

  let returnedN = 0
  let answer = 0
  let time = 0
  let tasks = []
  let added = Array(jobs.length).fill(false)
  // console.log(added)

  do {
    //담기
    for(let i=0; i<jobs.length; i++){
      if(jobs[i][0]>time) break
      if(added[i]) continue
      tasks.push({
        num:i,
        reqTime:jobs[i][0],
        cost:jobs[i][1]
      })
      added[i] = true
    }

    if(tasks.length>0){
      //정렬
      tasks.sort((a, b)=>{
        if(a.cost==b.cost){
          if(a.reqTime==b.reqTime)
            return b.num-a.num
          else
            return b.reqTime-a.reqTime
        } else
          return b.cost-a.cost
      })

      //반환
      let done = tasks.pop()
      time += done.cost
      returnedN ++
      answer += time-done.reqTime
      // console.log(time, done.reqTime, time-done.reqTime)
      continue
    }
    time++
  } while(!added.every((isAdded)=>isAdded) || tasks.length>0)

  return Number.parseInt(answer/returnedN)
}