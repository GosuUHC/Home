from geocoder import get_json, get_text_address, get_coordinates, get_administartive_area, get_postal_code
import requests


def num3():
    json = get_json("Москва, Красная площадь, 1")
    print("Адрес:", get_text_address(json))
    print("Координаты:", get_coordinates(json))


def num4():
    geocode = ["Барнаул", "Мелеуз", "Йошкар-Ола"]

    for address in geocode:
        print(address, get_administartive_area(
            get_json(address)), sep="  \t- ")


def num5():
    print(get_postal_code(get_json("Москва, ул. Петровка, 38")))


def num6():
    response = requests.get(
        "https://static-maps.yandex.ru/1.x/?ll=133.787277,-25.6947792&l=sat&z=4")
    with open("AU_Sat.png", "wb+") as file:
        file.write(response.content)


def num8():
    response = requests.get("https://static-maps.yandex.ru/1.x/?ll=86.087314,54.254968&l=map&z=6&"
                            "pl=c:C70039C0,w:4,86.086847,55.355198,86.162243,54.663609,87.136053,53.757553,87.983137,52.929298")

    with open("line.png", "wb") as file:
        file.write(response.content)


def num9():
    result = ""
    northern_latitude = 90
    cities = input("Введите через пробел список городов:").split(" ")

    for city in cities:
        temp_northern_latitude = get_coordinates(get_json(city))[1]
        if temp_northern_latitude < northern_latitude:
            result = city
            northern_latitude = temp_northern_latitude

    print('Южнее всех:', result)


num9()