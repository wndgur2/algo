let fs = require('fs')
let N = Number.parseInt(fs.readFileSync('dev/stdin').toString())

const curses = []
for(let i=666; i<=66600000; i++){
  if(i.toString().includes('666')){
    curses.push(i)
    if(curses.length==N) break
  }
}

console.log(curses[N-1])