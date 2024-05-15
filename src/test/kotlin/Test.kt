import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class Test {

    @ParameterizedTest
    @CsvSource("1+6,7", "10-6,4", "100-10,90", "24124-1,24123")
    fun simplePlusMinusTest(input: String, expected: Int) {
        val lexer = Lexer(input)
        val parser = Parser(lexer)
        val interpreter = Interpreter(parser)
        val result = interpreter.interpret()
        assertEquals(expected, result)
    }

    @ParameterizedTest
    @CsvSource("1*1,1", "1*2,2", "10/2,5", "100*100,10000")
    fun simpleMulDivTest(input: String, expected: Int) {
        val lexer = Lexer(input)
        val parser = Parser(lexer)
        val interpreter = Interpreter(parser)
        val result = interpreter.interpret()
        assertEquals(expected, result)
    }

    @ParameterizedTest
    @CsvSource("square(2),4", "cube(10),1000", "cube((100-91)),729", "square(square(2)),16", "func(10),100")
    fun withFunctions(input: String, expected: Int) {
        val lexer = Lexer(input)
        val parser = Parser(lexer)
        val interpreter = Interpreter(parser)
        val result = interpreter.interpret()
        assertEquals(expected, result)
    }


//    @ParameterizedTest
//    @CsvSource("square(-1),1")
//    fun withFunctionsNegative(input: String, expected: Int) {
//        val lexer = Lexer(input)
//        val parser = Parser(lexer)
//        val interpreter = Interpreter(parser)
//        val result = interpreter.interpret()
//        assertEquals(expected, result)
//    }

//    @ParameterizedTest
//    @CsvSource("-12-34,-46", "-12+34,22", "12-34,-22", "12+34,46", "-12+6,-6", "-100*2,-200", "-2/2,-1")
//    fun negativePlusMinusTest(input: String, expected: Int) {
//        val lexer = Lexer(input)
//        val parser = Parser(lexer)
//        val interpreter = Interpreter(parser)
//        val result = interpreter.interpret()
//        assertEquals(expected, result)
//    }

//    @ParameterizedTest
//    @ValueSource(strings = ["-12*-34\$", "-12*34\$", "-12/-34\$", "-12/34\$", "12/34\$", "12/-34\$"])
//    fun negativeMulDivTest(string: String) {
//        Assertions.assertDoesNotThrow { parser.parse(string) }
//    }

//    @ParameterizedTest
//    @ValueSource(strings = ["(a+b)\$", "(a/35)\$", "(a/10)+(a/53)\$", "(a/10)*(a+3)\$", "(a+b)/3\$", "2/(a+b)\$"])
//    fun expressions(string: String) {
//        Assertions.assertDoesNotThrow { parser.parse(string) }
//    }
}