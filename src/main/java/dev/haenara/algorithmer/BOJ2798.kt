package dev.haenara.algorithmer

fun main(args : Array<String>) {
    BOJ2798().main()
}
class BOJ2798 {
    fun main() {
        val firstLine = readLine()?.split(" ")
        val n = firstLine!![0].toInt()
        val m = firstLine!![1].toInt()
        println("${solution(n, m, readLine()?.split(" ")!!.map { it.toInt() })}")
    }
    fun solution(n : Int, m : Int, input : List<Int>) : Int {
        var max = input[0]
        for ((index1, element) in input.withIndex()) {
            for ((index2, another) in input.takeLast(input.size - index1 + 1).withIndex()){
                for (theOther in input.takeLast(input.size - index2 + 1)){
                    var sum = element + another + theOther
                    if (sum in (max + 1)..m) {
                        max = sum
                    }
                }
            }
        }
        return max
    }

}