import csv


def num11():
    names = []
    prices = []
    quantities = []
    with open("data.csv", "r") as csv_file:
        file_reader = csv.DictReader(csv_file, delimiter=";")
        for row in file_reader:
            names.append(row["name"])
            prices.append(row["price"])
            quantities.append(row["quantity"])
    t = -1
    while t != 0:
        t = int(input(
            "1 - вывести информацию\n2 - добавить товар\n3 - редактировать товар\n4 - удалить товар\n0 - выход\n"))
        if t == 1:
            print("name", "price", "quantity", sep="\t")
            for i, name in enumerate(names):
                print(names[i], prices[i], quantities[i], sep="\t")

        elif t == 2:
            name = input("Имя нового товара:")
            price = int(input("Цена нового товара:"))
            quantity = int(input("Кол-во нового товара:"))
            names.append(name)
            prices.append(price)
            quantities.append(quantity)
        elif t == 3:
            name = input("Название редактируемого товара:")
            if name not in names:
                print("Такого товара нет")
                return
            index = names.index(name)
            name = input("Новое название товара:")
            names[index] = name
            price = int(input("Новая цена товара:"))
            prices[index] = price
            quantity = int(input("Новое количество товара:"))
            quantities[index] = quantity
        elif t == 4:
            name = input("Название удаляемого товара:")
            if name not in names:
                print("Такого товара нет")
                return
            index = names.index(name)
            del names[index]
            del prices[index]
            del quantities[index]
        else:
            break
    with open("data.csv", "w") as csv_file:
        fieldnames = ["name", "price", "quantity"]
        file_writer = csv.DictWriter(
            csv_file, fieldnames, delimiter=";", lineterminator="\r")
        file_writer.writeheader()
        for i in range(len(names)):
            file_writer.writerow(
                {"name": f"{names[i]}", "price": f"{prices[i]}", "quantity": f"{quantities[i]}"})


def num12():
    with open("data2.csv", "r", encoding="utf8") as csv_file:
        file_reader = csv.DictReader(csv_file, delimiter=";")
        for row in file_reader:
            name = row["names"]
            old_price = int(row["old_price"])
            new_price = int(row["new_price"])
            if old_price > new_price:
                print(name)


num11()
