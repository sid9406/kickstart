import java.util.*

fun main(args: Array<String>) {
    val t = readLine()!!.toInt()
    repeat(t) {
        val (n, a, b) = readLine()!!.split(' ').map { it.toInt() }
        val parents = readLine()!!.split(' ').map { it.toInt() }
        val graph = mutableMapOf<Int, MutableList<Int>>()
        for(i in 0 until n - 1){
            if (!graph.containsKey(parents[i])) graph[parents[i]] = mutableListOf()
            graph[parents[i]]!!.add(i + 2)
        }
        val tree = Tree(n, graph, a, b)
        println("Case #${it + 1}: ${tree.ans}")
    }
}

class Tree(val n : Int, val graph : MutableMap<Int, MutableList<Int>>, val a : Int, val b : Int){

    val visitedA = Array(n + 1) { 0 }
    val visitedB = Array(n + 1) { 0 }
    val path = Stack<Int>()
    var ans = 0.0

    init {
        dfs(1)
    }

    fun dfs(node : Int){
        path.push(node)
        if(graph.containsKey(node)) {
            graph[node]!!.forEach { dfs(it) }
        }
        visitedA[node]+= 1
        visitedB[node]+= 1
        path.pop()
        if(path.size >= a) visitedA[path[path.size - a]]+= visitedA[node]
        if(path.size >= b) visitedB[path[path.size - b]]+= visitedB[node]
        ans+= 1.0 - (1.0 - visitedA[node].toDouble()/n.toDouble())*(1.0 - visitedB[node].toDouble()/n.toDouble())
    }
}
