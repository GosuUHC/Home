
from cmath import*
import matplotlib.pyplot as plt


ly = 0.001
w = [0.1, -0.05, 0.05]
x0 = [1, 1, 1, 1]
x1 = [0, 0, 1, 1]
x2 = [0, 1, 0, 1]

c = [0, 0, 0, 1]

for epoh in range(20000):
    
    for sample in range(4):
        y = w[0]*x0[sample]+w[1] * \
            x1[sample]+w[2]*x2[sample]
        w[0] += ly*x0[sample]*(c[sample]-y)
        w[1] += ly*x1[sample]*(c[sample]-y)
        w[2] += ly*x2[sample]*(c[sample]-y)

    for i in range(4):
        y = w[0]*x0[i]+w[1] * \
            x1[i]+w[2]*x2[i]
        e = 0.5*pow(c[i]-y, 2)
        
for sample in range(4):
    y = w[0]*x0[sample]+w[1] * \
        x1[sample]+w[2]*x2[sample]
    print(c[sample], "   ", y)


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


graph()
