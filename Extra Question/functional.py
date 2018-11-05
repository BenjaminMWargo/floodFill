#Given cons() which makes a pair, write car() which returns the first element and cdr() that 
#returns the last element


def cons(a, b):
    def pair(f):
        return f(a, b)
    return pair
#Answer
def car(pair):
    x= pair(lambda a,b:(a,b))
    return x[0]
def cdr(pair):
    return pair(lambda a,b:(a,b))[1]
print ( car(cons(3, 4)))
print ( cdr(cons(3, 4)))