
from cmath import sqrt


def num1():

    def triangle(a, b, c):
        if a+b > c and a+c > b and b+c > b:
            print("Это треугольник, стороны: {0}, {1}, {2}".format(a, b, c))
        else:
            print("Это не треугольник, стороны: {0}, {1}, {2}".format(a, b, c))
    triangle(1, 1, 2)
    triangle(7, 6, 10)
    triangle(20, 13, 17)


def num2():

    def distance(x1, y1, x2, y2):
        a = sqrt(pow((x1-x2), 2) + pow((y1-y2), 2))
        print("Расстояние:", a.real)
    dots = [10, 34, 3, 10]
    print("Точки:({0}, {1});({2}, {3})".format(*list(dot for dot in dots)))
    distance(*dots)


def num3():

    def number_to_words(n):
        dict1 = {1: "Один", 2: "Два", 3: "Три",
                 4: "Четыре", 5: "Пять", 6: "Шесть",
                 7: "Семь", 8: "Восемь", 9: "Девять"}

        dict2 = {10: "Десять", 11: "Одиннадцать", 12: "Двенадцать", 13: "Тринадцать",
                 14: "Четырнадцать", 15: "Пятнадцать", 16: "Шестнадцать",
                 17: "Семнадцать", 18: "Восемнадцать", 19: "Девятнадцать"}

        dict3 = {20: "Двадцать", 30: "Тридцать",
                 40: "Сорок", 50: "Пятьдесят", 60: "Шестьдесят",
                 70: "Семьдесят", 80: "Восемьдесят", 90: "Девяносто"}
        if n <= 9:
            return dict1[n]
        elif n <= 19:
            return dict2[n]
        elif n <= 99:
            return dict3[n - n % 10] + " " + dict1[n % 10]

    print(number_to_words(5))
    print(number_to_words(10))
    print(number_to_words(76))
    print(number_to_words(99))
    print(number_to_words(15))


def num4():

    def power(a, n):
        a1 = a
        for i in range(n-1):
            a1 *= a
        print(a1)

    power(10, 1)
    power(1.5, 2)
    power(8, 4)
    power(1, 1)
    power(10, 10)


def num5():

    def palindrome(s):
        s_1 = str(s)
        for i in range(len(s)):
            if s_1[i].lower() == s_1[-i-1].lower():
                return "Палиндром"
            else:
                return "Не палиндром"
    print(palindrome("А роза упала на лапу Азора"))
    print(palindrome("12321"))
    print(palindrome("Палиндром"))


def num6():
    msg_prev = ""

    def print_without_duplicate(msg):
        nonlocal msg_prev
        if msg_prev != msg:
            print(msg)
            msg_prev = msg
    print_without_duplicate("Привет")
    print_without_duplicate("Не могу до тебя дозвониться")
    print_without_duplicate("Не могу до тебя дозвониться")
    print_without_duplicate("Не могу до тебя дозвониться")
    print_without_duplicate("Когда доедешь до дома")
    print_without_duplicate("Ага, жду")
    print_without_duplicate("Ага, жду")


def num7():
    dictionary = {}  # словарь списков друзей

    def add_friends(name_of_person, list_of_friends):
        nonlocal dictionary
        if name_of_person not in dictionary.keys():
            dictionary[name_of_person] = list_of_friends
        elif list_of_friends != dictionary[name_of_person]:
            dictionary[name_of_person] = list_of_friends

    def are_friends(name_of_person1, name_of_person2):
        nonlocal dictionary
        if name_of_person2 in dictionary[name_of_person1]:
            return True
        else:
            return False

    def print_friends(name_of_person):
        nonlocal dictionary
        print(sorted(dictionary[name_of_person]))
    add_friends("Alla", ["Marina", "Ivan"])
    print(are_friends("Alla", "Maria"))
    add_friends("Alla", ["Maria"])
    print(are_friends("Alla", "Maria"))


def num8():
    def mirror(arr):
        arr += arr[::-1]
    arr = [1, 2]
    mirror(arr)
    print(*arr)


def num9():

    def from_string_to_list(string, container):
        container += string.split()
    a = [77, "abc"]
    from_string_to_list("", a)
    print(*a)


def num10():

    def transpose(matrix):
        result = []
        for i in range(len(matrix)):
            temp = []
            for j in range(len(matrix[0])):
                temp += [matrix[j][i]]
            result += [temp]
        matrix[:] = result
    matrix = [[1, 2], [3, 4]]
    transpose(matrix)
    for line in matrix:
        print(*line)


def num11():

    def matrix(n=1, m=None, a=0):
        matr = []
        if not m:
            m = n
        for i in range(n):
            temp = []
            for j in range(m):
                temp += [a]
            matr += [temp]
        return matr

    rows = matrix(2, 4, 10)
    for row in rows:
        print(*row)


def num12():

    def partial_sums(*args):
        result = [0, ]
        for i in range(len(args)):
            result.append(result[i]+args[i])
        return result
    print(partial_sums(1, 0.5, 0.25, 0.125))


def num13():

    def power(a, n):
        if n == 0:
            return 1
        if n == 1:
            return a
        else:
            return a*power(a, n-1)
    print(power(2, 2))
    print(power(10, 3))
    print(power(5, 4))


def num14():

    def recursive_len(some_list):
        if some_list:
            return 1 + recursive_len(some_list[:-1])
        return 0

    print(recursive_len([1, 2, 3]))
    print(recursive_len([]))
    print(recursive_len(["A", "aa", 1, 5]))
