import ast.*
import TokenType.*

class Parser(private val lexer: Lexer) {
    private var currentToken: Token = lexer.getNextToken()

    fun buildAST(): AST {
        return expr()
    }

    private fun eat(tokenType: TokenType) {
        if (currentToken.type == tokenType) {
            currentToken = lexer.getNextToken()
        } else {
            error("Invalid syntax")
        }
    }

    private fun factor(): AST {
        val token = currentToken
        return when (token.type) {
            INTEGER -> {
                eat(INTEGER)
                Num(token)
            }

            LPAREN -> {
                eat(LPAREN)
                val node = expr()
                eat(RPAREN)
                node
            }

            FUNCTION -> {
                eat(FUNCTION)
                eat(LPAREN)
                val arg = factor()
                eat(RPAREN)
                Fun(token, arg)
            }

            else -> error("Wrong token type, expected PARS or INT")
        }
    }

    private fun term(): AST {
        var node = factor()
        while (currentToken.type in setOf(MUL, DIV)) {
            val token = currentToken
            when (token.type) {
                MUL -> eat(MUL)
                DIV -> eat(DIV)
                else -> error("Wrong token type, expected MUL or DIV")
            }
            node = BinOp(node, token, factor())
        }
        return node
    }

    private fun expr(): AST {
        var node = term()
        while (currentToken.type in setOf(PLUS, MINUS)) {
            val token = currentToken
            when (token.type) {
                PLUS -> eat(PLUS)
                MINUS -> eat(MINUS)
                else -> error("Wrong token type, expected PLUS or MINUS")
            }
            node = BinOp(node, token, term())
        }
        return node
    }
}