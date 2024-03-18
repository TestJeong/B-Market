INSERT INTO users (user_email, user_password, user_nickname) VALUES ('user1@example.com', 'password123', 'nickname1');
INSERT INTO users (user_email, user_password, user_nickname) VALUES ('user2@example.com', 'password456', 'nickname2');
INSERT INTO categorys (category_name) VALUES ('전자기기');
INSERT INTO sub_categorys (category_id, sub_category_name) VALUES (1, '스마트폰');
INSERT INTO products (sub_category_id, product_name, product_description, product_price, discount_price, discount_rate, quantity) VALUES (1, '샘플 상품', '샘플 상품 설명', 10000, 8000, 20, 100);