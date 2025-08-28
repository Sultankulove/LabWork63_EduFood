INSERT INTO restaurants (name, description, image_url)
VALUES ('Пельменная №1', 'Домашние пельмени и вареники', 'https://avatars.mds.yandex.net/i?id=f31156203f6b3072d572e1a68099637a_l-5289247-images-thumbs&n=13'),
       ('Итальянская Лавка', 'Настоящая итальянская паста и пицца', 'https://avatars.mds.yandex.net/get-altay/7456447/2a00000182dbad0a4d2753a190a637433266/orig'),
       ('Восточный Базар', 'Блюда восточной кухни', 'https://avatars.mds.yandex.net/get-altay/1077949/2a000001688e44ad555cd07750d6b2788834/L_height'),
       ('Суши и Роллы', 'Японская кухня и суши-сеты', 'https://dynamic-media-cdn.tripadvisor.com/media/photo-o/0a/db/fd/8b/getlstd-property-photo.jpg?w=900&h=500&s=1'),
       ('Бургерная «Гриль»', 'Сочные бургеры и картофель фри', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTlpDUcz-zAsUVpTjE2ZTgTmSy9S1mnfQf9mQ&s'),
       ('ЗОЖ Кафе', 'Полезная еда и смузи', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQYmRzCgyGC0ALfUkIjYdATLm5MngdozF8hpQ&s'),
       ('Кофейня «Арома»', 'Кофе и десерты', 'https://dynamic-media-cdn.tripadvisor.com/media/photo-o/03/8e/0b/27/aroma.jpg?w=800&h=-1&s=1'),
       ('Китайская Лапша', 'Быстрая лапша и воки', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQcq_I6JExGNC8wAVeoM2OGp8SD2Kzcl5l7wg&s'),
       ('Шашлычная «Мангал»', 'Шашлыки и лаваш', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRGn4v_bpKMEVHSc1PX3ucgXMUYxx6gJdsQww&s'),
       ('Французский Дом', 'Круассаны, багеты и сыр', 'https://avatars.mds.yandex.net/get-altay/1637232/2a00000169a778a54376bd8916229cdb8d75/L_height');



insert into dishes (name, description, price, image_url, restaurant_id)
values
    ('Пельмени классические', 'Домашние пельмени из говядины и свинины', 290.00, 'https://avatars.mds.yandex.net/i?id=1eb1e8e4828ea2637ac9a9abde5a491629d6e2e9-10591076-images-thumbs&n=13', 1),
    ('Пельмени с курицей', 'Нежные пельмени с куриным фаршем', 270.00, 'https://avatars.mds.yandex.net/get-sprav-products/5236693/2a00000187dcb35cb36db8caeb4e9d126141/XXL', 1),
    ('Пельмени с грибами', 'Вегетарианские пельмени с шампиньонами и луком', 260.00, 'https://avatars.mds.yandex.net/i?id=2ea0abb2c1e5084bdfbec457c69d57af9f5bd907-16893175-images-thumbs&n=13', 1),
    ('Вареники с картошкой', 'Классические вареники с картофелем и луком', 250.00, 'https://1469922966.rsc.cdn77.org/wa-data/public/shop/products/14/17/1714/images/1265/1265.750x0.png', 1),
    ('Вареники с вишней', 'Сладкие вареники с сочной вишней', 270.00, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSKmNap3T1PLx4vKe7Vr8RV1QgLJw4-wAp9Bw&s', 1),
    ('Манты с тыквой', 'Сочные манты с ароматной тыквой', 280.00, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ8BQWT686wcVaSbHCRjRRT06bSWiuONw25wQ&s', 1),
    ('Манты с мясом', 'Манты из говядины и лука, приготовленные на пару', 310.00, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSimz-L35FtyUVzZocYtJCNlp5CRbnJ9DPTug&s', 1),
    ('Пельмени жареные', 'Обжаренные пельмени до золотистой корочки', 300.00, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRq_ShYQe9z9AJZJWb36QrY198bSvg5wK1qiw&s', 1),
    ('Пельмени с зеленью', 'Пельмени с добавлением зелени в фарш', 285.00, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRQUOI2XsttCD0FmulNGGcK5ESjUYaE7scMqw&s', 1),
    ('Вареники с творогом', 'Сладкие вареники с домашним творогом', 260.00, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTNhmduuTM8mTJzKRbWWBdsH5PZ8es40rYonA&s', 1);


insert into users (user_name, email, password, enabled, role)
values
    ('Teste', 'qwe@qwe.qwe', '$2a$10$IFds1Vn5pRK8tEOn6i8oY.P5Z5Q7mp4lcwRmU7DdrUhH6rULrw3v6', true, 'ROLE_USER' )