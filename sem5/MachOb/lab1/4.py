import math
import matplotlib.pyplot as plt
import numpy as np
#log(value, base)


def func(x):
    return (math.log((x**2 + 1),
            (1. + math.tan(1./(1. + math.sin(x)**2)))) * math.exp(-abs(x)/10.))


plt_x = []
plt_res = []

for i in np.arange(-8, 8, 0.1):
    plt_x.append(i)

    plt_res.append(func(i))

plt.plot(plt_x, plt_res)
plt.show()
