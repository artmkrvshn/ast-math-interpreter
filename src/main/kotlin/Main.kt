fun main() {
    while (true) {
        try {
            print("> ")
            val text = readlnOrNull() ?: break
            if (text.isBlank()) continue

            val lexer = Lexer(text)
            val parser = Parser(lexer)
            val interpreter = Interpreter(parser)
            val result = interpreter.interpret()
            println("Result: $result")
        } catch (e: Exception) {
            println("Error: ${e.message}")
        }
        /*
        TODO: Add unit tests for AST, Lexer etc
        TODO: Add floating numbers support
        TODO: Add functions support
        TODO: Remake exception handling
         */
    }
}