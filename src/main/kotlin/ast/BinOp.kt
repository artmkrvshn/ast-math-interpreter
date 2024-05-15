package ast

import Token

data class BinOp(val left: AST, val op: Token, val right: AST) : AST()