import numpy as np
from PIL import Image


def num1():
    print("a")
    arr_1 = np.array(np.arange(12), int).reshape(3, 4)
    arr_1.fill(3)
    print(arr_1)

    print("\nb")
    arr_2 = np.array(np.random.randint(0, 9, 8)).reshape(2, 4)
    print(arr_2)

    print("\nc")
    print("len arr_1:", np.size(arr_1))
    print("len arr_2:", np.size(arr_2))

    print("\nd")
    print(np.concatenate([arr_1, arr_2], axis=0))

    print("\ne")
    arr_3 = np.array((1, 8, 6, 5, 8, 3))
    print(arr_3)

    print("\nf")
    arr_4 = arr_3.dot(3) + 1
    print(arr_4)

    print("\ng")
    arr_5 = arr_3.reshape(2, 3)
    print(arr_5)

    print("\nh")
    print(arr_5.min(axis=1))

    print("\ni")
    print(arr_5.mean())

    print("\nj")
    arr_6 = np.array(np.arange(10))**2
    print(arr_6)

    print("\nk")
    print(arr_6[1::2])

    print("\nL")
    print(arr_6[::-1])

    print("\nm")
    arr_6[1::2].fill(2)
    print(arr_6)

    print("\nn")
    print("Значение 49 есть в arr_6" if 49 in arr_6 else "Значения 49 нет в arr_6")

    print("\no")
    A = np.array(np.random.randint(-10, 10, 10))
    print("A =", A)
    B = A[np.where(A < 0)]
    print("B =", B)


def num2():  # black - 0, white - 1
    def make_field(size):
        desk = np.zeros((size, size), dtype=np.int8)
        desk[(size + 1) % 2::2, ::2] = 1
        desk[size % 2::2, 1::2] = 1
        return desk

    print(make_field(8))
    print(make_field(18))


def num3():
    def super_sort(rows, cols):
        A = np.array(np.random.randint(
            1, 101, rows * cols)).reshape(rows, cols)
        B = np.copy(A)
        B[::, 1::2] = np.sort(B[::, 1::2], axis=0)
        B[::, ::2] = -np.sort(-B[::, ::2], axis=0)
        return (A, B)

    print(super_sort(5, 5))


def num4():
    def bw_convert(image):
        try:
            myimage = Image.open(image)
        except:
            print("No such a file")
        arr = np.array(myimage)
        C = np.array((0.2989, 0.587, 0.114, 0))  # RGBA
        res = np.round(arr.dot(C))
        Image.fromarray(res).convert("RGB").save("bw_house.png", "png")
        myimage.close()
    bw_convert("house.png")


def num5():
    table = np.genfromtxt("ABBREV.csv", delimiter=";",
                          dtype=None, names=True, encoding="utf8")

    names = table["Shrt_Desc"]
    calories = table["Energ_Kcal"]
    sugar = table["Sugar_Tot"]
    protein = table["Protein"]
    vitamin_c = table["Vit_C"]

    print("Most calories:", names[len(table) - np.argmax(calories[::-1])])
    print("Least sugar:", names[len(table) - np.argmin(sugar)])
    print("Most protein:", names[len(table) - np.argmax(protein)])
    print("Most vitamin C:", names[len(table) - np.argmax(vitamin_c)])
