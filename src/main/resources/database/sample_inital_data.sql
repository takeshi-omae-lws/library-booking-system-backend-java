-- customers
INSERT INTO customers (customer_id)
VALUES ('CST001');
INSERT INTO customers (customer_id)
VALUES ('CST002');
INSERT INTO customers (customer_id)
VALUES ('CST003');

-- articles
INSERT INTO articles (article_id, status)
VALUES ('A001', 0);
INSERT INTO articles (article_id, status)
VALUES ('A002', 0);
INSERT INTO articles (article_id, status)
VALUES ('A003', 0);
INSERT INTO articles (article_id, status)
VALUES ('A004', 0);
INSERT INTO articles (article_id, status)
VALUES ('A005', 0);
INSERT INTO articles (article_id, status)
VALUES ('A006', 0);
INSERT INTO articles (article_id, status)
VALUES ('A007', 0);
INSERT INTO articles (article_id, status)
VALUES ('A008', 0);
INSERT INTO articles (article_id, status)
VALUES ('A009', 0);
INSERT INTO articles (article_id, status)
VALUES ('A010', 0);
INSERT INTO articles (article_id, status)
VALUES ('A011', 0);
INSERT INTO articles (article_id, status)
VALUES ('A012', 0);
INSERT INTO articles (article_id, status)
VALUES ('A013', 0);
INSERT INTO articles (article_id, status)
VALUES ('A014', 0);
INSERT INTO articles (article_id, status)
VALUES ('A015', 0);
INSERT INTO articles (article_id, status)
VALUES ('A016', 0);
INSERT INTO articles (article_id, status)
VALUES ('A017', 0);
INSERT INTO articles (article_id, status)
VALUES ('A018', 0);
INSERT INTO articles (article_id, status)
VALUES ('A019', 0);
INSERT INTO articles (article_id, status)
VALUES ('A020', 0);

-- article_categories
INSERT INTO article_categories (article_id, category)
VALUES ('A001', 0);
INSERT INTO article_categories (article_id, category)
VALUES ('A002', 1);
INSERT INTO article_categories (article_id, category)
VALUES ('A003', 2);
INSERT INTO article_categories (article_id, category)
VALUES ('A004', 0);
INSERT INTO article_categories (article_id, category)
VALUES ('A005', 1);
INSERT INTO article_categories (article_id, category)
VALUES ('A006', 2);
INSERT INTO article_categories (article_id, category)
VALUES ('A007', 0);
INSERT INTO article_categories (article_id, category)
VALUES ('A008', 1);
INSERT INTO article_categories (article_id, category)
VALUES ('A009', 2);
INSERT INTO article_categories (article_id, category)
VALUES ('A010', 0);
INSERT INTO article_categories (article_id, category)
VALUES ('A011', 1);
INSERT INTO article_categories (article_id, category)
VALUES ('A012', 2);
INSERT INTO article_categories (article_id, category)
VALUES ('A013', 0);
INSERT INTO article_categories (article_id, category)
VALUES ('A014', 1);
INSERT INTO article_categories (article_id, category)
VALUES ('A015', 2);
INSERT INTO article_categories (article_id, category)
VALUES ('A016', 0);
INSERT INTO article_categories (article_id, category)
VALUES ('A017', 1);
INSERT INTO article_categories (article_id, category)
VALUES ('A018', 2);
INSERT INTO article_categories (article_id, category)
VALUES ('A019', 0);
INSERT INTO article_categories (article_id, category)
VALUES ('A020', 1);

-- borrow_rules
INSERT INTO borrow_rules(borrow_rule_id, max_amount, start_at)
VALUES ('BR001', 5,
        TO_TIMESTAMP('2019/01/01 00:00:00', 'YYYY/MM/DD HH24:MI:SS'));

-- keep_rules
INSERT INTO keep_rules(keep_rule_id, max_amount, max_new_amount, start_at)
VALUES ('BR001', 7, 2,
        TO_TIMESTAMP('2019/01/01 00:00:00', 'YYYY/MM/DD HH24:MI:SS'));

-- return_rules
INSERT INTO return_rules(return_rule_id, period, start_at)
VALUES ('BR001', 30,
        TO_TIMESTAMP('2019/01/01 00:00:00', 'YYYY/MM/DD HH24:MI:SS'));
