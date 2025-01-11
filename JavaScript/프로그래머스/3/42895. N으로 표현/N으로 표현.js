function solution(N, number) {
  // N으로 number를 만들 수 있는 최소값을 구하는 문제

  // N을 1번 사용해서 만들 수 있는 수는 N
  // N을 2번 사용해서 만들 수 있는 수는 N+N, N-N, N*N, N/N, NN
  
  let answer = -1;
  let dp = Array.from({length: 9}, () => new Set());
  for (let i = 1; i < 9; i++) {
    dp[i].add("1".repeat(i) * N);
    for (let j = 1; j < i; j++) {
      for (const arg1 of dp[j]) {
        for (const arg2 of dp[i - j]) {
          // i-j와 j를 더하면 i가 되므로 N을 i번 사용한다.
          // 두 수를 더하고 빼고 곱하고 나누는 경우를 j가 1일때부터 i-1일 때까지 모두 구하면 i번 사용한 경우를 모두 구할 수 있다.
          // ex, i가 5일 때
          // j가 1, i-j가 4일 때
          // j가 2, i-j가 3일 때
          // j가 3, i-j가 2일 때
          // j가 4, i-j가 1일 때
          dp[i].add(arg1 + arg2);
          dp[i].add(arg1 - arg2);
          dp[i].add(arg1 * arg2);
          dp[i].add(arg1 / arg2);
        }
      }
    }
    if (dp[i].has(number)) {
      answer = i;
      break;
    }
  }

  return answer;
}