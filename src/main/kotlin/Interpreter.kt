import ast.AST
import ast.BinOp
import ast.Fun
import ast.Num

class Interpreter(private val parser: Parser) {

    fun interpret(): Any {
        val tree = parser.buildAST()
        return visit(tree)
    }

    private fun visitBinOp(node: BinOp): Any {
        return when (node.op.type) {
            TokenType.PLUS -> visit(node.left) as Int + visit(node.right) as Int
            TokenType.MINUS -> visit(node.left) as Int - visit(node.right) as Int
            TokenType.MUL -> visit(node.left) as Int * visit(node.right) as Int
            TokenType.DIV -> visit(node.left) as Int / visit(node.right) as Int
            else -> error("Invalid operator")
        }
    }

    private fun visitNum(node: Num): Any {
        return node.value
    }

    private fun visitFun(node: Fun): Any {
        val ast = node.value
        val arg = visit(ast) as Int

        return when (node.token.value) {
            "square" -> arg.pow(2) // ^2
            "cube" -> arg.pow(3) // ^3
            "func" -> arg.times(10) // *
            "ni" -> arg / 2
            "tak" -> arg * 2
            else -> error("Invalid function")
        }
    }

    private fun visit(node: AST): Any {
        return when (node) {
            is BinOp -> visitBinOp(node)
            is Num -> visitNum(node)
            is Fun -> visitFun(node)
            else -> error("Invalid node type")
        }
    }

    private fun Int.pow(x: Int): Int = (2..x).fold(this) { r, _ -> r * this }
}