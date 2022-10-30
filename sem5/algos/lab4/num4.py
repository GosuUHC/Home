from num3 import HashTable

if __name__ == "__main__":
    text = input("Text:").split()
    H = HashTable()
    for word in text:
        if H[word] == None:
            H[word] = 0
        H[word] = H[word] + 1
        print(H[word])
