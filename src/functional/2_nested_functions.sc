
def abs(x:Double): Double = if (x < 0) -x else x



//i put functions inside functions
// a block contain definition and the last expression is return
//external function are visible if there are no function or element with the
//same name
def sqrt(x:Double): Double = {
  def isGoodEnough(guess:Double, x:Double): Boolean =
  // abs(guess * guess -x) < 0.001
    abs(guess * guess -x) / x < 0.001

  def improve(guess:Double, x:Double): Double = {
    (guess + x / guess) / 2
  }

  def sqrtIter(guess:Double, x:Double):Double = {
    if (isGoodEnough(guess, x)) guess
    else sqrtIter(improve(guess, x), x)
  }

  sqrtIter(1.0, x)
}

sqrt(2)




//BLOCK EXAMPLE
def blockExample(): Int = {
  val x = 0

  def f(y: Int): Int = y + 1

  val result = {
    val x = f(3)
    x * x //internal x
  } + x //external x

  result
}

blockExample()


//square root clean
def sqrt1(x:Double): Double = {
  def isGoodEnough(guess:Double): Boolean =
  // abs(guess * guess -x) < 0.001
    abs(guess * guess -x) / x < 0.001

  def improve(guess:Double): Double = {
    (guess + x / guess) / 2
  }

  def sqrtIter(guess:Double):Double = {
    if (isGoodEnough(guess)) guess
    else sqrtIter(improve(guess))
  }

  sqrtIter(1.0)
}

//Semicolon are optional you use if u put all in one line
val y = 3 + 1; y + y
