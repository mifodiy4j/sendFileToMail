Нужно на Java написать программу. Можно без визуального интерфейса (т.е. запуск в командной строке). В программе должно быть 2 конфигурационных файла:

1. Конфигурационный файл (где хранятся настройки smtp сервера и другие настройки)

2. Файл шаблон письма, в котором есть 2 параметра для подстановки ${full_name} и ${phone}

При запуске программа должна подставить в шаблон письма параметры ФИО и телефон (прочитав их из конфигурационного файла) и отправить на мой адрес письмо.

В качестве SMTP сервера для отправки почты нужно использовать smtp.yandex.ru (на яндекс почте можно создать себе почтовый ящик и от его имени отправлять)