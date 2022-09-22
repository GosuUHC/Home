import matplotlib.pyplot as plt
import numpy as np

def num1():
    fig = plt.figure()
    plt.plot((0.0, 100.0), (0.0, 200.0))
    plt.xlabel("x")
    plt.ylabel("y")
    fig.add_axes([0.6, 0.5, 0.17, 0.15])
    plt.plot([0.0, 100.0], [0.0, 200.0])
    plt.xlabel("x")
    plt.ylabel("y")
    plt.show()
    plt.close()


def num2():
    plt.figure(figsize=(10, 2))
    plt.subplot(1, 2, 1)
    plt.plot([0.0, 100.0], [0.0, 200.0], color="blue", linewidth=5)
    plt.xlabel("x")
    plt.ylabel("y")
    plt.subplot(1, 2, 2)
    x = np.linspace(0, 100, 100)
    y = x**2
    plt.plot(x, y, color="red", linestyle="--", linewidth=3)
    plt.xlabel("x")
    plt.ylabel("z")
    plt.subplots_adjust(top=1, bottom=0.3, wspace=0.25)
    plt.show()
    plt.close()

num2()