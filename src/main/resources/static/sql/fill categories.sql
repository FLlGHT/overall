delete from category;

delete from category_group;

insert into category_group (id, title, description)
values (1, 'Самоопределение', 'Личные качества человека'),
       (2, 'Отношения', 'Взаимодействие человека с другими людьми'),
       (3, 'Деятельность', 'Труд и деятельность человека'),
       (4, 'Эмоции', 'Эмоциональность'),
       (5, 'Интеллект', 'Способность к познанию и решению проблем');


insert into category (id, title, description, category_group_id)
values (1, 'Трудолюбие', 'Отношение личности к процессу трудовой деятельности, стремление, много и усердно работать
                          0 (Ленивый) - 99 (Трудолюбивый)', 3),
       (2, 'Ответственность', 'Обязанность и готовность отвечать за совершённые действия, поступки и их последствия
                          0 (Безответственный) - 99 (Ответственный)', 3),
       (3, 'Добросовестность', 'Старательность в выполнении своих обязанностей
                          0 (Недобросовестный) - 99 (Добросовестный)', 3),
       (4, 'Инициативность', 'Склонность к проявлению инициативы
                          0 (Пассивный) - 99 (Инициативный)', 3),
       (5, 'Общительность', 'Склонность к общению
                          0 (Замкнутый) - 99 (Общительный)', 2),
       (6, 'Честность', ' Искренность перед другими и перед самим собой в отношении тех мотивов, которыми человек руководствуется
                          0 (Бесчестный) - 99 (Честный)', 2),
       (7, 'Самостоятельность', 'Способность действовать без чьего-либо вмешательства, самостоятельно принимать решения
                          0 (Несамостоятельный) - 99 (Самостоятельный)', 2),
       (8, 'Вежливость', 'Умение уважительно и тактично общаться с людьми, готовность найти компромисс,
                          0 (Грубый) - 99 (Вежливый)', 2),
       (9, 'Аккуратность', 'Точность, упорядоченность и тщательность в исполнении чего-либо
                          0 (Неряшливый) - 99 (Аккуратный)', 3),
       (10, 'Бережливость', 'Осмотрительное и умеренное расходование чего-либо
                          0 (Небрежный) - 99 (Бережливый)', 1),
       (11, 'Требовательность', 'Предъявление высоких требований к себе и другим
                          0 (Нетребовательный) - 99 (Требовательный)', 1),
       (12, 'Самокритичность', 'Рефлексивное отношение человека к себе,
                                способность к самостоятельному поиску собственных ошибок и непримиримое к ним отношение,
                          0 (Несамокритичный) - 99 (Самокритичный)', 1),
       (13, 'Скромность', 'Умеренность, сдержанность в демонстрации собственных достоинств или заслуг
                          0 (Нескромный) - 99 (Скромный)', 1),
       (14, 'Эгоизм', 'Предпочтение своих личных интересов общественным, интересам других людей
                          0 (Альтруист) - 99 (Эгоист)', 1),
       (15, 'Находчивость', 'Умение быстро находить решение; сообразительность
                          0 (Ненаходчивый) - 99 (Находчивый)'  , 5),
       (16, 'Любознательность', 'Стремление к приобретению всё новых знаний
                          0 (Нелюбознательный) - 99 (Любознательный)', 5),
       (17, 'Эрудированность', 'Глубокие и всесторонние познания в какой-либо области или нескольких областях
                          0 (Неэрудированный) - 99 (Эрудированный)', 5),
       (18, 'Практичность', 'Умение разбираться в жизненных делах и предпочитать то, что даёт реальные результаты
                          0 (Непрактичный) - 99 (Практичный)', 5),
       (19, 'Увлеченность', 'проникнутый сильной заинтересованностью, глубоким увлечением чем-либо
                          0 (Не увлеченный) - 99 (Увлеченный)', 4),
       (21, 'Впечатлительность', 'Способность иметь представления, различные по яркости и по связи с внешним миром,
                                  с различной степенью выраженности в них чувств
                          0 (Невпечатлительный) - 99 (Впечатлительный)', 4),
       (22, 'Вспыльчивость', 'Способность легко приходить в раздражение, гнев, озлобление
                          0 (Хладнокровный) - 99 (Вспыльчивый)', 4),
       (23, 'Жизнерадостность', 'Позитивное отношение человека к жизни и жизненным обстоятельствам
                          0 (Нежизнерадостный) - 99 (Жизнерадостный)', 4),
       (24, 'Смелость', 'Решительность при возникновении опасности, способность не поддаваться страху
                          0 (Трусливый) - 99 (Смелый)', 4),
       (25, 'Настойчивость', 'Упорство в достижении цели
                          0 (Ненастойчивый) - 99 (Настойчивый)', 3),
       (26, 'Целеустремленность', 'Стремление и подчинение действий достижению цели
                          0 (Бесцельный) - 99 (Целеустремленный)', 3),
       (27, 'Справедливость', 'Беспристрастное, непредвзятое, справедливое отношение
                          0 (Несправедливость) - 99 (Справедливость)', 2),
       (28, 'Отзывчивость', 'Готовность помочь другому
                          0 (Неотзывчивый) - 99 (Отзывчивый)', 2),
       (29, 'Доброта', 'Проявление участия, желание добра другому, готовность содействовать благополучию других
                          0 (Злой) - 99 (Добрый)', 2),
       (30, 'Креативность', 'Умение отступать от стандартных идей, правил и шаблонов
                          0 (Не креативный) - 99 (Креативный)', 3);


