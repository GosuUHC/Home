
def num1():
    txt = input("Text:")
    d = dict()
    for word in txt.split():
        d[word] = d.get(word, 0) + 1
        print(d[word], end=" ")


def num2():  # ключ-значение
    d = {}
    for i in range(int(input("Количество пар:"))):
        key = input()
        value = input()
        d[key] = value
    word = input("Найти синоним для: ")
    if word in d.keys():
        print("Синоним:", d.get(word))
    elif word in d.values():
        s = []  # ключ и значение меняются местами
        for i in d.keys():
            s.append((d[i], i))
        d_1 = dict(s)
        print("Синоним:", d_1.get(word))



def num3():
    d = {}
    d1 = {}
    for i in range(int(input())):
        last_name_key = input()
        votes_count_value = input()
        d[last_name_key] = (int(d.get(last_name_key, 0)) +
                            int(votes_count_value))

    for key in sorted(d.keys()):
        print(key, d[key])



def num4():
    N = int(input("N:"))
    d = {}
    for i in range(N):
        f_name_key = input()
        permissions_value = input()
        d[f_name_key] = permissions_value
    M = int(input("M:"))
    res = []
    for i in range(M):
        permis = input()
        f_name = input()
        if f_name in d.keys():
            if permis[0].upper() in str(d[f_name]).split() \
                    or (permis == "execute" and permis[1].upper() in str(d[f_name]).split()):
                res.append("OK")
            else:
                res.append("Access denied")
    for i in res:
        print(i)

num4()


