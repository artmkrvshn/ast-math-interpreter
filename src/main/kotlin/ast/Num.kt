package ast

import Token

data class Num(val token: Token) : AST() {
    val value: Any = token.value
}