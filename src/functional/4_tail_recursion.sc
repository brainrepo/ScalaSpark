import scala.annotation.tailrec
def factorial(n: Int): Int =
  if (n == 0) 1 else n * factorial(n - 1) //<-it is underlined by tailrec annotations

factorial(10)


def fact(n:Int): Int = {
  @tailrec
  def tailFactorial(acc:Int, n:Int):Int =
    if (n == 0) acc else tailFactorial(acc * n, n - 1)

  tailFactorial(1, n)
}

fact(10)


//if the last command is a function call it is good

