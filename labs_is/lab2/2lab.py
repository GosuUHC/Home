import numpy as np


def num1():
    string = str(input("Строка:"))
    lenght = len(string)
    print("1", string[2])
    print("2", string[-2])
    print("3", string[:5])
    print("4", string[:lenght-2])
    print("5", string[:lenght:2])
    print("6", string[1:lenght:2])
    print("7", string[::-1])
    print("8", string[::-2])
    print("9", lenght)


def num2():
    string = str(input("Строка:"))

    string1 = string[(len(string)+1)//2:]
    string2 = string[:(len(string)+1)//2]

    print(string1+string2)


def num3():
    string = str(input("Строка:"))

    s = string.index("h")
    st = string[::-1]
    s2 = st.index("h")
    s3 = len(string)-s2-1
    answer = ""
    for i in range(s+1, s3):
        answer += string[i]
    print(answer[::-1])


def num4():
    string = str(input("Строка:"))
    if string.count("f") == 1:
        i = string.index("f")
        print(i)
    elif string.count("f") > 1:
        i = string.index("f")
        i2 = string[::-1].index("f")
        i3 = len(string)-i2-1
        print(i, i3)


def num5():
    l = [1, 2, 3, 1, 1, 1, 2, 8, 3, 9, 9, 0, 3]
    for i in range(1, len(l)):
        if l[i] > l[i-1]:
            print(l[i])


def num6():
    l = [1, 2, 3, 1, 1, 1, -2, -8, 3, -9, -9, 0, 3]
    for i in range(1, len(l)):
        if l[i-1] < 0 and l[i] < 0:
            print(l[i-1], l[i])
            break


def num7():
    A = [1, 2, 3, 4, 5, 6, 7, 8, 9]
    for i in range(0, len(A)-1, 2):
        A[i], A[i+1] = A[i+1], A[i]

    print(A)


def num8():
    l = [1, 1, 1, 1, 2, 2, 3, 5, 8, 4, 9, 9]
    for i in range(0, len(l)):
        if l.count(l[i]) == 1:
            print(l[i])


def num9():
    a = np.zeros((8, 8))
    for i in range(1, 9):
        print("Введите координаты {0} элемента(начинается с 0)".format(i))
        a[int(input())][int(input())] = 1

    print(a)
    c = np.sum(a, axis=0)
    c1 = np.sum(a, axis=1)
    c_count = 0
    c1_count = 0
    for i in range(len(c)):
        if c[i] > 1:
            c_count += c[i]
        if c1[i] > 1:
            c1_count += c1[i]

    if c_count == 0 and c1_count == 0:

        for i in range(-7, 8):
            if np.sum(np.diagonal(a, offset=i)) > 1:
                print("YES")
                quit()
    else:
        print("YES")
        quit()

    print("NO")

# alt shift down
