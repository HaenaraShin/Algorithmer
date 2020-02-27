package dev.haenara.algorithmer

import kotlin.math.min

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
        var lastIndex = list.lastIndex
        while (amount > 0) {
            val unit = list[list.indexOfMaxUnderX(amount, start = 0, last = lastIndex)]
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