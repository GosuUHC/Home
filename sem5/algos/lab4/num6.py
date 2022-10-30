import hashlib
import os

if __name__ == "__main__":
    path = input("dir path:")
    hashed_files = {}
    for filename in os.listdir(path=path):
        with open(os.path.join(path, filename), "rb") as in_file:
            md5 = hashlib.md5()
            print(filename)
            for chunk in iter(lambda: in_file.read(65536), b""):
                md5.update(chunk)
            hashed = md5.hexdigest()
            if hashed_files.get(hashed) == None:
                hashed_files[hashed] = []
            hashed_files[hashed].append(filename)

    for value in hashed_files.values():
        if len(value) > 1:
            print(f"DUPLICATES: {value}")
