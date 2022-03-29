from cmath import sqrt

from numpy import real

import sys


def num1():
    values_list = [1, 2, 3, 4, 5, 6, 7, 8]
    less_than_five = list(filter(lambda value: value < 5, values_list))
    print(less_than_five)

    less_than_five_2 = [x for x in values_list if x < 5]
    print(less_than_five_2)


def num2():
    values_list = [1, 2, 3, 4, 5, 6, 7, 8]
    div_by_2 = list(map(lambda value: value / 2, values_list))
    print(div_by_2)

    div_by_2_2 = [x / 2 for x in values_list]
    print(div_by_2_2)


def num3():
    values_list = [x for x in range(10, 100)]

    div_by_9 = list(filter(lambda value: value % 9 == 0, values_list))
    squared = list(map(lambda value: value**2, div_by_9))
    summed = sum(squared)
    print(summed)


"""
Разница в том, что эти функции возвращают.

filter - отбирает те элементы, для которых функция вернула true.

А map - возвращает массив из элементов исходного массива
после применения к ним функции.
"""


def num4():
    values_list = [x for x in range(30)]
    greater_than_17 = list(filter(lambda x: x > 17, values_list))
    div_by_2 = list(map(lambda x: x / 2, greater_than_17))
    print(div_by_2)

    greater_than_17_2 = [x / 2 for x in values_list if x > 17]
    print(greater_than_17_2)


def num5():
    def factorials(n):
        num = 1
        for i in range(1, n + 1):
            num *= i
            yield num

    result = list(factorials(7))
    print(*result)


def num6():
    def square_fibonacci(n):
        fib1 = 1
        fib2 = 1
        yield fib1
        for i in range(n - 1):
            fib1, fib2 = fib2, fib1 + fib2
            yield fib1**2

    result = list(square_fibonacci(7))
    print(*result)


def num7():  # ascii
    def LetterGen():
        for letter in range(ord("а"), ord("а") + 32):
            yield chr(letter)
    alphabet = list(LetterGen())
    print(*alphabet)


def num8():
    alphabet = (chr(letter) for letter in range(ord("а"), ord("а") + 32))
    print(*alphabet)


def num9():

    def arithmetic_operation(operation):
        if operation == "+":
            return lambda x, y: x + y
        if operation == "-":
            return lambda x, y: x - y
        if operation == "*":
            return lambda x, y: x * y
        if operation == "/":
            return lambda x, y: x / y
    operation = arithmetic_operation("+")
    print(operation(1, 4))
    operation = arithmetic_operation("/")
    print(operation(5, 2))


def num10():

    def same_by(characteristic, objects):
        if len(set(map(characteristic, objects))) == 1:
            return True
        else:
            return False
    values = [1, 2, 3, 4]
    if same_by(lambda x: x % 2, values):
        print("same")
    else:
        print("different")


def num11():

    def print_operation_table(operation, num_rows=9, num_columns=9):
        for row in range(1, num_rows + 1):
            print("  ")
            for column in range(1,  num_columns + 1):
                print(operation(column, row), end="\t")
    print_operation_table(lambda x, y: x * y)
    print("\n")
    print_operation_table(lambda x, y: x * y, 5)


def num12():
    text = input("Строка:")
    print(*sorted(text.split(), key=str.lower))


def num13():
    values = [3, 6, -8, -78, 1, 23, -45, 9]
    print(sorted(values, key=lambda x: -abs(x)))


def num14():
    coords = [(10, 10), (3, 4), (-3, -4), (-3, 4)]
    distances = sorted(coords, key=lambda x: ((real(
        sqrt(x[0]**2 + x[1]**2))), x[0], x[1]))
    print(distances)


def num15():
    matrix = [0, 1, 2, 3,
              1, 0, 2, 3]
    matrix2 = [1, 2, 3, 4,
               5, 6, 7, 8]
    print(not all(matrix))
    print(not all(matrix2))


def num16():
    text = {}
    text_buff = ""
    for line in sys.stdin:
        if "" == line.strip():
            break
        text_buff += line.strip() + " "

    for j, word in enumerate(text_buff.split()):
        if word.endswith((".", ",", "!")):
            word = word[:-1]
        if (word.istitle() or word.isupper()) and word not in text.keys():
            text[word] = j

    for word in sorted(text.keys()):
        print(text[word], "-", word)


def num17():
    matrix = []
    for line in sys.stdin:
        if "" == line.strip():
            break
        matrix.append([int(elem) for elem in line.strip().split()])

    print(matrix)

    test_sum = sum(elem_in_first_row for elem_in_first_row in matrix[0])
    print(f"Матрица будет полумагическим квадратом при суммах = {test_sum}")

    is_row_sum_equal = all((sum(elem) == test_sum for elem in matrix))
    is_column_sum_equal = all(
        (sum(elem) == test_sum for elem in list(zip(*matrix))))

    if is_row_sum_equal and is_column_sum_equal:
        print("YES")
    else:
        print("NO")


def num18():
    def check_password(func):
        password = 1234

        def wrapped(n):
            nonlocal password_is_entered
            if not password_is_entered:
                password_in = int(input("Password:"))
                password_is_entered = True
                if password_in == password:
                    return func(n)
                else:
                    print("В доступе отказано")
                    return
            else:
                return func(n)
        return wrapped

    password_is_entered = False

    @check_password
    def fibo_numbers(n):
        if n == 1 or n == 2:
            return 1
        else:
            return fibo_numbers(n - 1) + fibo_numbers(n - 2)

    print(fibo_numbers(int(input("Число Фибоначчи №:"))))


def num19():

    def check_password(password):

        def wrapper(func):

            def make_wrap():
                password_in = input("Password:")
                if password_in != password:
                    print("В доступе отказано")
                    return
                return func()
            return make_wrap
        return wrapper

    @check_password("12345")
    def make_burger():
        print("Бургер сделан")

    make_burger()


def num20():

    def cached(func):
        def wrapper(n):
            if not func_results.get(n):
                func_results[n] = func(n)
                return func(n)
            else:
                return func_results[n]
        return wrapper

    func_results = {}

    @cached
    def fibo_numbers(n):
        if n == 1 or n == 2:
            return 1
        else:
            return fibo_numbers(n - 1) + fibo_numbers(n - 2)

    print(fibo_numbers(int(input("Число Фибоначчи №:"))))
    print(sorted(func_results.values()))
