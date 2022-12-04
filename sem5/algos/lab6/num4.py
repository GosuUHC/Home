words = []
orig_word = input("first word: ")
size = int(input("words count: "))
for i in range(size):
    inp = input("word: ")
    words.append(inp)

print(f"{orig_word = }")
print(f"{words = }")

results = {}

for word in words:
    cells = [[0] * len(word)] * len(orig_word)
    count = 0
    for i in range(len(word)):
        for j in range(len(orig_word)):
            if word[i] == orig_word[j]:
                cells[i][j] = cells[i - 1][j - 1] + 1
                if cells[i][j] > count:
                    count = cells[i][j]

    results[word] = count


winner = max(results, key=results.__getitem__)
print(results)
print(f"result: {winner}: {results[winner]} ")
