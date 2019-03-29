def loop: Boolean = loop

def x = loop //loop is passed by name

//val y = loop //the loop is evaluate and there is unterminate executions


def and(x:Boolean, y:Boolean) = {
  if (x) y else false
}

and(true, true)
and(true, false)
and(false, false)
and(false, true)


//non terminating argumentr

//and(false, loop) infinite iteration


//now we force the second parameter to be passed by name
def and1(x:Boolean, y: => Boolean) = {
  if (x) y else false
}

and1(false, loop) //now the second parameter is lazy evaluated



