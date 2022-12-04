nominalo = [100, 50, 10, 4, 3, 1]

money = int(input("Сколько снять:"))

res = {}

for nominal in nominalo:
    count = money // nominal
    if count > 0:
        res[nominal] = count

    money %= nominal

print(f"Снято: {res}, кол-во купюр и монет: {sum(res.values())}")
