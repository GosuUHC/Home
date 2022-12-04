nominalo = [100, 50, 10, 4, 3, 1]

bank_money = dict.fromkeys(nominalo, 3)
print("В банке:")
print(bank_money)

clients_count = int(input("Клиентов:"))


for i in range(clients_count):
    print(f"Клиент {i}")
    money = int(input("Сколько снять:"))
    res = {}

    cells = [0]

    for i in range(1, money + 1):
        min_ = min(cells[i - nominal] for nominal in nominalo if nominal <= i)
        cells += [1 + min_]

    while money > 0:
        for nominal in nominalo[::-1]:
            if cells[money - nominal] == cells[money] - 1:
                money -= nominal
                res[nominal] = res.get(nominal, 0) + 1
                break

    stat = True
    bank_money_copy = bank_money.copy()

    for client_money in res.keys():
        bank_money_copy[client_money] -= res[client_money]
        if bank_money_copy[client_money] < 0:
            stat = False
            break

    if stat:
        bank_money = bank_money_copy
        print(f"Снято: {res}, кол-во купюр и монет: {sum(res.values())}")
        print("В банке:")
        print(bank_money)
    else:
        print("Невозможно выдать")
