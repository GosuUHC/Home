import matplotlib.pyplot as plt
import numpy as np


def num1():
    plt.minorticks_on()
    plt.title(
        "PopularitY of Programming language\nWorldwide, Oct 2017 compared to a year ago")
    ax = plt.gca()
    ax.set_axisbelow(True)
    ax.yaxis.grid(color="red", which="major")
    ax.yaxis.grid(color="grey", linestyle="--", which="minor", alpha=0.5)
    ax.xaxis.grid(color="grey", linestyle="--", which="minor", alpha=0.5)
    ax.xaxis.grid(color="red", which="major")

    plt.xlabel("Languages")
    plt.ylabel("Popularity")
    plt.ylim(0, 25)
    x = ["Java", "Python", "PHP", "JS", "C#", "C++"]
    y = [22.2, 17.6, 8.8, 8, 7.7, 6.7]
    plt.bar(x, y, color="darkblue", align="edge", edgecolor="black", alpha=0.8)
    plt.show()


def num2():
    plt.minorticks_on()
    plt.title(
        "PopularitY of Programming language\nWorldwide, Oct 2017 compared to a year ago")
    ax = plt.gca()
    ax.set_axisbelow(True)
    ax.yaxis.grid(color="red", which="major")
    ax.yaxis.grid(color="grey", linestyle="--", which="minor", alpha=0.5)
    ax.xaxis.grid(color="grey", linestyle="--", which="minor", alpha=0.5)
    ax.xaxis.grid(color="red", which="major")

    plt.ylabel("Languages")
    plt.xlabel("Popularity")
    plt.xlim(0, 25)
    x = ["Java", "Python", "PHP", "JS", "C#", "C++"]
    y = [22.2, 17.6, 8.8, 8, 7.7, 6.7]
    plt.barh(x, y, color="darkgreen", align="edge",
             edgecolor="black", alpha=0.8)
    plt.show()


def num3():
    plt.minorticks_on()
    plt.title(
        "PopularitY of Programming language\nWorldwide, Oct 2017 compared to a year ago")
    ax = plt.gca()
    ax.set_axisbelow(True)
    ax.yaxis.grid(color="red", which="major")
    ax.yaxis.grid(color="grey", linestyle="--", which="minor", alpha=0.5)
    ax.xaxis.grid(color="grey", linestyle="--", which="minor", alpha=0.5)
    ax.xaxis.grid(color="red", which="major")

    plt.xlabel("Languages")
    plt.ylabel("Popularity")
    plt.ylim(0, 25)
    x = ["Java", "Python", "PHP", "JS", "C#", "C++"]
    y = [22.2, 17.6, 8.8, 8, 7.7, 6.7]
    plt.bar(x, y, color=["red", "black", "green", "blue",
            "yellow", "cyan"], align="edge", edgecolor="black")
    plt.show()


def num4():
    plt.minorticks_on()
    plt.title(
        "PopularitY of Programming language\nWorldwide, Oct 2017 compared to a year ago")
    ax = plt.gca()
    ax.set_axisbelow(True)
    ax.yaxis.grid(color="red", which="major")
    ax.yaxis.grid(color="grey", linestyle="--", which="minor", alpha=0.5)
    ax.xaxis.grid(color="grey", linestyle="--", which="minor", alpha=0.5)
    ax.xaxis.grid(color="red", which="major")

    plt.xlabel("Languages")
    plt.ylabel("Popularity")
    plt.ylim(0, 25)
    x = ["Java", "Python", "PHP", "JS", "C#", "C++"]
    y = [22.2, 17.6, 8.8, 8, 7.7, 6.7]
    plt.bar(x, y, color="darkblue", align="edge", edgecolor="black", alpha=0.8)
    for i in range(len(x)):
        plt.text(i, y[i], y[i], ha="left")
    plt.show()


def num5():
    plt.minorticks_on()
    plt.title(
        "PopularitY of Programming language\nWorldwide, Oct 2017 compared to a year ago")
    ax = plt.gca()
    ax.set_axisbelow(True)
    ax.yaxis.grid(color="red", which="major")
    ax.yaxis.grid(color="grey", linestyle="--", which="minor", alpha=0.5)
    ax.xaxis.grid(color="grey", linestyle="--", which="minor", alpha=0.5)
    ax.xaxis.grid(color="red", which="major")

    plt.xlabel("Languages")
    plt.ylabel("Popularity")
    plt.ylim(0, 25)
    x = ["Java", "Python", "PHP", "JS", "C#", "C++"]
    y = [22.2, 17.6, 8.8, 8, 7.7, 6.7]
    width = [0.1, 0.2, 0.5, 0.8, 0.15, 0.25]
    plt.bar(x, y, color="darkblue", align="edge",
            edgecolor="black", alpha=0.8, width=width)
    plt.show()


def num6():
    plt.title("Scores by person")
    plt.xlabel("Person")
    plt.ylabel("Scores")

    person = ["G1", "G2", "G3", "G4", "G5"]
    scores_male = [22, 30, 33, 30, 25]
    scores_female = [25, 32, 30, 35, 28]
    person_len = np.arange(len(person))
    plt.ylim(0, 35)
    plt.bar(person_len-0.2, scores_male, width=0.4, color="green")
    plt.bar(person_len+0.2, scores_female, width=0.4, color="red")
    plt.xticks(person_len, person)
    plt.legend(["men", "women"])
    plt.show()


def num7():
    x = ["Java", "Python", "PHP", "JS", "C#", "C++"]
    y = [22.2, 17.6, 8.8, 8, 7.7, 6.7]
    explode = [0.1]
    explode.extend([0] * (len(y) - len(explode)))  # заполнение нулями
    plt.pie(y, labels=x, autopct=lambda p: f"{p:.2f}%", explode=explode)
    plt.show()


def num8():
    plt.title(
        "Popularity of Programming Language\n Worldwide, Oct 2017 compared to a year ago",
        bbox={"edgecolor": "black", "facecolor": "grey"})

    x = ["Java", "Python", "PHP", "JS", "C#", "C++"]
    y = [22.2, 17.6, 8.8, 8, 7.7, 6.7]
    explode = [0.1]
    explode.extend([0] * (len(y) - len(explode)))  # заполнение нулями
    explode[x.index("C++")] = 0.05
    plt.pie(y, labels=x, autopct=lambda p: f"{p:.2f}%", explode=explode)
    plt.show()


num8()
