package ast

import Token

data class Fun(val token: Token, val value: AST) : AST()