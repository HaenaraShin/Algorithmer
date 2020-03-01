package dev.haenara.algorithmer

/**
 * 빙산
 * 첫 줄에는 이차원 배열의 행의 개수와 열의 개수를 나타내는 두 정수 N과 M이 한 개의 빈칸을 사이에 두고 주어진다.
 * N과 M은 3 이상 300 이하이다. 그 다음 N개의 줄에는 각 줄마다 배열의 각 행을 나타내는 M개의 정수가 한 개의 빈 칸을
 * 사이에 두고 주어진다. 각 칸에 들어가는 값은 0 이상 10 이하이다. 배열에서 빙산이 차지하는 칸의 개수, 즉, 1 이상의
 * 정수가 들어가는 칸의 개수는 10,000 개 이하이다. 배열의 첫 번째 행과 열, 마지막 행과 열에는 항상 0으로 채워진다.
 *
 */
fun main(args : Array<String>) {
    BOJ2573().main()
}

class BOJ2573 {
    var N : Int = 0
    var M : Int = 0
    lateinit var map : Array<Array<Int>?>
    var ices = setOf<Ice>()

    fun main() {
        val firstLine = readLine()?.split(" ")
        N = firstLine!![0].toInt()
        M = firstLine!![1].toInt()
        map = arrayOfNulls<Array<Int>?>(N)
        repeat(N) {
            map[it] = readLine()!!.split(" ").map { it.toInt() }.toTypedArray()
        }
    }
    fun solution(n : Int, k : Int, list : List<Int>) : Int {
        var amount = k
        var count = 0
        var ices = arrayListOf<Ice>()
        while(true) {
            var x = 0
            var y = 0
            for (x in 0..N) {
                for (y in 0..M) {

                }
            }
//            if (map[x][y] > 0) {
//                ices.add(Ice(x,y, map[x][y]) )
//            }
        }

    }

    data class Ice(val x : Int, val y : Int, var count : Int,
              var left : Ice? = null,
              var right : Ice? = null,
              var up: Ice? = null,
              var down : Ice? = null) {
        fun melt() : Ice? {
            if (left == null) { count-- }
            if (right == null) { count-- }
            if (up == null) { count-- }
            if (down == null) { count-- }
            return if (count > 0) {
                this
            } else {
                null
            }
        }
        fun find(target : Ice, except: Set<Ice> = setOf()) : Boolean{
            return when (target) {
                left -> true
                right -> true
                up -> true
                down -> true
                else -> {
                    except.plus(this)
                    (left?.let { if (!except.contains(it)) { it.find(target, except) } else { false }} ?: false) or
                    (right?.let { if (!except.contains(it)) { it.find(target, except) } else { false }} ?: false) or
                    (up?.let { if (!except.contains(it)) { it.find(target, except) } else { false }} ?: false) or
                    (down?.let { if (!except.contains(it)) { it.find(target, except) } else { false }} ?: false)}
            }
        }
    }
}
