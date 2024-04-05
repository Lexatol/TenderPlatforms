package ru.lexp00.platform.tptenders.enums;

public enum MsgTenders {

    msgSaveTender ("Ваш тендер %s сохранен. Вам осталось его только опубликовать."),
    msgPublicTender ("%s, Ваш заказ: %s опубликован. Скоро вы начнете получать предложения от исполнителей"),
    msgDeleteTender ("%s, Вы удалили свой заказ: %s. Для восстановления заказа пройдите " +
            "во вкладку удаленные и опубликуйте его снова при необходимости"),
    msgCloseTender ("Ваш заказ закрыт. Причин может быть несколько, либо истек срок давности заказа или он " +
            "нарушил правила сервиса."),
    msgForCustomer ("\"%s, поздравляем Вас с выбором исполнителя. \" +\n" +
            "                        \"Не забудьте заключить договор и прописать все необходимые условия. Надеемся, что Вам \" +\n" +
            "                        \"понравился наш сервис и Вы снова придете к нам с новым заказом.\\n \" +\n" +
            "                        \"Скорее всего исполнитель заказа попросит Вас оставить отзыв о его работе, \" +\n" +
            "                        \"просим оценить его честно. Это поможет Вам или другим заказчикам в своем будущем выборе. \\n.\" +\n" +
            "                        \"Контакты выбранного вами исполнителя: \\n\" +\n" +
            "                        \"Имя: %s \\n\" +\n" +
            "                        \"телефон: %s, \\n\" +\n" +
            "                        \"email: %s \\n\""),
    msgForContractor ("%s, привет. Меня зовут %s. Твое предложение показалось интересным. \n" +
            "Свяжись со мной по ниже указанным контактам, чтобы обсудить условия контракта.\n" +
            "телефон: %s, \n" +
            "email: %s \n"),
    msgRespondTender ("Отклик на заказ: %s. " +
            "Привет, Меня зовут %s.  \n" +
            "Срок выполнения  - %s.\n" +
            "Цена - %s. \n" +
            "Описание: %s \n" +
            "Если Вас заинтересовало мое предложение, прошу связаться со мной удобным для Вас способом"),
    msgForRejectContractor ("Увы, заказчик выбрал другого исполнителя для своего заказа: %s\n" +
            "Такое бывает.\n" +
            "Не отчаивайтесь и продолжайте откликаться.");

    private String value;

    MsgTenders(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}