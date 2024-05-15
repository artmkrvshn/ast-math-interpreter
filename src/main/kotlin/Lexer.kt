class Lexer(private var text: String) {
    private var pos: Int = 0
    private var currentChar: Char = text[pos]

    private fun advance() {
        pos++
        currentChar = if (pos > text.length - 1) {
            '\u0000' // Indicates end of input
        } else {
            text[pos]
        }
    }

    private fun skipWhitespace() {
        while (currentChar != '\u0000' && currentChar.isWhitespace()) {
            advance()
        }
    }

    private fun integer(): Int {
        var result = ""
        while (currentChar != '\u0000' && currentChar.isDigit()) {
            result += currentChar
            advance()
        }
        return result.toInt()
    }

    private fun function(): String {
        var result = ""
        while (currentChar != '\u0000' && currentChar.isLetter()) {
            result += currentChar
            advance()
        }
        return result
    }

    fun getNextToken(): Token {
        while (currentChar != '\u0000') {
            when {
                currentChar.isWhitespace() -> {
                    skipWhitespace()
                    continue
                }
                currentChar.isLetter() -> return Token(TokenType.FUNCTION, function())
                currentChar.isDigit() -> return Token(TokenType.INTEGER, integer())
                currentChar == '+' -> {
                    advance()
                    return Token(TokenType.PLUS, '+')
                }

                currentChar == '-' -> {
                    advance()
                    return Token(TokenType.MINUS, '-')
                }

                currentChar == '*' -> {
                    advance()
                    return Token(TokenType.MUL, '*')
                }

                currentChar == '/' -> {
                    advance()
                    return Token(TokenType.DIV, '/')
                }

                currentChar == '(' -> {
                    advance()
                    return Token(TokenType.LPAREN, '(')
                }

                currentChar == ')' -> {
                    advance()
                    return Token(TokenType.RPAREN, ')')
                }

                else -> error("Invalid character")
            }
        }
        return Token(TokenType.EOF, '\u0000')
    }
}