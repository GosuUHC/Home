
def num4():
    class Point:

        def __init__(self, x, y):
            self.x = x
            self.y = y

        def __eq__(self, __o: object):
            if self.x == __o.x and self.y == __o.y:
                return True
            else:
                return False

    p1 = Point(0, 10)
    p2 = Point(0, 0)

    if p1 == p2:
        print("Equal True")
    else:
        print("Equal False")

    if p1 != p2:
        print("Not equal True")
    else:
        print("Not equal False")


def num5():
    class ReversedList:

        def __init__(self, lst):
            self.rev_list = lst[::-1]

        def __len__(self):
            return len(self.rev_list)

        def __getitem__(self, key):
            return self.rev_list[key]

    rl = ReversedList([10, 20, 30])
    for i in range(len(rl)):
        print(rl[i])

    rl2 = ReversedList([])
    print(len(rl2))

    rl3 = ReversedList([10])
    print(len(rl3))
    print(rl3[0])


def num6():
    class SparseArray:

        def __init__(self) -> None:
            self.array = {}

        def __getitem__(self, key):
            return self.array.get(key, 0)

        def __setitem__(self, key, value):
            self.array[key] = value

    arr = SparseArray()
    arr[1] = 10
    arr[8] = 20
    for i in range(10):
        print("arr[{}] = {}".format(i, arr[i]))

    print()

    arr2 = SparseArray()
    arr2[10] = 123
    for i in range(8, 13):
        print("arr[{}] = {}".format(i, arr2[i]))

    print()

    arr3 = SparseArray()
    index = 10000000
    arr3[index] = 123
    print(f"arr[{index - 1}] = {arr3[index - 1]}")
    print(f"arr[{index}] = {arr3[index]}")
    print(f"arr[{index + 1}] = {arr3[index + 1]}")


def num7():

    class Polynomial:

        def __init__(self, coefs):
            self.coefs = coefs

        def __call__(self, x):
            return self.coefs[0] + sum([res * x**(i+1) for i, res in enumerate(self.coefs[1:])])

        def __add__(self, other):
            self.coefs.extend([0, ] * (len(other.coefs) - len(self.coefs)))
            other.coefs.extend([0, ] * (len(self.coefs) - len(other.coefs)))
            coefs = list(map(sum, zip(self.coefs, other.coefs)))
            return Polynomial(coefs)

    poly = Polynomial([10, -1])
    for i in range(3):
        print(poly(i))

    print()

    poly1 = Polynomial([0, 0, 1])
    for i in range(-2, 3):
        print(poly1(i))

    print()

    poly2 = Polynomial([0, 0, 2])
    for i in range(-2, 3):
        print(poly2(i))

    print()

    poly3 = poly1 + poly2
    for i in range(-2, 3):
        print(poly3(i))

    print()
    poly1 = Polynomial([0, 1])
    poly2 = Polynomial([10])
    poly3 = poly1 + poly2
    poly4 = poly2 + poly1

    for i in range(-2, 3):
        print(poly3(i), poly4(i))


def num8():
    class Queue:

        def __init__(self, *args):
            self.elements = []
            for i in args:
                self.elements.append(i)

        def append(self, *args):
            for i in args:
                self.elements.append(i)

        def copy(self):
            return Queue(*self.elements)

        def pop(self):
            return self.elements.pop(0)

        def extend(self, other):
            self.append(*other.elements)

        def next(self):
            return Queue(*self.elements[1:])

        def __add__(self, other):
            self.extend(other)
            return Queue(*self.elements)

        def __iadd__(self, other):
            self.extend(other)
            return Queue(*self.elements)

        def __eq__(self, other):
            if self.elements == other.elements:
                return True
            else:
                return False

        def __rshift__(self, n):
            if n > len(self.elements):
                return Queue()
            return Queue(*self.elements[n:])

        def __next__(self):
            return Queue(*self.elements[1:])

        def __str__(self):
            str_elems = "["
            for i in self.elements[:-1]:
                str_elems += str(i) + " -> "
            str_elems += str(self.elements[-1]) + "]"
            return str_elems

    q1 = Queue(1, 2, 3)
    print(q1)
    q1.append(4, 5)
    print(q1)
    qx = q1.copy()
    print(qx.pop())
    print(qx)
    q2 = q1.copy()
    print(q2)
    print(q1 == q2, id(q1) == id(q2))
    q3 = q2.next()
    print(q1, q2, q3, sep="\n")
    print(q1 + q3)
    q3.extend(Queue(1, 2))
    print(q3)
    q4 = Queue(1, 2)
    q4 += q3 >> 4
    print(q4)
    q5 = next(q4)
    print(q4)
    print(q5)
