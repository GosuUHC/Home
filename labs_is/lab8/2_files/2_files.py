from pickle import load
import numpy as np


def num6():
    with open("lines.txt", "r") as f:
        lines = f.readlines()
        if len(lines) == 0:
            return

        line_index = np.random.randint(0, 4)
        print(lines[line_index])


def num7():
    replacing_dict = {
        "й": "j", "ц": "c", "у": "u", "к": "k", "е": "e", "н": "n",
        "г": "g", "ш": "sh", "щ": "shh", "з": "z", "х": "h", "ъ": "#",
        "ф": "f", "ы": "y", "в": "v", "а": "a", "п": "p", "р": "r",
        "о": "o", "л": "l", "д": "d", "ж": "zh", "э": "je", "я": "ya",
        "ч": "ch", "с": "s", "м": "m", "и": "i", "т": "t", "ь": "'",
        "б": "b", "ю": "ju", "ё": "jo"
    }
    translit = ""
    translit_lines = []
    with open("cyrillic.txt", "r", encoding="utf8") as cyril_file:
        lines = cyril_file.readlines()

    for line in lines:
        translit = ""
        for letter in line:
            if letter.lower() in replacing_dict:
                if letter.isupper():
                    translit += replacing_dict[letter.lower()].capitalize()
                else:
                    translit += replacing_dict[letter.lower()]
            else:
                translit += letter
        translit_lines.append(translit)

    with open("transliteration.txt", "w+", encoding="utf8") as trn_file:
        trn_file.writelines(translit_lines)


def num8():
    def reverse():
        with open("input.dat", "rb") as in_file:
            data = in_file.read()

        with open("output.dat", "wb") as out_file:
            out_file.write(data[::-1])

    reverse()


def num9():
    with open("input.txt", "r", encoding="utf8") as in_file:
        data = in_file.read().split()

    data_inted = [int(i) for i in data]

    plus_count = 0
    minus_count = 0
    zero_count = 0

    for i in data_inted:
        if i > 0:
            plus_count += 1
        elif i < 0:
            minus_count += 1
        else:
            zero_count += 1

    with open("output.txt", "w+") as out_file:
        out_file.write(str(plus_count + minus_count + zero_count))
        out_file.write("\n" + "1 " + str(plus_count) + " -1 " +
                       str(minus_count) + " 0 " + str(zero_count))


def num10():
    with open("bmp_file.bmp", "rb") as in_file:
        data = list(in_file.read())

    for i, byte in enumerate(data):
        if i <= 53:
            pass
        else:
            data[i] = 255 - byte

    with open("bmp_file_neg.bmp", "wb+") as out_file:
        out_file.write(bytes(data))
