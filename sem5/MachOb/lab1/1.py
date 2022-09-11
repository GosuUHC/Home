import matplotlib.pyplot as plt
import datetime as dt
import matplotlib.dates as mdates


def num1():

    line = list(i for i in range(200))

    plt.xlabel("x - axis")
    plt.ylabel("y - axis")
    plt.plot(line)
    plt.show()


def num2():

    plt.xlabel("x - axis")
    plt.ylabel("y - axis")

    plt.plot((10, 20, 30), (20, 40, 10), linewidth=3, color="blue")
    plt.plot((10, 20, 30), (40, 10, 30), linewidth=5, color="red")
    plt.legend(["line1-width-3", "line2-width-5"])

    plt.show()


def num3():
    plt.xlabel("x - axis")
    plt.ylabel("y - axis")

    plt.plot((10, 20, 30), (20, 40, 10), linewidth=3,
             color="blue", linestyle="dotted")
    plt.plot((10, 20, 30), (40, 10, 30), linewidth=4,
             color="red", linestyle="dashed")
    plt.legend(["line1-dotted", "line2-dashed"])

    plt.show()


def num4():
    plt.xlabel("x - axis")
    plt.ylabel("y - axis")

    plt.plot((1, 4, 5, 6, 7), (2, 6, 3, 6, 3),
             linestyle="dashdot", color="red")
    plt.scatter((1, 4, 5, 6, 7), (2, 6, 3, 6, 3), color="blue")

    plt.show()


def num5():
    plt.xlim(0, 10)
    plt.ylim(0, 30)
    plt.scatter((2, 3, 5, 6, 8), (1, 5, 10, 17, 20), marker="*", color="blue")
    plt.scatter((3, 4, 6, 7, 9), (2, 6, 12, 21, 23), color="red")
    plt.show()


def num6():
    plt.ylim(772.5, 777.0)
    plt.xlabel("Date")
    plt.ylabel("Closing Value")
    dates = ["2016-10-03", "2016-10-04",
             "2016-10-05", "2016-10-06", "2016-10-07"]

    x = [mdates.datestr2num(date) for date in dates]
    y = [772.5, 776.4, 776.5, 776.9, 775.1]

    plt.gca().xaxis.set_major_formatter(mdates.DateFormatter("%Y-%m-%d"))
    plt.gca().xaxis.set_major_locator(mdates.DayLocator())

    plt.plot(x, y, color="red")

    plt.minorticks_on()
    plt.gcf().autofmt_xdate()

    plt.grid(True, color="red", which="major")
    plt.grid(True, linestyle="--", which="minor")

    plt.scatter(x, y, color="red")

    plt.show()
