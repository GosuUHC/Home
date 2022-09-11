from telegram.ext import Updater, MessageHandler, Filters, CommandHandler
from datetime import datetime

token = ""

def num1():

    def echo(update, context):
        update.message.reply_text(update.message.text)
        print(update.message.from_user)

    updater = Updater(
        token, use_context=True)

    dp = updater.dispatcher

    text_handler = MessageHandler(Filters.text, echo)

    dp.add_handler(text_handler)

    updater.start_polling()

    updater.idle()


def num2():

    def tg_time(update, context):
        now = datetime.now()
        current_time = now.strftime("%H:%M:%S")
        update.message.reply_text(f"Time: {current_time}")

    def tg_date(update, context):
        now = datetime.now()
        update.message.reply_text(f"Date: {now.date()}")

    updater = Updater(token, use_context=True)

    dp = updater.dispatcher

    dp.add_handler(CommandHandler("time", tg_time))
    dp.add_handler(CommandHandler("date", tg_date))

    updater.start_polling()

    updater.idle()


def num3():
    pass
