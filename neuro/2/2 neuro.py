
from cmath import*
import matplotlib.pyplot as plt
import numpy as np


x = np.array([[0, 0, 1, 1],   # ввод
              [0, 1, 0, 1]])  # массив векторов

y = np.array([[0, 1, 1, 0]]) # вывод

num_x=2 # кол-во ввода

num_y=1 # кол-во внешних нейронов

num_h=2 # кол-во внутренних(скрытых) нейронов

total=x.shape[1] # всего сэмплов

ly = 0.1 # лямбда

np.random.seed(2) # для корректных результатов

w1 = np.random.rand(num_h, num_x) #веса для скрытых нейронов
w2 = np.random.rand(num_y, num_h) #веса для внешних нейронов

E=0 # ошибка

for epoh in range(20000):

    for sample in range(4):
        z = w1[0]*y0[sample]+w1[1] * \
            y1[sample]+w1[2]*y2[sample]
        w1[0] += ly*y0[sample]*(c[sample]-z)
        w1[1] += ly*y1[sample]*(c[sample]-z)
        w1[2] += ly*y2[sample]*(c[sample]-z)

    for i in range(4):
        z = w1[0]*x0[i]+w1[1] * \
            x1[i]+w1[2]*x2[i]
        e = c[i]-z
        E = 0.5*pow(c[i]-z, 2)

    for i in range(3):

        e1 = e*w1[1]
        e2 = e*w2[2]
        w1[i] += ly*e1*y1[i]
        w2[i] += ly*e*y2[i]


for sample in range(4):
    z = w1[0]*x0[sample]+w1[1] * \
        x1[sample]+w1[2]*x2[sample]
    print(c[sample], "   ", z)


def graph():
    plt.xlabel("X1")
    plt.ylabel("X2")
    plt.scatter([x1[i] for i in range(4)], [x2[i] for i in range(4)])
    for i in range(4):
        x, y = x1[i], x2[i]
        kx = -1 if x == 0 else 4
        ky = -1 if y == 0 else 4
        plt.text(x-0.02*kx, y-0.02*ky, f"({x}, {y}")
    xx = [x1[i] for i in range(4)]
    yy = [(-(w[1]/w[2])*x1[i]+(0.5-w[0]*x0[i])/w[2]) for i in range(4)]
    print(xx)
    print(yy)
    plt.plot(xx, yy)
    plt.savefig("grapho.png")
    plt.show()


# graph()
