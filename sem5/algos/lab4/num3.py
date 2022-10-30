
class HashTable:
    def __init__(self):
        self.size = 11
        self.slots = [None] * self.size
        self.data = [None] * self.size

    def put(self, key, data):
        hashvalue = self.hashfunction(key, len(self.slots))

        if self.slots[hashvalue] == None:
            self.slots[hashvalue] = key
            self.data[hashvalue] = data
        else:
            if self.slots[hashvalue] == key:
                self.data[hashvalue] = data  # replace
            else:
                i = 1
                nextslot = self.sq_prob_rehash(
                    hashvalue, len(self.slots), i)  # new hash
                while self.slots[nextslot] != None and \
                        self.slots[nextslot] != key:
                    i += 1
                    nextslot = self.sq_prob_rehash(
                        nextslot, len(self.slots), i)

                if self.slots[nextslot] == None:
                    self.slots[nextslot] = key
                    self.data[nextslot] = data
                else:
                    self.data[nextslot] = data  # replace

    def hashfunction(self, key, size):
        if isinstance(key, str):
            return len(key) % size

        return key % size

    def rehash(self, oldhash, size):
        return (oldhash + 1) % size

    def sq_prob_rehash(self, oldhash, size, i):
        return (oldhash + i + i**2) % size

    def get(self, key):
        startslot = self.hashfunction(key, len(self.slots))

        data = None
        stop = False
        found = False
        position = startslot
        i = 1
        while self.slots[position] != None and \
                not found and not stop:
            if self.slots[position] == key:
                found = True
                data = self.data[position]
            else:
                position = self.sq_prob_rehash(position, len(self.slots), i)
                i += 1
                if position == startslot:
                    stop = True
        return data

    def get_load_factor(self):
        return self.__len__() / self.size

    def __getitem__(self, key):
        return self.get(key)

    def __setitem__(self, key, data):
        self.put(key, data)

    def __len__(self):
        size = 0
        for elem in self.data:
            if elem != None:
                size += 1

        return size

    def __contains__(self, value):
        return value in self.data


if __name__ == "__main__":

    H = HashTable()
    H["first"] = "cat"
    H["second"] = "dog"
    H["third"] = "lion"
    H["fourth"] = "tiger"
    H["test"] = "bird"
    H["hello"] = "cow"
    H["easy"] = "goat"
    H["funny"] = "pig"
    H["lol"] = "chicken"
    H[123] = "H123"
    print(H.slots)
    print(H.data)

    print(f"{H['first'] = }")

    print(f"{H['fourth'] = }")
    H['lol'] = 'duck'
    print(f"{H['lol'] = }")

    print(f"{H[99] = }")
    print(f"{H[123] = }")

    print(f"{len(H) = }")

    print(f"{'dog' in H = }")
    print(f"{'SDAADAfaa' in H = }")

    H["test"] = 555
    print(f"{H['test'] = }")
