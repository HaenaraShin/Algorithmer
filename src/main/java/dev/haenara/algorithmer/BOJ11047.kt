package dev.haenara.algorithmer

import kotlin.math.min

/**
 * 동전0
 * 준규가 가지고 있는 동전은 총 N종류이고, 각각의 동전을 매우 많이 가지고 있다.
 * 동전을 적절히 사용해서 그 가치의 합을 K로 만들려고 한다. 이때 필요한 동전 개수의 최솟값을 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 N과 K가 주어진다. (1 ≤ N ≤ 10, 1 ≤ K ≤ 100,000,000)
 * 둘째 줄부터 N개의 줄에 동전의 가치 Ai가 오름차순으로 주어진다. (1 ≤ Ai ≤ 1,000,000, A1 = 1, i ≥ 2인 경우에 Ai는 Ai-1의 배수)
 *
 * 출력
 * 첫째 줄에 K원을 만드는데 필요한 동전 개수의 최솟값을 출력한다.
 *
 * 예제 입력 1
 * 10 4200
 * 1
 * 5
 * 10
 * 50
 * 100
 * 500
 * 1000
 * 5000
 * 10000
 * 50000
 *
 * 예제 출력 1
 * 6
 *
 * 풀이
 * 바이너리 서치로 기준금액보다 작은 최대의 동전을 찾아서 나눈 갯수만큼 카운트한뒤
 * 그 금액만금 빼고 나머지 금액을 가지고 재귀적으로 동일한 계산을 시행한다.
 *
 * 채점
 * https://www.acmicpc.net/source/18006638
 */
fun main(args : Array<String>) {
    BOJ11047().main()
}

class BOJ11047 {
    fun main() {
        val firstLine = readLine()?.split(" ")
        val n = firstLine!![0].toInt()
        val k = firstLine!![1].toInt()
        val coins = arrayListOf<Int>()
        repeat(n) {
            coins.add(readLine()?.trim()?.toInt()!!)
        }

        println("${solution(n, k, coins.toList())}")
    }
    fun solution(n : Int, k : Int, list : List<Int>) : Int {
        var amount = k
        var count = 0
        while (amount > 0) {
            val unit = list[list.indexOfMaxUnderX(amount)]
            count += amount / unit
            amount %= unit
        }
        return count
    }
}

fun List<Int>.indexOfMaxUnderX(x : Int, start : Int = 0, last : Int = lastIndex) : Int {
    // X이하의 가장 큰 수의 인덱스를 바이너리 서치로 찾는다.
    var left = start
    var right = last
    while(left <= right) {
        var pos = (right - left)/2 + left
        if (get(pos) > x) {
            right = pos - 1
        } else if (get(pos) < x){
            left = pos + 1
        } else {
            return pos
        }
    }
    return right
}