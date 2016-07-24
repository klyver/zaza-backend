INSERT INTO Manufacturer (name) VALUES
  ('PillowCompany'),
  ('CandleCompany');

INSERT INTO User (username, password, manufacturer_id) VALUES
  ('pillowCompanyUser', '1234', 1),
  ('candleCompanyUser', '1234', 2),
  ('admin', '1234', NULL),
  ('p', '1234', 1),
  ('c', '1234', 2);


INSERT INTO User_roles (User_id, role) VALUES
  (1, 'MANUFACTURER'),
  (2, 'MANUFACTURER'),
  (3, 'ADMIN'),
  (4, 'MANUFACTURER'),
  (5, 'MANUFACTURER');

INSERT INTO Category (name, parent_id, displayOrder) VALUES
  ('Home', NULL, 1),
  ('Women', NULL, 2),
  ('Men', NULL, 3),
  ('Baby & Kids', NULL, 4),
  ('Healthcare', NULL, 5),
  ('Food', NULL, 6),
  ('Accessories', 2, 1),
  ('Bags', 7, 1),
  ('Wallets', 7, 2),
  ('Belts', 7, 3),
  ('Sunglasses', 7, 4),
  ('Umbrellas', 7, 5);

INSERT INTO ProductOption (label, displayOrder) VALUES
  ('Color', 1), ('Size', 2);

INSERT INTO ProductOptionValue (productOption_id, attributeValue, displayOrder) VALUES
  (1, 'Black', 1),
  (1, 'Blue', 2),
  (1, 'Bronze', 3),
  (1, 'Gold', 4),
  (1, 'Green', 5),
  (1, 'Red', 6),
  (1, 'White', 7),
  (2, 'XS', 1),
  (2, 'S', 2),
  (2, 'M', 3),
  (2, 'L', 4),
  (2, 'XL', 5),
  (2, 'XXL', 6);

INSERT INTO Product (sourceId, manufacturer_id, name, description, longDescription, category_id, approved)
VALUES
  ('ZFG7',
    1,
    'Elvang summer rocks',
    'SUMMER ROCKS cushion collection is made of 100% Peruvian pima cotton. Pima cotton is a species of cotton known for its extraordinary softness and durability. The cotton originates from Peru, even now spread to other places. The weaving style of Summer Rocks gives the textiles an expression as if they were made of small stones - thereby the term "rocks"',
    'SUMMER ROCKS cushion collection is made of 100% Peruvian pima cotton. Pima cotton is a species of cotton known for its extraordinary softness and durability. The cotton originates from Peru, even now spread to other places. The weaving style of Summer Rocks gives the textiles an expression as if they were made of small stones - thereby the term "rocks"',
    1,
    FALSE ),
  ('EH4532', 2, 'A product name', 'a description', 'a longer description', 1, FALSE);

INSERT INTO ProductOptionXref (product_id, productOption_id) VALUES (1, 1), (1, 2);
INSERT INTO Sku (sourceId, product_id, defaultSku, width, height, depth, weight, price, quantityInStock) VALUES
  ('ID-1', 1, FALSE, 30.2, 20, 30.2, 0.98, 220, 7),
  ('ID-2', 1, FALSE, 30.2, 20, 30.2, 0.98, 220, 7),
  ('ID-3', 1, TRUE, 30.2, 20, 30.2, 0.98, 220, 7),
  ('ID-4', 1, FALSE, 30.2, 20, 30.2, 0.98, 220, 7),
  ('ID-5', 1, FALSE, 30.2, 20, 30.2, 0.98, 220, 7),
  ('ID-6', 1, FALSE, 30.2, 20, 30.2, 0.98, 220, 7),
  ('ID-7', 1, FALSE, 30.2, 20, 30.2, 0.98, 220, 7),
  ('ID-8', 1, FALSE, 30.2, 20, 30.2, 0.98, 220, 7),
  ('ID-9', 1, FALSE, 30.2, 20, 30.2, 0.98, 220, 7),
  ('ID-10', 1, FALSE, 30.2, 20, 30.2, 0.98, 220, 7),
  ('ID-11', 1, FALSE, 30.2, 20, 30.2, 0.98, 220, 7),
  ('ID-12', 1, FALSE, 30.2, 20, 30.2, 0.98, 220, 7),
  ('ID-13', 1, FALSE, 30.2, 20, 30.2, 0.98, 220, 7),
  ('ID-14', 1, FALSE, 30.2, 20, 30.2, 0.98, 220, 7),
  ('ID-15', 1, FALSE, 30.2, 20, 30.2, 0.98, 220, 7),
  ('EH4532', 2, TRUE, 12.2, 32.4, 3.4, 0.6, 112, 11);

INSERT INTO SkuProductOptionValueXref (sku_id, productOptionValue_id) VALUES
  (1, 6),
  (2, 6),
  (3, 6),
  (4, 6),
  (5, 6),
  (6, 5),
  (7, 5),
  (8, 5),
  (9, 5),
  (10, 5),
  (11, 7),
  (12, 7),
  (13, 7),
  (14, 7),
  (15, 7),
  (1, 8),
  (2, 9),
  (3, 10),
  (4, 11),
  (5, 12),
  (6, 8),
  (7, 9),
  (8, 10),
  (9, 11),
  (10, 12),
  (11, 8),
  (12, 9),
  (13, 10),
  (14, 11),
  (15, 12);

INSERT INTO ProductAttribute (product_id, name, value) VALUES
  (1, 'Material', '100% pima cotton'),
  (1, 'Weight', 'app 500 gram'),
  (1, 'Size', '130x180 cm/ 51x71 inches'),
  (1, 'Washable', '30 degrees in the machine');

