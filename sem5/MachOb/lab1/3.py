import matplotlib.pyplot as plt
from matplotlib.ticker import FormatStrFormatter, AutoMinorLocator


def func(x):
    return x*x - x - 6


plt_x = []
plt_res = []

for i in range(-5, 7, 1):
    plt_x.append(i)
    res = func(i)
    plt_res.append(res)
    if res == 0:
        plt.scatter(i, res)

ax = plt.gca()
ax.tick_params(axis="both", length=7)
ax.tick_params(axis="x", which="minor",  length=4)
ax.tick_params(axis="x", which="minor", labelsize=7)


ax.xaxis.set_minor_formatter(FormatStrFormatter("%.1f"))
ax.xaxis.set_minor_locator(AutoMinorLocator(n=2))

plt.grid(True, linestyle="--", which="both")

plt.xlabel("X")
plt.ylabel("Y")

plt.plot(plt_x, plt_res)
plt.show()
