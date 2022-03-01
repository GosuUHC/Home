
def num1():
    l = [1, 2, 1, 2, 3, 3, 3, 4, 5, 6]
    print(l)
    print(set(l))
    print(len(set(l)))


def num2():
    l_1 = [1, 2, 3, 4, 5, 6, 7]
    l_2 = [6, 7, 8, 9, 10, 11]
    print(l_1, l_2, sep="\n")
    print(set(l_1).intersection(set(l_2)))
    print(len(set(l_1).intersection(set(l_2))))


def num3():
    l_1 = [5, 4, 3, 2, 1]
    l_2 = [5, 6, 4, 3, 7]
    print(l_1, l_2, sep="\n")
    print(set((set(l_1).intersection(set(l_2)))))


def num4():
    s = str(input("Строка:"))
    a = set([])
    for i in range(0, len(s), 2):
        if s[i] in a:
            print(s[i], "\nYES")
        else:
            a.add(s[i])
            print(s[i], "\nNO")

    print(sorted(a))


def num5():
    s = ""
    s += input("Количество строк:")
    for i in range(int(s[0])):
        s += "\n"
        s += input()
    a = []
    a += s.split()
    print(len(set(a)))
    print(sorted(set(a)))
