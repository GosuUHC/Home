
def num1():
    film = input("Название фильма:")
    cinema = input("Название кинотеатра:")
    time = input("Время:")
    print('Билет на "', film, '" в "', cinema, '" на', time, 'забронирован')


def num2():
    string1 = str(input("Первая строка:"))
    string2 = str(input("Вторая строка:"))
    if(string1 == "нет" and string2 == "нет") or (string1 == "нет" and string2 == "да") or (
            string1 == "да" and string2 == "нет") or (string1 == "да" and string2 == "да"):
        print("ВЕРНО")
    else:
        print("НЕВЕРНО")

def num3():
    login = input("Login:")
    res_addr = input("Address:")
    if(not("@" in login) and ("@" in res_addr)):
        print("Все верно")
    else:
        print("Ошибка")


def num4():
    print(input())

def num5():
    string = str(input("Строка:"))
    if(string == ""):
        print("ДА")
    else:
        print("НЕТ")


def num6():
    n = int(input("Число:"))
    max = -1
    min = 10
    n1 = n
    while(n1 > 0):
        buff = n1 % 10
        n1 //= 10
        if(buff > max):
            max = buff
        if(buff < min):
            min = buff
    n1 = n
    left_number = -1
    while(n1 > 0):
        buff = n1 % 10
        n1 //= 10
        if((buff != min) and (buff != max)):
            left_number = buff
    if(((max+min)/2) == left_number):
        print("Вы ввели красивое число")
    else:
        print("Жаль, вы ввели обычное число")


def num7():
    n = int(input("Число:"))
    n1 = n

    first = n1 % 10
    second = n1 // 10 % 10
    third = n1 // 100 % 10
    fourth = n1//1000

    if (first > second):
        first, second = second, first
    if (second > third):
        second, third = third, second
    if (third > fourth):
        third, fourth = fourth, third
    if (first > second):
        first, second = second, first
    if (second > third):
        second, third = third, second
    if (first > second):
        first, second = second, first
    if (first == 0 and second):
        first, second = second, first
    elif (first == 0 and third):
        first, third = third, first
    elif (first == 0 and fourth):
        first, fourth = fourth, first
    number = fourth+10*(third+10*(second+10*first))
    print(number)



def num8():
    height = []
    count = 0
    while(True):
        n = str(input("Ввод:"))
        if(n == "!"):
            break
        elif(int(n) > 149 and int(n) < 191):
            count += 1
            height.append(n)

    print(count)
    print(min(height), max(height))


def num9():
    while(True):
        passw1 = str(input("Password:"))
        if(len(passw1) < 8):
            print("Короткий!")
        elif("123" in passw1):
            print("Простой!")
        else:
            passw2 = str(input("Confirm password:"))
            if(passw1 != passw2):
                print("Различаются")
            else:
                print("OK")
                break



def num11():
    quan = int(input("Высота пирамиды:"))
    a = 1
    quan1 = quan
    for i in range(quan):
        quan1 -= 1
        print(" "*quan1, "*"*a, " "*quan1)
        a += 2

def num12():
    N = int(input("Число:"))
    if(N > 0):
        n_1 = 1
        n_2 = 1
        n_3 = 0
        while(n_1 <= N):
            print(n_1, end=" ")
            n_1 += 1
            n_3 += 1
            if(n_3 == n_2):
                print()
                n_2 += 1
                n_3 = 0
    else:
        print("Отрицательное значение!")


def num13():
    n = int(input("n:"))
    m = int(input("m:"))
    symbol = str(input("Символ:"))

    print(symbol*m, sep=" ")
    for x in range(n-2):
        print(symbol, " " * (m-2), symbol, sep="")
    print(symbol*m, sep=" ")


num13()
