import numpy as np
import matplotlib.pyplot as plt
#                                  x1 x2 res
input_data_and_expected_result = [(0, 0, 0),
                                  (0, 1, 1),
                                  (1, 0, 1),
                                  (1, 1, 0)]


w1 = np.array([[0.5, 0.5], [0.5, -0.5]])
w2 = np.array([0.5, 0.5])
learn = 0.1
losses = []


def tanh(x):
    return np.tanh(x)


def d_tanh(x):
    return 1 - x**2


def predict(inpuTs):
    summ_hidden = np.dot(w1, inpuTs)
    output_activated_hidden = np.array([tanh(x) for x in summ_hidden])
    summ_out = np.dot(w2, output_activated_hidden)
    output_activated_out = tanh(summ_out)
    return (output_activated_out, output_activated_hidden)


def train():
    global w1, w2
    epochs = 5000
    for epoch in range(epochs):
        for sample in range(4):
            x = input_data_and_expected_result[sample]
            final_result, hidden_layer_result = predict(x[0:2])
            error = final_result - x[-1]
            losses.append(error)
            weight_delta_out = error*d_tanh(final_result)
            w2[0] -= learn * weight_delta_out * hidden_layer_result[0]
            w2[1] -= learn * weight_delta_out * hidden_layer_result[1]

            weight_delta_hidden = w2 * weight_delta_out * \
                d_tanh(hidden_layer_result)
            w1[0, :] -= \
                np.array(x[0:2]) * weight_delta_hidden[0] * learn
            w1[1, :] -=  \
                np.array(x[0:2]) * weight_delta_hidden[1] * learn
    for i in input_data_and_expected_result:
        z, o = predict(i[0:2])
        print(f"Result:{z},  expected: {i[-1]}")
    print(w1)
    print(w2)


def graph():
    plt.xlabel("Epochs")
    plt.ylabel("Error")

    plt.plot(losses)
    plt.savefig("Errors value graph")
    plt.show()


train()
graph()
