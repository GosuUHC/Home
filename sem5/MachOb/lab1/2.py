import matplotlib.pyplot as plt
from matplotlib.ticker import FormatStrFormatter, AutoMinorLocator

plt.title("Элементы изображения")

plt.xlabel("Подпись оси Х")
plt.ylabel("Подпись оси Y")

plt.minorticks_on()

plt.plot((0, 1, 10), (0, 8, 6), color="red")
plt.plot((0, 10), (0, 6), color="blue")
plt.scatter((0, 1, 2, 0.5, 1, 7), (1, 0.5, 4, 2, 5, 6),
            facecolors="none", edgecolors="black")

plt.legend(["Красная линия", "Синяя линия"])

ax = plt.gca()
ax.tick_params(axis="both", length=7)
ax.tick_params(axis="x", which="minor", labelsize=7)


ax.xaxis.set_minor_formatter(FormatStrFormatter("%.1f"))
ax.xaxis.set_minor_locator(AutoMinorLocator(n=4))

plt.grid(linestyle="--")


plt.show()
