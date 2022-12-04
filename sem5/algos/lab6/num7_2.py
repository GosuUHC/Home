nominalo = [100, 50, 10, 4, 3, 1]

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


print(f"Снято: {res}, кол-во купюр и монет: {sum(res.values())}")
