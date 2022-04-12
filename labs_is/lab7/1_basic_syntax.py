
def num1():
    class BigBell:
        def __init__(self):
            self.ding = True

        def sound(self):
            if self.ding:
                self.ding = False
                print("ding")
            else:
                self.ding = True
                print("dong")
    bell = BigBell()
    bell.sound()
    bell.sound()
    bell.sound()


def num2():
    class Balance:

        def __init__(self) -> None:
            self.weight_left = 0
            self.weight_right = 0

        def add_left(self, weight):
            self.weight_left += weight

        def add_right(self, weight):
            self.weight_right += weight

        def result(self):
            if self.weight_left > self.weight_right:
                return "L"
            elif self.weight_left < self.weight_right:
                return "R"
            else:
                return "="
    balance = Balance()
    balance.add_right(10)
    balance.add_left(9)
    balance.add_left(2)
    print(balance.result())

    balance2 = Balance()
    balance2.add_right(10)
    balance2.add_left(5)
    balance2.add_left(5)
    print(balance2.result())
    balance2.add_left(1)
    print(balance2.result())


def num3():
    class Selector:

        def __init__(self, values_list):
            self.values_list = values_list

        def get_odds(self):
            return filter(lambda x: x % 2 != 0, self.values_list)

        def get_evens(self):
            return filter(lambda x: x % 2 == 0, self.values_list)

    values = [11, 12, 13, 14, 15, 16, 22, 44, 66]
    selector = Selector(values)
    odds = selector.get_odds()
    evens = selector.get_evens()
    print(" ".join(map(str, odds)))
    print(" ".join(map(str, evens)))

    values2 = [6, 6, 0, 4, 8, 7, 6, 4, 7, 5]
    selector2 = Selector(values2)
    odds2 = selector2.get_odds()
    evens2 = selector2.get_evens()
    print(" ".join(map(str, odds2)))
    print(" ".join(map(str, evens2)))

    values3 = []
    selector3 = Selector(values3)
    odds3 = selector3.get_odds()
    evens3 = selector3.get_evens()
    print(" ".join(map(str, odds3)))
    print(" ".join(map(str, evens3)))
