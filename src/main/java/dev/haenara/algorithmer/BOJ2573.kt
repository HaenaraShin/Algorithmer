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
    lateinit var map : Array<Array<Int>>
    val ices = arrayListOf<Ice>()

    fun main() {
        val firstLine = readLine()?.split(" ")
        N = firstLine!![0].toInt()
        M = firstLine!![1].toInt()
        val temp = arrayOfNulls<Array<Int>?>(N)
        repeat(N) {
            temp[it] = readLine()!!.split(" ").map { it.toInt() }.toTypedArray()
        }

        map = temp as Array<Array<Int>>

        for (y in 0 until N) {
            for (x in 0 until M) {
                if (map[y][x] > 0) {
                    ices.add(Ice(x, y, map[y][x]))
                }
            }
        }

        printMap(map)
        var count = 0
        while (true) {
            count++
            ices.melt(map)
            printMap(map)
            var sum = 0
            if (ices.size == 0) {
                println("0")
                return
            } else {
                sum = paintAll(ices[0].x, ices[0].y, map.clone())
            }
            if (sum != ices.size) {
                println(count)
                return
            }
        }

    }

    class Ice (val x : Int, val y : Int, var count : Int) {
        fun melt(map : Array<Array<Int>>) : Boolean {
            if (map[y-1][x] == 0) {
                count--
            }
            if (map[y][x-1] == 0) {
                count--
            }
            if (map[y+1][x] == 0) {
                count--
            }
            if (map[y][x+1] == 0) {
                count--
            }
            if (count < 0) { count = 0 }
            return (count == 0)
        }
    }

    fun ArrayList<BOJ2573.Ice>.melt(map : Array<Array<Int>>) {
        val toRemove = arrayListOf<Ice>()
        forEach {
            if (it.melt(map)) {
                toRemove.add(it)
            }
        }
        forEach {
            map[it.y][it.x] = it.count
        }
        toRemove.forEach {
            remove(it)
        }
    }

    fun paintAll(x : Int, y : Int, map : Array<Array<Int>>, sum : Int = 0) : Int {
        var toReturn = sum + 1
        map[y][x] = -1
        if (map[y-1][x] != 0 && map[y-1][x] != -1) {
            toReturn += paintAll(x, y-1, map)
        }
        if (map[y][x-1] != 0 && map[y][x-1] != -1) {
            toReturn += paintAll(x-1, y, map)
        }
        if (map[y+1][x] != 0 && map[y+1][x] != -1) {
            toReturn += paintAll(x, y + 1, map)
        }
        if (map[y][x+1] != 0 && map[y][x+1] != -1) {
            toReturn += paintAll(x+1, y, map)
        }
        return toReturn
    }
}


fun printMap(map : Array<Array<Int>>) {
//    map.forEach { row->
//        row.forEach {
//            if (it > 0) {
//                print("${it} ")
//            } else {
//                print("_ ")
//            }
//        }
//        println()
//    }
}
