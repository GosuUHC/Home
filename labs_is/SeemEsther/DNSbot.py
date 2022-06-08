from bs4 import BeautifulSoup
from selenium import webdriver
from api_token import api_token
from aiogram import Bot, Dispatcher, executor, types
from aiogram.types import ReplyKeyboardMarkup, KeyboardButton
import logging


class Parser():

    def __init__(self):
        self.firefox_driver = "./geckodriver.exe"
        self.options = webdriver.FirefoxOptions()
        self.options.add_argument("headless")
        self.browser = webdriver.Firefox(
            executable_path=self.firefox_driver, options=self.options)

    def parse(self, search):
        url = f'https://www.dns-shop.ru/search/?q={search}&p=1&order=popular&stock=all'
        self.browser.get(url)
        html = self.browser.page_source
        soup = BeautifulSoup(html, "html5lib")
        links = []
        for data in soup.find_all(class_="catalog-product__name ui-link ui-link_black"):
            links.append(str(data.select('span')).strip(
                "[<span>").strip("</span>]"))
            links.append("www.dns-shop.ru" + data.get('href'))
        return links[:20]


def write_log(user_id, data):
    with open(str(user_id)+".log", "a", encoding="utf-8") as log_file:
        log_file.write(f"\n{data}")


parser = Parser()
logging.basicConfig(level=logging.INFO)
greet_kb = ReplyKeyboardMarkup(resize_keyboard=True, one_time_keyboard=True)
button_GPU = KeyboardButton("/Видеокарты")
button_CPU = KeyboardButton("/Процессоры")
button_CASE = KeyboardButton("/Корпуса")
button_MB = KeyboardButton("/МатеринскиеПлаты")
greet_kb.add(button_GPU, button_CPU, button_CASE, button_MB)
bot = Bot(token=api_token)
dp = Dispatcher(bot)


@dp.message_handler(commands=["start", "help"])
async def send_welcome(message: types.Message):
    await message.reply("Hi! I am DNS-shop bot", reply_markup=greet_kb)


@dp.message_handler(commands="Видеокарты")
async def send_GPU(message: types.Message):
    await message.reply("Загрузка...")
    gpus = parser.parse("Видеокарты")
    await message.reply(gpus)
    write_log(message.from_user.id, gpus)


@dp.message_handler(commands="Процессоры")
async def send_CPU(message: types.Message):
    await message.reply("Загрузка...")
    cpus = parser.parse("Процессоры")
    await message.reply(cpus)
    write_log(message.from_user.id, cpus)


@dp.message_handler(commands="Корпуса")
async def send_CASE(message: types.Message):
    await message.reply("Загрузка...")
    cases = parser.parse("Корпуса")
    await message.reply(cases)
    write_log(message.from_user.id, cases)


@dp.message_handler(commands="МатеринскиеПлаты")
async def send_MB(message: types.Message):
    await message.reply("Загрузка...")
    mbs = parser.parse("Материнские-платы")
    await message.reply(mbs)
    write_log(message.from_user.id, mbs)


executor.start_polling(dp, skip_updates=True)
